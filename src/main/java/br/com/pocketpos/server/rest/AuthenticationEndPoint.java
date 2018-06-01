package br.com.pocketpos.server.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import br.com.pocketpos.server.bean.CredentialBean001;
import br.com.pocketpos.server.bean.ExceptionBean001;
import br.com.pocketpos.server.bean.TokenBean001;
import br.com.pocketpos.server.exception.NotFoundException;
import br.com.pocketpos.server.exception.UnauthorizedException;
import br.com.pocketpos.server.orm.Company;
import br.com.pocketpos.server.orm.Token;
import br.com.pocketpos.server.orm.User;
import br.com.pocketpos.server.util.AuthenticationFactory;
import br.com.pocketpos.server.util.HibernateUtil;
import br.com.pocketpos.server.util.I18N;

@Path("/authentication")
public class AuthenticationEndPoint {

	static Logger log = LogManager.getRootLogger();	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticate(CredentialBean001 credential) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		try {

			Token token = AuthenticationFactory.authenticate(
					session,
					credential.getLogin(), 
					credential.getPassword(),
					credential.getCompany());

			session.getTransaction().commit();

			Company company = (Company) token.
					getSubjectSubject().
					getIdentifier().
					getParent();

			User user = (User) token.
					getSubjectSubject().
					getIdentifier().
					getChild();

			log.info(AuthenticationEndPoint.class.getSimpleName() + 
					": Usuário " +
					user.toString() +
					" efetuou login na empresa " + 
					company.toString() + 
					" através do token " +
					token.toString());

			return Response.status(Response.Status.OK).
					entity(new TokenBean001(token.getIdentifier())).
					build();

		} catch (NotFoundException e){

			if (session.getTransaction().isActive())

				session.getTransaction().rollback();

			return Response.status(Status.NOT_FOUND).
					entity(new ExceptionBean001(e.getMessage())).
					build();			

		} catch (UnauthorizedException e){

			if (session.getTransaction().isActive())

				session.getTransaction().rollback();

			return Response.status(Status.UNAUTHORIZED).
					entity(new ExceptionBean001(e.getMessage())).
					build();			

		} catch (Exception e){			

			if (session.getTransaction().isActive())

				session.getTransaction().rollback();

			log.error(AuthenticationEndPoint.class.getSimpleName() + ": " + 
					e.getMessage(), 
					e.getCause());

			return Response.status(Status.INTERNAL_SERVER_ERROR).
					entity(new ExceptionBean001(I18N.get(I18N.INTERNAL_SERVER_ERROR))).
					build();			

		} finally {

			session.close();

		}

	}

}