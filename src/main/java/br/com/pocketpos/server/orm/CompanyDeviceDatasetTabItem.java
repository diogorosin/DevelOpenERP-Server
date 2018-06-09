package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="\"CompanyDeviceDatasetTabItem\"")
public class CompanyDeviceDatasetTabItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CompanyDeviceDatasetTabItemPK identifier;

	@ManyToOne(optional=false)
	@JoinColumn(name="\"product\"", referencedColumnName="identifier", nullable=false)
	private Product product;	
	
	public CompanyDeviceDatasetTabItemPK getIdentifier() {

		return identifier;

	}

	public void setIdentifier(CompanyDeviceDatasetTabItemPK identifier) {

		this.identifier = identifier;

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
		CompanyDeviceDatasetTabItem other = (CompanyDeviceDatasetTabItem) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}