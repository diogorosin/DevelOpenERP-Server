package br.com.developen.erp.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;

import br.com.developen.erp.bean.ExceptionBean001;
import br.com.developen.erp.bean.UserBean001;
import br.com.developen.erp.exception.UserNotFoundException;
import br.com.developen.erp.orm.Level;
import br.com.developen.erp.orm.User;
import br.com.developen.erp.orm.UserDAO;
import br.com.developen.erp.util.HibernateUtil;
import br.com.developen.erp.util.I18N;

@Path("/user")
@Authentication(level=Level.CASHIER)
public class UserEndPoint {

	@GET
	@Path("/login/{login:([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+){1,}}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveByLogin(@PathParam("login") String login) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {

			User user = new UserDAO(session).retrieveByLogin(login);

			if (user==null)

				throw new UserNotFoundException();

			UserBean001 userBean = new UserBean001();

			userBean.setIdentifier(user.getIdentifier());

			userBean.setActive(user.getActive());

			userBean.setName(user.getName());

			userBean.setLogin(user.getLogin());

			userBean.setPassword(user.getPassword());

			userBean.setNumericPassword(user.getNumericPassword());

			return Response.status(
					Response.Status.OK).
					entity(userBean).build();			

		} catch (UserNotFoundException e) {

			return Response.status(Response.Status.NOT_FOUND).
					entity(new ExceptionBean001(e.getMessage())).
					build();

		} catch (Exception e) {

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(new ExceptionBean001(I18N.get(I18N.INTERNAL_SERVER_ERROR))).
					build();

		} finally {

			session.close();

		}

	}

}