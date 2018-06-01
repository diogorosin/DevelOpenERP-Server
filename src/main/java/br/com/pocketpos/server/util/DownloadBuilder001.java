package br.com.pocketpos.server.util;

import java.util.stream.IntStream;

import br.com.pocketpos.server.bean.CompanyBean001;
import br.com.pocketpos.server.bean.DatasetBean001;
import br.com.pocketpos.server.bean.DeviceBean001;
import br.com.pocketpos.server.bean.DocumentBean001;
import br.com.pocketpos.server.bean.DownloadBean;
import br.com.pocketpos.server.bean.DownloadBean001;
import br.com.pocketpos.server.bean.FolderBean001;
import br.com.pocketpos.server.bean.IndividualBean001;
import br.com.pocketpos.server.bean.OrganizationBean001;
import br.com.pocketpos.server.bean.PartBean001;
import br.com.pocketpos.server.bean.PriceBean001;
import br.com.pocketpos.server.bean.ProductBean001;
import br.com.pocketpos.server.bean.TariffBean001;
import br.com.pocketpos.server.bean.UserBean001;
import br.com.pocketpos.server.orm.Company;
import br.com.pocketpos.server.orm.CompanyDevice;
import br.com.pocketpos.server.orm.CompanyDeviceDataset;
import br.com.pocketpos.server.orm.CompanyDeviceDatasetFolder;
import br.com.pocketpos.server.orm.CompanyDeviceDatasetFolderProduct;
import br.com.pocketpos.server.orm.CompanyDeviceDatasetIndividual;
import br.com.pocketpos.server.orm.CompanyDeviceDatasetOrganization;
import br.com.pocketpos.server.orm.CompanyDeviceDatasetProduct;
import br.com.pocketpos.server.orm.CompanyDeviceDatasetProductProduct;
import br.com.pocketpos.server.orm.CompanyDeviceDatasetTariff;
import br.com.pocketpos.server.orm.CompanyDeviceDatasetTariffProduct;
import br.com.pocketpos.server.orm.CompanyDeviceDatasetUser;

public class DownloadBuilder001 extends DownloadBuilder {

	public DownloadBean build(int[] options, Company company) {

		CompanyBean001 companyBean = new CompanyBean001();

		populateCompany(companyBean, company);

		for (CompanyDevice companyDevice : company.getDevices()) {

			DeviceBean001 deviceBean = new DeviceBean001();

			populateDevice(deviceBean, companyDevice);

			for (CompanyDeviceDataset companyDeviceDataset : companyDevice.getDatasets()) {

				DatasetBean001 datasetBean = new DatasetBean001();

				populateDataset(datasetBean, companyDeviceDataset);

				if (IntStream.of(options).anyMatch(x -> x == DATASET_INDIVIDUALS)
						&& companyDeviceDataset.getIndividuals() != null){

					for (CompanyDeviceDatasetIndividual companydeviceDatasetIndividual : companyDeviceDataset.getIndividuals()){

						IndividualBean001 individualBean = new UserBean001();

						populateIndividual(individualBean, companydeviceDatasetIndividual);

						datasetBean.getIndividuals().add(individualBean);

					}

				}

				if (IntStream.of(options).anyMatch(x -> x == DATASET_ORGANIZATIONS)
						&& companyDeviceDataset.getOrganizations() != null){

					for (CompanyDeviceDatasetOrganization companydeviceDatasetOrganization : companyDeviceDataset.getOrganizations()){

						OrganizationBean001 organizationBean = new OrganizationBean001();

						populateOrganization(organizationBean, companydeviceDatasetOrganization);

						datasetBean.getOrganizations().add(organizationBean);

					}

				}

				if (IntStream.of(options).anyMatch(x -> x == DATASET_USERS)
						&& companyDeviceDataset.getUsers() != null){

					for (CompanyDeviceDatasetUser companydeviceDatasetUser : companyDeviceDataset.getUsers()){

						UserBean001 userBean = new UserBean001();

						populateUser(userBean, companydeviceDatasetUser);

						datasetBean.getUsers().add(userBean);

					}

				}

				if (IntStream.of(options).anyMatch(x -> x == DATASET_PRODUCTS)
						&& companyDeviceDataset.getProducts() != null){					

					for (CompanyDeviceDatasetProduct product : companyDeviceDataset.getProducts()){

						ProductBean001 productBean = new ProductBean001();

						populateProduct(productBean, product);

						datasetBean.getProducts().add(productBean);

					}

				}

				if (IntStream.of(options).anyMatch(x -> x == DATASET_FOLDERS)
						&& companyDeviceDataset.getFolders() != null){					

					for (CompanyDeviceDatasetFolder folder : companyDeviceDataset.getFolders()){

						FolderBean001 folderBean = new FolderBean001();

						populateFolder(folderBean, folder);

						datasetBean.getFolders().add(folderBean);

					}

				}

				if (IntStream.of(options).anyMatch(x -> x == DATASET_TARIFFS)
						&& companyDeviceDataset.getTariffs() != null){					

					for (CompanyDeviceDatasetTariff tariff : companyDeviceDataset.getTariffs()){

						TariffBean001 tariffBean = new TariffBean001();

						populateTariff(tariffBean, tariff);

						datasetBean.getTariffs().add(tariffBean);

					}

				}

				deviceBean.getDatasets().add(datasetBean);

			}

			companyBean.getDevices().add(deviceBean);

		}

		DownloadBean001 downloadBean = new DownloadBean001();

		downloadBean.setCompany(companyBean);

		return downloadBean;

	}

