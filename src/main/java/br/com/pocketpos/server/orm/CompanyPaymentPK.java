package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class CompanyPaymentPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="\"company\"", nullable=false)
	private Company company;

	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="\"payment\"", nullable=false)
	private Payment payment;

	public Company getCompany() {

		return company;

	}

	public void setCompany(Company company) {

		this.company = company;

	}

	public Payment getPayment() {

		return payment;

	}

	public void setPayment(Payment payment) {

		this.payment = payment;

	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((payment == null) ? 0 : payment.hashCode());
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
		CompanyPaymentPK other = (CompanyPaymentPK) obj;
		if (payment == null) {
			if (other.payment != null)
				return false;
		} else if (!payment.equals(other.payment))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		return true;

	}

}