package br.com.developen.erp.orm;


import org.hibernate.Session;

public class CompanyDeviceSaleItemDAO extends DAO<CompanyDeviceSaleItem, CompanyDeviceSaleItemPK>{

	public CompanyDeviceSaleItemDAO(Session session) {

		super(session, CompanyDeviceSaleItem.class);

	}

}