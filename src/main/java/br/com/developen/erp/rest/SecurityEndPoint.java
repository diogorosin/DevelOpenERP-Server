package br.com.developen.erp.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
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
import br.com.developen.erp.orm.Level;
import br.com.developen.erp.orm.Subject;
import br.com.developen.erp.orm.SubjectSubject;
import br.com.developen.erp.orm.SubjectSubjectDAO;
import br.com.developen.erp.orm.SubjectSubjectPK;
import br.com.developen.erp.orm.Token;
import br.com.developen.erp.orm.TokenDAO;
import br.com.developen.erp.orm.User;
import br.com.developen.erp.orm.UserDAO;
import br.com.developen.erp.util.AuthenticationFactory;
import br.com.developen.erp.util.HibernateUtil;
import br.com.developen.erp.util.I18N;

@Path("/security")
public class SecurityEndPoint {

	static Logger log = LogManager.getRootLogger();	

	@PUT
	@Path("/company")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Authentication(level=Level.CUSTOMER_SUPPLIER)
	public Response switchCompany(@Context HttpServletRequest request, CompanyBean001 companyBean) {

		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		String tokenIdentifier = authorizationHeader.substring("Bearer".length()).trim();

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		try {

			TokenDAO tokenDAO = new TokenDAO(session);

			Token token = tokenDAO.retrieve(tokenIdentifier);

			Company company = new CompanyDAO(session).
					retrieve(companyBean.getIdentifier());

			if (company == null)

				throw new CompanyNotFoundException();

			if (!company.getActive())

				throw new CompanyNotActiveException();

			User user = (User) token.
					getSubjectSubject().
					getIdentifier().
					getChild();

			SubjectSubjectPK subjectSubjectPK = new SubjectSubjectPK();

			subjectSubjectPK.setParent(company);

			subjectSubjectPK.setChild(user);

			SubjectSubject subjectSubject = new SubjectSubjectDAO(session).
					retrieve(subjectSubjectPK);

			if (subjectSubject == null)

				throw new UserNotLinkedToTheCompanyException();

			if (subjectSubject.getLevel().equals(Level.UNDEFINED))

				throw new UserNotAllowedException();

			token.setSubjectSubject(subjectSubject);

			Calendar calendar = Calendar.getInstance();

			calendar.add(Calendar.MINUTE, +15);

			token.setExpire(calendar.getTime());

			new TokenDAO(session).update(token);

			user.setLastLoggedInCompany(company);

			new UserDAO(session).update(user);
			
			session.getTransaction().commit();

			log.info(SecurityEndPoint.class.getSimpleName() + 
					": Usuário " +
					user.toString() +
					" alterou para a empresa " + 
					company.toString() + 
					" através do token " +
					token.toString());

			TokenBean001 tokenBean = new TokenBean001();

			tokenBean.setIdentifier(token.getIdentifier());


			UserBean001 userBean = new UserBean001();

			userBean.setIdentifier(user.getIdentifier());

			userBean.setActive(user.getActive());

			userBean.setName(user.getName());

			userBean.setLogin(user.getLogin());

			userBean.setPassword(user.getPassword());

			userBean.setNumericPassword(user.getNumericPassword());

			userBean.setLevel(token.getSubjectSubject().getLevel().ordinal());

			tokenBean.setUser(userBean);


			companyBean = new CompanyBean001();

			companyBean.setIdentifier(company.getIdentifier());

			companyBean.setActive(company.getActive());

			companyBean.setDenomination(company.getDenomination());

			companyBean.setFancyName(company.getFancyName());

			companyBean.setCouponTitle(company.getCouponTitle());

			companyBean.setCouponSubtitle(company.getCouponSubtitle());

			tokenBean.setCompany(companyBean);


			return Response.status(Response.Status.OK).
					entity(tokenBean).
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

	
	@GET
	@Path("/company")
	@Produces(MediaType.APPLICATION_JSON)
	@Authentication(level=Level.CUSTOMER_SUPPLIER)
	public Response getAllowedCompanies(@Context HttpServletRequest request) {

		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		String tokenIdentifier = authorizationHeader.substring("Bearer".length()).trim();

		Session session = HibernateUtil.getSessionFactory().openSession();

		TokenDAO tokenDAO = new TokenDAO(session);

		Token token = tokenDAO.retrieve(tokenIdentifier);

		List<CompanyBean001> companyBeans = new ArrayList<CompanyBean001>();

		for (SubjectSubject parent : token.
				getSubjectSubject().
				getIdentifier().
				getChild().
				getParents()) {

			Subject subject = parent.getIdentifier().getParent();

			if (!parent.getLevel().equals(Level.UNDEFINED) 
					&& subject.getActive()
					&& subject instanceof Company) {

				Company company = (Company) subject;

				CompanyBean001 companyBean = new CompanyBean001();

				companyBean.setIdentifier(company.getIdentifier());

				companyBean.setDenomination(company.getDenomination());

				companyBean.setFancyName(company.getFancyName());

				companyBean.setActive(company.getActive());

				companyBean.setCouponTitle(company.getCouponTitle());				

				companyBean.setCouponSubtitle(company.getCouponSubtitle());

				companyBeans.add(companyBean);

			}

		}

		return Response.status(Response.Status.OK).
				entity(companyBeans).
				build();

	}

	
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

			TokenBean001 tokenBean = new TokenBean001();

			tokenBean.setIdentifier(token.getIdentifier());

			UserBean001 userBean = new UserBean001();

			userBean.setIdentifier(user.getIdentifier());

			userBean.setActive(user.getActive());

			userBean.setName(user.getName());

			userBean.setLogin(user.getLogin());

			userBean.setPassword(user.getPassword());

			userBean.setNumericPassword(user.getNumericPassword());

			userBean.setLevel(token.getSubjectSubject().getLevel().ordinal());

			tokenBean.setUser(userBean);

			CompanyBean001 companyBean = new CompanyBean001();

			companyBean.setIdentifier(company.getIdentifier());

			companyBean.setActive(company.getActive());

			companyBean.setDenomination(company.getDenomination());

			companyBean.setFancyName(company.getFancyName());

			companyBean.setCouponTitle(company.getCouponTitle());

			companyBean.setCouponSubtitle(company.getCouponSubtitle());

			tokenBean.setCompany(companyBean);

			return Response.status(Response.Status.OK).
					entity(tokenBean).
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