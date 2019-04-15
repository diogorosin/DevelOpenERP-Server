package br.com.developen.erp.orm;


import org.hibernate.Session;

public class ProgenyDAO extends DAO<Progeny, Integer>{

	public ProgenyDAO(Session session) {

		super(session, Progeny.class);

	}

}