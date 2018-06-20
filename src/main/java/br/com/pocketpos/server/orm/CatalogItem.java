package br.com.pocketpos.server.orm;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="\"CatalogItem\"")
public class CatalogItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CatalogItemPK identifier;

	@NotNull
	@Column(name="\"position\"", nullable=false)
	private Integer position;

	@NotNull
	@Column(name="\"code\"", nullable=false)
	private Integer code;

	@ManyToOne(optional=false)
	@JoinColumn(name="\"product\"", referencedColumnName="identifier", nullable=false)
	private Product product;

	@NotNull
	@Column(name="\"price\"", nullable=false)
	private BigDecimal price;

	public CatalogItemPK getIdentifier() {

		return identifier;

	}

	public void setIdentifier(CatalogItemPK identifier) {

		this.identifier = identifier;

	}

	public Integer getPosition() {

		return position;

	}

	public void setPosition(Integer position) {

		this.position = position;

	}

	public Integer getCode() {

		return code;

	}

	public void setCode(Integer code) {

		this.code = code;

	}

	public Product getProduct() {

		return product;

	}

	public void setProduct(Product product) {

		this.product = product;

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

	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatalogItem other = (CatalogItem) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}