package br.com.developen.erp.orm;

import java.security.Principal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name="\"User\"")
@PrimaryKeyJoinColumn(name="individual")
@NamedQueries({
	@NamedQuery(
			name = User.FIND_BY_LOGIN,
			query = "FROM User U WHERE U.login = :login"
	)
})
public class User extends Individual implements Principal{

	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_LOGIN = "User.findByLogin";	

	@Email
	@NotNull
	@Size(min=1, max=254)
	@Column(unique=true)
	private String login;

	@NotNull
	@Size(min=64, max=64)
	private String password;

	@NotNull
	@Size(min=4, max=4)
	@Column(name="\"numericPassword\"", nullable=false)
	private String numericPassword;

	@ManyToOne
	@JoinColumn(name="\"lastLoggedInCompany\"", nullable=true)
	private Company lastLoggedInCompany;

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

}