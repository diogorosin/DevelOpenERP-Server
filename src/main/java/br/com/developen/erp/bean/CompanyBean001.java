package br.com.developen.erp.bean;

import java.util.LinkedHashMap;
import java.util.Map;

public class CompanyBean001 {

	private Boolean active;

	private Map<Integer, AddressBean001> address;

	private String denomination;

	private String fancyName;
	
	private String couponTitle;

	private String couponSubtitle;

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

	public String getDenomination() {
		
		return denomination;
		
	}

	public void setDenomination(String denomination) {

		this.denomination = denomination;
		
	}

	public String getFancyName() {
		
		return fancyName;
		
	}

	public void setFancyName(String fancyName) {
		
		this.fancyName = fancyName;
		
	}

	public String getCouponTitle() {

		return couponTitle;

	}

	public void setCouponTitle(String couponTitle) {

		this.couponTitle = couponTitle;

	}

	public String getCouponSubtitle() {

		return couponSubtitle;
		
	}

	public void setCouponSubtitle(String couponSubtitle) {

		this.couponSubtitle = couponSubtitle;

	}

}