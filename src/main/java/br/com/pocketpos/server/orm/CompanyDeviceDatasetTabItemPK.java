package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


@Embeddable
public class CompanyDeviceDatasetTabItemPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="company", referencedColumnName="company"),
		@JoinColumn(name="device", referencedColumnName="device"),
		@JoinColumn(name="dataset", referencedColumnName="dataset"),
		@JoinColumn(name="tab", referencedColumnName="tab")})
	private CompanyDeviceDatasetTab companyDeviceDatasetTab;

	@Column(name="\"item\"", nullable=false)
	private Integer item;

	public CompanyDeviceDatasetTab getCompanyDeviceDatasetTab() {

		return companyDeviceDatasetTab;

	}

	public void setCompanyDeviceDatasetTab(CompanyDeviceDatasetTab companyDeviceDatasetTab) {
		
		this.companyDeviceDatasetTab = companyDeviceDatasetTab;
		
	}

	public Integer getItem() {
		
		return item;
		
	}

	public void setItem(Integer item) {
		
		this.item = item;
		
	}

	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyDeviceDatasetTab == null) ? 0 : companyDeviceDatasetTab.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		return result;

	}

	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyDeviceDatasetTabItemPK other = (CompanyDeviceDatasetTabItemPK) obj;
		if (companyDeviceDatasetTab == null) {
			if (other.companyDeviceDatasetTab != null)
				return false;
		} else if (!companyDeviceDatasetTab.equals(other.companyDeviceDatasetTab))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;

	}	
	
}