package br.com.pocketpos.server.orm;


import org.hibernate.Session;

public class TokenDAO extends DAO<Token, String>{

	public TokenDAO(Session session) {

		super(session, Token.class);

	}

}