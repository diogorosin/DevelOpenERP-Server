package br.com.pocketpos.server.orm;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="\"TariffProduct\"")
public class TariffProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TariffProductPK identifier;

	@NotNull
	@Column(name="\"active\"", nullable=false)
	private Boolean active;

	@NotNull
	@Column(name="\"price\"", nullable=false)
	private BigDecimal price;

	public TariffProductPK getIdentifier() {

		return identifier;

	}

	public void setIdentifier(TariffProductPK identifier) {

		this.identifier = identifier;

	}

	public Boolean getActive() {

		return active;

	}

	public void setActive(Boolean active) {

		this.active = active;

	}

	public BigDecimal getPrice() {

		return price;

	}

	public void setPrice(BigDecimal price) {

		this.price = price;

	}

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
		TariffProduct other = (TariffProduct) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}