package br.com.developen.erp.orm;


import java.util.List;

import org.hibernate.Session;

public class CompanyUserDAO extends DAO<CompanyUser, CompanyUserPK>{

	public CompanyUserDAO(Session session) {

		super(session, CompanyUser.class);

	}

	public List<CompanyUser> getCompaniesOfUser(User user){

		return getSession().
				createNamedQuery(CompanyUser.COMPANIES_OF_USER, CompanyUser.class).
				setParameter("user", user).
				getResultList();  

	}	

	public List<CompanyUser> getUsersOfCompany(Company company){

		return getSession().
				createNamedQuery(CompanyUser.USERS_OF_COMPANY, CompanyUser.class).
				setParameter("company", company).
				getResultList();  

	}	

}