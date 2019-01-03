package br.com.developen.erp.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.StringTokenizer;

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
import br.com.developen.erp.orm.Device;
import br.com.developen.erp.orm.DeviceDAO;
import br.com.developen.erp.util.HibernateUtil;
import br.com.developen.erp.util.I18N;

@Authorization
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

	static Logger log = LogManager.getRootLogger();	

	@Context
	private HttpServletRequest request;

	@Context
	private ResourceInfo resourceInfo;	

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {

			//BUSCA O TOKEN NO CABECALHO DA REQUISICAO
			String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

			if (authorizationHeader == null || !authorizationHeader.startsWith("Basic "))

				throw new WebApplicationException(Response.
						status(Status.UNAUTHORIZED).
						header("WWW-Authenticate", "Basic").
						entity(new ExceptionBean001(I18N.get(I18N.HTTP_AUTHORIZATION_HEADER_NOT_FOUND))).
						type(MediaType.APPLICATION_JSON).
						build());

			log.info(authorizationHeader);

			//PROCESSA O TOKEN ENVIADO
			StringTokenizer st = new StringTokenizer(authorizationHeader);

			if (st.hasMoreTokens()) {

				String basic = st.nextToken();

				if (basic.equalsIgnoreCase("Basic")) {

					try {

						String credentials = new String(Base64.getDecoder().decode(st.nextToken()), "UTF-8");

						int p = credentials.indexOf(":");

						if (p != -1) {

							//VERIFICA SE O DISPOSITIVO EXISTE
							Device device = new DeviceDAO(session).retrieve(
									Integer.valueOf(credentials.substring(0, p).trim()));

							if (device==null)

								throw new WebApplicationException(Response.
										status(Status.UNAUTHORIZED).
										header("WWW-Authenticate", "Basic").
										entity(new ExceptionBean001(I18N.get(I18N.DEVICE_NOT_FOUND))).
										type(MediaType.APPLICATION_JSON).
										build());

							//VERIFICA SE O DISPOSITIVO ESTA ATIVO
							if (!device.getActive())

								throw new WebApplicationException(Response.
										status(Status.UNAUTHORIZED).
										header("WWW-Authenticate", "Basic").
										entity(new ExceptionBean001(I18N.get(I18N.DEVICE_NOT_ACTIVE))).
										type(MediaType.APPLICATION_JSON).
										build());

							//VERIFICA SE O NUMERO DE SERIE COINCIDE COM O DISPOSITIVO INFORMADO
							if (!credentials.substring(p + 1).trim().equals(device.getSerialNumber()))

								throw new WebApplicationException(Response.
										status(Status.UNAUTHORIZED).
										header("WWW-Authenticate", "Basic").
										entity(new ExceptionBean001(I18N.get(I18N.INVALID_DEVICE_CREDENTIALS_ERROR))).
										type(MediaType.APPLICATION_JSON).
										build());

						} else {

							throw new WebApplicationException(Response.
									status(Status.BAD_REQUEST).
									entity(new ExceptionBean001(I18N.get(I18N.MALFOMED_AUTHENTICATION_TOKEN_ERROR))).
									type(MediaType.APPLICATION_JSON).
									build());

						}						

					} catch (UnsupportedEncodingException e) {

						throw new WebApplicationException(Response.
								status(Status.INTERNAL_SERVER_ERROR).
								entity(new ExceptionBean001(I18N.get(I18N.INTERNAL_SERVER_ERROR))).
								type(MediaType.APPLICATION_JSON).
								build());

					}

				}

			}

		} catch (WebApplicationException e) {

			requestContext.abortWith(e.getResponse());

		} catch (Exception e) {

			log.error(AuthorizationFilter.class.getSimpleName() + ": " +
					e.getMessage(),
					e.getCause());

			requestContext.abortWith(Response.
					status(Status.INTERNAL_SERVER_ERROR).
					entity(new ExceptionBean001(I18N.get(I18N.INTERNAL_SERVER_ERROR))).
					type(MediaType.APPLICATION_JSON).
					build());

		} finally {

			session.close();

		}

	}

}