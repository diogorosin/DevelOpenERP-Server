package br.com.pocketpos.server.orm;


import org.hibernate.Session;

public class TariffDAO extends DAO<Tariff, Integer>{

	public TariffDAO(Session session) {

		super(session, Tariff.class);

	}

}