	public DownloadBean build(int[] options, CompanyDevice companyDevice) {

		CompanyBean001 companyBean = new CompanyBean001();

		populateCompany(companyBean, companyDevice.getIdentifier().getCompany());

		DeviceBean001 deviceBean = new DeviceBean001();

		populateDevice(deviceBean, companyDevice);

		for (CompanyDeviceDataset companyDeviceDataset : companyDevice.getDatasets()) {

			DatasetBean001 datasetBean = new DatasetBean001();

			populateDataset(datasetBean, companyDeviceDataset);

			if (IntStream.of(options).anyMatch(x -> x == DATASET_INDIVIDUALS)
					&& companyDeviceDataset.getIndividuals() != null){

				for (CompanyDeviceDatasetIndividual companydeviceDatasetIndividual : companyDeviceDataset.getIndividuals()){

					IndividualBean001 individualBean = new UserBean001();

					populateIndividual(individualBean, companydeviceDatasetIndividual);

					datasetBean.getIndividuals().add(individualBean);

				}

			}

			if (IntStream.of(options).anyMatch(x -> x == DATASET_ORGANIZATIONS)
					&& companyDeviceDataset.getOrganizations() != null){

				for (CompanyDeviceDatasetOrganization companydeviceDatasetOrganization : companyDeviceDataset.getOrganizations()){

					OrganizationBean001 organizationBean = new OrganizationBean001();

					populateOrganization(organizationBean, companydeviceDatasetOrganization);

					datasetBean.getOrganizations().add(organizationBean);

				}

			}

			if (IntStream.of(options).anyMatch(x -> x == DATASET_USERS)
					&& companyDeviceDataset.getUsers() != null){

				for (CompanyDeviceDatasetUser companydeviceDatasetUser : companyDeviceDataset.getUsers()){

					UserBean001 userBean = new UserBean001();

					populateUser(userBean, companydeviceDatasetUser);

					datasetBean.getUsers().add(userBean);

				}

			}

			if (IntStream.of(options).anyMatch(x -> x == DATASET_PRODUCTS) 
					&& companyDeviceDataset.getProducts() != null){

				for (CompanyDeviceDatasetProduct product : companyDeviceDataset.getProducts()){

					ProductBean001 productBean = new ProductBean001();

					populateProduct(productBean, product);

					datasetBean.getProducts().add(productBean);

				}

			}

			if (IntStream.of(options).anyMatch(x -> x == DATASET_FOLDERS)
					&& companyDeviceDataset.getFolders() != null){					

				for (CompanyDeviceDatasetFolder folder : companyDeviceDataset.getFolders()){

					FolderBean001 folderBean = new FolderBean001();

					populateFolder(folderBean, folder);

					datasetBean.getFolders().add(folderBean);

				}

			}

			if (IntStream.of(options).anyMatch(x -> x == DATASET_TARIFFS) 
					&& companyDeviceDataset.getTariffs() != null){					

				for (CompanyDeviceDatasetTariff tariff : companyDeviceDataset.getTariffs()){

					TariffBean001 tariffBean = new TariffBean001();

					populateTariff(tariffBean, tariff);

					datasetBean.getTariffs().add(tariffBean);

				}

			}

			deviceBean.getDatasets().add(datasetBean);

		}

		companyBean.getDevices().add(deviceBean);

		DownloadBean001 downloadBean = new DownloadBean001();

		downloadBean.setCompany(companyBean);

		return downloadBean;

	}

