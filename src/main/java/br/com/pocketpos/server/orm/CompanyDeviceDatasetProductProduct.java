package br.com.pocketpos.server.orm;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="\"CompanyDeviceDatasetProductProduct\"")
public class CompanyDeviceDatasetProductProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CompanyDeviceDatasetProductProductPK identifier;
	
	@NotNull
	@Column(name="\"active\"", nullable=false)
	private Boolean active;

	@NotNull
	@Column(name="\"quantity\"", nullable=false)
	private BigDecimal quantity;

	public CompanyDeviceDatasetProductProductPK getIdentifier() {

		return identifier;

	}

	public void setIdentifier(CompanyDeviceDatasetProductProductPK identifier) {

		this.identifier = identifier;

	}

	public Boolean getActive() {

		return active;

	}

	public void setActive(Boolean active) {

		this.active = active;

	}

	public BigDecimal getQuantity() {

		return quantity;

	}

	public void setQuantity(BigDecimal quantity) {

		this.quantity = quantity;

	}

	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		return result;

	}

	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyDeviceDatasetProductProduct other = (CompanyDeviceDatasetProductProduct) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}