package br.com.developen.erp.bean;

import java.util.LinkedHashMap;
import java.util.Map;

public class CityBean001 {

	private String denomination;

	private Map<Integer, StateBean001> state;

	public String getDenomination() {

		return denomination;

	}

	public void setDenomination(String denomination) {

		this.denomination = denomination;

	}

	public Map<Integer, StateBean001> getState() {

		if (state==null)

			state = new LinkedHashMap<Integer, StateBean001>();

		return state;

	}

	public void setState(Map<Integer, StateBean001> state) {

		this.state = state;

	}

}