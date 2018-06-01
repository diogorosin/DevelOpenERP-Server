package br.com.pocketpos.server.orm;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="\"CompanyDeviceDatasetProduct\"")
public class CompanyDeviceDatasetProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CompanyDeviceDatasetProductPK identifier;
	
	@NotNull
	private Boolean active;

	@NotNull
	@Size(min=1,max=5)
	@Column(name="\"code\"", nullable=false)
	private String code;
	
	@NotNull
	@Size(min=1,max=100)
	@Column(name="\"longDenomination\"", nullable=false)
	private String longDenomination;

	@NotNull
	@Size(min=1,max=20)
	@Column(name="\"shortDenomination\"", nullable=false)
	private String shortDenomination;

	@OneToMany(
			fetch=FetchType.LAZY,
			mappedBy="identifier.companyDeviceDatasetProduct",
			cascade={CascadeType.ALL}, 
			orphanRemoval=true)
	private List<CompanyDeviceDatasetProductProduct> parts;

	public CompanyDeviceDatasetProductPK getIdentifier() {

		return identifier;

	}

	public void setIdentifier(CompanyDeviceDatasetProductPK identifier) {

		this.identifier = identifier;

	}

	public Boolean getActive() {

		return active;

	}

	public void setActive(Boolean active) {

		this.active = active;

	}	

	public String getCode() {

		return code;

	}

	public void setCode(String code) {

		this.code = code;

	}

	public String getLongDenomination() {

		return longDenomination;

	}

	public void setLongDenomination(String longDenomination) {

		this.longDenomination = longDenomination;

	}

	public String getShortDenomination() {

		return shortDenomination;

	}

	public void setShortDenomination(String shortDenomination) {

		this.shortDenomination = shortDenomination;

	}

	public List<CompanyDeviceDatasetProductProduct> getParts() {

		return parts;

	}

	public void setParts(List<CompanyDeviceDatasetProductProduct> parts) {

		this.parts = parts;

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
		CompanyDeviceDatasetProduct other = (CompanyDeviceDatasetProduct) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}