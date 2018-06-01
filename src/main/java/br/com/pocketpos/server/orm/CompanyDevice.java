package br.com.pocketpos.server.orm;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="\"CompanyDevice\"")
@NamedQueries({
	@NamedQuery(
			name = CompanyDevice.FIND_BY_COMPANY,
			query = "FROM CompanyDevice CD WHERE CD.identifier.company = :company"
	),
	@NamedQuery(
			name = CompanyDevice.FIND_BY_DEVICE, 
			query = "FROM CompanyDevice CD where CD.identifier.device = :device"
	)
})
public class CompanyDevice implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_COMPANY = "CompanyDevice.findByCompany";

	public static final String FIND_BY_DEVICE = "CompanyDevice.findByDevice";

	@EmbeddedId
	private CompanyDevicePK identifier;

	@NotNull
	private Boolean active;

	@Size(min=0, max=20)
	private String alias;

	@OneToMany(
			fetch=FetchType.LAZY,
			mappedBy="identifier.companyDevice", 
			cascade={CascadeType.ALL}, 
			orphanRemoval=true)
	private List<CompanyDeviceDataset> datasets;

	public CompanyDevicePK getIdentifier() {

		return this.identifier;

	}

	public void setIdentifier(CompanyDevicePK identifier) {

		this.identifier = identifier;

	}

	public Boolean getActive() {

		return active;

	}

	public void setActive(Boolean active) {

		this.active = active;

	}

	public String getAlias() {

		return alias;

	}

	public void setAlias(String alias) {

		this.alias = alias;

	}

	public List<CompanyDeviceDataset> getDatasets() {

		return datasets;

	}

	public void setDatasets(List<CompanyDeviceDataset> datasets) {

		this.datasets = datasets;

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
		CompanyDevice other = (CompanyDevice) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}