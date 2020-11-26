package br.com.developen.erp.orm;

import org.hibernate.Session;

public class TicketDAO extends DAO<Ticket, String>{

	public TicketDAO(Session session) {

		super(session, Ticket.class);

	}

}