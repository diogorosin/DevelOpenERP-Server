package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


@Embeddable
public class CompanyDeviceDatasetOrganizationPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="\"company\"", referencedColumnName="\"company\""),
		@JoinColumn(name="\"device\"", referencedColumnName="\"device\""),
		@JoinColumn(name="\"dataset\"", referencedColumnName="\"dataset\"")})
	private CompanyDeviceDataset companyDeviceDataset;

	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="\"organization\"", referencedColumnName="\"subject\"")})
	private Organization organization;

	public CompanyDeviceDataset getCompanyDeviceDataset() {

		return companyDeviceDataset;

	}

	public void setCompanyDeviceDataset(CompanyDeviceDataset companyDeviceDataset) {

		this.companyDeviceDataset = companyDeviceDataset;

	}

	public Organization getOrganization() {

		return organization;

	}

	public void setOrganization(Organization organization) {

		this.organization = organization;

	}

	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyDeviceDataset == null) ? 0 : companyDeviceDataset.hashCode());
		result = prime * result + ((organization == null) ? 0 : organization.hashCode());
		return result;

	}

	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyDeviceDatasetOrganizationPK other = (CompanyDeviceDatasetOrganizationPK) obj;
		if (companyDeviceDataset == null) {
			if (other.companyDeviceDataset != null)
				return false;
		} else if (!companyDeviceDataset.equals(other.companyDeviceDataset))
			return false;
		if (organization == null) {
			if (other.organization != null)
				return false;
		} else if (!organization.equals(other.organization))
			return false;
		return true;

	}

}