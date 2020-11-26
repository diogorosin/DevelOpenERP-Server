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
			name = User.RETRIEVE_BY_LOGIN,
			query = "FROM User U WHERE U.login = :login"
	),
	@NamedQuery(
			name = User.FIND_ALL,
			query = "FROM User U"
	),
	@NamedQuery(
			name = User.COUNT_ALL,
			query = "SELECT COUNT(U) FROM User U"
			),
	@NamedQuery(
			name = User.FIND_BY_NAME,
			query = "FROM User U WHERE UNACCENT(LOWER(U.name)) LIKE UNACCENT(LOWER(:name))"
	),
	@NamedQuery(
			name = User.COUNT_BY_NAME,
			query = "SELECT COUNT(U) FROM User U WHERE UNACCENT(LOWER(U.name)) LIKE UNACCENT(LOWER(:name))"
			)
})
public class User implements Serializable, Principal{

	private static final long serialVersionUID = 1L;

	public static final String RETRIEVE_BY_LOGIN = "User.findByLogin";	

	public static final String FIND_ALL = "User.findAll";

	public static final String COUNT_ALL = "User.countAll";
	
	public static final String FIND_BY_NAME = "User.findByName";
	
	public static final String COUNT_BY_NAME = "User.countByName";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer identifier;

	@Column(name="\"active\"", nullable=false)
	private Boolean active;

	@Size(min=1, max=200)
	@Column(name="\"image\"", nullable=true)
	private String image;

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="address", nullable=false)
	private Address address;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="contact", nullable=false)
	private Contact contact;

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

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="\"company\"", nullable=false)
	private Company company;

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="level", nullable=false)
	private Level level;	

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

	public String getImage() {

		return image;

	}

	public void setImage(String image) {

		this.image = image;

	}

	public Address getAddress() {
		
		return this.address;

	}

	public void setAddress(Address address) {

		this.address = address;

	}

	public Contact getContact() {

		return contact;

	}

	public void setContact(Contact contact) {

		this.contact = contact;

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
		
		return numericPassword;

	}

	public void setNumericPassword(String numericPassword) {
		
		this.numericPassword = numericPassword;

	}	

	public Company getCompany() {

		return company;

	}

	public void setCompany(Company company) {
		
		this.company = company;
		
	}

	public Level getLevel() {
		
		return level;
		
	}

	public void setLevel(Level level) {
		
		this.level = level;
		
	}

	public String toString(){

		return "(" + getIdentifier() + ") " + getName();

	}

	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		return result;
		
	}

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