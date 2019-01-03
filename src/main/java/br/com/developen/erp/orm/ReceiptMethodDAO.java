package br.com.developen.erp.orm;


import org.hibernate.Session;

public class ReceiptMethodDAO extends DAO<ReceiptMethod, String>{

	public ReceiptMethodDAO(Session session) {

		super(session, ReceiptMethod.class);

	}

}