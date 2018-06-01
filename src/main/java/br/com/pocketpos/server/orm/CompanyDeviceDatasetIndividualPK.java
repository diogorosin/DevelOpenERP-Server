package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


@Embeddable
public class CompanyDeviceDatasetIndividualPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="\"company\"", referencedColumnName="\"company\""),
		@JoinColumn(name="\"device\"", referencedColumnName="\"device\""),
		@JoinColumn(name="\"dataset\"", referencedColumnName="\"dataset\"")})
	private CompanyDeviceDataset companyDeviceDataset;

	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="\"individual\"", referencedColumnName="\"subject\"")})
	private Individual individual;

	public CompanyDeviceDataset getCompanyDeviceDataset() {

		return companyDeviceDataset;

	}

	public void setCompanyDeviceDataset(CompanyDeviceDataset companyDeviceDataset) {

		this.companyDeviceDataset = companyDeviceDataset;

	}

	public Individual getIndividual() {

		return individual;

	}

	public void setIndividual(Individual individual) {

		this.individual = individual;

	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyDeviceDataset == null) ? 0 : companyDeviceDataset.hashCode());
		result = prime * result + ((individual == null) ? 0 : individual.hashCode());
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
		CompanyDeviceDatasetIndividualPK other = (CompanyDeviceDatasetIndividualPK) obj;
		if (companyDeviceDataset == null) {
			if (other.companyDeviceDataset != null)
				return false;
		} else if (!companyDeviceDataset.equals(other.companyDeviceDataset))
			return false;
		if (individual == null) {
			if (other.individual != null)
				return false;
		} else if (!individual.equals(other.individual))
			return false;
		return true;

	}

}