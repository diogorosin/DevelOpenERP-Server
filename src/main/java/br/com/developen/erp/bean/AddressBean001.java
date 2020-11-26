package br.com.developen.erp.bean;

import java.util.LinkedHashMap;
import java.util.Map;


public class AddressBean001 {

	private String denomination;

	private String number;	

	private String reference;

	private String district;

	private Integer postalCode;

	private Map<Integer, CityBean001> city;

	private Long phone;

	private String email;

	private String webSite;

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

	public String getReference() {

		return reference;

	}

	public void setReference(String reference) {

		this.reference = reference;

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

	public Map<Integer, CityBean001> getCity() {

		if (this.city == null)

			this.city = new LinkedHashMap<Integer, CityBean001>();

		return city;

	}

	public void setCity(Map<Integer, CityBean001> city) {

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

}