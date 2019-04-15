package br.com.developen.erp.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


@Embeddable
public class CompanyDeviceSalePK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="company", referencedColumnName="company"),
		@JoinColumn(name="device", referencedColumnName="device")})	
	private CompanyDevice companyDevice;

	@Column(name="\"sale\"", nullable=false)
	private Integer sale;

	public CompanyDevice getCompanyDevice() {
		
		return companyDevice;
		
	}

	public void setCompanyDevice(CompanyDevice companyDevice) {
		
		this.companyDevice = companyDevice;
		
	}

	public Integer getSale() {
		
		return sale;
		
	}

	public void setSale(Integer sale) {
		
		this.sale = sale;
		
	}

	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyDevice == null) ? 0 : companyDevice.hashCode());
		result = prime * result + ((sale == null) ? 0 : sale.hashCode());
		return result;

	}

	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyDeviceSalePK other = (CompanyDeviceSalePK) obj;
		if (companyDevice == null) {
			if (other.companyDevice != null)
				return false;
		} else if (!companyDevice.equals(other.companyDevice))
			return false;
		if (sale == null) {
			if (other.sale != null)
				return false;
		} else if (!sale.equals(other.sale))
			return false;
		return true;

	}	

}