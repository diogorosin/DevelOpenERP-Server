package br.com.pocketpos.server.bean;

import java.util.HashMap;
import java.util.Map;

public class FolderBean001 {

	private Integer identifier;	

	private Boolean active;

	private Integer position;

	private String denomination;

	private Map<Integer, DocumentBean001> documents;

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

	public Map<Integer, DocumentBean001> getDocuments() {

		if (documents==null)

			documents = new HashMap<Integer, DocumentBean001>();

		return documents;

	}

	public void setDocuments(Map<Integer, DocumentBean001> documents) {

		this.documents = documents;

	}

}