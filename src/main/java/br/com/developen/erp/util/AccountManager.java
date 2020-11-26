package br.com.developen.erp.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;

import br.com.developen.erp.exception.CompanyNotActiveException;
import br.com.developen.erp.exception.CompanyNotFoundException;
import br.com.developen.erp.exception.InvalidPasswordException;
import br.com.developen.erp.exception.InvalidTokenException;
import br.com.developen.erp.exception.NotFoundException;
import br.com.developen.erp.exception.TokenExpiredException;
import br.com.developen.erp.exception.UnauthorizedException;
import br.com.developen.erp.exception.UserAlreadyLoggedIntoCompanyException;
import br.com.developen.erp.exception.UserNotActiveException;
import br.com.developen.erp.exception.UserNotAllowedException;
import br.com.developen.erp.exception.UserNotFoundException;
import br.com.developen.erp.exception.UserNotLinkedToTheCompanyException;
import br.com.developen.erp.orm.Company;
import br.com.developen.erp.orm.CompanyDAO;
import br.com.developen.erp.orm.CompanyUser;
import br.com.developen.erp.orm.CompanyUserDAO;
import br.com.developen.erp.orm.CompanyUserPK;
import br.com.developen.erp.orm.Level;
import br.com.developen.erp.orm.Token;
import br.com.developen.erp.orm.TokenDAO;
import br.com.developen.erp.orm.User;
import br.com.developen.erp.orm.UserDAO;

public class AccountManager {

	private static final String TOKEN_ALLOWED_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private static final int TOKEN_SIZE = 10;

	public static final Token authenticate(
			Session session,
			String loginValue,
			String passwordValue,
			Integer companyValue,
			Boolean forDevice) 
					throws
					NotFoundException,
					UnauthorizedException,
					NoSuchAlgorithmException, 
					UnsupportedEncodingException {

		if (loginValue == null) 

			loginValue = new String();

		if (passwordValue == null)

			passwordValue = new String();

		if (companyValue == null)

			companyValue = Integer.valueOf(0);

		User user = new UserDAO(session).retrieveByLogin(loginValue);

		if (user == null)

			throw new UserNotFoundException();

		if (!user.getActive())

			throw new UserNotActiveException();			

		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");

		byte messageDigest[] = algorithm.digest(passwordValue.getBytes("UTF-8"));

		StringBuilder hexString = new StringBuilder();

		for (byte b : messageDigest)

			hexString.append(String.format("%02X", 0xFF & b));

		String hexPassword = hexString.toString();

		if (!user.getPassword().equals(hexPassword))

			throw new InvalidPasswordException();

		Company company = new Company();

		if (companyValue > 0) {

			company = new CompanyDAO(session).retrieve(companyValue);

		} else {

			if (user.getLastLoggedInCompany() != null) {

				company = user.getLastLoggedInCompany();

			} else {

				CompanyUserDAO dao = new CompanyUserDAO(session);

				List<CompanyUser> list = dao.getCompaniesOfUser(user);

				if (list != null && !list.isEmpty()) {

					for (CompanyUser companyUsers : list) {

						Company someCompany = companyUsers.getIdentifier().getCompany(); 

						if (someCompany.getActive()) {

							company = someCompany;

							break;

						}

					}

				}

			}

		}

		if (company == null)

			throw new CompanyNotFoundException();

		if (!company.getActive())

			throw new CompanyNotActiveException();

		CompanyUserDAO companyUserDAO = new CompanyUserDAO(session);

		CompanyUserPK companyUserPk = new CompanyUserPK();

		companyUserPk.setCompany(company);

		companyUserPk.setUser(user);

		CompanyUser companyUser = companyUserDAO.retrieve(companyUserPk);

		if (companyUser == null)

			throw new UserNotLinkedToTheCompanyException();

		if (companyUser.getLevel().equals(Level.UNDEFINED) || 
				(forDevice && (companyUser.getLevel().ordinal() < Level.SUPERVISOR.ordinal())))

			throw new UserNotAllowedException();

		Token token = new Token();

		SecureRandom secureRandom = new SecureRandom();

		StringBuilder stringBuilder = new StringBuilder(TOKEN_SIZE);

		for(int i = 0; i < TOKEN_SIZE; i++)

			stringBuilder.append(
					TOKEN_ALLOWED_CHARS.charAt(
							secureRandom.nextInt(
									TOKEN_ALLOWED_CHARS.length())));

		token.setIdentifier(stringBuilder.toString());

		token.setCompanyUser(companyUser);

		Calendar calendar = Calendar.getInstance();

		calendar.add(Calendar.HOUR, +24);

		token.setExpire(calendar.getTime());

		new TokenDAO(session).create(token);

		if (user.getLastLoggedInCompany() == null || (user.getLastLoggedInCompany() != null && !user.getLastLoggedInCompany().equals(company))) {

			user.setLastLoggedInCompany(company);

			new UserDAO(session).update(user);

		}

		return token;

	}


	public static final Token changeCompany(
			Session session,
			String tokenValue,
			Integer companyValue) 
					throws 
					InvalidTokenException, 
					UserNotAllowedException, 
					TokenExpiredException, 
					UserNotActiveException, 
					CompanyNotFoundException, 
					CompanyNotActiveException, 
					UserAlreadyLoggedIntoCompanyException, 
					UserNotLinkedToTheCompanyException {

		Token token = new TokenDAO(session).retrieve(tokenValue);

		User user = (User) token.getCompanyUser().getIdentifier().getUser();

		if (companyValue == null)

			companyValue = Integer.valueOf(0);

		Company company = new CompanyDAO(session).retrieve(companyValue);

		if (company == null)

			throw new CompanyNotFoundException();

		if (!company.getActive())

			throw new CompanyNotActiveException();

		if (token.getCompanyUser().getIdentifier().getCompany().equals(company))

			throw new UserAlreadyLoggedIntoCompanyException();

		CompanyUserPK companyUserPK = new CompanyUserPK();

		companyUserPK.setCompany(company);

		companyUserPK.setUser(user);

		CompanyUser companyUser = new CompanyUserDAO(session).retrieve(companyUserPK);

		if (companyUser == null)

			throw new UserNotLinkedToTheCompanyException();

		if (companyUser.getLevel().equals(Level.UNDEFINED))

			throw new UserNotAllowedException();

		token.setCompanyUser(companyUser);

		new TokenDAO(session).update(token);

		return token;

	}	


}