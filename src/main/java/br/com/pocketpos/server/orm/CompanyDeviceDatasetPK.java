package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


@Embeddable
public class CompanyDeviceDatasetPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="company", referencedColumnName="company"),
		@JoinColumn(name="device", referencedColumnName="device")})
	private CompanyDevice companyDevice;

	@Column(name="\"dataset\"", nullable=false)
	private Integer dataset;

	public CompanyDevice getCompanyDevice() {

		return companyDevice;

	}

	public void setCompanyDevice(CompanyDevice companyDevice) {

		this.companyDevice = companyDevice;

	}

	public Integer getDataset() {

		return dataset;

	}

	public void setDataset(Integer dataset) {

		this.dataset = dataset;

	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyDevice == null) ? 0 : companyDevice.hashCode());
		result = prime * result + ((dataset == null) ? 0 : dataset.hashCode());
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
		CompanyDeviceDatasetPK other = (CompanyDeviceDatasetPK) obj;
		if (companyDevice == null) {
			if (other.companyDevice != null)
				return false;
		} else if (!companyDevice.equals(other.companyDevice))
			return false;
		if (dataset == null) {
			if (other.dataset != null)
				return false;
		} else if (!dataset.equals(other.dataset))
			return false;
		return true;

	}

}