package br.com.developen.erp.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

import br.com.developen.erp.bean.DeviceBean001;
import br.com.developen.erp.bean.ExceptionBean001;
import br.com.developen.erp.exception.DeviceNotFoundException;
import br.com.developen.erp.orm.Device;
import br.com.developen.erp.orm.DeviceDAO;
import br.com.developen.erp.util.HibernateUtil;
import br.com.developen.erp.util.I18N;

@Path("/device")
public class DeviceEndPoint {

	@GET
	@Path("/{identifier: \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieve(@PathParam("identifier") Integer identifier) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {

			Device device = new Device();

			new DeviceDAO(session).load(device, identifier);

			DeviceBean001 deviceBean = new DeviceBean001();

			deviceBean.setIdentifier(device.getIdentifier());

			deviceBean.setSerialNumber(device.getSerialNumber());

			deviceBean.setManufacturer(device.getManufacturer());
			
			deviceBean.setModel(device.getModel());

			return Response.status(Response.Status.OK).entity(deviceBean).build();

		} catch (ObjectNotFoundException e) {

			return Response.status(Response.Status.NOT_FOUND).
					entity(new ExceptionBean001(I18N.get(I18N.DEVICE_NOT_FOUND))).
					build();

		} catch (Exception e) {

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(new ExceptionBean001(I18N.get(I18N.INTERNAL_SERVER_ERROR))).
					build();

		} finally {

			session.close();

		}

	}

	@GET
	@Path("/serialNumber/{serialNumber: [a-z0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveBySerialNumber(@PathParam("serialNumber") String serialNumber) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {

			Device device = new DeviceDAO(session).retrieveBySerialNumber(serialNumber);

			if (device == null)

				throw new DeviceNotFoundException();

			DeviceBean001 deviceBean = new DeviceBean001();

			deviceBean.setIdentifier(device.getIdentifier());

			deviceBean.setSerialNumber(device.getSerialNumber());			

			deviceBean.setManufacturer(device.getManufacturer());

			deviceBean.setModel(device.getModel());

			return Response.status(
					Response.Status.OK).
					entity(deviceBean).build();			

		} catch (DeviceNotFoundException e) {

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