package br.com.developen.erp.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;


@Entity
@Table(name="\"Address\"")
public class Address implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer identifier;

	@Size(min=1, max=100)
	@Column(name="\"denomination\"", nullable=true)
	private String denomination;

	@Size(min=1, max=5)	
	@Column(name="\"number\"", nullable=true)	
	private String number;	

	@Size(min=1, max=10)	
	@Column(name="\"complement\"", nullable=true)	
	private String complement;

	@Size(min=1, max=100)	
	@Column(name="\"district\"", nullable=true)	
	private String district;

	@Column(name="\"postalCode\"", nullable=true)
	private Integer postalCode;

	@ManyToOne
	@JoinColumn(name="city", nullable=true)
	private City city;

	@Column(name="\"phone\"", nullable=true)	
	private Long phone;

	@Email
	@Size(min=1, max=254)
	@Column(name="\"email\"", nullable=true)	
	private String email;

	@URL
	@Size(min=1, max=254)
	@Column(name="\"webSite\"", nullable=true)
	private String webSite;

	public Integer getIdentifier() {

		return identifier;

	}

	public void setIdentifier(Integer identifier) {

		this.identifier = identifier;

	}

	public String getDenomination() {

		return denomination;

	}

	public void setDenomination(String denomination) {

		this.denomination = denomination;

	}

	public String getNumber() {

		return number;

	}

	public void setNumber(String number) {

		this.number = number;

	}

	public String getComplement() {

		return complement;

	}

	public void setComplement(String complement) {

		this.complement = complement;

	}

	public String getDistrict() {

		return district;

	}

	public void setDistrict(String district) {

		this.district = district;

	}

	public Integer getPostalCode() {

		return postalCode;

	}

	public void setPostalCode(Integer postalCode) {

		this.postalCode = postalCode;

	}

	public City getCity() {

		return city;

	}

	public void setCity(City city) {

		this.city = city;

	}

	public Long getPhone() {

		return phone;

	}

	public void setPhone(Long phone) {

		this.phone = phone;

	}

	public String getEmail() {

		return email;

	}

	public void setEmail(String email) {

		this.email = email;

	}

	public String getWebSite() {

		return webSite;

	}

	public void setWebSite(String webSite) {

		this.webSite = webSite;

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
		Address other = (Address) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}