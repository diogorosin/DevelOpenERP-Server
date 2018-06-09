package br.com.pocketpos.server.orm;

import java.util.ArrayList;
import java.util.Date;


public class CompanyDeviceDatasetFacade {

	private CompanyDeviceDatasetDAO companyDeviceDatasetDAO;

	public CompanyDeviceDatasetFacade(CompanyDeviceDatasetDAO companyDeviceDatasetDAO){

		this.companyDeviceDatasetDAO = companyDeviceDatasetDAO;

	}

	public void buildDatasetsForAllCompanyDevices(Company company){

		for (CompanyDevice companyDevice : company.getDevices()) {

			CompanyDeviceDatasetPK companyDeviceDatasetPK = new CompanyDeviceDatasetPK();

			companyDeviceDatasetPK.setCompanyDevice(companyDevice);

			companyDeviceDatasetPK.setDataset(getLastDatasetOfCompanyDevice(companyDevice) + 1);

			CompanyDeviceDataset companyDeviceDataset = new CompanyDeviceDataset();

			companyDeviceDataset.setIdentifier(companyDeviceDatasetPK);

			companyDeviceDataset.setDenomination(

					companyDeviceDatasetPK.getCompanyDevice().getIdentifier().getCompany().getIdentifier() + "." +

					companyDeviceDatasetPK.getCompanyDevice().getIdentifier().getDevice().getIdentifier() + "." +

					companyDeviceDatasetPK.getDataset());

			companyDeviceDataset.setCreated(new Date());

			companyDeviceDataset.setUsers(new ArrayList<CompanyDeviceDatasetUser>());

			if (company.getChilds() != null){

				for (SubjectSubject subjectSubject: company.getChilds()) {

					if (subjectSubject.getIdentifier().getChild() instanceof User){

						User user = (User) subjectSubject.getIdentifier().getChild();

						CompanyDeviceDatasetUserPK companyDeviceDatasetUserPK = new CompanyDeviceDatasetUserPK();

						companyDeviceDatasetUserPK.setCompanyDeviceDataset(companyDeviceDataset);

						companyDeviceDatasetUserPK.setUser(user);				

						CompanyDeviceDatasetUser companyDeviceDatasetSubject = new CompanyDeviceDatasetUser();

						companyDeviceDatasetSubject.setIdentifier(companyDeviceDatasetUserPK);

						companyDeviceDatasetSubject.setActive(subjectSubject.getActive());

						companyDeviceDatasetSubject.setName(user.getName());

						companyDeviceDatasetSubject.setLogin(user.getLogin());

						companyDeviceDatasetSubject.setPassword(user.getPassword());

						companyDeviceDatasetSubject.setLevel(subjectSubject.getLevel());

						companyDeviceDataset.getUsers().add(companyDeviceDatasetSubject);

					} else {

						if (subjectSubject.getIdentifier().getChild() instanceof Individual){

							Individual individual = (Individual) subjectSubject.getIdentifier().getChild();

							CompanyDeviceDatasetIndividualPK companyDeviceDatasetIndividualPK = new CompanyDeviceDatasetIndividualPK();

							companyDeviceDatasetIndividualPK.setCompanyDeviceDataset(companyDeviceDataset);

							companyDeviceDatasetIndividualPK.setIndividual(individual);				

							CompanyDeviceDatasetIndividual companyDeviceDatasetIndividual = new CompanyDeviceDatasetIndividual();

							companyDeviceDatasetIndividual.setIdentifier(companyDeviceDatasetIndividualPK);

							companyDeviceDatasetIndividual.setActive(subjectSubject.getActive());

							companyDeviceDatasetIndividual.setName(individual.getName());

							companyDeviceDatasetIndividual.setLevel(subjectSubject.getLevel());

							companyDeviceDataset.getIndividuals().add(companyDeviceDatasetIndividual);

						} else {

							if (subjectSubject.getIdentifier().getChild() instanceof Organization){

								Organization organization = (Organization) subjectSubject.getIdentifier().getChild();

								CompanyDeviceDatasetOrganizationPK companyDeviceDatasetOrganizationPK = new CompanyDeviceDatasetOrganizationPK();

								companyDeviceDatasetOrganizationPK.setCompanyDeviceDataset(companyDeviceDataset);

								companyDeviceDatasetOrganizationPK.setOrganization(organization);				

								CompanyDeviceDatasetOrganization companyDeviceDatasetOrganization = new CompanyDeviceDatasetOrganization();

								companyDeviceDatasetOrganization.setIdentifier(companyDeviceDatasetOrganizationPK);

								companyDeviceDatasetOrganization.setActive(subjectSubject.getActive());

								companyDeviceDatasetOrganization.setDenomination(organization.getDenomination());

								companyDeviceDatasetOrganization.setFancyName(organization.getFancyName());

								companyDeviceDatasetOrganization.setLevel(subjectSubject.getLevel());

								companyDeviceDataset.getOrganizations().add(companyDeviceDatasetOrganization);

							}

						}

					}

				}

			}

			companyDeviceDataset.setProducts(new ArrayList<CompanyDeviceDatasetProduct>());

			if (company.getProducts() != null){

				for (Product product: company.getProducts()) {

					CompanyDeviceDatasetProductPK companyDeviceDatasetProductPK = new CompanyDeviceDatasetProductPK();

					companyDeviceDatasetProductPK.setCompanyDeviceDataset(companyDeviceDataset);

					companyDeviceDatasetProductPK.setProduct(product);				

					CompanyDeviceDatasetProduct companyDeviceDatasetProduct = new CompanyDeviceDatasetProduct();

					companyDeviceDatasetProduct.setIdentifier(companyDeviceDatasetProductPK);

					companyDeviceDatasetProduct.setActive(product.getActive());

					companyDeviceDatasetProduct.setCode(product.getCode());

					companyDeviceDatasetProduct.setLongDenomination(product.getLongDenomination());

					companyDeviceDatasetProduct.setShortDenomination(product.getShortDenomination());

					companyDeviceDatasetProduct.setParts(new ArrayList<CompanyDeviceDatasetProductProduct>());

					if (companyDeviceDatasetProductPK.getProduct().getParts() != null){

						for (ProductProduct productProduct : companyDeviceDatasetProductPK.getProduct().getParts()) {

							CompanyDeviceDatasetProductProductPK companyDeviceDatasetProductProductPK = new CompanyDeviceDatasetProductProductPK();

							companyDeviceDatasetProductProductPK.setCompanyDeviceDatasetProduct(companyDeviceDatasetProduct);

							companyDeviceDatasetProductProductPK.setProduct(productProduct.getIdentifier().getPart());

							CompanyDeviceDatasetProductProduct companyDeviceDatasetProductProduct = new CompanyDeviceDatasetProductProduct();

							companyDeviceDatasetProductProduct.setIdentifier(companyDeviceDatasetProductProductPK);

							companyDeviceDatasetProductProduct.setActive(productProduct.getActive());

							companyDeviceDatasetProductProduct.setQuantity(productProduct.getQuantity());

							companyDeviceDatasetProduct.getParts().add(companyDeviceDatasetProductProduct);

						}

					}

					companyDeviceDataset.getProducts().add(companyDeviceDatasetProduct);

				}			

			}

			companyDeviceDataset.setTabs(new ArrayList<CompanyDeviceDatasetTab>());

			if (company.getTabs() != null){

				for (Tab tab: company.getTabs()) {

					CompanyDeviceDatasetTabPK companyDeviceDatasetTabPK = new CompanyDeviceDatasetTabPK();

					companyDeviceDatasetTabPK.setCompanyDeviceDataset(companyDeviceDataset);

					companyDeviceDatasetTabPK.setTab(tab);

					CompanyDeviceDatasetTab companyDeviceDatasetTab = new CompanyDeviceDatasetTab();

					companyDeviceDatasetTab.setIdentifier(companyDeviceDatasetTabPK);

					companyDeviceDatasetTab.setPosition(tab.getPosition());

					companyDeviceDatasetTab.setDenomination(tab.getDenomination());

					companyDeviceDatasetTab.setItems(new ArrayList<CompanyDeviceDatasetTabItem>());

					if (companyDeviceDatasetTabPK.getTab().getItems() != null){

						for (TabItem tabItem : companyDeviceDatasetTabPK.getTab().getItems()) {

							CompanyDeviceDatasetTabItemPK companyDeviceDatasetTabItemPK = new CompanyDeviceDatasetTabItemPK();

							companyDeviceDatasetTabItemPK.setCompanyDeviceDatasetTab(companyDeviceDatasetTab);
							
							companyDeviceDatasetTabItemPK.setItem(tabItem.getIdentifier().getItem());

							CompanyDeviceDatasetTabItem companyDeviceDatasetTabItem = new CompanyDeviceDatasetTabItem();

							companyDeviceDatasetTabItem.setIdentifier(companyDeviceDatasetTabItemPK);

							companyDeviceDatasetTabItem.setProduct(tabItem.getProduct());

							companyDeviceDatasetTab.getItems().add(companyDeviceDatasetTabItem);

						}

					}

					companyDeviceDataset.getTabs().add(companyDeviceDatasetTab);

				}			

			}

			companyDeviceDataset.setTariffs(new ArrayList<CompanyDeviceDatasetTariff>());

			if (company.getTariffs() != null){

				for (Tariff tariff: company.getTariffs()) {

					CompanyDeviceDatasetTariffPK companyDeviceDatasetTariffPK = new CompanyDeviceDatasetTariffPK();

					companyDeviceDatasetTariffPK.setCompanyDeviceDataset(companyDeviceDataset);

					companyDeviceDatasetTariffPK.setTariff(tariff);				

					CompanyDeviceDatasetTariff companyDeviceDatasetTariff = new CompanyDeviceDatasetTariff();

					companyDeviceDatasetTariff.setIdentifier(companyDeviceDatasetTariffPK);

					companyDeviceDatasetTariff.setActive(tariff.getActive());

					companyDeviceDatasetTariff.setDenomination(tariff.getDenomination());

					companyDeviceDatasetTariff.setProducts(new ArrayList<CompanyDeviceDatasetTariffProduct>());

					if (companyDeviceDatasetTariffPK.getTariff().getProducts() != null){

						for (TariffProduct tariffProduct : companyDeviceDatasetTariffPK.getTariff().getProducts()) {

							CompanyDeviceDatasetTariffProductPK companyDeviceDatasetTariffProductPK = new CompanyDeviceDatasetTariffProductPK();

							companyDeviceDatasetTariffProductPK.setCompanyDeviceDatasetTariff(companyDeviceDatasetTariff);

							companyDeviceDatasetTariffProductPK.setProduct(tariffProduct.getIdentifier().getProduct());

							CompanyDeviceDatasetTariffProduct companyDeviceDatasetTariffProduct = new CompanyDeviceDatasetTariffProduct();

							companyDeviceDatasetTariffProduct.setIdentifier(companyDeviceDatasetTariffProductPK);

							companyDeviceDatasetTariffProduct.setActive(tariffProduct.getActive());

							companyDeviceDatasetTariffProduct.setPrice(tariffProduct.getPrice());

							companyDeviceDatasetTariff.getProducts().add(companyDeviceDatasetTariffProduct);

						}

					}

					companyDeviceDataset.getTariffs().add(companyDeviceDatasetTariff);

				}			

			}

			if (companyDevice.getDatasets()==null)

				companyDevice.setDatasets(new ArrayList<CompanyDeviceDataset>());

			companyDevice.getDatasets().add(companyDeviceDataset);

			this.companyDeviceDatasetDAO.create(companyDeviceDataset);

		}

	}

	private Integer getLastDatasetOfCompanyDevice(CompanyDevice companyDevice){

		return this.companyDeviceDatasetDAO.
				getLatterIdentifierByCompanyDevice(companyDevice);

	}

}