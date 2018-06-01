package br.com.pocketpos.server.orm;


import org.hibernate.Session;

public class SubjectSubjectDAO extends DAO<SubjectSubject, SubjectSubjectPK>{

	public SubjectSubjectDAO(Session session) {

		super(session, SubjectSubject.class);

	}

}