package br.com.pocketpos.server.util;

import java.util.List;

import br.com.pocketpos.server.bean.CatalogBean001;
import br.com.pocketpos.server.bean.CatalogItemBean001;
import br.com.pocketpos.server.bean.CompanyBean001;
import br.com.pocketpos.server.bean.ConversionBean001;
import br.com.pocketpos.server.bean.DatasetBean001;
import br.com.pocketpos.server.bean.DeviceBean001;
import br.com.pocketpos.server.bean.IndividualBean001;
import br.com.pocketpos.server.bean.MeasureUnitBean001;
import br.com.pocketpos.server.bean.OrganizationBean001;
import br.com.pocketpos.server.bean.PartBean001;
import br.com.pocketpos.server.bean.ProductBean001;
import br.com.pocketpos.server.bean.UserBean001;
import br.com.pocketpos.server.orm.Catalog;
import br.com.pocketpos.server.orm.CatalogItem;
import br.com.pocketpos.server.orm.Company;
import br.com.pocketpos.server.orm.CompanyDevice;
import br.com.pocketpos.server.orm.Individual;
import br.com.pocketpos.server.orm.MeasureUnit;
import br.com.pocketpos.server.orm.MeasureUnitMeasureUnit;
import br.com.pocketpos.server.orm.Organization;
import br.com.pocketpos.server.orm.Product;
import br.com.pocketpos.server.orm.ProductProduct;
import br.com.pocketpos.server.orm.Progeny;
import br.com.pocketpos.server.orm.SubjectSubject;
import br.com.pocketpos.server.orm.User;

public class DatasetBuilder001 implements DatasetBuilder {

	private DatasetBean001 datasetBean;

	private DatasetBean001 getDatasetBean() {

		if (datasetBean==null)

			datasetBean = new DatasetBean001();

		return datasetBean;

	}

	public DatasetBuilder withCompany(Company company) {

		populateCompany(getDatasetBean().getCompany(), company);

		return this;

	}

	public DatasetBuilder withDevices(List<CompanyDevice> devices) {

		getDatasetBean().getDevices().clear();

		for (CompanyDevice companyDevice : devices) {

			DeviceBean001 deviceBean = new DeviceBean001();

			populateDevice(deviceBean, companyDevice);

			getDatasetBean().getDevices().add(deviceBean);

		}

		return this;

	}

	public DatasetBuilder withSubjects(List<SubjectSubject> subjects) {

		getDatasetBean().getUsers().clear();

		getDatasetBean().getIndividuals().clear();

		getDatasetBean().getOrganizations().clear();

		for (SubjectSubject subjectSubject: subjects) {

			if (subjectSubject.getIdentifier().getChild() instanceof User){

				UserBean001 userBean = new UserBean001();

				populateUser(userBean, subjectSubject);

				getDatasetBean().getUsers().add(userBean);

			} else {

				if (subjectSubject.getIdentifier().getChild() instanceof Organization){

					OrganizationBean001 organizationBean = new OrganizationBean001();

					populateOrganization(organizationBean, subjectSubject);

					getDatasetBean().getOrganizations().add(organizationBean);

				} else {

					if (subjectSubject.getIdentifier().getChild() instanceof Organization){

						IndividualBean001 individualBean = new IndividualBean001();

						populateIndividual(individualBean, subjectSubject);

						getDatasetBean().getIndividuals().add(individualBean);

					}					

				}					

			}

		}		

		return this;

	}

	public DatasetBuilder withMeasureUnits(List<MeasureUnit> measureUnits) {

		getDatasetBean().getMeasureUnits().clear();

		for (MeasureUnit measureUnit : measureUnits) {

			MeasureUnitBean001 measureUnitBean = new MeasureUnitBean001();

			populateMeasureUnit(measureUnitBean, measureUnit);

			getDatasetBean().getMeasureUnits().add(measureUnitBean);

		}

		return this;

	}

	public DatasetBuilder withProgenies(List<Progeny> progenies) {

		getDatasetBean().getProducts().clear();

		for (Progeny progeny: progenies) {

			if (progeny instanceof Product){

				ProductBean001 productBean = new ProductBean001();

				populateProduct(productBean, (Product) progeny);

				getDatasetBean().getProducts().add(productBean);

			}

		}

		return this;

	}

	public DatasetBuilder withCatalogs(List<Catalog> catalogs) {

		getDatasetBean().getCatalogs().clear();

		for (Catalog catalog : catalogs) {

			CatalogBean001 catalogBean = new CatalogBean001();

			populateCatalog(catalogBean, catalog);

			getDatasetBean().getCatalogs().add(catalogBean);

		}
		
		return this;

	}

	private void populateCompany(CompanyBean001 companyBean, Company company){

		companyBean.setIdentifier(company.getIdentifier());

		companyBean.setActive(company.getActive());

		companyBean.setDenomination(company.getDenomination());

		companyBean.setFancyName(company.getFancyName());

		companyBean.setCouponTitle(company.getCouponTitle());

		companyBean.setCouponSubtitle(company.getCouponSubtitle());

	}

	private void populateDevice(DeviceBean001 deviceBean, CompanyDevice device){

		deviceBean.setIdentifier(device.getIdentifier().getDevice().getIdentifier());

		deviceBean.setActive(device.getIdentifier().getDevice().getActive() && device.getAllow());

		deviceBean.setSerialNumber(device.getIdentifier().getDevice().getSerialNumber());

		deviceBean.setManufacturer(device.getIdentifier().getDevice().getManufacturer());

		deviceBean.setModel(device.getIdentifier().getDevice().getModel());

		deviceBean.setAlias(device.getAlias());

	}

