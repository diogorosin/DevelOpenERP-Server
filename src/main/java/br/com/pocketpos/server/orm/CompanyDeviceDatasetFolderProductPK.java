package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


@Embeddable
public class CompanyDeviceDatasetFolderProductPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="company", referencedColumnName="company"),
		@JoinColumn(name="device", referencedColumnName="device"),
		@JoinColumn(name="dataset", referencedColumnName="dataset"),
		@JoinColumn(name="folder", referencedColumnName="folder")})
	private CompanyDeviceDatasetFolder companyDeviceDatasetFolder;

	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="product", referencedColumnName="identifier")})
	private Product product;

	public CompanyDeviceDatasetFolder getCompanyDeviceDatasetFolder() {

		return companyDeviceDatasetFolder;

	}

	public void setCompanyDeviceDatasetFolder(CompanyDeviceDatasetFolder companyDeviceDatasetFolder) {

		this.companyDeviceDatasetFolder = companyDeviceDatasetFolder;

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
		result = prime * result + ((companyDeviceDatasetFolder == null) ? 0 : companyDeviceDatasetFolder.hashCode());
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
		CompanyDeviceDatasetFolderProductPK other = (CompanyDeviceDatasetFolderProductPK) obj;
		if (companyDeviceDatasetFolder == null) {
			if (other.companyDeviceDatasetFolder != null)
				return false;
		} else if (!companyDeviceDatasetFolder.equals(other.companyDeviceDatasetFolder))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;

	}

}