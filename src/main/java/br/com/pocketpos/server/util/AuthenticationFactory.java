package br.com.pocketpos.server.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Calendar;

import org.hibernate.Session;

import br.com.pocketpos.server.exception.CompanyNotActiveException;
import br.com.pocketpos.server.exception.CompanyNotFoundException;
import br.com.pocketpos.server.exception.InvalidPasswordException;
import br.com.pocketpos.server.exception.NotFoundException;
import br.com.pocketpos.server.exception.UnauthorizedException;
import br.com.pocketpos.server.exception.UserNotActiveException;
import br.com.pocketpos.server.exception.UserNotAllowedException;
import br.com.pocketpos.server.exception.UserNotFoundException;
import br.com.pocketpos.server.exception.UserNotLinkedToTheCompanyException;
import br.com.pocketpos.server.orm.SubjectSubject;
import br.com.pocketpos.server.orm.SubjectSubjectDAO;
import br.com.pocketpos.server.orm.SubjectSubjectPK;
import br.com.pocketpos.server.orm.Company;
import br.com.pocketpos.server.orm.CompanyDAO;
import br.com.pocketpos.server.orm.Level;
import br.com.pocketpos.server.orm.User;
import br.com.pocketpos.server.orm.UserDAO;
import br.com.pocketpos.server.orm.Token;
import br.com.pocketpos.server.orm.TokenDAO;

public class AuthenticationFactory {

	private static final String TOKEN_ALLOWED_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private static final int TOKEN_SIZE = 10;

	public static final Token authenticate(
			Session session,
			String loginValue,
			String passwordValue,
			Integer companyIdentifier) 
					throws
					NotFoundException,
					UnauthorizedException, 
					NoSuchAlgorithmException, 
					UnsupportedEncodingException {

		if (loginValue == null) 

			loginValue = new String();

		if (passwordValue == null)

			passwordValue = new String();

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

		SubjectSubjectDAO subjectSubjectDAO = new SubjectSubjectDAO(session);

		Company company = new Company();

		company = new CompanyDAO(session).retrieve(companyIdentifier);

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

		calendar.add(Calendar.MINUTE, +15);

		token.setExpire(calendar.getTime());

		new TokenDAO(session).create(token);

		new UserDAO(session).update(user);

		return token;

	}

}