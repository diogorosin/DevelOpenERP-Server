package br.com.developen.erp.rest;

import java.util.ArrayList;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

import br.com.developen.erp.bean.POSConfigurationBean001;
import br.com.developen.erp.bean.ExceptionBean001;
import br.com.developen.erp.orm.Company;
import br.com.developen.erp.orm.CompanyDAO;
import br.com.developen.erp.orm.CompanyDevice;
import br.com.developen.erp.orm.CompanyDevicePK;
import br.com.developen.erp.orm.Device;
import br.com.developen.erp.orm.DeviceDAO;
import br.com.developen.erp.orm.Level;
import br.com.developen.erp.orm.MeasureUnitDAO;
import br.com.developen.erp.orm.User;
import br.com.developen.erp.orm.UserDAO;
import br.com.developen.erp.util.DatasetBuilder001;
import br.com.developen.erp.util.HibernateUtil;
import br.com.developen.erp.util.I18N;

@Path("/pos")
@Authentication(level=Level.CASHIER)
public class POSEndPoint {

	static Logger log = LogManager.getRootLogger();	

	@POST
	@Path("/configure")	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response configure(POSConfigurationBean001 posConfigurationBean) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		try {

			boolean isNewCompanyDevice = false;

			//COMPANY
			Company company = new Company();

			CompanyDAO companyDAO = new CompanyDAO(session);

			companyDAO.load(company, posConfigurationBean.
					getCompany().
					getIdentifier());

			//DEVICE
			Device device = new Device();

			DeviceDAO deviceDAO = new DeviceDAO(session);

			if (posConfigurationBean.getDevice().getIdentifier()==null){

				device.setActive(posConfigurationBean.getDevice().getActive());

				device.setSerialNumber(posConfigurationBean.getDevice().getSerialNumber());

				device.setManufacturer(posConfigurationBean.getDevice().getManufacturer());

				device.setModel(posConfigurationBean.getDevice().getModel());

				deviceDAO.create(device);

			} else

				deviceDAO.load(device, posConfigurationBean.getDevice().getIdentifier());

			//USER
			User user = new User();

			UserDAO userDAO = new UserDAO(session);

			userDAO.load(user, posConfigurationBean.
					getUser().
					getIdentifier());

			//COMPANY_DEVICE
			if (company.getDevices()==null)

				company.setDevices(new ArrayList<CompanyDevice>());

			CompanyDevicePK companyDevicePK = new CompanyDevicePK();

			companyDevicePK.setCompany(company);

			companyDevicePK.setDevice(device);

			CompanyDevice companyDevice = new CompanyDevice();

			companyDevice.setIdentifier(companyDevicePK);

			if (!company.getDevices().contains(companyDevice)){

				companyDevice.setAlias((device.getManufacturer() + "/" + device.getModel()).substring(0,11));

				companyDevice.setAllow(false);

				company.getDevices().add(companyDevice);

				isNewCompanyDevice = true;

			}

			if (isNewCompanyDevice)

				companyDAO.update(company);

			session.getTransaction().commit();

			log.info(POSEndPoint.class.getSimpleName() + 
					": Conta configurada com sucesso. Usuário: " + 
					user.toString() +
					" vinculado a empresa: " +
					company.toString() +
					" através do dispositivo: " + 
					device.toString());

			return Response.status(Response.Status.OK).
					entity(new DatasetBuilder001().
							withCompany(company).
							withDevices(company.getDevices()).
							withSubjects(company.getChilds()).
							withMeasureUnits(new MeasureUnitDAO(session).list()).
							withProgenies(company.getProgenies()).
							withCatalogs(company.getCatalogs()).
							withReceiptMethods(company.getReceiptMethods()).
							withPaymentMethods(company.getPaymentMethods()).
							build()).build();

		} catch (ConstraintViolationException e) {

			if (session.getTransaction().isActive())

				session.getTransaction().rollback();

			ExceptionBean001 exceptionBean = new ExceptionBean001();

			int i = 0;

			for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {

				exceptionBean.getMessages()[i] = constraintViolation.getRootBeanClass().getSimpleName() + "." +
						constraintViolation.getPropertyPath().toString() + " " +
						constraintViolation.getMessage().toLowerCase();

				i++;

			}

			return Response.status(Response.Status.BAD_REQUEST).
					entity(exceptionBean).
					build(); 

		} catch (ObjectNotFoundException e) {				

			if (session.getTransaction().isActive())

				session.getTransaction().rollback();

			log.error(POSEndPoint.class.getSimpleName() + ": " + 
					e.getMessage(), 
					e.getCause());

			return Response.status(Response.Status.NOT_FOUND).
					entity(new ExceptionBean001(I18N.get(I18N.NOT_FOUND_ERROR))).
					build();

		} catch (Exception e) {

			if (session.getTransaction().isActive())

				session.getTransaction().rollback();

			log.error(POSEndPoint.class.getSimpleName() + ": " + 
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