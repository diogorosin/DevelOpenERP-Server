package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.URL;


@Entity
@Table(name="\"Address\"")
@NamedQuery(name="Address.findAll", query="FROM Address A")
public class Address implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer identifier;

	private String complement;

	private String district;

	@Email
	private String email;

	private String number;

	private Long phone;

	@Column(name="\"playArea\"")
	private String playArea;
	
	@Column(name="\"postalCode\"")
	private Integer postalCode;

	@URL
	@Column(name="\"webSite\"")
	private String webSite;

	@ManyToOne
	@JoinColumn(name="city")
	private City city;

	public Integer getIdentifier() {

		return this.identifier;

	}

	public void setIdentifier(Integer identifier) {

		this.identifier = identifier;

	}

	public String getComplement() {

		return this.complement;

	}

	public void setComplement(String complement) {

		this.complement = complement;

	}

	public String getDistrict() {

		return this.district;

	}

	public void setDistrict(String district) {

		this.district = district;

	}

	public String getEmail() {

		return this.email;

	}

	public void setEmail(String email) {

		this.email = email;

	}

	public String getNumber() {

		return this.number;

	}

	public void setNumber(String number) {

		this.number = number;

	}

	public Long getPhone() {

		return this.phone;

	}

	public void setPhone(Long phone) {

		this.phone = phone;

	}

	public String getPlayArea() {

		return this.playArea;

	}

	public void setPlayArea(String playArea) {

		this.playArea = playArea;

	}

	public Integer getPostalCode() {

		return this.postalCode;

	}

	public void setPostalCode(Integer postalCode) {

		this.postalCode = postalCode;

	}

	public String getWebSite() {

		return this.webSite;

	}

	public void setWebSite(String webSite) {

		this.webSite = webSite;

	}

	public City getCity() {

		return this.city;

	}

	public void setCity(City city) {

		this.city = city;

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
		Address other = (Address) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}