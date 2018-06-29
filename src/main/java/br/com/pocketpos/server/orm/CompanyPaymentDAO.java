package br.com.pocketpos.server.orm;


import java.util.List;

import org.hibernate.Session;

public class CompanyPaymentDAO extends DAO<CompanyPayment, CompanyPaymentPK>{

	public CompanyPaymentDAO(Session session) {

		super(session, CompanyPayment.class);

	}

	public List<CompanyPayment> listByCompany(Company company){

		return getSession().
				createNamedQuery(CompanyPayment.FIND_BY_COMPANY, CompanyPayment.class).
				setParameter("company", company).				
				getResultList();

	}

}