package br.com.developen.erp.bean;

import java.util.ArrayList;
import java.util.List;

public class CompanyDeviceDatasetBean001 implements DatasetBean {


	private CompanyBean001 company;

	private DeviceBean001 device;

	private Boolean allow;

	private String alias;

	private List<OrganizationBean001> organizations;

	private List<IndividualBean001> individuals;

	private List<UserBean001> users;

	private List<MeasureUnitBean001> measureUnits;

	private List<MeasureUnitMeasureUnitBean001> measureUnitMeasureUnits;	

	private List<ProductBean001> products;

	private List<ProductProductBean001> productProducts;

	private List<ServiceBean001> services;	
	
	private List<MerchandiseBean001> merchandises;

	private List<CatalogBean001> catalogs;

	private List<ReceiptMethodBean001> receiptMethods;

	private List<PaymentMethodBean001> paymentMethods;

	private List<SaleBean001> sales;


	public CompanyBean001 getCompany() {

		if (company == null)

			company = new CompanyBean001();

		return company;

	}

	public void setCompany(CompanyBean001 company) {

		this.company = company;

	}

	public DeviceBean001 getDevice() {

		if (device==null)

			device = new DeviceBean001();

		return device;

	}

	public void setDevice(DeviceBean001 device) {

		this.device = device;

	}
	

	public Boolean getAllow() {

		return allow;

	}

	public void setAllow(Boolean allow) {

		this.allow = allow;

	}

	public String getAlias() {

		return alias;

	}

	public void setAlias(String alias) {

		this.alias = alias;

	}
	
	public List<UserBean001> getUsers() {

		if (users == null)

			users = new ArrayList<UserBean001>();

		return users;

	}

	public void setUsers(List<UserBean001> users) {

		this.users = users;

	}

	public List<OrganizationBean001> getOrganizations() {

		if (organizations == null)

			organizations = new ArrayList<OrganizationBean001>();

		return organizations;

	}

	public void setOrganizations(List<OrganizationBean001> organizations) {

		this.organizations = organizations;

	}

	public List<IndividualBean001> getIndividuals() {

		if (individuals == null)

			individuals = new ArrayList<IndividualBean001>();

		return individuals;

	}

	public void setIndividuals(List<IndividualBean001> individuals) {

		this.individuals = individuals;

	}

	public List<MeasureUnitBean001> getMeasureUnits() {

		if (measureUnits == null)

			measureUnits = new ArrayList<MeasureUnitBean001>();

		return measureUnits;

	}

	public void setMeasureUnits(List<MeasureUnitBean001> measureUnits) {

		this.measureUnits = measureUnits;

	}

	public List<MeasureUnitMeasureUnitBean001> getMeasureUnitMeasureUnits() {

		if (measureUnitMeasureUnits == null)

			measureUnitMeasureUnits = new ArrayList<MeasureUnitMeasureUnitBean001>();

		return measureUnitMeasureUnits;

	}

	public void setMeasureUnitMeasureUnits(List<MeasureUnitMeasureUnitBean001> measureUnitMeasureUnits) {

		this.measureUnitMeasureUnits = measureUnitMeasureUnits;

	}
	
	public List<ProductBean001> getProducts() {

		if (products == null)

			products = new ArrayList<ProductBean001>();

		return products;

	}

	public void setProducts(List<ProductBean001> products) {

		this.products = products;

	}

	public List<ProductProductBean001> getProductProducts() {

		if (productProducts == null)

			productProducts = new ArrayList<ProductProductBean001>();

		return productProducts;

	}

	public void setProductProducts(List<ProductProductBean001> productProducts) {

		this.productProducts = productProducts;

	}

	public List<ServiceBean001> getServices() {

		if (services == null)

			services = new ArrayList<ServiceBean001>();

		return services;

	}

	public void setServices(List<ServiceBean001> services) {

		this.services = services;

	}

	public List<MerchandiseBean001> getMerchandises() {

		if (merchandises == null)

			merchandises = new ArrayList<MerchandiseBean001>();
		
		return merchandises;
		
	}

	public void setMerchandises(List<MerchandiseBean001> merchandises) {

		this.merchandises = merchandises;

	}
	
	public List<CatalogBean001> getCatalogs() {

		if (catalogs == null)

			catalogs = new ArrayList<CatalogBean001>();

		return catalogs;

	}

	public void setCatalogs(List<CatalogBean001> catalogs) {

		this.catalogs = catalogs;

	}

	public List<ReceiptMethodBean001> getReceiptMethods() {

		if (receiptMethods == null)

			receiptMethods = new ArrayList<ReceiptMethodBean001>();

		return receiptMethods;

	}

	public void setReceiptMethods(List<ReceiptMethodBean001> receiptMethods) {

		this.receiptMethods = receiptMethods;

	}
	
	public List<PaymentMethodBean001> getPaymentMethods() {

		if (paymentMethods == null)

			paymentMethods = new ArrayList<PaymentMethodBean001>();

		return paymentMethods;

	}

	public void setPaymentMethods(List<PaymentMethodBean001> paymentMethods) {

		this.paymentMethods = paymentMethods;

	}

	public List<SaleBean001> getSales() {

		if (sales == null)

			sales = new ArrayList<SaleBean001>();

		return sales;

	}

	public void setSales(List<SaleBean001> sales) {

		this.sales = sales;

	}

}