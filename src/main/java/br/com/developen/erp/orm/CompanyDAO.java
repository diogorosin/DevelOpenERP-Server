package br.com.developen.erp.orm;


import java.util.List;

import org.hibernate.Session;

public class CompanyDAO extends DAO<Company, Integer>{

	public CompanyDAO(Session session) {

		super(session, Company.class);

	}

	public List<Company> list(){

		return getSession().
				createNamedQuery(Company.FIND_ALL, Company.class).
				getResultList();

	}

	public List<Company> list(int firstResult, int maxResults){

		return getSession().
				createNamedQuery(Company.FIND_ALL, Company.class).
				setFirstResult(firstResult).
				setMaxResults(maxResults).
				getResultList();

	}

	public int getCount(){

		return ((Long) getSession().
				getNamedQuery(Company.ROW_COUNT).
				uniqueResult()).
				intValue();

	}
	
}