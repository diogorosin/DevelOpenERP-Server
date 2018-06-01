package br.com.pocketpos.server.util;

import br.com.pocketpos.server.bean.DownloadBean;
import br.com.pocketpos.server.exception.DownloadVersionNotFoundException;
import br.com.pocketpos.server.orm.Company;
import br.com.pocketpos.server.orm.CompanyDevice;
import br.com.pocketpos.server.orm.CompanyDeviceDataset;

public class DownloadBeanFactory {

	public static final int VERSION_001 = 001;

	public static DownloadBean buildDownloadBean (
			Company company,
			int version) throws DownloadVersionNotFoundException{

		switch (version) {

		case VERSION_001:

			return new DownloadBuilder001().build(
					DownloadBuilder.PRIVATE_BUILD, company);

		default:

			throw new DownloadVersionNotFoundException();

		}

	}

	public static DownloadBean buildDownloadBean (
			CompanyDevice companyDevice, 
			int version) throws DownloadVersionNotFoundException{

		switch (version) {

		case VERSION_001:

			return new DownloadBuilder001().build(
					DownloadBuilder.PRIVATE_BUILD, companyDevice);

		default:

			throw new DownloadVersionNotFoundException();

		}

	}

	public static DownloadBean buildDownloadBean (
			CompanyDeviceDataset companyDeviceDataset, 
			int version) throws DownloadVersionNotFoundException{

		switch (version) {

		case VERSION_001:

			return new DownloadBuilder001().build(
					DownloadBuilder.PRIVATE_BUILD, companyDeviceDataset);

		default:

			throw new DownloadVersionNotFoundException();

		}

	}

}