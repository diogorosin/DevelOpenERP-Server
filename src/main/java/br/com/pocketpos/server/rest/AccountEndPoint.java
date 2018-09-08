package br.com.pocketpos.server.rest;

import java.security.MessageDigest;
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

import br.com.pocketpos.server.bean.AccountBean001;
import br.com.pocketpos.server.bean.ExceptionBean001;
import br.com.pocketpos.server.orm.Company;
import br.com.pocketpos.server.orm.CompanyDAO;
import br.com.pocketpos.server.orm.CompanyDevice;
import br.com.pocketpos.server.orm.CompanyDevicePK;
import br.com.pocketpos.server.orm.CompanyPaymentMethod;
import br.com.pocketpos.server.orm.CompanyPaymentMethodPK;
import br.com.pocketpos.server.orm.CompanyReceiptMethod;
import br.com.pocketpos.server.orm.CompanyReceiptMethodPK;
import br.com.pocketpos.server.orm.Device;
import br.com.pocketpos.server.orm.DeviceDAO;
import br.com.pocketpos.server.orm.Level;
import br.com.pocketpos.server.orm.MeasureUnitDAO;
import br.com.pocketpos.server.orm.PaymentMethodDAO;
import br.com.pocketpos.server.orm.ReceiptMethodDAO;
import br.com.pocketpos.server.orm.SubjectSubject;
import br.com.pocketpos.server.orm.SubjectSubjectPK;
import br.com.pocketpos.server.orm.User;
import br.com.pocketpos.server.orm.UserDAO;
import br.com.pocketpos.server.util.DatasetBuilder001;
import br.com.pocketpos.server.util.HibernateUtil;
import br.com.pocketpos.server.util.I18N;

@Path("/account")
public class AccountEndPoint {

