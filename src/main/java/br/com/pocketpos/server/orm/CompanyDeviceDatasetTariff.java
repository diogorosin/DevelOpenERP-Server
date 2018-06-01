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
@Table(name="\"CompanyDeviceDatasetTariff\"")
public class CompanyDeviceDatasetTariff implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CompanyDeviceDatasetTariffPK identifier;

	@NotNull
	private Boolean active;

	@NotNull
	@Size(min=1,max=20)
	@Column(name="\"denomination\"", nullable=false)
	private String denomination;
	
	@OneToMany(
			fetch=FetchType.LAZY,
			mappedBy="identifier.companyDeviceDatasetTariff",
			cascade={CascadeType.ALL}, 
			orphanRemoval=true)
	private List<CompanyDeviceDatasetTariffProduct> products;

	public CompanyDeviceDatasetTariffPK getIdentifier() {

		return identifier;

	}

	public void setIdentifier(CompanyDeviceDatasetTariffPK identifier) {

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

	public List<CompanyDeviceDatasetTariffProduct> getProducts() {

		return products;

	}

	public void setProducts(List<CompanyDeviceDatasetTariffProduct> products) {

		this.products = products;

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
		CompanyDeviceDatasetTariff other = (CompanyDeviceDatasetTariff) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}