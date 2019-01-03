package br.com.developen.erp.orm;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class CompanyPaymentMethodPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="\"company\"", nullable=false)
	private Company company;

	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="\"paymentMethod\"", nullable=false)
	private PaymentMethod paymentMethod;

	public Company getCompany() {

		return company;

	}

	public void setCompany(Company company) {

		this.company = company;

	}

	public PaymentMethod getPaymentMethod() {

		return paymentMethod;

	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {

		this.paymentMethod = paymentMethod;

	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((paymentMethod == null) ? 0 : paymentMethod.hashCode());
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
		CompanyPaymentMethodPK other = (CompanyPaymentMethodPK) obj;
		if (paymentMethod == null) {
			if (other.paymentMethod != null)
				return false;
		} else if (!paymentMethod.equals(other.paymentMethod))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		return true;

	}

}