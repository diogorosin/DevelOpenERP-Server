package br.com.developen.erp.orm;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="\"ProductProduct\"")
@NamedQueries({
	@NamedQuery(
			name = ProductProduct.FIND_ALL,
			query = "FROM ProductProduct"
	)
})
public class ProductProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "ProductProduct.findAll";
	
	@EmbeddedId
	private ProductProductPK identifier;

	@NotNull
	@Column(name="\"active\"", nullable=false)
	private Boolean active;

	@NotNull
	@Column(name="\"quantity\"", nullable=false)
	private BigDecimal quantity;

	public ProductProductPK getIdentifier() {

		return identifier;

	}

	public void setIdentifier(ProductProductPK identifier) {

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
		ProductProduct other = (ProductProduct) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}