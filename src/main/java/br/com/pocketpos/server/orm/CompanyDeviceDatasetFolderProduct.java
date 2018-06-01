package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="\"CompanyDeviceDatasetFolderProduct\"")
public class CompanyDeviceDatasetFolderProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CompanyDeviceDatasetFolderProductPK identifier;
	
	@NotNull
	@Column(name="\"active\"", nullable=false)
	private Boolean active;

	@NotNull
	@Column(name="\"position\"", nullable=false)
	private Integer position;

	public CompanyDeviceDatasetFolderProductPK getIdentifier() {

		return identifier;

	}

	public void setIdentifier(CompanyDeviceDatasetFolderProductPK identifier) {

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
		CompanyDeviceDatasetFolderProduct other = (CompanyDeviceDatasetFolderProduct) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}