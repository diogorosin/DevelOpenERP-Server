package br.com.developen.erp.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import br.com.developen.erp.bean.CompanyBean001;
import br.com.developen.erp.bean.CredentialBean001;
import br.com.developen.erp.bean.ExceptionBean001;
import br.com.developen.erp.bean.LevelBean001;
import br.com.developen.erp.bean.StringBean;
import br.com.developen.erp.bean.TokenBean001;
import br.com.developen.erp.bean.UserBean001;
import br.com.developen.erp.exception.CompanyNotActiveException;
import br.com.developen.erp.exception.CompanyNotFoundException;
import br.com.developen.erp.exception.NotFoundException;
import br.com.developen.erp.exception.UnauthorizedException;
import br.com.developen.erp.exception.UserNotAllowedException;
import br.com.developen.erp.exception.UserNotLinkedToTheCompanyException;
import br.com.developen.erp.orm.Company;
import br.com.developen.erp.orm.CompanyDAO;
import br.com.developen.erp.orm.CompanyUser;
import br.com.developen.erp.orm.CompanyUserDAO;
import br.com.developen.erp.orm.CompanyUserPK;
import br.com.developen.erp.orm.Level;
import br.com.developen.erp.orm.Token;
import br.com.developen.erp.orm.TokenDAO;
import br.com.developen.erp.orm.User;
import br.com.developen.erp.orm.UserDAO;
import br.com.developen.erp.util.AccountManager;
import br.com.developen.erp.util.HibernateUtil;
import br.com.developen.erp.util.I18N;

@Path("/token")
public class TokenEndPoint {

	static Logger log = LogManager.getRootLogger();	

