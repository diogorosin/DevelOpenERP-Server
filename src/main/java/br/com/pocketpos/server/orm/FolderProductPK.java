package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class FolderProductPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	@JoinColumn(name="\"folder\"", referencedColumnName="identifier", nullable=false)
	private Folder folder;

	@ManyToOne(optional=false)
	@JoinColumn(name="\"product\"", referencedColumnName="identifier", nullable=false)
	private Product product;

	public Folder getFolder() {

		return folder;

	}

	public void setFolder(Folder folder) {

		this.folder = folder;

	}

	public Product getProduct() {

		return product;

	}

	public void setProduct(Product part) {

		this.product = part;

	}

	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((folder == null) ? 0 : folder.hashCode());
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
		FolderProductPK other = (FolderProductPK) obj;
		if (folder == null) {
			if (other.folder != null)
				return false;
		} else if (!folder.equals(other.folder))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;

	}

}