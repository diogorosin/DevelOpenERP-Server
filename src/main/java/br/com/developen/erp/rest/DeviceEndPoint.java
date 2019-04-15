package br.com.developen.erp.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

import br.com.developen.erp.bean.CompanyDeviceDatasetBean001;
import br.com.developen.erp.bean.ConfigurationBean001;
import br.com.developen.erp.bean.ExceptionBean001;
import br.com.developen.erp.bean.SaleBean001;
import br.com.developen.erp.bean.SaleItemBean001;
import br.com.developen.erp.bean.StringBean;
import br.com.developen.erp.exception.DeviceNotActiveException;
import br.com.developen.erp.exception.DeviceNotAllowedOnThisCompanyException;
import br.com.developen.erp.exception.DeviceNotFoundException;
import br.com.developen.erp.exception.DeviceNotLinkedToTheCompanyException;
import br.com.developen.erp.orm.Company;
import br.com.developen.erp.orm.CompanyDevice;
import br.com.developen.erp.orm.CompanyDeviceDAO;
import br.com.developen.erp.orm.CompanyDevicePK;
import br.com.developen.erp.orm.CompanyDeviceSale;
import br.com.developen.erp.orm.CompanyDeviceSaleDAO;
import br.com.developen.erp.orm.CompanyDeviceSaleItem;
import br.com.developen.erp.orm.CompanyDeviceSaleItemPK;
import br.com.developen.erp.orm.CompanyDeviceSalePK;
import br.com.developen.erp.orm.Device;
import br.com.developen.erp.orm.DeviceDAO;
import br.com.developen.erp.orm.Level;
import br.com.developen.erp.orm.MeasureUnitDAO;
import br.com.developen.erp.orm.ProgenyDAO;
import br.com.developen.erp.orm.Status;
import br.com.developen.erp.orm.Token;
import br.com.developen.erp.orm.TokenDAO;
import br.com.developen.erp.orm.User;
import br.com.developen.erp.orm.UserDAO;
import br.com.developen.erp.util.CompanyDeviceDatasetBuilder001;
import br.com.developen.erp.util.HibernateUtil;
import br.com.developen.erp.util.I18N;

@Path("/device")
@Authentication(level=Level.CASHIER)
public class DeviceEndPoint {

