package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="\"Device\"")
@NamedQueries({
	@NamedQuery(
			name = Device.FIND_BY_SERIAL_NUMBER,
			query = "FROM Device D WHERE D.serialNumber = :serialNumber"
	)
})
public class Device implements Serializable{

	public static final String FIND_BY_SERIAL_NUMBER="Device.findBySerialNumber";	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer identifier;

	@NotNull
	private Boolean active;

	@NotNull
	@Column(unique=true, name="\"serialNumber\"")
	@Size(min=16, max=16)
	private String serialNumber;

	@NotNull
	@Size(min=0, max=30)
	private String manufacturer;

	@NotNull
	@Size(min=0, max=20)
	private String model;

	public Integer getIdentifier() {

		return this.identifier;	

	}

	public void setIdentifier(Integer identifier) {

		this.identifier = identifier;

	}

	public Boolean getActive() {

		return active;

	}

	public void setActive(Boolean active) {

		this.active = active;

	}

	public String getSerialNumber() {

		return this.serialNumber;

	}

	public void setSerialNumber(String serialNumber) {

		this.serialNumber = serialNumber;

	}

	public String getManufacturer() {

		return manufacturer;

	}

	public void setManufacturer(String manufacturer) {

		this.manufacturer = manufacturer;

	}

	public String getModel() {

		return model;

	}

	public void setModel(String model) {

		this.model = model;

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
		Device other = (Device) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

	public String toString(){

		return "(" + getIdentifier() + ") " + getManufacturer() + "/" + getModel();

	}

}