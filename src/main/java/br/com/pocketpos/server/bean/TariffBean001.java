package br.com.pocketpos.server.bean;

import java.util.HashMap;
import java.util.Map;

public class TariffBean001 {

	private Integer identifier;	

	private Boolean active;

	private String denomination;

	private Map<Integer, PriceBean001> prices;

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

	public String getDenomination() {

		return denomination;

	}

	public void setDenomination(String denomination) {

		this.denomination = denomination;

	}

	public Map<Integer, PriceBean001> getPrices() {

		if (prices==null)

			prices = new HashMap<Integer, PriceBean001>();

		return prices;

	}

	public void setPrices(Map<Integer, PriceBean001> prices) {

		this.prices = prices;

	}

}