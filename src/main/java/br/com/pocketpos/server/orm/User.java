package br.com.pocketpos.server.orm;

import java.security.Principal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


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
	@Column(unique=true)
	private String login;

	@NotNull
	private String password;

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

	public String toString(){

		return "(" + getIdentifier() + ") " + getName();

	}

}