package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class CompanyDevicePK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="\"company\"", nullable=false)
	private Company company;

	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="\"device\"", nullable=false)
	private Device device;

	public Company getCompany() {

		return company;

	}

	public void setCompany(Company company) {

		this.company = company;

	}

	public Device getDevice() {

		return device;

	}

	public void setDevice(Device device) {

		this.device = device;

	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((device == null) ? 0 : device.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
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
		CompanyDevicePK other = (CompanyDevicePK) obj;
		if (device == null) {
			if (other.device != null)
				return false;
		} else if (!device.equals(other.device))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		return true;

	}

}