package br.com.developen.erp.bean;

import java.util.LinkedHashMap;
import java.util.Map;

public class TokenBean001 {

	private Map<Integer, CompanyBean001> company;

	private Map<Integer, LevelBean001> level;

	private Map<Integer, UserBean001> user;

	public Map<Integer, CompanyBean001> getCompany() {

		if (company==null)
			
			company = new LinkedHashMap<Integer, CompanyBean001>();
		
		return company;

	}

	public void setCompany(Map<Integer, CompanyBean001> company) {

		this.company = company;

	}

	public Map<Integer, LevelBean001> getLevel() {
		
		if (level==null)
			
			level = new LinkedHashMap<Integer, LevelBean001>();

		return level;

	}

	public void setLevel(Map<Integer, LevelBean001> level) {

		this.level = level;

	}

	public Map<Integer, UserBean001> getUser() {

		if (user==null)

			user = new LinkedHashMap<Integer, UserBean001>();

		return user;

	}

	public void setUser(Map<Integer, UserBean001> user) {

		this.user = user;

	}
	
}