	public DownloadBean build(int[] options, CompanyDeviceDataset companyDeviceDataset) {

		CompanyBean001 companyBean = new CompanyBean001();

		populateCompany(companyBean, companyDeviceDataset.
				getIdentifier().
				getCompanyDevice().
				getIdentifier().
				getCompany());

		DeviceBean001 deviceBean = new DeviceBean001();

		populateDevice(deviceBean, companyDeviceDataset.
				getIdentifier().
				getCompanyDevice());

		DatasetBean001 datasetBean = new DatasetBean001();

		populateDataset(datasetBean, companyDeviceDataset);

		if (IntStream.of(options).anyMatch(x -> x == DATASET_INDIVIDUALS) 
				&& companyDeviceDataset.getIndividuals() != null){

			for (CompanyDeviceDatasetIndividual companydeviceDatasetIndividual : companyDeviceDataset.getIndividuals()){

				IndividualBean001 individualBean = new UserBean001();

				populateIndividual(individualBean, companydeviceDatasetIndividual);

				datasetBean.getIndividuals().add(individualBean);

			}

		}

		if (IntStream.of(options).anyMatch(x -> x == DATASET_ORGANIZATIONS)
				&& companyDeviceDataset.getOrganizations() != null){

			for (CompanyDeviceDatasetOrganization companydeviceDatasetOrganization : companyDeviceDataset.getOrganizations()){

				OrganizationBean001 organizationBean = new OrganizationBean001();

				populateOrganization(organizationBean, companydeviceDatasetOrganization);

				datasetBean.getOrganizations().add(organizationBean);

			}

		}

		if (IntStream.of(options).anyMatch(x -> x == DATASET_USERS)
				&& companyDeviceDataset.getUsers() != null){

			for (CompanyDeviceDatasetUser companydeviceDatasetUser : companyDeviceDataset.getUsers()){

				UserBean001 userBean = new UserBean001();

				populateUser(userBean, companydeviceDatasetUser);

				datasetBean.getUsers().add(userBean);

			}

		}

		if (IntStream.of(options).anyMatch(x -> x == DATASET_PRODUCTS) 
				&& companyDeviceDataset.getProducts() != null){

			for (CompanyDeviceDatasetProduct product : companyDeviceDataset.getProducts()){

				ProductBean001 productBean = new ProductBean001();

				populateProduct(productBean, product);

				datasetBean.getProducts().add(productBean);

			}

		}

		if (IntStream.of(options).anyMatch(x -> x == DATASET_FOLDERS)
				&& companyDeviceDataset.getFolders() != null){					

			for (CompanyDeviceDatasetFolder folder : companyDeviceDataset.getFolders()){

				FolderBean001 folderBean = new FolderBean001();

				populateFolder(folderBean, folder);

				datasetBean.getFolders().add(folderBean);

			}

		}

		if (IntStream.of(options).anyMatch(x -> x == DATASET_TARIFFS) 
				&& companyDeviceDataset.getTariffs() != null){					

			for (CompanyDeviceDatasetTariff tariff : companyDeviceDataset.getTariffs()){

				TariffBean001 tariffBean = new TariffBean001();

				populateTariff(tariffBean, tariff);

				datasetBean.getTariffs().add(tariffBean);

			}

		}

		deviceBean.getDatasets().add(datasetBean);

		companyBean.getDevices().add(deviceBean);

		DownloadBean001 downloadBean = new DownloadBean001();

		downloadBean.setCompany(companyBean);

		return downloadBean;

	}

	private void populateCompany(CompanyBean001 companyBean, Company company){

		companyBean.setIdentifier(company.getIdentifier());

		companyBean.setActive(company.getActive());

		companyBean.setDenomination(company.getDenomination());

		companyBean.setFancyName(company.getFancyName());

		companyBean.setCurrentTariff(company.getCurrentTariff().getIdentifier());
		
		companyBean.setCouponTitle(company.getCouponTitle());

		companyBean.setCouponSubtitle(company.getCouponSubtitle());

	}

