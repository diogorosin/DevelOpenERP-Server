package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="\"FolderProduct\"")
public class FolderProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FolderProductPK identifier;

	@NotNull
	@Column(name="\"active\"", nullable=false)
	private Boolean active;

	@NotNull
	@Column(name="\"position\"", nullable=false)
	private Integer position;

	public FolderProductPK getIdentifier() {

		return identifier;

	}

	public void setIdentifier(FolderProductPK identifier) {

		this.identifier = identifier;

	}

	public Boolean getActive() {

		return active;

	}

	public void setActive(Boolean active) {

		this.active = active;

	}

	public Integer getPosition() {

		return position;

	}

	public void setPosition(Integer position) {

		this.position = position;

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
		FolderProduct other = (FolderProduct) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}