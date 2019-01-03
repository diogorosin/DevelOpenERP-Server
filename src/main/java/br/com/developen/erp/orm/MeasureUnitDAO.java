package br.com.developen.erp.orm;


import java.util.List;

import org.hibernate.Session;

public class MeasureUnitDAO extends DAO<MeasureUnit, Integer>{

	public MeasureUnitDAO(Session session) {

		super(session, MeasureUnit.class);

	}

	public List<MeasureUnit> list(){

		return getSession().
				createNamedQuery(MeasureUnit.FIND_ALL, MeasureUnit.class).
				getResultList();  

	}

}