package br.com.pocketpos.server.bean;

import java.util.HashMap;
import java.util.Map;

public class TabBean001 {

	private Integer identifier;

	private Boolean active;	

	private Integer position;

	private String denomination;

	private Map<Integer, ItemBean001> items;

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
	
	public Integer getPosition() {

		return position;

	}

	public void setPosition(Integer position) {

		this.position = position;

	}

	public String getDenomination() {

		return denomination;

	}

	public void setDenomination(String denomination) {

		this.denomination = denomination;

	}

	public Map<Integer, ItemBean001> getItems() {

		if (items==null)

			items = new HashMap<Integer, ItemBean001>();

		return items;

	}

	public void setItems(Map<Integer, ItemBean001> items) {

		this.items = items;

	}

}