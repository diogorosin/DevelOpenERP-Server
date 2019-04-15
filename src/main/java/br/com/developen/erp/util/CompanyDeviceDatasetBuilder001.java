package br.com.developen.erp.util;

import java.util.List;

import br.com.developen.erp.bean.CatalogBean001;
import br.com.developen.erp.bean.CompanyBean001;
import br.com.developen.erp.bean.CompanyDeviceDatasetBean001;
import br.com.developen.erp.bean.DeviceBean001;
import br.com.developen.erp.bean.IndividualBean001;
import br.com.developen.erp.bean.MeasureUnitBean001;
import br.com.developen.erp.bean.MeasureUnitMeasureUnitBean001;
import br.com.developen.erp.bean.MerchandiseBean001;
import br.com.developen.erp.bean.OrganizationBean001;
import br.com.developen.erp.bean.PaymentMethodBean001;
import br.com.developen.erp.bean.ProductBean001;
import br.com.developen.erp.bean.ProductProductBean001;
import br.com.developen.erp.bean.ProgenyBean001;
import br.com.developen.erp.bean.ReceiptMethodBean001;
import br.com.developen.erp.bean.SaleBean001;
import br.com.developen.erp.bean.SaleItemBean001;
import br.com.developen.erp.bean.ServiceBean001;
import br.com.developen.erp.bean.SubjectBean001;
import br.com.developen.erp.bean.UserBean001;
import br.com.developen.erp.orm.Catalog;
import br.com.developen.erp.orm.Company;
import br.com.developen.erp.orm.CompanyDeviceSale;
import br.com.developen.erp.orm.CompanyDeviceSaleItem;
import br.com.developen.erp.orm.CompanyPaymentMethod;
import br.com.developen.erp.orm.CompanyReceiptMethod;
import br.com.developen.erp.orm.Device;
import br.com.developen.erp.orm.Individual;
import br.com.developen.erp.orm.MeasureUnit;
import br.com.developen.erp.orm.MeasureUnitMeasureUnit;
import br.com.developen.erp.orm.Merchandise;
import br.com.developen.erp.orm.Organization;
import br.com.developen.erp.orm.Product;
import br.com.developen.erp.orm.ProductProduct;
import br.com.developen.erp.orm.Progeny;
import br.com.developen.erp.orm.Service;
import br.com.developen.erp.orm.SubjectSubject;
import br.com.developen.erp.orm.User;

public class CompanyDeviceDatasetBuilder001 implements CompanyDeviceDatasetBuilder {

	private CompanyDeviceDatasetBean001 companyDeviceDatasetBean;

	private CompanyDeviceDatasetBean001 getCompanyDeviceDatasetBean() {

		if (companyDeviceDatasetBean==null)

			companyDeviceDatasetBean = new CompanyDeviceDatasetBean001();

		return companyDeviceDatasetBean;

	}

	public CompanyDeviceDatasetBuilder withCompany(Company company) {

		if (company != null)

			populateCompany(getCompanyDeviceDatasetBean().getCompany(), company);

		return this;

	}

	public CompanyDeviceDatasetBuilder withDevice(Device device) {

		if (device != null)

			populateDevice(getCompanyDeviceDatasetBean().getDevice(), device);			

		return this;

	}

	public CompanyDeviceDatasetBuilder withAlias(String alias) {

		getCompanyDeviceDatasetBean().setAlias(alias);

		return this;

	}

	public CompanyDeviceDatasetBuilder withAllow(Boolean allow) {

		getCompanyDeviceDatasetBean().setAllow(allow);

		return this;

	}

	public CompanyDeviceDatasetBuilder withSubjects(List<SubjectSubject> subjects) {

		getCompanyDeviceDatasetBean().getUsers().clear();

		getCompanyDeviceDatasetBean().getIndividuals().clear();

		getCompanyDeviceDatasetBean().getOrganizations().clear();

		if (subjects != null){

			for (SubjectSubject subjectSubject: subjects) {

				if (subjectSubject.getIdentifier().getChild() instanceof User){

					UserBean001 userBean = new UserBean001();

					populateUser(userBean, subjectSubject);

					getCompanyDeviceDatasetBean().getUsers().add(userBean);

				} else {

					if (subjectSubject.getIdentifier().getChild() instanceof Organization){

						OrganizationBean001 organizationBean = new OrganizationBean001();

						populateOrganization(organizationBean, subjectSubject);

						getCompanyDeviceDatasetBean().getOrganizations().add(organizationBean);

					} else {

						if (subjectSubject.getIdentifier().getChild() instanceof Organization){

							IndividualBean001 individualBean = new IndividualBean001();

							populateIndividual(individualBean, subjectSubject);

							getCompanyDeviceDatasetBean().getIndividuals().add(individualBean);

						}					

					}					

				}

			}

		}		

		return this;

	}

