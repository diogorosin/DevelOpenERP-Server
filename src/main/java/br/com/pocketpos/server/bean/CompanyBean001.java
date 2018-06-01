package br.com.pocketpos.server.bean;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CompanyBean001 extends OrganizationBean001{


	@JsonIgnore
	private Integer level;

	private Integer currentTariff;
	
	private String couponTitle;
	
	private String couponSubtitle;

	private List<DeviceBean001> devices;


	public Integer getCurrentTariff() {

		return currentTariff;

	}

	public void setCurrentTariff(Integer currentTariff) {

		this.currentTariff = currentTariff;

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

	public List<DeviceBean001> getDevices() {

		if (devices == null)

			devices = new ArrayList<DeviceBean001>();

		return devices;

	}

	public void setDevices(List<DeviceBean001> devices){

		this.devices = devices;

	}


}