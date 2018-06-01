package br.com.pocketpos.server.util;

import br.com.pocketpos.server.bean.DownloadBean;
import br.com.pocketpos.server.orm.Company;
import br.com.pocketpos.server.orm.CompanyDevice;
import br.com.pocketpos.server.orm.CompanyDeviceDataset;


public abstract class DownloadBuilder {

	protected static final int DATASET_USERS = 001;

	protected static final int DATASET_INDIVIDUALS = 002;

	protected static final int DATASET_ORGANIZATIONS = 003;	

	protected static final int DATASET_PRODUCTS = 004;

	protected static final int DATASET_TARIFFS = 005;
	
	protected static final int DATASET_FOLDERS = 006;

	public static final int[] PRIVATE_BUILD = {
			DATASET_USERS, 
			DATASET_INDIVIDUALS, 
			DATASET_ORGANIZATIONS, 
			DATASET_PRODUCTS, 
			DATASET_TARIFFS, 
			DATASET_FOLDERS
	};

	public abstract DownloadBean build(int[] options, Company company);

	public abstract DownloadBean build(int[] options, CompanyDevice companyDevice);

	public abstract DownloadBean build(int[] options, CompanyDeviceDataset companyDeviceDataset);

}