	public CompanyDeviceDatasetBuilder withMeasureUnits(List<MeasureUnit> measureUnits) {

		getCompanyDeviceDatasetBean().getMeasureUnits().clear();

		getCompanyDeviceDatasetBean().getMeasureUnitMeasureUnits().clear();

		if (measureUnits != null) {

			for (MeasureUnit measureUnit : measureUnits) {

				MeasureUnitBean001 measureUnitBean = new MeasureUnitBean001();

				populateMeasureUnit(measureUnitBean, measureUnit);

				getCompanyDeviceDatasetBean().getMeasureUnits().add(measureUnitBean);

				List<MeasureUnitMeasureUnit> measureUnitMeasureUnits = measureUnit.getConversions(); 

				if (measureUnitMeasureUnits != null && !measureUnitMeasureUnits.isEmpty()) {

					for(MeasureUnitMeasureUnit measureUnitMeasureUnit : measureUnitMeasureUnits) {

						MeasureUnitMeasureUnitBean001 measureUnitMeasureUnitBean = new MeasureUnitMeasureUnitBean001();

						populateMeasureUnitMeasureUnit(measureUnitMeasureUnitBean, measureUnitMeasureUnit);

						getCompanyDeviceDatasetBean().getMeasureUnitMeasureUnits().add(measureUnitMeasureUnitBean);

					}

				}

			}

		}

		return this;

	}

	public CompanyDeviceDatasetBuilder withProgenies(List<Progeny> progenies) {

		getCompanyDeviceDatasetBean().getServices().clear();

		getCompanyDeviceDatasetBean().getProducts().clear();

		getCompanyDeviceDatasetBean().getProductProducts().clear();

		getCompanyDeviceDatasetBean().getMerchandises().clear();

		if (progenies != null) {

			for (Progeny progeny: progenies) {

				if (progeny instanceof Merchandise) {

					MerchandiseBean001 merchandiseBean = new MerchandiseBean001();

					populateMerchandise(merchandiseBean, (Merchandise) progeny);

					getCompanyDeviceDatasetBean().getMerchandises().add(merchandiseBean);

					List<ProductProduct> productProducts = ((Merchandise) progeny).getParts();

					if (productProducts != null && !productProducts.isEmpty()) {

						for (ProductProduct productProduct : productProducts) {

							ProductProductBean001 productProductBean = new ProductProductBean001();

							populateProductProduct(productProductBean, productProduct);

							getCompanyDeviceDatasetBean().getProductProducts().add(productProductBean);

						}

					}

				} else {

					if (progeny instanceof Product) {

						ProductBean001 productBean = new ProductBean001();

						populateProduct(productBean, (Product) progeny);

						getCompanyDeviceDatasetBean().getProducts().add(productBean);

						List<ProductProduct> productProducts = ((Product) progeny).getParts();

						if (productProducts != null && !productProducts.isEmpty()) {

							for (ProductProduct productProduct : productProducts) {

								ProductProductBean001 productProductBean = new ProductProductBean001();

								populateProductProduct(productProductBean, productProduct);

								getCompanyDeviceDatasetBean().getProductProducts().add(productProductBean);

							}

						}

					} else {

						if (progeny instanceof Service) {

							ServiceBean001 serviceBean = new ServiceBean001();

							populateService(serviceBean, (Service) progeny);

							getCompanyDeviceDatasetBean().getServices().add(serviceBean);

						}

					}

				}

			}

		} 

		return this;

	}

	public CompanyDeviceDatasetBuilder withCatalogs(List<Catalog> catalogs) {

		getCompanyDeviceDatasetBean().getCatalogs().clear();

		if (catalogs != null) {

			for (Catalog catalog : catalogs) {

				CatalogBean001 catalogBean = new CatalogBean001();

				populateCatalog(catalogBean, catalog);

				getCompanyDeviceDatasetBean().getCatalogs().add(catalogBean);

			}

		}

		return this;

	}

