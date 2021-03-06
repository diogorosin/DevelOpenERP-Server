package br.com.developen.erp.rest;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import br.com.developen.erp.bean.ExceptionBean001;
import br.com.developen.erp.exception.CompanyNotActiveException;
import br.com.developen.erp.exception.UnauthorizedException;
import br.com.developen.erp.exception.UserNotActiveException;
import br.com.developen.erp.exception.UserNotAllowedException;
import br.com.developen.erp.orm.Level;
import br.com.developen.erp.orm.Token;
import br.com.developen.erp.orm.TokenDAO;
import br.com.developen.erp.util.HibernateUtil;
import br.com.developen.erp.util.I18N;

@Authentication
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	static Logger log = LogManager.getRootLogger();	

	@Context 
	private HttpServletRequest request;

	@Context
	private ResourceInfo resourceInfo;	

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {

			session.beginTransaction();

			//BUSCA O TOKEN NO CABECALHO DA REQUISICAO
			String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

			if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))

				throw new WebApplicationException(Response.status(Status.BAD_REQUEST).
						entity(new ExceptionBean001(I18N.get(I18N.HTTP_AUTHORIZATION_HEADER_NOT_FOUND))).
						type(MediaType.APPLICATION_JSON).
						build());

			String tokenIdentifier = authorizationHeader.substring("Bearer".length()).trim();

			TokenDAO tokenDAO = new TokenDAO(session);

			Token token = tokenDAO.retrieve(tokenIdentifier);

			//VERIFICA SE O TOKEN FOI ENCONTRADO
			if (token==null)

				throw new WebApplicationException(Response.status(Status.UNAUTHORIZED).
						entity(new ExceptionBean001(I18N.get(I18N.INVALID_TOKEN))).
						type(MediaType.APPLICATION_JSON).
						build());

			//VERIFICA A VALIDADE DO TOKEN
			if (token.getExpire().before(new Date()))

				throw new WebApplicationException(Response.status(Status.UNAUTHORIZED).
						entity(new ExceptionBean001(I18N.get(I18N.EXPIRED_TOKEN))).
						type(MediaType.APPLICATION_JSON).
						build());

			//VERIFICA SE TOKEN POSSUI MAIS DE 24H
//			Calendar calendar = Calendar.getInstance();

//			calendar.add(Calendar.HOUR, -24);

//			if (token.getExpire().before(calendar.getTime()))

//				throw new WebApplicationException(Response.status(Status.UNAUTHORIZED).
//				entity(new ExceptionBean001(I18N.get(I18N.EXPIRED_TOKEN))).
//				type(MediaType.APPLICATION_JSON).
//				build());

			//VERIFICA SE A EMPRESA ESTA ATIVA			
			if (!token.
					getCompanyUser().
					getIdentifier().
					getCompany().
					getActive())

				throw new CompanyNotActiveException();

			//VERIFICA SE O USUARIO ESTA ATIVO			
			if (!token.
					getCompanyUser().
					getIdentifier().
					getUser().
					getActive())

				throw new UserNotActiveException();

			//VERIFICA SE O USUARIO ESTA ATIVO PARA A EMPRESA
			if (token.getCompanyUser().
					getLevel().
					equals(Level.UNDEFINED)) {

				throw new UserNotAllowedException();

			} else {

				//VERIFICA O NIVEL DE ACESSO
				Method method = resourceInfo.getResourceMethod();

				if (method.isAnnotationPresent(Authentication.class)){

					Authentication secured = method.getAnnotation(Authentication.class);

					if (secured.level().ordinal() > token.
							getCompanyUser().
							getLevel().
							ordinal()){

						throw new WebApplicationException(Response.status(Status.FORBIDDEN).
								entity(new ExceptionBean001(I18N.get(I18N.RESOURCE_NOT_ALLOWED))).
								type(MediaType.APPLICATION_JSON).
								build());

					}

				}

			}

			Calendar calendar = Calendar.getInstance();

			calendar.add(Calendar.HOUR, +24);

			token.setExpire(calendar.getTime());

			tokenDAO.update(token);

			session.getTransaction().commit();			

		} catch (WebApplicationException e) {

			if (session.getTransaction().isActive())

				session.getTransaction().rollback();

			log.error(AuthenticationFilter.class.getSimpleName() + ": " + 
					e.getMessage(), 
					e.getCause());

			requestContext.abortWith(e.getResponse());

		} catch (UnauthorizedException e) {

			if (session.getTransaction().isActive())

				session.getTransaction().rollback();

			log.error(AuthenticationFilter.class.getSimpleName() + ": " + 
					e.getMessage(), 
					e.getCause());

			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).
					entity(new ExceptionBean001(e.getMessage())).
					type(MediaType.APPLICATION_JSON).
					build());

		} catch (Exception e) {

			if (session.getTransaction().isActive())

				session.getTransaction().rollback();

			log.error(AuthenticationFilter.class.getSimpleName() + ": " + 
					e.getMessage(), 
					e.getCause());

			requestContext.abortWith(Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(new ExceptionBean001(I18N.get(I18N.INTERNAL_SERVER_ERROR))).
					type(MediaType.APPLICATION_JSON).
					build());

		} finally {

			session.close();

		}

	}

}