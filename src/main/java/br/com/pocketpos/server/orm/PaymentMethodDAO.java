package br.com.pocketpos.server.orm;


import org.hibernate.Session;

public class PaymentMethodDAO extends DAO<PaymentMethod, String>{

	public PaymentMethodDAO(Session session) {

		super(session, PaymentMethod.class);

	}

}