package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


@Embeddable
public class CompanyDeviceDatasetUserPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="\"company\"", referencedColumnName="\"company\""),
		@JoinColumn(name="\"device\"", referencedColumnName="\"device\""),
		@JoinColumn(name="\"dataset\"", referencedColumnName="\"dataset\"")})
	private CompanyDeviceDataset companyDeviceDataset;

	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="\"user\"", referencedColumnName="\"individual\"")})
	private User user;

	public CompanyDeviceDataset getCompanyDeviceDataset() {

		return companyDeviceDataset;

	}

	public void setCompanyDeviceDataset(CompanyDeviceDataset companyDeviceDataset) {

		this.companyDeviceDataset = companyDeviceDataset;

	}

	public User getUser() {

		return user;

	}

	public void setUser(User user) {

		this.user = user;

	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyDeviceDataset == null) ? 0 : companyDeviceDataset.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		CompanyDeviceDatasetUserPK other = (CompanyDeviceDatasetUserPK) obj;
		if (companyDeviceDataset == null) {
			if (other.companyDeviceDataset != null)
				return false;
		} else if (!companyDeviceDataset.equals(other.companyDeviceDataset))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;

	}

}