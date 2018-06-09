package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="\"TabItem\"")
public class TabItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TabItemPK identifier;

	@ManyToOne(optional=false)
	@JoinColumn(name="\"product\"", referencedColumnName="identifier", nullable=false)
	private Product product;

	public TabItemPK getIdentifier() {

		return identifier;

	}

	public void setIdentifier(TabItemPK identifier) {

		this.identifier = identifier;

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
		TabItem other = (TabItem) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}