package br.com.developen.erp.bean;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserBean001{

	private Boolean active;

	private Map<Integer, AddressBean001> address;

	private String name;
	
	private String login;

	private String password;

	private String numericPassword;

	public Boolean getActive() {
		
		return active;
		
	}

	public void setActive(Boolean active) {
		
		this.active = active;
		
	}

	public Map<Integer, AddressBean001> getAddress() {
		
		if (address==null)
			
			address = new LinkedHashMap<Integer, AddressBean001>();
		
		return address;
		
	}

	public void setAddress(Map<Integer, AddressBean001> address) {
		
		this.address = address;
		
	}

	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}

	public String getLogin() {

		return login;

	}

	public void setLogin(String login) {

		this.login = login;

	}

	public String getPassword() {

		return password;

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

}