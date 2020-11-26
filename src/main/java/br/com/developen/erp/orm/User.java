package br.com.developen.erp.orm;

import java.io.Serializable;
import java.security.Principal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;



@Entity
@Table(name="\"User\"")
@NamedQueries({
	@NamedQuery(
			name = User.FIND_BY_LOGIN,
			query = "FROM User U WHERE U.login = :login"
	)
})
public class User implements Serializable, Principal{

	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_LOGIN = "User.findByLogin";	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer identifier;

	@Column(name="\"active\"", nullable=false)
	private Boolean active;

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="address", nullable=false)
	private Address address;

	@Size(min=1, max=50)
	@Column(name="\"name\"", nullable=false)
	private String name;

	@Email
	@Size(min=1, max=254)
	@Column(name="\"login\"", unique=true)
	private String login;

	@Size(min=64, max=64)
	@Column(name="\"password\"", nullable=false)
	private String password;

	@Size(min=4, max=4)
	@Column(name="\"numericPassword\"", nullable=false)
	private String numericPassword;

	@ManyToOne(optional=true)
	@JoinColumn(name="\"lastLoggedInCompany\"", nullable=true)
	private Company lastLoggedInCompany;

	public Integer getIdentifier() {

		return this.identifier;

	}

	public void setIdentifier(Integer identifier) {

		this.identifier = identifier;

	}

	public Boolean getActive() {

		return this.active;

	}

	public void setActive(Boolean active) {

		this.active = active;

	}

	public Address getAddress() {
		
		return this.address;

	}

	public void setAddress(Address address) {

		this.address = address;

	}
	
	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}

	public String getLogin() {

		return this.login;

	}

	public void setLogin(String login) {

		this.login = login;

	}

	public String getPassword() {

		return this.password;

	}

	public void setPassword(String password) {

		this.password = password;

	}

	public String getNumericPassword() {

		return this.numericPassword;

	}

	public void setNumericPassword(String numericPassword) {

		this.numericPassword = numericPassword;

	}

	public Company getLastLoggedInCompany() {
		
		return lastLoggedInCompany;
		
	}

	public void setLastLoggedInCompany(Company lastLoggedInCompany) {

		this.lastLoggedInCompany = lastLoggedInCompany;

	}

	public String toString(){

		return "(" + getIdentifier() + ") " + getName();

	}

	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
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
		User other = (User) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;
		
	}

}