	@POST
	@Path("/device")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createForDevice(CredentialBean001 credential) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {

			session.beginTransaction();

			Token token = AccountManager.authenticate(session,
					credential.getLogin(), 
					credential.getPassword(),
					credential.getCompany(),
					true);

			session.getTransaction().commit();

			Company company = token.
					getCompanyUser().
					getIdentifier().
					getCompany();

			User user = token.
					getCompanyUser().
					getIdentifier().
					getUser();

			log.info(TokenEndPoint.class.getSimpleName() + 
					": Usuario " +
					user.toString() +
					" efetuou login na Agencia " + 
					company.toString() + 
					" atraves do Token " +
					token.toString());

			return Response.status(Response.Status.OK).
					entity(new StringBean(token.getIdentifier())).
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

			log.error(TokenEndPoint.class.getSimpleName() + ": " + 
					e.getMessage(), 
					e.getCause());

			return Response.status(Status.INTERNAL_SERVER_ERROR).
					entity(new ExceptionBean001(I18N.get(I18N.INTERNAL_SERVER_ERROR))).
					build();			

		} finally {

			session.close();

		}

	
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(CredentialBean001 credential) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {

			session.beginTransaction();

			Token token = AccountManager.authenticate(session,
					credential.getLogin(), 
					credential.getPassword(),
					credential.getCompany(),
					false);

			session.getTransaction().commit();

			Company company = token.
					getCompanyUser().
					getIdentifier().
					getCompany();

			User user = token.
					getCompanyUser().
					getIdentifier().
					getUser();

			log.info(TokenEndPoint.class.getSimpleName() + 
					": Usuario " +
					user.toString() +
					" efetuou login na Agencia " + 
					company.toString() + 
					" atraves do Token " +
					token.toString());

			return Response.status(Response.Status.OK).
					entity(new StringBean(token.getIdentifier())).
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

			log.error(TokenEndPoint.class.getSimpleName() + ": " + 
					e.getMessage(), 
					e.getCause());

			return Response.status(Status.INTERNAL_SERVER_ERROR).
					entity(new ExceptionBean001(I18N.get(I18N.INTERNAL_SERVER_ERROR))).
					build();			

		} finally {

			session.close();

		}

	}

	@GET
	@Authentication(level=Level.CUSTOMER_SUPPLIER)
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@Context HttpServletRequest request){

		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		String tokenIdentifier = authorizationHeader.substring("Bearer".length()).trim();

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {

			TokenDAO tokenDAO = new TokenDAO(session);

			Token token = tokenDAO.retrieve(tokenIdentifier);
			
			Company company = token.
					getCompanyUser().
					getIdentifier().
					getCompany();

			User user = (User) token.
					getCompanyUser().
					getIdentifier().
					getUser();

			Level level = token.
					getCompanyUser().
					getLevel();

			CompanyBean001 companyBean = new CompanyBean001();

			companyBean.setActive(company.getActive());

			companyBean.setDenomination(company.getDenomination());

			companyBean.setFancyName(company.getFancyName());

			UserBean001 userBean = new UserBean001();

			userBean.setActive(user.getActive());

			userBean.setName(user.getName());

			LevelBean001 levelBean = new LevelBean001();

			levelBean.setDenomination(level.name());

			TokenBean001 tokenBean = new TokenBean001();

			tokenBean.getCompany().put(company.getIdentifier(), companyBean);			

			tokenBean.getUser().put(user.getIdentifier(), userBean);			

			tokenBean.getLevel().put(level.ordinal(), levelBean);

			return Response.status(Response.Status.OK).entity(tokenBean).build();

		} catch (Exception e) {

			log.error(TokenEndPoint.class.getSimpleName() + ": " + 
					e.getMessage(), 
					e.getCause());

			return Response.status(Status.INTERNAL_SERVER_ERROR).
					entity(new ExceptionBean001(I18N.get(I18N.INTERNAL_SERVER_ERROR))).
					build();

		} finally {

			session.close();

		}

	}


	@DELETE
	@Authentication(level=Level.CUSTOMER_SUPPLIER)
	public Response delete(@Context HttpServletRequest request){

		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		String tokenIdentifier = authorizationHeader.substring("Bearer".length()).trim();

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		try {

			TokenDAO tokenDAO = new TokenDAO(session);

			Token token = tokenDAO.retrieve(tokenIdentifier);

			tokenDAO.delete(token);

			session.getTransaction().commit();

			return Response.status(Response.Status.OK).build();

		} catch (Exception e) {

			if (session.getTransaction().isActive())

				session.getTransaction().rollback();

			log.error(TokenEndPoint.class.getSimpleName() + ": " + 
					e.getMessage(), 
					e.getCause());

			return Response.status(Status.INTERNAL_SERVER_ERROR).build();

		} finally {

			session.close();

		}

	}

	
	@PUT
	@Path("/agency/{agencyIdentifier: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	@Authentication(level=Level.CUSTOMER_SUPPLIER)
	public Response agency(
			@Context 
			HttpServletRequest request, 
			@PathParam("agencyIdentifier")
			Integer companyIdentifier) {

		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		String tokenIdentifier = authorizationHeader.substring("Bearer".length()).trim();

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		try {

			TokenDAO tokenDAO = new TokenDAO(session);

			Token token = tokenDAO.retrieve(tokenIdentifier);

			Company company = new CompanyDAO(session).retrieve(companyIdentifier);

			if (company == null)

				throw new CompanyNotFoundException();

			if (!company.getActive())

				throw new CompanyNotActiveException();

			User user = (User) token.
					getCompanyUser().
					getIdentifier().
					getUser();

			CompanyUserPK companyUserPK = new CompanyUserPK();

			companyUserPK.setCompany(company);

			companyUserPK.setUser(user);

			CompanyUser companyUser = new CompanyUserDAO(session).retrieve(companyUserPK);

			if (companyUser == null)

				throw new UserNotLinkedToTheCompanyException();

			if (companyUser.getLevel().equals(Level.UNDEFINED))

				throw new UserNotAllowedException();

			token.setCompanyUser(companyUser);

			new TokenDAO(session).update(token);

			user.setLastLoggedInCompany(company);

			new UserDAO(session).update(user);

			session.getTransaction().commit();

			log.info(TokenEndPoint.class.getSimpleName() + 
					": Usuário " +
					user.toString() +
					" alterou para a agencia " + 
					company.toString() + 
					" através do token " +
					token.toString());

			return Response.status(Response.Status.OK).
					entity(new StringBean(token.getIdentifier())).
					build();

		} catch (NotFoundException e){

			if (session.getTransaction().isActive())

				session.getTransaction().rollback();

			log.error(TokenEndPoint.class.getSimpleName() + ": " + 
					e.getMessage(), 
					e.getCause());

			return Response.status(Status.NOT_FOUND).
					entity(new ExceptionBean001(e.getMessage())).
					build();			

		} catch (UnauthorizedException e){

			if (session.getTransaction().isActive())

				session.getTransaction().rollback();

			log.error(TokenEndPoint.class.getSimpleName() + ": " + 
					e.getMessage(), 
					e.getCause());

			return Response.status(Status.UNAUTHORIZED).
					entity(new ExceptionBean001(e.getMessage())).
					build();			

		} catch (Exception e){

			if (session.getTransaction().isActive())

				session.getTransaction().rollback();

			log.error(TokenEndPoint.class.getSimpleName() + ": " + 
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