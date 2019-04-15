package br.com.developen.erp.orm;


import org.hibernate.Session;

public class CompanyDeviceSaleDAO extends DAO<CompanyDeviceSale, CompanyDeviceSalePK>{

	public CompanyDeviceSaleDAO(Session session) {

		super(session, CompanyDeviceSale.class);

	}

}