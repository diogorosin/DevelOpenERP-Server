package br.com.developen.erp.bean;

import java.util.LinkedHashMap;
import java.util.Map;

public class SubjectBean001 {

	private Boolean active;

	private Map<Integer, AddressBean001> address;

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

}