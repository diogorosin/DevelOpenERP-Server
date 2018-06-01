package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


@Embeddable
public class CompanyDeviceDatasetTariffProductPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="company", referencedColumnName="company"),
		@JoinColumn(name="device", referencedColumnName="device"),
		@JoinColumn(name="dataset", referencedColumnName="dataset"),
		@JoinColumn(name="tariff", referencedColumnName="tariff")})
	private CompanyDeviceDatasetTariff companyDeviceDatasetTariff;

	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="product", referencedColumnName="identifier")})
	private Product product;

	public CompanyDeviceDatasetTariff getCompanyDeviceDatasetTariff() {

		return companyDeviceDatasetTariff;

	}

	public void setCompanyDeviceDatasetTariff(CompanyDeviceDatasetTariff companyDeviceDatasetTariff) {

		this.companyDeviceDatasetTariff = companyDeviceDatasetTariff;

	}

	public Product getProduct() {

		return product;

	}

	public void setProduct(Product product) {

		this.product = product;

	}

	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyDeviceDatasetTariff == null) ? 0 : companyDeviceDatasetTariff.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;

	}

	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyDeviceDatasetTariffProductPK other = (CompanyDeviceDatasetTariffProductPK) obj;
		if (companyDeviceDatasetTariff == null) {
			if (other.companyDeviceDatasetTariff != null)
				return false;
		} else if (!companyDeviceDatasetTariff.equals(other.companyDeviceDatasetTariff))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;

	}

}