	public CompanyDeviceDatasetBuilder withReceiptMethods(List<CompanyReceiptMethod> receiptMethods) {

		getCompanyDeviceDatasetBean().getReceiptMethods().clear();

		if (receiptMethods != null) {

			for (CompanyReceiptMethod receiptMethod : receiptMethods) {

				ReceiptMethodBean001 receiptMethodBean = new ReceiptMethodBean001();

				populateReceiptMethod(receiptMethodBean, receiptMethod);

				getCompanyDeviceDatasetBean().getReceiptMethods().add(receiptMethodBean);

			}

		}

		return this;

	}

	public CompanyDeviceDatasetBuilder withPaymentMethods(List<CompanyPaymentMethod> paymentMethods) {

		getCompanyDeviceDatasetBean().getPaymentMethods().clear();

		if (paymentMethods != null) {

			for (CompanyPaymentMethod paymentMethod : paymentMethods) {

				PaymentMethodBean001 paymentMethodBean = new PaymentMethodBean001();

				populatePaymentMethod(paymentMethodBean, paymentMethod);

				getCompanyDeviceDatasetBean().getPaymentMethods().add(paymentMethodBean);

			}

		}

		return this;

	}

	public CompanyDeviceDatasetBuilder withSales(List<CompanyDeviceSale> sales) {

		getCompanyDeviceDatasetBean().getSales().clear();

		if (sales != null) {

			for (CompanyDeviceSale companyDeviceSale : sales) {

				SaleBean001 saleBean = new SaleBean001();

				populateSale(saleBean, companyDeviceSale);

				getCompanyDeviceDatasetBean().getSales().add(saleBean);

			}

		}

		return this;

	}

	private void populateSubject(SubjectBean001 subjectBean, SubjectSubject subject){

		subjectBean.setIdentifier(subject.getIdentifier().getChild().getIdentifier());

		subjectBean.setActive(subject.getIdentifier().getChild().getActive());

		subjectBean.setLevel(subject.getLevel().ordinal());

	}

	private void populateIndividual(IndividualBean001 individualBean, SubjectSubject subjectSubject){

		populateSubject(individualBean, subjectSubject);

		individualBean.setName(((Individual) subjectSubject.getIdentifier().getChild()).getName());

	}

	private void populateUser(UserBean001 userBean, SubjectSubject subjectSubject){

		populateIndividual(userBean, subjectSubject);

		userBean.setLogin(((User) subjectSubject.getIdentifier().getChild()).getLogin());

		userBean.setPassword(((User) subjectSubject.getIdentifier().getChild()).getPassword());

		userBean.setNumericPassword(((User) subjectSubject.getIdentifier().getChild()).getNumericPassword());

	}

	private void populateOrganization(OrganizationBean001 organizationBean, SubjectSubject subjectSubject){

		populateSubject(organizationBean, subjectSubject);

		organizationBean.setDenomination(((Organization) subjectSubject.getIdentifier().getChild()).getDenomination());

		organizationBean.setFancyName(((Organization) subjectSubject.getIdentifier().getChild()).getFancyName());

	}

	private void populateCompany(CompanyBean001 companyBean, Company company){

		companyBean.setIdentifier(company.getIdentifier());

		companyBean.setActive(company.getActive());

		companyBean.setDenomination(company.getDenomination());

		companyBean.setFancyName(company.getFancyName());

		companyBean.setCouponTitle(company.getCouponTitle());

		companyBean.setCouponSubtitle(company.getCouponSubtitle());

	}

	private void populateDevice(DeviceBean001 deviceBean, Device device){

		deviceBean.setIdentifier(device.getIdentifier());

		deviceBean.setActive(device.getActive());

		deviceBean.setSerialNumber(device.getSerialNumber());

		deviceBean.setManufacturer(device.getManufacturer());

	}

	private void populateMeasureUnit(MeasureUnitBean001 measureUnitBean, MeasureUnit measureUnit){

		measureUnitBean.setIdentifier(measureUnit.getIdentifier());

		measureUnitBean.setDenomination(measureUnit.getDenomination());

		measureUnitBean.setAcronym(measureUnit.getAcronym());

		measureUnitBean.setGroup(measureUnit.getGroup().ordinal());

	}

	private void populateMeasureUnitMeasureUnit(MeasureUnitMeasureUnitBean001 measureUnitMeasureUnitBean, 
			MeasureUnitMeasureUnit measureUnitMeasureUnit){

		measureUnitMeasureUnitBean.setFrom(measureUnitMeasureUnit.
				getIdentifier().
				getFrom().
				getIdentifier());

		measureUnitMeasureUnitBean.setTo(measureUnitMeasureUnit.
				getIdentifier().
				getTo().
				getIdentifier());

		measureUnitMeasureUnitBean.setFactor(measureUnitMeasureUnit.getFactor());

	}

