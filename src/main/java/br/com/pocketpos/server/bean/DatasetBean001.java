package br.com.pocketpos.server.bean;

import java.util.ArrayList;
import java.util.List;

public class DatasetBean001 implements DatasetBean {

	private CompanyBean001 company;

	private List<DeviceBean001> devices;

	private List<OrganizationBean001> organizations;

	private List<IndividualBean001> individuals;

	private List<UserBean001> users;

	private List<MeasureUnitBean001> measureUnits;	

	private List<ProductBean001> products;

	private List<CatalogBean001> catalogs;

	public CompanyBean001 getCompany() {

		if (company == null)

			company = new CompanyBean001();

		return company;

	}

	public void setCompany(CompanyBean001 company) {

		this.company = company;

	}

	public List<DeviceBean001> getDevices() {

		if (devices==null)

			devices = new ArrayList<DeviceBean001>();

		return devices;

	}

	public void setDevices(List<DeviceBean001> devices) {

		this.devices = devices;

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

		if (this.measureUnits == null)

			measureUnits = new ArrayList<MeasureUnitBean001>();

		return measureUnits;

	}

	public void setMeasureUnits(List<MeasureUnitBean001> measureUnits) {

		this.measureUnits = measureUnits;

	}

	public List<ProductBean001> getProducts() {

		if (products == null)

			products = new ArrayList<ProductBean001>();

		return products;

	}

	public void setProducts(List<ProductBean001> products) {

		this.products = products;

	}

	public List<CatalogBean001> getCatalogs() {

		if (catalogs == null)

			catalogs = new ArrayList<CatalogBean001>();

		return catalogs;

	}

	public void setCatalogs(List<CatalogBean001> catalogs) {

		this.catalogs = catalogs;

	}

}