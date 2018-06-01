package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


@Embeddable
public class CompanyDeviceDatasetTariffPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="company", referencedColumnName="company"),
		@JoinColumn(name="device", referencedColumnName="device"),
		@JoinColumn(name="dataset", referencedColumnName="dataset")})
	private CompanyDeviceDataset companyDeviceDataset;

	@ManyToOne(optional=false)
	@JoinColumn(name="tariff", nullable=false)
	private Tariff tariff;

	public CompanyDeviceDataset getCompanyDeviceDataset() {

		return companyDeviceDataset;

	}

	public void setCompanyDeviceDataset(CompanyDeviceDataset companyDeviceDataset) {

		this.companyDeviceDataset = companyDeviceDataset;

	}

	public Tariff getTariff() {

		return tariff;

	}

	public void setTariff(Tariff tariff) {

		this.tariff = tariff;

	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyDeviceDataset == null) ? 0 : companyDeviceDataset.hashCode());
		result = prime * result + ((tariff == null) ? 0 : tariff.hashCode());
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
		CompanyDeviceDatasetTariffPK other = (CompanyDeviceDatasetTariffPK) obj;
		if (companyDeviceDataset == null) {
			if (other.companyDeviceDataset != null)
				return false;
		} else if (!companyDeviceDataset.equals(other.companyDeviceDataset))
			return false;
		if (tariff == null) {
			if (other.tariff != null)
				return false;
		} else if (!tariff.equals(other.tariff))
			return false;
		return true;

	}

}