package br.com.developen.erp.orm;


import org.hibernate.Session;

public class TokenDAO extends DAO<Token, String>{

	public TokenDAO(Session session) {

		super(session, Token.class);

	}

}