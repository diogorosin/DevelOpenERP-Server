package br.com.pocketpos.server.orm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="\"CompanyDeviceDataset\"")
@NamedQueries({
	@NamedQuery(
			name = CompanyDeviceDataset.FIND_BY_COMPANY_DEVICE,
			query = "FROM CompanyDeviceDataset CDD "
					+ "WHERE CDD.identifier.companyDevice = :companyDevice "
					+ "ORDER BY CDD.created DESC"
			),
	@NamedQuery(
			name = CompanyDeviceDataset.GET_LATTER_IDENTIFIER_BY_COMPANY_DEVICE,
			query = "SELECT MAX(CDD.identifier.dataset) "
					+ "FROM CompanyDeviceDataset CDD "
					+ "WHERE CDD.identifier.companyDevice = :companyDevice"
			)
})
public class CompanyDeviceDataset implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_COMPANY_DEVICE = "CompanyDeviceDataset.findByCompanyDevice";

	public static final String GET_LATTER_IDENTIFIER_BY_COMPANY_DEVICE = "CompanyDeviceDataset.getLatterIdentifierByCompanyDevice";

	@EmbeddedId
	private CompanyDeviceDatasetPK identifier;

	@NotNull
	@Size(min=0, max=20)
	@Column(nullable = false)
	private String denomination;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	private Date downloaded;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	private Date imported;

	@OneToMany(
			fetch=FetchType.LAZY, 
			mappedBy="identifier.companyDeviceDataset", 
			cascade={CascadeType.ALL}, 
			orphanRemoval=true)
	private List<CompanyDeviceDatasetIndividual> individuals;

	@OneToMany(
			fetch=FetchType.LAZY, 
			mappedBy="identifier.companyDeviceDataset", 
			cascade={CascadeType.ALL}, 
			orphanRemoval=true)
	private List<CompanyDeviceDatasetOrganization> organizations;

	@OneToMany(
			fetch=FetchType.LAZY, 
			mappedBy="identifier.companyDeviceDataset", 
			cascade={CascadeType.ALL}, 
			orphanRemoval=true)
	private List<CompanyDeviceDatasetUser> users;

	@OneToMany(
			fetch=FetchType.LAZY, 
			mappedBy="identifier.companyDeviceDataset", 
			cascade={CascadeType.ALL}, 
			orphanRemoval=true)
	private List<CompanyDeviceDatasetProduct> products;

	@OneToMany(
			fetch=FetchType.LAZY, 
			mappedBy="identifier.companyDeviceDataset", 
			cascade={CascadeType.ALL}, 
			orphanRemoval=true)
	private List<CompanyDeviceDatasetTab> tabs;

	@OneToMany(
			fetch=FetchType.LAZY, 
			mappedBy="identifier.companyDeviceDataset", 
			cascade={CascadeType.ALL}, 
			orphanRemoval=true)
	private List<CompanyDeviceDatasetTariff> tariffs;

	public CompanyDeviceDatasetPK getIdentifier() {

		return identifier;

	}

	public void setIdentifier(CompanyDeviceDatasetPK identifier) {

		this.identifier = identifier;

	}

	public String getDenomination() {

		return denomination;

	}

	public void setDenomination(String denomination) {

		this.denomination = denomination;

	}

	public Date getCreated() {

		return created;

	}

	public void setCreated(Date created) {

		this.created = created;

	}

	public Date getDownloaded() {

		return downloaded;

	}

	public void setDownloaded(Date downloaded) {

		this.downloaded = downloaded;

	}

	public Date getImported() {

		return imported;

	}

	public void setImported(Date imported) {

		this.imported = imported;

	}

	public List<CompanyDeviceDatasetIndividual> getIndividuals() {

		return individuals;

	}

	public void setIndividual(List<CompanyDeviceDatasetIndividual> individuals) {

		this.individuals = individuals;

	}

	public List<CompanyDeviceDatasetOrganization> getOrganizations() {

		return organizations;

	}

	public void setOrganizations(List<CompanyDeviceDatasetOrganization> organizations) {

		this.organizations = organizations;

	}

	public List<CompanyDeviceDatasetUser> getUsers() {

		return users;

	}

	public void setUsers(List<CompanyDeviceDatasetUser> users) {

		this.users = users;

	}

	public List<CompanyDeviceDatasetProduct> getProducts() {

		return products;

	}

	public void setProducts(List<CompanyDeviceDatasetProduct> products) {

		this.products = products;

	}

	public List<CompanyDeviceDatasetTab> getTabs() {

		return tabs;

	}

	public void setTabs(List<CompanyDeviceDatasetTab> tabs) {

		this.tabs = tabs;

	}

	public List<CompanyDeviceDatasetTariff> getTariffs() {

		return tariffs;

	}

	public void setTariffs(List<CompanyDeviceDatasetTariff> tariffs) {

		this.tariffs = tariffs;

	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		return result;

	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyDeviceDataset other = (CompanyDeviceDataset) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}