package br.com.pocketpos.server.bean;

import java.util.ArrayList;
import java.util.List;

public class DatasetBean001 {


	private Integer identifier;

	private String denomination;

	private List<OrganizationBean001> organizations;

	private List<IndividualBean001> individuals;

	private List<UserBean001> users;

	private List<ProductBean001> products;

	private List<TabBean001> tabs;

	private List<TariffBean001> tariffs;


	public Integer getIdentifier() {

		return identifier;

	}

	public void setIdentifier(Integer identifier) {

		this.identifier = identifier;

	}

	public String getDenomination() {

		return denomination;

	}

	public void setDenomination(String denomination) {

		this.denomination = denomination;

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

	public List<ProductBean001> getProducts() {

		if (products == null)

			products = new ArrayList<ProductBean001>();

		return products;

	}

	public void setProducts(List<ProductBean001> products) {

		this.products = products;

	}

	public List<TabBean001> getTabs() {

		if (tabs == null)

			tabs = new ArrayList<TabBean001>();

		return tabs;

	}

	public void setTabs(List<TabBean001> tabs) {

		this.tabs = tabs;

	}

	public List<TariffBean001> getTariffs() {

		if (tariffs == null)

			tariffs = new ArrayList<TariffBean001>();

		return tariffs;

	}

	public void setTariffs(List<TariffBean001> tariffs) {

		this.tariffs = tariffs;

	}

}