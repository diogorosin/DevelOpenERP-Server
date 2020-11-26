package br.com.developen.erp.orm;

import java.util.List;

import org.hibernate.Session;

public class LevelDAO extends DAO<Level, Integer>{

	public LevelDAO(Session session) {

		super(session, Level.class);

	}
	
	public List<Level> list(){

		return getSession().
				createNamedQuery(Level.FIND_ALL, Level.class).
				getResultList();

	}
	
	public List<Level> list(int firstResult, int maxResults){

		return getSession().
				createNamedQuery(Level.FIND_ALL, Level.class).
				setFirstResult(firstResult).
				setMaxResults(maxResults).
				getResultList();

	}

	public int getCount(){

		return ((Long) getSession().
				getNamedQuery(Level.COUNT_ALL).
				uniqueResult()).
				intValue();

	}
	
}