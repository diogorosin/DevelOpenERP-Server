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
import br.com.pocketpos.server.orm.Company;
import br.com.pocketpos.server.orm.CompanyDAO;
import br.com.pocketpos.server.orm.CompanyDevice;
import br.com.pocketpos.server.orm.CompanyDeviceDAO;
import br.com.pocketpos.server.orm.CompanyDeviceDataset;
import br.com.pocketpos.server.orm.CompanyDeviceDatasetDAO;
import br.com.pocketpos.server.orm.CompanyDeviceDatasetPK;
import br.com.pocketpos.server.orm.CompanyDevicePK;
import br.com.pocketpos.server.orm.Device;
import br.com.pocketpos.server.orm.DeviceDAO;
import br.com.pocketpos.server.util.DownloadBeanFactory;
import br.com.pocketpos.server.util.HibernateUtil;
import br.com.pocketpos.server.util.I18N;

@Path("/download")
public class DownloadEndPoint {

	static Logger log = LogManager.getRootLogger();	

	@GET
	@Path("/{company: \\d+}/{device: \\d+}/{dataset: \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
//	@Authorization
	public Response getPrivateDownloadBean(
			@PathParam("company") Integer companyIdentifier,
			@PathParam("device") Integer deviceIdentifier,
			@PathParam("dataset") Integer datasetIdentifier) {

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

			CompanyDeviceDatasetPK companyDeviceDatasetPK = new CompanyDeviceDatasetPK();

			companyDeviceDatasetPK.setCompanyDevice(companyDevice);

			companyDeviceDatasetPK.setDataset(datasetIdentifier);

			CompanyDeviceDataset companyDeviceDataset = new CompanyDeviceDataset();			

			new CompanyDeviceDatasetDAO(session).load(companyDeviceDataset, companyDeviceDatasetPK);			

			return Response.
					status(Response.Status.OK).
					entity(DownloadBeanFactory.
							buildDownloadBean(
									companyDeviceDataset,
									DownloadBeanFactory.VERSION_001)).
					build(); 

		} catch (ObjectNotFoundException e) {

			log.error(DownloadEndPoint.class.getSimpleName() + ": " + 
					e.getMessage(), 
					e.getCause());

			return Response.status(Response.Status.NOT_FOUND).
					entity(new ExceptionBean001(I18N.get(I18N.NOT_FOUND_ERROR))).
					build();

		} catch (Exception e) {

			log.error(DownloadEndPoint.class.getSimpleName() + ": " + 
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