package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


@Embeddable
public class CompanyDeviceDatasetProductProductPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="company", referencedColumnName="company"),
		@JoinColumn(name="device", referencedColumnName="device"),
		@JoinColumn(name="dataset", referencedColumnName="dataset"),
		@JoinColumn(name="product", referencedColumnName="product")})
	private CompanyDeviceDatasetProduct companyDeviceDatasetProduct;

	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="part", referencedColumnName="identifier")})
	private Product product;

	public CompanyDeviceDatasetProduct getCompanyDeviceDatasetProduct() {

		return companyDeviceDatasetProduct;

	}

	public void setCompanyDeviceDatasetProduct(CompanyDeviceDatasetProduct companyDeviceDatasetProduct) {

		this.companyDeviceDatasetProduct = companyDeviceDatasetProduct;

	}

	public Product getProduct() {

		return product;

	}

	public void setProduct(Product product) {

		this.product = product;

	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyDeviceDatasetProduct == null) ? 0 : companyDeviceDatasetProduct.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		CompanyDeviceDatasetProductProductPK other = (CompanyDeviceDatasetProductProductPK) obj;
		if (companyDeviceDatasetProduct == null) {
			if (other.companyDeviceDatasetProduct != null)
				return false;
		} else if (!companyDeviceDatasetProduct.equals(other.companyDeviceDatasetProduct))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;

	}

}