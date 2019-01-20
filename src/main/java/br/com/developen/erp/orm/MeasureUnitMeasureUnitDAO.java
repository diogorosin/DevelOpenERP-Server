package br.com.developen.erp.orm;


import java.util.List;

import org.hibernate.Session;

public class MeasureUnitMeasureUnitDAO extends DAO<MeasureUnitMeasureUnit, MeasureUnitMeasureUnitPK>{

	public MeasureUnitMeasureUnitDAO(Session session) {

		super(session, MeasureUnitMeasureUnit.class);

	}

	public List<MeasureUnitMeasureUnit> list(){

		return getSession().
				createNamedQuery(MeasureUnitMeasureUnit.FIND_ALL, MeasureUnitMeasureUnit.class).
				getResultList();  

	}

}