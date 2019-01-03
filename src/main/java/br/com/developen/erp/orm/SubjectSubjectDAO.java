package br.com.developen.erp.orm;


import org.hibernate.Session;

public class SubjectSubjectDAO extends DAO<SubjectSubject, SubjectSubjectPK>{

	public SubjectSubjectDAO(Session session) {

		super(session, SubjectSubject.class);

	}

}