	static Logger log = LogManager.getRootLogger();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Authentication(level=Level.CUSTOMER_SUPPLIER)
	@Path("/alias/{serialNumber: [a-z0-9]+}")
	public Response retrieveBySerialNumber(
			@Context HttpServletRequest request, 
			@PathParam("serialNumber") String serialNumber) {

		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		String tokenIdentifier = authorizationHeader.substring("Bearer".length()).trim();

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {

			Device device = new DeviceDAO(session).retrieveBySerialNumber(serialNumber);

			if (device == null)

				throw new DeviceNotFoundException();

			Token token = new TokenDAO(session).retrieve(tokenIdentifier);

			CompanyDevicePK companyDevicePK = new CompanyDevicePK();

			companyDevicePK.setCompany((Company) token.getSubjectSubject().getIdentifier().getParent());

			companyDevicePK.setDevice(device);

			CompanyDevice companyDevice = new CompanyDeviceDAO(session).retrieve(companyDevicePK);

			if (companyDevice == null)

				throw new DeviceNotLinkedToTheCompanyException();

			return Response.
					status(Response.Status.OK).
					entity(new StringBean(companyDevice.getAlias())).
					build();

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


	@POST
	@Path("/configure")
	@Authentication(level=Level.MANAGER)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response configure(@Context HttpServletRequest request, ConfigurationBean001 configurationBean) {

		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		String tokenIdentifier = authorizationHeader.substring("Bearer".length()).trim();

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		try {

			//DEVICE
			DeviceDAO deviceDAO = new DeviceDAO(session);

			Device device = deviceDAO.retrieveBySerialNumber(configurationBean.getDeviceSerialNumber()); 

			if (device == null){

				device = new Device();

				device.setActive(true);

				device.setSerialNumber(configurationBean.getDeviceSerialNumber());

				device.setManufacturer(configurationBean.getDeviceManufacturer());

				device.setModel(configurationBean.getDeviceModel());

				deviceDAO.create(device);

			}

			//TOKEN
			Token token = new TokenDAO(session).retrieve(tokenIdentifier);

			//USER
			User user = (User) token.getSubjectSubject().getIdentifier().getChild();

			//COMPANY
			Company company = (Company) token.getSubjectSubject().getIdentifier().getParent();

			//COMPANY_DEVICE
			String alias = configurationBean.getDeviceAlias() == null ? configurationBean.getDeviceManufacturer() + "/" + configurationBean.getDeviceModel() : configurationBean.getDeviceAlias();

			if (alias.length() > 20)

				alias = alias.substring(0, 19);

			CompanyDevicePK companyDevicePK = new CompanyDevicePK();

			companyDevicePK.setCompany(company);

			companyDevicePK.setDevice(device);

			CompanyDeviceDAO companyDeviceDAO = new CompanyDeviceDAO(session);

			CompanyDevice companyDevice = companyDeviceDAO.retrieve(companyDevicePK);

			if (companyDevice == null) {

				companyDevice = new CompanyDevice();

				companyDevice.setIdentifier(companyDevicePK);	

				companyDevice.setAlias(alias);

				companyDevice.setAllow(true);

				companyDeviceDAO.create(companyDevice);

			} else {

				companyDevice.setAlias(alias);

				companyDeviceDAO.update(companyDevice);

			}

			session.getTransaction().commit();

			log.info(DeviceEndPoint.class.getSimpleName() + 
					": PDV configurado com sucesso. Usuário: " + 
					user.toString() +
					" vinculado a empresa: " +
					company.toString() +
					" através do dispositivo: " + 
					device.toString());

			return Response.status(Response.Status.OK).
					entity(new CompanyDeviceDatasetBuilder001().
							withCompany(company).
							withDevice(device).
							withAlias(companyDevice.getAlias()).
							withAllow(companyDevice.getAllow()).
							withSubjects(company.getChilds()).
							withMeasureUnits(new MeasureUnitDAO(session).list()).
							withProgenies(company.getProgenies()).
							withCatalogs(company.getCatalogs()).
							withReceiptMethods(company.getReceiptMethods()).
							withPaymentMethods(company.getPaymentMethods()).
							withSales(companyDevice.getSales()).
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

			log.error(DeviceEndPoint.class.getSimpleName() + ": " + 
					e.getMessage(), 
					e.getCause());

			return Response.status(Response.Status.NOT_FOUND).
					entity(new ExceptionBean001(I18N.get(I18N.NOT_FOUND_ERROR))).
					build();

		} catch (Exception e) {

			if (session.getTransaction().isActive())

				session.getTransaction().rollback();

			log.error(DeviceEndPoint.class.getSimpleName() + ": " + 
					e.getMessage(), 
					e.getCause());

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(new ExceptionBean001(I18N.get(I18N.INTERNAL_SERVER_ERROR))).
					build();

		} finally {

			session.close();

		}

	}


	@POST
	@Path("/sale")
	@Authentication(level=Level.CASHIER)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response sale(@Context HttpServletRequest request, CompanyDeviceDatasetBean001 companyDeviceDatasetBean) {

		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		String tokenIdentifier = authorizationHeader.substring("Bearer".length()).trim();

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		try {

			//RETURN
			Map<Integer, Boolean> imported = new HashMap<>();

			//TOKEN
			Token token = new TokenDAO(session).retrieve(tokenIdentifier);

			//USER
			User user = (User) token.getSubjectSubject().getIdentifier().getChild();

			//COMPANY
			Company company = (Company) token.getSubjectSubject().getIdentifier().getParent();

			//DEVICE
			Device device = new DeviceDAO(session).retrieve(companyDeviceDatasetBean.getDevice().getIdentifier());

			if (device==null)

				throw new DeviceNotFoundException();

			if (!device.getActive())

				throw new DeviceNotActiveException();

			//COMPANY_DEVICE
			CompanyDevicePK companyDevicePK = new CompanyDevicePK();

			companyDevicePK.setCompany(company);

			companyDevicePK.setDevice(device);

			CompanyDevice companyDevice = new CompanyDeviceDAO(session).retrieve(companyDevicePK);

			if (companyDevice==null)

				throw new DeviceNotLinkedToTheCompanyException();

			if (!companyDevice.getAllow())

				throw new DeviceNotAllowedOnThisCompanyException();

			//IMPORT SALES			
			if (companyDeviceDatasetBean.getSales() != null && !companyDeviceDatasetBean.getSales().isEmpty()) {

				CompanyDeviceSaleDAO companyDeviceSaleDAO = new CompanyDeviceSaleDAO(session);

				for (SaleBean001 saleBean : companyDeviceDatasetBean.getSales()) {

					CompanyDeviceSalePK companyDeviceSalePK = new CompanyDeviceSalePK();

					companyDeviceSalePK.setCompanyDevice(companyDevice);

					companyDeviceSalePK.setSale(saleBean.getIdentifier());

					CompanyDeviceSale companyDeviceSale = companyDeviceSaleDAO.retrieve(companyDeviceSalePK);

					if (companyDeviceSale==null) { 

						companyDeviceSale = new CompanyDeviceSale();

						companyDeviceSale.setIdentifier(companyDeviceSalePK);

						companyDeviceSale.setNumber(saleBean.getNumber());

						companyDeviceSale.setDateTime(saleBean.getDateTime());

						companyDeviceSale.setStatus(Status.valueOf(saleBean.getStatus()));

						companyDeviceSale.setUser(new UserDAO(session).retrieve(saleBean.getUser()));

						companyDeviceSale.setNote(saleBean.getNote());

						companyDeviceSale.setItems(new ArrayList<>());

						for (Map.Entry<Integer, SaleItemBean001> item : saleBean.getItems().entrySet()) {

							Integer key = item.getKey();

						    SaleItemBean001 value = item.getValue();

						    CompanyDeviceSaleItemPK companyDeviceSaleItemPK = new CompanyDeviceSaleItemPK();

						    companyDeviceSaleItemPK.setCompanyDeviceSale(companyDeviceSale);

						    companyDeviceSaleItemPK.setItem(key);

						    CompanyDeviceSaleItem companyDeviceSaleItem = new CompanyDeviceSaleItem();

						    companyDeviceSaleItem.setIdentifier(companyDeviceSaleItemPK);

						    companyDeviceSaleItem.setProgeny(new ProgenyDAO(session).retrieve(value.getProgeny()));

						    companyDeviceSaleItem.setMeasureUnit(new MeasureUnitDAO(session).retrieve(value.getMeasureUnit()));

						    companyDeviceSaleItem.setQuantity(value.getQuantity());

						    companyDeviceSaleItem.setPrice(value.getPrice());

						    companyDeviceSaleItem.setTotal(value.getTotal());

						    companyDeviceSale.getItems().add(companyDeviceSaleItem);

						}

						companyDeviceSaleDAO.create(companyDeviceSale);

						imported.put(saleBean.getIdentifier(), true);

					} else {

						companyDeviceSale.setStatus(Status.valueOf(saleBean.getStatus()));

						companyDeviceSale.setNote(saleBean.getNote());

						companyDeviceSaleDAO.update(companyDeviceSale);

						imported.put(saleBean.getIdentifier(), true);

					}

				}

			}

			session.getTransaction().commit();

			log.info(DeviceEndPoint.class.getSimpleName() + 
					": Vendas importadas com sucesso" +
					". Usuário: " + 
					user.toString() +
					". Empresa: " +
					company.toString() +
					". Dispositivo: " + 
					device.toString());

			return Response.
					status(Response.Status.OK).
					entity(imported).
					build();

		} catch (ConstraintViolationException e) {

			e.printStackTrace();
			
			if (session.getTransaction().isActive())

				session.getTransaction().rollback();

			ExceptionBean001 exceptionBean = new ExceptionBean001();

			/*int i = 0;

			for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {

				exceptionBean.getMessages()[i] = constraintViolation.getRootBeanClass().getSimpleName() + "." +
						constraintViolation.getPropertyPath().toString() + " " +
						constraintViolation.getMessage().toLowerCase();

				i++;

			}*/

			return Response.status(Response.Status.BAD_REQUEST).
					entity(exceptionBean).
					build(); 

		} catch (ObjectNotFoundException e) {				

			if (session.getTransaction().isActive())

				session.getTransaction().rollback();

			log.error(DeviceEndPoint.class.getSimpleName() + ": " + 
					e.getMessage(), 
					e.getCause());

			return Response.status(Response.Status.NOT_FOUND).
					entity(new ExceptionBean001(I18N.get(I18N.NOT_FOUND_ERROR))).
					build();

		} catch (Exception e) {

			if (session.getTransaction().isActive())

				session.getTransaction().rollback();

			log.error(DeviceEndPoint.class.getSimpleName() + ": " + 
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