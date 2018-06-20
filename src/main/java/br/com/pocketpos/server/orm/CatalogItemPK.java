package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class CatalogItemPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	@JoinColumn(name="\"catalog\"", referencedColumnName="identifier", nullable=false)
	private Catalog catalog;

	@Column(name="\"item\"", nullable=false)
	private Integer item;

	public Catalog getCatalog() {

		return catalog;

	}

	public void setCatalog(Catalog catalog) {

		this.catalog = catalog;

	}

	public Integer getItem() {

		return item;

	}

	public void setItem(Integer item) {

		this.item = item;

	}

	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((catalog == null) ? 0 : catalog.hashCode());
		return result;

	}

	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatalogItemPK other = (CatalogItemPK) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (catalog == null) {
			if (other.catalog != null)
				return false;
		} else if (!catalog.equals(other.catalog))
			return false;
		return true;

	}	

}