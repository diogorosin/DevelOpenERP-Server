package br.com.developen.erp.orm;


import java.util.List;

import org.hibernate.Session;

public class CompanyPaymentMethodDAO extends DAO<CompanyPaymentMethod, CompanyPaymentMethodPK>{

	public CompanyPaymentMethodDAO(Session session) {

		super(session, CompanyPaymentMethod.class);

	}

	public List<CompanyPaymentMethod> listByCompany(Company company){

		return getSession().
				createNamedQuery(CompanyPaymentMethod.FIND_BY_COMPANY, CompanyPaymentMethod.class).
				setParameter("company", company).				
				getResultList();

	}

}