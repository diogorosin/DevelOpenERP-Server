package br.com.developen.erp.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


@Embeddable
public class CompanyDeviceSaleItemPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="company", referencedColumnName="company"),
		@JoinColumn(name="device", referencedColumnName="device"),
		@JoinColumn(name="sale", referencedColumnName="sale")})	
	private CompanyDeviceSale companyDeviceSale;

	@Column(name="\"item\"", nullable=false)
	private Integer item;

	public CompanyDeviceSale getCompanyDeviceSale() {
		
		return companyDeviceSale;
		
	}

	public void setCompanyDeviceSale(CompanyDeviceSale companyDeviceSale) {
		
		this.companyDeviceSale = companyDeviceSale;
		
	}

	public Integer getItem() {
		
		return item;
		
	}

	public void setItem(Integer item) {
		
		this.item = item;
		
	}

	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyDeviceSale == null) ? 0 : companyDeviceSale.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		return result;
		
	}

	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyDeviceSaleItemPK other = (CompanyDeviceSaleItemPK) obj;
		if (companyDeviceSale == null) {
			if (other.companyDeviceSale != null)
				return false;
		} else if (!companyDeviceSale.equals(other.companyDeviceSale))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;

	}

}