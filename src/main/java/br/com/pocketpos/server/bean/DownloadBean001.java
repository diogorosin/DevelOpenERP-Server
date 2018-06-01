package br.com.pocketpos.server.bean;

public class DownloadBean001 implements DownloadBean{

	private CompanyBean001 company;

	public CompanyBean001 getCompany() {

		if (company==null)

			company = new CompanyBean001();

		return company;

	}

	public void setCompany(CompanyBean001 company) {

		this.company = company;

	}

}