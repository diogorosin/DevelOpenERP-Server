package br.com.pocketpos.server.orm;


import java.util.List;

import org.hibernate.Session;

public class CompanyReceiptMethodDAO extends DAO<CompanyReceiptMethod, CompanyReceiptMethodPK>{

	public CompanyReceiptMethodDAO(Session session) {

		super(session, CompanyReceiptMethod.class);

	}

	public List<CompanyReceiptMethod> listByCompany(Company company){

		return getSession().
				createNamedQuery(CompanyReceiptMethod.FIND_BY_COMPANY, CompanyReceiptMethod.class).
				setParameter("company", company).				
				getResultList();

	}

}