	private void populateDevice(DeviceBean001 deviceBean, CompanyDevice device){

		deviceBean.setIdentifier(device.getIdentifier().getDevice().getIdentifier());

		deviceBean.setActive(device.getIdentifier().getDevice().getActive() && device.getActive());

		deviceBean.setSerialNumber(device.getIdentifier().getDevice().getSerialNumber());

		deviceBean.setManufacturer(device.getIdentifier().getDevice().getManufacturer());

		deviceBean.setModel(device.getIdentifier().getDevice().getModel());

		deviceBean.setAlias(device.getAlias());

	}

	private void populateDataset(DatasetBean001 datasetBean, CompanyDeviceDataset dataset){

		datasetBean.setIdentifier(dataset.getIdentifier().getDataset());

		datasetBean.setDenomination(dataset.getDenomination());

	}

	private void populateIndividual(IndividualBean001 individualBean, CompanyDeviceDatasetIndividual individual){

		individualBean.setIdentifier(individual.getIdentifier().getIndividual().getIdentifier());

		individualBean.setActive(individual.getIdentifier().getIndividual().getActive() && individual.getActive());

		individualBean.setLevel(individual.getLevel().ordinal());

		individualBean.setName(individual.getName());		

	}

	private void populateOrganization(OrganizationBean001 organizationBean, CompanyDeviceDatasetOrganization organization){

		organizationBean.setIdentifier(organization.getIdentifier().getOrganization().getIdentifier());

		organizationBean.setActive(organization.getIdentifier().getOrganization().getActive() && organization.getActive());

		organizationBean.setLevel(organization.getLevel().ordinal());

		organizationBean.setDenomination(organization.getDenomination());

		organizationBean.setFancyName(organization.getFancyName());

	}

	private void populateUser(UserBean001 userBean, CompanyDeviceDatasetUser user){

		userBean.setIdentifier(user.getIdentifier().getUser().getIdentifier());

		userBean.setActive(user.getIdentifier().getUser().getActive() && user.getActive());

		userBean.setLevel(user.getLevel().ordinal());

		userBean.setName(user.getName());

		userBean.setLogin(user.getLogin());

		userBean.setPassword(user.getPassword());

	}

	private void populateProduct(ProductBean001 productBean, CompanyDeviceDatasetProduct product){

		productBean.setIdentifier(product.getIdentifier().getProduct().getIdentifier());

		productBean.setActive(product.getActive());

		productBean.setCode(product.getCode());

		productBean.setLongDenomination(product.getLongDenomination());

		productBean.setShortDenomination(product.getShortDenomination());

		if (product.getParts() != null){

			for (CompanyDeviceDatasetProductProduct part : product.getParts()) {

				PartBean001 partBean = new PartBean001();

				partBean.setActive(part.getActive());

				partBean.setQuantity(part.getQuantity());

				productBean.getParts().put(part.
						getIdentifier().
						getProduct().
						getIdentifier(), partBean);

			}

		}

	}

	private void populateFolder(FolderBean001 folderBean, CompanyDeviceDatasetFolder folder){

		folderBean.setIdentifier(folder.getIdentifier().getFolder().getIdentifier());

		folderBean.setActive(folder.getActive());

		folderBean.setPosition(folder.getPosition());

		folderBean.setDenomination(folder.getDenomination());

		if (folder.getProducts() != null){

			for (CompanyDeviceDatasetFolderProduct folderProduct : folder.getProducts()) {

				DocumentBean001 documentBean = new DocumentBean001();

				documentBean.setActive(folderProduct.getActive());

				documentBean.setPosition(folderProduct.getPosition());

				folderBean.getDocuments().put(folderProduct.
						getIdentifier().
						getProduct().
						getIdentifier(), documentBean);

			}

		}

	}

	private void populateTariff(TariffBean001 tariffBean, CompanyDeviceDatasetTariff tariff){

		tariffBean.setIdentifier(tariff.getIdentifier().getTariff().getIdentifier());

		tariffBean.setActive(tariff.getActive());

		tariffBean.setDenomination(tariff.getDenomination());

		if (tariff.getProducts() != null){

			for (CompanyDeviceDatasetTariffProduct tariffProduct : tariff.getProducts()) {

				PriceBean001 priceBean = new PriceBean001();

				priceBean.setActive(tariffProduct.getActive());

				priceBean.setPrice(tariffProduct.getPrice());

				tariffBean.getPrices().put(tariffProduct.
						getIdentifier().
						getProduct().
						getIdentifier(), priceBean);

			}

		}

	}

}