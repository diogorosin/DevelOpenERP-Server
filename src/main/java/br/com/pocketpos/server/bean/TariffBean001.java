package br.com.pocketpos.server.bean;

import java.math.BigDecimal;

public class TariffBean001 {

	private Integer identifier;	

	private Boolean active;

	private String denomination;

	private BigDecimal factor;

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

	public BigDecimal getFactor() {
		
		return factor;
		
	}

	public void setFactor(BigDecimal factor) {
		
		this.factor = factor;

	}

}