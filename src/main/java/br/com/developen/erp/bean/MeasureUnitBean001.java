package br.com.developen.erp.bean;

import java.util.HashMap;
import java.util.Map;

public class MeasureUnitBean001 {

	private Integer identifier;

	private String denomination;

	private String acronym;
	
	private Integer group;

	private Map<Integer, ConversionBean001> conversions;

	public Integer getIdentifier() {

		return identifier;

	}

	public void setIdentifier(Integer identifier) {

		this.identifier = identifier;

	}

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

	public Integer getGroup() {

		return group;

	}

	public void setGroup(Integer group) {
		
		this.group = group;
		
	}

	public Map<Integer, ConversionBean001> getConversions() {

		if (conversions==null)

			conversions = new HashMap<Integer, ConversionBean001>();

		return conversions;

	}

	public void setConversions(Map<Integer, ConversionBean001> conversions) {

		this.conversions = conversions;

	}

}