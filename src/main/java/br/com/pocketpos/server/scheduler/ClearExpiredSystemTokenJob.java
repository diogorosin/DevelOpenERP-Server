package br.com.pocketpos.server.scheduler;

import java.util.Date;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.hibernate.Session;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.pocketpos.server.orm.Token;
import br.com.pocketpos.server.util.HibernateUtil;

public class ClearExpiredSystemTokenJob implements Job {

	@Override
	public void execute(final JobExecutionContext ctx) throws JobExecutionException {

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Query spQuery = session.createNamedQuery(Token.CLEAR_EXPIRED_TOKEN_JOB)
				.setParameter("now", new Date(), TemporalType.TIMESTAMP);

		spQuery.executeUpdate();

		session.getTransaction().commit();

		session.close();

	}

}