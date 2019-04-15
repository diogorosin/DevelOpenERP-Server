package br.com.developen.erp.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Calendar;

import org.hibernate.Session;

import br.com.developen.erp.exception.CompanyNotActiveException;
import br.com.developen.erp.exception.CompanyNotFoundException;
import br.com.developen.erp.exception.InvalidPasswordException;
import br.com.developen.erp.exception.NotFoundException;
import br.com.developen.erp.exception.UnauthorizedException;
import br.com.developen.erp.exception.UserNotActiveException;
import br.com.developen.erp.exception.UserNotAllowedException;
import br.com.developen.erp.exception.UserNotFoundException;
import br.com.developen.erp.exception.UserNotLinkedToTheCompanyException;
import br.com.developen.erp.orm.Company;
import br.com.developen.erp.orm.CompanyDAO;
import br.com.developen.erp.orm.Level;
import br.com.developen.erp.orm.Subject;
import br.com.developen.erp.orm.SubjectSubject;
import br.com.developen.erp.orm.SubjectSubjectDAO;
import br.com.developen.erp.orm.SubjectSubjectPK;
import br.com.developen.erp.orm.Token;
import br.com.developen.erp.orm.TokenDAO;
import br.com.developen.erp.orm.User;
import br.com.developen.erp.orm.UserDAO;

public class AuthenticationFactory {


	private static final String TOKEN_ALLOWED_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private static final int TOKEN_SIZE = 10;


	public static final Token authenticate(
			Session session,
			String login,
			String password) 
					throws
					NotFoundException,
					UnauthorizedException, 
					NoSuchAlgorithmException, 
					UnsupportedEncodingException {

		if (login == null) 

			login = new String();

		if (password == null)

			password = new String();

		User user = new UserDAO(session).retrieveByLogin(login);

		if (user == null)

			throw new UserNotFoundException();

		if (!user.getActive())

			throw new UserNotActiveException();			

		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");

		byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));

		StringBuilder hexString = new StringBuilder();

		for (byte b : messageDigest)

			hexString.append(String.format("%02X", 0xFF & b));

		String hexPassword = hexString.toString();

		if (!user.getPassword().equals(hexPassword))

			throw new InvalidPasswordException();

		Company company = user.getLastLoggedInCompany();

		if (user.getLastLoggedInCompany()==null) {

			for (SubjectSubject subjectSubject : user.getParents()){

				if (!subjectSubject.getLevel().equals(Level.UNDEFINED)) {

					Subject subject = subjectSubject.getIdentifier().getParent(); 

					if (subject instanceof Company)

						company = (Company) subject; 

				}

			}

		} 

		if (company == null)

			throw new CompanyNotFoundException();

		if (!company.getActive())

			throw new CompanyNotActiveException();

		SubjectSubjectPK subjectSubjectPk = new SubjectSubjectPK();

		subjectSubjectPk.setParent(company);

		subjectSubjectPk.setChild(user);

		SubjectSubjectDAO subjectSubjectDAO = new SubjectSubjectDAO(session);

		SubjectSubject subjectSubject = subjectSubjectDAO.retrieve(subjectSubjectPk);

		if (subjectSubject == null)

			throw new UserNotLinkedToTheCompanyException();

		if (subjectSubject.getLevel().equals(Level.UNDEFINED))

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

		token.setSubjectSubject(subjectSubject);

		Calendar calendar = Calendar.getInstance();

		calendar.add(Calendar.YEAR, +1);

		token.setExpire(calendar.getTime());

		user.setLastLoggedInCompany(company);

		new TokenDAO(session).create(token);

		new UserDAO(session).update(user);

		return token;

	} 


	public static final Token authenticate(
			Session session,
			String login,
			String password,
			Integer toCompany) 
					throws
					NotFoundException,
					UnauthorizedException, 
					NoSuchAlgorithmException, 
					UnsupportedEncodingException {

		if (login == null) 

			login = new String();

		if (password == null)

			password = new String();

		User user = new UserDAO(session).retrieveByLogin(login);

		if (user == null)

			throw new UserNotFoundException();

		if (!user.getActive())

			throw new UserNotActiveException();			

		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");

		byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));

		StringBuilder hexString = new StringBuilder();

		for (byte b : messageDigest)

			hexString.append(String.format("%02X", 0xFF & b));

		String hexPassword = hexString.toString();

		if (!user.getPassword().equals(hexPassword))

			throw new InvalidPasswordException();

		SubjectSubjectDAO subjectSubjectDAO = new SubjectSubjectDAO(session);

		Company company = new Company();

		company = new CompanyDAO(session).retrieve(toCompany);

		if (company == null)

			throw new CompanyNotFoundException();

		if (!company.getActive())

			throw new CompanyNotActiveException();

		SubjectSubjectPK subjectSubjectPk = new SubjectSubjectPK();

		subjectSubjectPk.setParent(company);

		subjectSubjectPk.setChild(user);

		SubjectSubject subjectSubject = subjectSubjectDAO.retrieve(subjectSubjectPk);

		if (subjectSubject == null)

			throw new UserNotLinkedToTheCompanyException();

		if (subjectSubject.getLevel().equals(Level.UNDEFINED))

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

		token.setSubjectSubject(subjectSubject);

		Calendar calendar = Calendar.getInstance();

		calendar.add(Calendar.YEAR, +1);

		token.setExpire(calendar.getTime());

		user.setLastLoggedInCompany(company);

		new TokenDAO(session).create(token);

		new UserDAO(session).update(user);

		return token;

	}


}