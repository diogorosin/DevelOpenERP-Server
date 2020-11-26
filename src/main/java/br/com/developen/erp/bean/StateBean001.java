package br.com.developen.erp.bean;
import java.util.LinkedHashMap;
import java.util.Map;

public class StateBean001 {

	private String denomination;

	private String acronym;

	private Map<Integer, CountryBean001> country;

	public String getDenomination() {

		return denomination;

	}

	public void setDenomination(String denomination) {

		this.denomination = denomination;

	}

	public String getAcronym() {

		return acronym;

	}

	public void setAcronym(String acronym) {

		this.acronym = acronym;

	}

	public Map<Integer, CountryBean001> getCountry() {
		
		if (country==null)
			
			country = new LinkedHashMap<Integer, CountryBean001>();

		return country;
		
	}

	public void setCountry(Map<Integer, CountryBean001> country) {
		
		this.country = country;
		
	}

}