	private void populateUser(UserBean001 userBean, SubjectSubject subjectSubject){

		userBean.setIdentifier(subjectSubject.getIdentifier().getChild().getIdentifier());

		userBean.setActive(subjectSubject.getIdentifier().getChild().getActive());

		userBean.setLevel(subjectSubject.getLevel().ordinal());

		userBean.setName(((User) subjectSubject.getIdentifier().getChild()).getName());

		userBean.setLogin(((User) subjectSubject.getIdentifier().getChild()).getLogin());

		userBean.setPassword(((User) subjectSubject.getIdentifier().getChild()).getPassword());

	}

	private void populateIndividual(IndividualBean001 individualBean, SubjectSubject subjectSubject){

		individualBean.setIdentifier(subjectSubject.getIdentifier().getChild().getIdentifier());

		individualBean.setActive(subjectSubject.getIdentifier().getChild().getActive());

		individualBean.setLevel(subjectSubject.getLevel().ordinal());

		individualBean.setName(((Individual) subjectSubject.getIdentifier().getChild()).getName());

	}

	private void populateOrganization(OrganizationBean001 organizationBean, SubjectSubject subjectSubject){

		organizationBean.setIdentifier(subjectSubject.getIdentifier().getChild().getIdentifier());

		organizationBean.setActive(subjectSubject.getIdentifier().getChild().getActive());

		organizationBean.setLevel(subjectSubject.getLevel().ordinal());

		organizationBean.setDenomination(((Organization) subjectSubject.getIdentifier().getChild()).getDenomination());

		organizationBean.setFancyName(((Organization) subjectSubject.getIdentifier().getChild()).getFancyName());

	}

	private void populateMeasureUnit(MeasureUnitBean001 measureUnitBean, MeasureUnit measureUnit){

		measureUnitBean.setIdentifier(measureUnit.getIdentifier());

		measureUnitBean.setDenomination(measureUnit.getDenomination());

		measureUnitBean.setAcronym(measureUnit.getAcronym());

		measureUnitBean.setGroup(measureUnit.getGroup().ordinal());

		if (measureUnit.getConversions() != null){

			for (MeasureUnitMeasureUnit measureUnitMeasureUnit : measureUnit.getConversions()) {

				ConversionBean001 conversionBean = new ConversionBean001();

				conversionBean.setFactor(measureUnitMeasureUnit.getFactor());

				measureUnitBean.getConversions().put(
						measureUnitMeasureUnit.
						getIdentifier().
						getTo().
						getIdentifier(), conversionBean);

			}

		}

	}

	private void populateProduct(ProductBean001 productBean, Product product){

		productBean.setIdentifier(product.getIdentifier());

		productBean.setActive(product.getActive());

		productBean.setDenomination(product.getDenomination());

		productBean.setWidthValue(product.getWidthValue());

		productBean.setWidthUnit(product.getWidthUnit() != null ? product.getWidthUnit().getIdentifier() : null);

		productBean.setHeightValue(product.getHeightValue());

		productBean.setHeightUnit(product.getHeightUnit() != null ? product.getHeightUnit().getIdentifier() : null);

		productBean.setLengthValue(product.getLengthValue());

		productBean.setLengthUnit(product.getLengthUnit() != null ? product.getLengthUnit().getIdentifier() : null);

		productBean.setContentValue(product.getContentValue());

		productBean.setContentUnit(product.getContentUnit() != null ? product.getContentUnit().getIdentifier() : null);

		productBean.setGrossWeightValue(product.getGrossWeightValue());

		productBean.setGrossWeightUnit(product.getGrossWeightUnit() != null ? product.getGrossWeightUnit().getIdentifier() : null);

		productBean.setNetWeightValue(product.getNetWeightValue());

		productBean.setNetWeightUnit(product.getNetWeightUnit() != null ? product.getNetWeightUnit().getIdentifier() : null);

		if (product.getParts() != null){

			for (ProductProduct productProduct : product.getParts()) {

				PartBean001 partBean = new PartBean001();

				partBean.setActive(productProduct.getActive());

				partBean.setQuantity(productProduct.getQuantity());

				productBean.getParts().put(
						productProduct.
						getIdentifier().
						getPart().
						getIdentifier(), partBean);

			}

		}

	}

	private void populateCatalog(CatalogBean001 catalogBean, Catalog catalog){

		catalogBean.setIdentifier(catalog.getIdentifier());

		catalogBean.setPosition(catalog.getPosition());

		catalogBean.setDenomination(catalog.getDenomination());

		if (catalog.getItems() != null){

			for (CatalogItem catalogItem : catalog.getItems()) {

				CatalogItemBean001 catalogItemBean = new CatalogItemBean001();

				catalogItemBean.setPosition(catalogItem.getPosition());

				catalogItemBean.setCode(catalogItem.getCode());

				catalogItemBean.setDenomination(catalogItem.getDenomination());

				catalogItemBean.setProduct(catalogItem.getProduct().getIdentifier());

				catalogItemBean.setMeasureUnit(catalogItem.getMeasureUnit().getIdentifier());

				catalogItemBean.setPrice(catalogItem.getPrice());

				catalogBean.getItems().put(catalogItem.
						getIdentifier().
						getItem(), catalogItemBean);

			}

		}

	}

	public DatasetBean001 build() {

		return getDatasetBean();

	}

}