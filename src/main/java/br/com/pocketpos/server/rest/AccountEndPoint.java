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
import br.com.pocketpos.server.bean.MessageBean001;
import br.com.pocketpos.server.orm.Company;
import br.com.pocketpos.server.orm.CompanyDAO;
import br.com.pocketpos.server.orm.CompanyDevice;
import br.com.pocketpos.server.orm.CompanyDeviceDatasetDAO;
import br.com.pocketpos.server.orm.CompanyDeviceDatasetFacade;
import br.com.pocketpos.server.orm.CompanyDevicePK;
import br.com.pocketpos.server.orm.Device;
import br.com.pocketpos.server.orm.DeviceDAO;
import br.com.pocketpos.server.orm.Level;
import br.com.pocketpos.server.orm.SubjectSubject;
import br.com.pocketpos.server.orm.SubjectSubjectPK;
import br.com.pocketpos.server.orm.Tariff;
import br.com.pocketpos.server.orm.TariffDAO;
import br.com.pocketpos.server.orm.User;
import br.com.pocketpos.server.orm.UserDAO;
import br.com.pocketpos.server.util.DownloadBeanFactory;
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

				companyDAO.create(company);

				//CRIA A TARIFA PADRAO
				TariffDAO tariffDAO = new TariffDAO(session);

				Tariff tariff = new Tariff();

				tariff.setActive(true);

				tariff.setDenomination("Padrão");

				tariff.setOrganization(company);

				tariffDAO.create(tariff);

				//ATRIBUI A TARIFA A EMPRESA
				company.setTariffs(new ArrayList<Tariff>());

				company.getTariffs().add(tariff);

				company.setCurrentTariff(tariff);

				companyDAO.update(company);

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

				companyDevice.setActive(isNewCompany);

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

				subjectSubject.setLevel(isNewCompany ? Level.OWNER_PARTNER : Level.CUSTOMER_SUPPLIER);

				subjectSubject.setAllow(isNewCompany);

				company.getChilds().add(subjectSubject);

				isNewSubjectSubject = true;				

			}

			//DATASET
			//ATUALIZA SE HOUVE INCLUSAO DO DISPOSITIVO OU DO USUARIO
			//SE O DISPOSITIVO OU USUARIO ACABARAM DE SEREM VINCULADOS A EMPRESA
			//ENTAO CRIA-SE NOVOS DATASETS PARA SEREM ENVIADOS AOS DISPOSITIVOS.			
			if (isNewCompanyDevice || isNewSubjectSubject){

				companyDAO.update(company);

				new CompanyDeviceDatasetFacade(
						new CompanyDeviceDatasetDAO(session)).
				buildDatasetsForAllCompanyDevices(company);

			}

			session.getTransaction().commit();

			log.info(AccountEndPoint.class.getSimpleName() + 
					": Conta configurada com sucesso. " + (isNewUser?"Novo ":"") + "Usuário: " + 
					user.toString() +
					" vinculado à " + (isNewCompany?"Nova ":"") + "Empresa: " +
					company.toString() +
					" através do "+(isNewDevice?"Novo ":"")+"Dispositivo: " + 
					device.toString());

			return Response.status(Response.Status.OK).
					entity(
							DownloadBeanFactory.
							buildDownloadBean(
									new CompanyDeviceDatasetDAO(session).
									retrieveMostRecentDatasetByCompanyDevice(companyDevice),
									DownloadBeanFactory.VERSION_001)). 
					build();

		} catch (ConstraintViolationException e) {

			if (session.getTransaction().isActive())

				session.getTransaction().rollback();

			ExceptionBean001 exceptionBean = new ExceptionBean001();

			for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations())

				exceptionBean.getMessages().add(new MessageBean001(
						constraintViolation.getRootBeanClass().getSimpleName() + "." +
								constraintViolation.getPropertyPath().toString() + " " +
								constraintViolation.getMessage().toLowerCase()
						));

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