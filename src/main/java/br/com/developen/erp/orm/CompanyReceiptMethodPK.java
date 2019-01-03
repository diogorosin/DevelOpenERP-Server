package br.com.developen.erp.orm;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class CompanyReceiptMethodPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="\"company\"", nullable=false)
	private Company company;

	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="\"receiptMethod\"", nullable=false)
	private ReceiptMethod receiptMethod;

	public Company getCompany() {

		return company;

	}

	public void setCompany(Company company) {

		this.company = company;

	}

	public ReceiptMethod getReceiptMethod() {

		return receiptMethod;

	}

	public void setReceiptMethod(ReceiptMethod receiptMethod) {

		this.receiptMethod = receiptMethod;

	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((receiptMethod == null) ? 0 : receiptMethod.hashCode());
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
		CompanyReceiptMethodPK other = (CompanyReceiptMethodPK) obj;
		if (receiptMethod == null) {
			if (other.receiptMethod != null)
				return false;
		} else if (!receiptMethod.equals(other.receiptMethod))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		return true;

	}

}