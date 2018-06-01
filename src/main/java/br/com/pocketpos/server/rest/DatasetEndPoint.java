package br.com.pocketpos.server.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

import br.com.pocketpos.server.bean.ExceptionBean001;
import br.com.pocketpos.server.bean.IntegerBean001;
import br.com.pocketpos.server.orm.Company;
import br.com.pocketpos.server.orm.CompanyDAO;
import br.com.pocketpos.server.orm.CompanyDevice;
import br.com.pocketpos.server.orm.CompanyDeviceDAO;
import br.com.pocketpos.server.orm.CompanyDeviceDatasetDAO;
import br.com.pocketpos.server.orm.CompanyDevicePK;
import br.com.pocketpos.server.orm.Device;
import br.com.pocketpos.server.orm.DeviceDAO;
import br.com.pocketpos.server.util.HibernateUtil;
import br.com.pocketpos.server.util.I18N;

@Path("/dataset")
public class DatasetEndPoint {

	static Logger log = LogManager.getRootLogger();	

	@GET
	@Path("/latter/{company: \\d+}/{device: \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDownloadBean(
			@PathParam("company") Integer companyIdentifier,
			@PathParam("device") Integer deviceIdentifier) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {

			Company company = new Company();

			new CompanyDAO(session).load(company, companyIdentifier);

			Device device = new Device();

			new DeviceDAO(session).load(device, deviceIdentifier);

			CompanyDevicePK companyDevicePK = new CompanyDevicePK();

			companyDevicePK.setCompany(company);

			companyDevicePK.setDevice(device);

			CompanyDevice companyDevice = new CompanyDevice();

			new CompanyDeviceDAO(session).load(companyDevice, companyDevicePK);

			return Response.
					status(Response.Status.OK).
					entity(new IntegerBean001(
							new CompanyDeviceDatasetDAO(session).
							getLatterIdentifierByCompanyDevice(companyDevice))).
					build();

		} catch (ObjectNotFoundException e) {

			log.error(DownloadEndPoint.class.getSimpleName() + ": " + 
					e.getMessage(), 
					e.getCause());

			return Response.status(Response.Status.NOT_FOUND).
					entity(new ExceptionBean001(I18N.get(I18N.NOT_FOUND_ERROR))).
					build();

		} catch (Exception e) {

			log.error(DatasetEndPoint.class.getSimpleName() + ": " + 
					e.getMessage(), 
					e.getCause());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(new ExceptionBean001(I18N.get(I18N.INTERNAL_SERVER_ERROR))).
					build();

		} finally {

			session.close();

		}

	}

}