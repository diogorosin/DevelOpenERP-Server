package br.com.pocketpos.server.orm;


import org.hibernate.Session;

public class ReceiptMethodDAO extends DAO<ReceiptMethod, String>{

	public ReceiptMethodDAO(Session session) {

		super(session, ReceiptMethod.class);

	}

}