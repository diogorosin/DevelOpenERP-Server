package br.com.developen.erp.orm;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class CompanyUserPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	@JoinColumn(name="\"company\"", nullable=false)
	private Company company;

	@ManyToOne(optional=false)
	@JoinColumn(name="\"user\"", nullable=false)
	private User user;

	public Company getCompany() {
		
		return company;
		
	}

	public void setCompany(Company company) {
		
		this.company = company;
		
	}

	public User getUser() {
		
		return user;
		
	}

	public void setUser(User user) {
		
		this.user = user;
		
	}

	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
		
	}

	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyUserPK other = (CompanyUserPK) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
		
	}

}