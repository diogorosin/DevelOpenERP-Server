package br.com.developen.erp.orm;


import java.util.List;

import org.hibernate.Session;

public class UserDAO extends DAO<User, Integer>{

	public UserDAO(Session session) {

		super(session, User.class);

	}

	public User retrieveByLogin(String login){

		List<User> list = getSession().
				createNamedQuery(User.RETRIEVE_BY_LOGIN, User.class).
				setParameter("login", login).
				setMaxResults(1).
				getResultList(); 

		return list.isEmpty() ? null : list.get(0); 

	}

	/* COM FILTRO PELO NOME */
	public List<User> listByName(String name){

		return getSession().
				createNamedQuery(User.FIND_BY_NAME, User.class).
				setParameter("name", name).
				getResultList();

	}

	public List<User> listByName(String name, int firstResult, int maxResults){

		return getSession().
				createNamedQuery(User.FIND_BY_NAME, User.class).
				setParameter("name", name).
				setFirstResult(firstResult).
				setMaxResults(maxResults).
				getResultList();

	}

	public int getCountByName(String name){

		return ((Long) getSession().
				getNamedQuery(User.COUNT_BY_NAME).
				setParameter("name", name).
				uniqueResult()).
				intValue();

	}
	
	/* SEM FILTRO */
	public List<User> list(){

		return getSession().
				createNamedQuery(User.FIND_ALL, User.class).
				getResultList();

	}
	
	public List<User> list(int firstResult, int maxResults){

		return getSession().
				createNamedQuery(User.FIND_ALL, User.class).
				setFirstResult(firstResult).
				setMaxResults(maxResults).
				getResultList();

	}

	public int getCount(){

		return ((Long) getSession().
				getNamedQuery(User.COUNT_ALL).
				uniqueResult()).
				intValue();

	}

}