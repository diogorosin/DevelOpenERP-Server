package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="\"CompanyDeviceDatasetOrganization\"")
public class CompanyDeviceDatasetOrganization implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CompanyDeviceDatasetOrganizationPK identifier;

	@NotNull
	private Boolean active;

	@NotNull
	@Size(min=1, max=100)
	@Column(name="\"denomination\"")
	private String denomination;

	@NotNull
	@Size(min=1, max=50)
	@Column(name="\"fancyName\"")
	private String fancyName;

	@Enumerated(EnumType.ORDINAL)
	private Level level;

	public CompanyDeviceDatasetOrganizationPK getIdentifier() {

		return identifier;

	}

	public void setIdentifier(CompanyDeviceDatasetOrganizationPK identifier) {

		this.identifier = identifier;

	}

	public Boolean getActive() {

		return active;

	}

	public void setActive(Boolean active) {

		this.active = active;

	}

	public String getDenomination() {

		return denomination;

	}

	public void setDenomination(String denomination) {

		this.denomination = denomination;

	}

	public String getFancyName() {

		return fancyName;

	}

	public void setFancyName(String fancyName) {

		this.fancyName = fancyName;

	}

	public Level getLevel() {

		return level;

	}

	public void setLevel(Level level) {

		this.level = level;

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
		CompanyDeviceDatasetOrganization other = (CompanyDeviceDatasetOrganization) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}