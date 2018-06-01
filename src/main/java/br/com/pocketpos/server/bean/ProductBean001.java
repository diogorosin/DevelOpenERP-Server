package br.com.pocketpos.server.bean;

import java.util.HashMap;
import java.util.Map;

public class ProductBean001 {

	private Integer identifier;

	private Boolean active;

	private String code;

	private String longDenomination;

	private String shortDenomination;

	private Map<Integer, PartBean001> parts;

	public Integer getIdentifier() {

		return identifier;

	}

	public void setIdentifier(Integer identifier) {

		this.identifier = identifier;

	}

	public Boolean getActive() {

		return active;

	}

	public void setActive(Boolean active) {

		this.active = active;

	}

	public String getCode() {

		return code;

	}

	public void setCode(String code) {

		this.code = code;

	}

	public String getLongDenomination() {

		return longDenomination;

	}

	public void setLongDenomination(String longDenomination) {

		this.longDenomination = longDenomination;

	}

	public String getShortDenomination() {

		return shortDenomination;

	}

	public void setShortDenomination(String shortDenomination) {

		this.shortDenomination = shortDenomination;

	}

	public Map<Integer, PartBean001> getParts() {

		if (parts == null)

			parts = new HashMap<Integer, PartBean001>();

		return parts;

	}

	public void setPart(Map<Integer, PartBean001> parts) {

		this.parts = parts;

	}

}