	static Logger log = LogManager.getRootLogger();	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(AccountBean001 account) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		try {

			boolean isNewCompany = false;

			boolean isNewDevice = false;

			boolean isNewUser = false;

			boolean isNewCompanyDevice = false;

			boolean isNewSubjectSubject = false;

			//COMPANY
			CompanyDAO companyDAO = new CompanyDAO(session);

			Company company = new Company();

			if (account.getCompany().getIdentifier() == null){

				//CRIA A EMPRESA
				company.setDenomination(account.getCompany().getDenomination());

				company.setFancyName(account.getCompany().getFancyName());

				company.setActive(true);

				company.setCouponTitle(Company.COUPON_TITLE_DEFAULT);

				company.setCouponSubtitle(null);

				company.setReceiptMethods(new ArrayList<CompanyReceiptMethod>());

				company.setPaymentMethods(new ArrayList<CompanyPaymentMethod>());

				//INCLUI AS FORMAS DE RECEBIMENTO
				CompanyReceiptMethodPK companyReceiptMethodPK = new CompanyReceiptMethodPK();

				companyReceiptMethodPK.setCompany(company);

				companyReceiptMethodPK.setReceiptMethod(new ReceiptMethodDAO(session).retrieve("DIN"));
 
				CompanyReceiptMethod companyReceiptMethod = new CompanyReceiptMethod();

				companyReceiptMethod.setIdentifier(companyReceiptMethodPK);

				company.getReceiptMethods().add(companyReceiptMethod);

				//INCLUI AS FORMAS DE PAGAMENTOS				
				CompanyPaymentMethodPK companyPaymentMethodPK = new CompanyPaymentMethodPK();

				companyPaymentMethodPK.setCompany(company);

				companyPaymentMethodPK.setPaymentMethod(new PaymentMethodDAO(session).retrieve("DIN"));
 
				CompanyPaymentMethod companyPaymentMethod = new CompanyPaymentMethod();

				companyPaymentMethod.setIdentifier(companyPaymentMethodPK);

				company.getPaymentMethods().add(companyPaymentMethod);


				companyDAO.create(company);

				isNewCompany = true;

			} else

				companyDAO.load(company, account.
						getCompany().
						getIdentifier());

			//DEVICE
			DeviceDAO deviceDAO = new DeviceDAO(session);

			Device device = new Device();

			if (account.getDevice().getIdentifier()==null){

				device.setActive(account.getDevice().getActive());

				device.setSerialNumber(account.getDevice().getSerialNumber());

				device.setManufacturer(account.getDevice().getManufacturer());

				device.setModel(account.getDevice().getModel());

				deviceDAO.create(device);

				isNewDevice = true;

			} else

				deviceDAO.load(device, account.getDevice().getIdentifier());

			//USER
			UserDAO userDAO = new UserDAO(session);

			User user = new User();

			if (account.getUser().getIdentifier() == null){

				user.setActive(true);

				user.setName(account.getUser().getName());

				user.setLogin(account.getUser().getLogin());

				MessageDigest algorithm = MessageDigest.getInstance("SHA-256");

				byte messageDigest[] = algorithm.digest(
						account.
						getUser().
						getPassword().
						getBytes("UTF-8"));

				StringBuilder hexString = new StringBuilder();

				for (byte b : messageDigest)

					hexString.append(String.format("%02X", 0xFF & b));

				String hexPassword = hexString.toString();

				user.setPassword(hexPassword);

				userDAO.create(user);

				isNewUser = true;

			} else {

				userDAO.load(user, account.
						getUser().
						getIdentifier());

			}

			//COMPANY_DEVICE
			if (company.getDevices()==null)

				company.setDevices(new ArrayList<CompanyDevice>());

			CompanyDevicePK companyDevicePK = new CompanyDevicePK();

			companyDevicePK.setCompany(company);

			companyDevicePK.setDevice(device);

			CompanyDevice companyDevice = new CompanyDevice();

			companyDevice.setIdentifier(companyDevicePK);

			if (!company.getDevices().contains(companyDevice)){

				companyDevice.setAlias(device.getManufacturer() + "/" + device.getModel());

				companyDevice.setAllow(isNewCompany);

				company.getDevices().add(companyDevice);

				isNewCompanyDevice = true;

			}

			//SUBJECT_SUBJECT
			if (company.getChilds()==null)

				company.setChilds(new ArrayList<SubjectSubject>());

			SubjectSubjectPK subjectSubjectPK = new SubjectSubjectPK();

			subjectSubjectPK.setParent(company);

			subjectSubjectPK.setChild(user);

			SubjectSubject subjectSubject = new SubjectSubject();

			subjectSubject.setIdentifier(subjectSubjectPK);

			if (!company.getChilds().contains(subjectSubject)){

				subjectSubject.setLevel(isNewCompany ? Level.OWNER_PARTNER : Level.UNDEFINED);

				company.getChilds().add(subjectSubject);

				isNewSubjectSubject = true;				

			}

			if (isNewCompanyDevice || isNewSubjectSubject)

				companyDAO.update(company);

			session.getTransaction().commit();

			log.info(AccountEndPoint.class.getSimpleName() + 
					": Conta configurada com sucesso. " + (isNewUser?"Novo ":"") + "Usuário: " + 
					user.toString() +
					" vinculado à " + (isNewCompany?"Nova ":"") + "Empresa: " +
					company.toString() +
					" através do " + (isNewDevice?"Novo ":"") + "Dispositivo: " + 
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
							build()).
					build();

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

			log.error(AccountEndPoint.class.getSimpleName() + ": " + 
					e.getMessage(), 
					e.getCause());

			return Response.status(Response.Status.NOT_FOUND).
					entity(new ExceptionBean001(I18N.get(I18N.NOT_FOUND_ERROR))).
					build();

		} catch (Exception e) {

			e.printStackTrace();

			if (session.getTransaction().isActive())

				session.getTransaction().rollback();

			log.error(AccountEndPoint.class.getSimpleName() + ": " + 
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