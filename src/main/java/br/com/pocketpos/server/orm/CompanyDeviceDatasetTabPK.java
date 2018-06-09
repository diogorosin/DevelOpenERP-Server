package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


@Embeddable
public class CompanyDeviceDatasetTabPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="company", referencedColumnName="company"),
		@JoinColumn(name="device", referencedColumnName="device"),
		@JoinColumn(name="dataset", referencedColumnName="dataset")})
	private CompanyDeviceDataset companyDeviceDataset;

	@ManyToOne(optional=false)
	@JoinColumn(name="tab", nullable=false)
	private Tab tab;

	public CompanyDeviceDataset getCompanyDeviceDataset() {

		return companyDeviceDataset;

	}

	public void setCompanyDeviceDataset(CompanyDeviceDataset companyDeviceDataset) {

		this.companyDeviceDataset = companyDeviceDataset;

	}

	public Tab getTab() {

		return tab;

	}

	public void setTab(Tab tab) {

		this.tab = tab;

	}

	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyDeviceDataset == null) ? 0 : companyDeviceDataset.hashCode());
		result = prime * result + ((tab == null) ? 0 : tab.hashCode());
		return result;

	}

	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyDeviceDatasetTabPK other = (CompanyDeviceDatasetTabPK) obj;
		if (companyDeviceDataset == null) {
			if (other.companyDeviceDataset != null)
				return false;
		} else if (!companyDeviceDataset.equals(other.companyDeviceDataset))
			return false;
		if (tab == null) {
			if (other.tab != null)
				return false;
		} else if (!tab.equals(other.tab))
			return false;
		return true;

	}

}