package br.com.developen.erp.rest;

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

import br.com.developen.erp.bean.CredentialBean001;
import br.com.developen.erp.bean.ExceptionBean001;
import br.com.developen.erp.bean.TokenBean001;
import br.com.developen.erp.exception.NotFoundException;
import br.com.developen.erp.exception.UnauthorizedException;
import br.com.developen.erp.orm.Company;
import br.com.developen.erp.orm.Token;
import br.com.developen.erp.orm.User;
import br.com.developen.erp.util.AuthenticationFactory;
import br.com.developen.erp.util.HibernateUtil;
import br.com.developen.erp.util.I18N;

@Path("/security")
public class SecurityEndPoint {

	static Logger log = LogManager.getRootLogger();	

	@POST
	@Path("/authenticate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticate(CredentialBean001 credential) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		try {

			Token token; 

			if (credential.getCompany() == null)

				token = AuthenticationFactory.authenticate(
						session,
						credential.getLogin(), 
						credential.getPassword());

			else 

				token = AuthenticationFactory.authenticate(
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

			log.info(SecurityEndPoint.class.getSimpleName() + 
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

			log.error(SecurityEndPoint.class.getSimpleName() + ": " + 
					e.getMessage(), 
					e.getCause());

			return Response.status(Status.NOT_FOUND).
					entity(new ExceptionBean001(e.getMessage())).
					build();			

		} catch (UnauthorizedException e){

			if (session.getTransaction().isActive())

				session.getTransaction().rollback();

			log.error(SecurityEndPoint.class.getSimpleName() + ": " + 
					e.getMessage(), 
					e.getCause());

			return Response.status(Status.UNAUTHORIZED).
					entity(new ExceptionBean001(e.getMessage())).
					build();			

		} catch (Exception e){

			if (session.getTransaction().isActive())

				session.getTransaction().rollback();

			log.error(SecurityEndPoint.class.getSimpleName() + ": " + 
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