package br.com.developen.erp.orm;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class ProductProductPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	@JoinColumn(name="\"parent\"", referencedColumnName="progeny", nullable=false)
	private Product parent;

	@ManyToOne(optional=false)
	@JoinColumn(name="\"child\"", referencedColumnName="progeny", nullable=false)
	private Product child;

	public Product getParent() {

		return parent;

	}

	public void setParent(Product parent) {

		this.parent = parent;

	}

	public Product getChild() {

		return child;

	}

	public void setChild(Product child) {

		this.child = child;

	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((child == null) ? 0 : child.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
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
		ProductProductPK other = (ProductProductPK) obj;
		if (child == null) {
			if (other.child != null)
				return false;
		} else if (!child.equals(other.child))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		return true;

	}

}