	private void populateProgeny(ProgenyBean001 progenyBean, Progeny progeny){

		progenyBean.setIdentifier(progeny.getIdentifier());

		progenyBean.setActive(progeny.getActive());

		progenyBean.setDenomination(progeny.getDenomination());

	}

	private void populateService(ServiceBean001 serviceBean, Service service){

		populateProgeny(serviceBean, service);

		serviceBean.setCatalog(service.getCatalog().getIdentifier());

		serviceBean.setPosition(service.getPosition());

		serviceBean.setReference(service.getReference());

		serviceBean.setLabel(service.getLabel());

		serviceBean.setMeasureUnit(service.getMeasureUnit().getIdentifier());

		serviceBean.setPrice(service.getPrice());

	}

	private void populateProduct(ProductBean001 productBean, Product product){

		populateProgeny(productBean, product);

		productBean.setStockUnit(product.getStockUnit().getIdentifier());

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

	}

	private void populateProductProduct(ProductProductBean001 productProductBean, ProductProduct productProduct){

		productProductBean.setParent(productProduct.
				getIdentifier().
				getParent().
				getIdentifier());

		productProductBean.setChild(productProduct.
				getIdentifier().
				getChild().
				getIdentifier());

		productProductBean.setActive(productProduct.getActive());

		productProductBean.setQuantity(productProduct.getQuantity());

	}

	private void populateMerchandise(MerchandiseBean001 merchandiseBean, Merchandise merchandise){

		populateProduct(merchandiseBean, merchandise);

		merchandiseBean.setCatalog(merchandise.getCatalog().getIdentifier());

		merchandiseBean.setPosition(merchandise.getPosition());

		merchandiseBean.setReference(merchandise.getReference());

		merchandiseBean.setLabel(merchandise.getLabel());

		merchandiseBean.setPrice(merchandise.getPrice());

	}

	private void populateCatalog(CatalogBean001 catalogBean, Catalog catalog){

		catalogBean.setIdentifier(catalog.getIdentifier());

		catalogBean.setActive(catalog.getActive());

		catalogBean.setPosition(catalog.getPosition());

		catalogBean.setDenomination(catalog.getDenomination());

	}

	private void populateReceiptMethod(ReceiptMethodBean001 receiptMethodBean, CompanyReceiptMethod receiptMethod){

		receiptMethodBean.setIdentifier(receiptMethod.getIdentifier().getReceiptMethod().getIdentifier());

		receiptMethodBean.setDenomination(receiptMethod.getIdentifier().getReceiptMethod().getDenomination());


	}

	private void populatePaymentMethod(PaymentMethodBean001 paymentMethodBean, CompanyPaymentMethod paymentMethod){

		paymentMethodBean.setIdentifier(paymentMethod.getIdentifier().getPaymentMethod().getIdentifier());

		paymentMethodBean.setDenomination(paymentMethod.getIdentifier().getPaymentMethod().getDenomination());

	}

	private void populateSale(SaleBean001 saleBean, CompanyDeviceSale companyDeviceSale){

		saleBean.setIdentifier(companyDeviceSale.getIdentifier().getSale());

		saleBean.setNumber(companyDeviceSale.getNumber());

		saleBean.setDateTime(companyDeviceSale.getDateTime());

		saleBean.setStatus(companyDeviceSale.getStatus().name());

		saleBean.setUser(companyDeviceSale.getUser().getIdentifier());

		saleBean.setNote(companyDeviceSale.getNote());

		for(CompanyDeviceSaleItem companyDeviceSaleItem : companyDeviceSale.getItems()) {

			SaleItemBean001 saleItemBean = new SaleItemBean001();

			saleItemBean.setProgeny(companyDeviceSaleItem.getProgeny().getIdentifier());

			saleItemBean.setMeasureUnit(companyDeviceSaleItem.getMeasureUnit().getIdentifier());

			saleItemBean.setQuantity(companyDeviceSaleItem.getQuantity());

			saleItemBean.setPrice(companyDeviceSaleItem.getPrice());

			saleItemBean.setTotal(companyDeviceSaleItem.getTotal());

			saleBean.getItems().put(companyDeviceSaleItem.getIdentifier().getItem(), saleItemBean);

		}

	}

	public CompanyDeviceDatasetBean001 build() {

		return getCompanyDeviceDatasetBean();

	}

}