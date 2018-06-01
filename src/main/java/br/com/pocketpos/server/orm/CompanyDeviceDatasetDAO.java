package br.com.pocketpos.server.orm;


import java.util.List;

import org.hibernate.Session;

public class CompanyDeviceDatasetDAO extends DAO<CompanyDeviceDataset, CompanyDeviceDatasetPK>{

	public CompanyDeviceDatasetDAO(Session session) {

		super(session, CompanyDeviceDataset.class);

	}

	public List<CompanyDeviceDataset> listByCompanyDevice(CompanyDevice companyDevice){

		return getSession().
				createNamedQuery(CompanyDeviceDataset.FIND_BY_COMPANY_DEVICE, CompanyDeviceDataset.class).
				setParameter("companyDevice", companyDevice).
				getResultList();

	}

	public CompanyDeviceDataset retrieveMostRecentDatasetByCompanyDevice(CompanyDevice companyDevice){

		List<CompanyDeviceDataset> list = getSession().
				createNamedQuery(CompanyDeviceDataset.FIND_BY_COMPANY_DEVICE, CompanyDeviceDataset.class).
				setParameter("companyDevice", companyDevice).
				setFirstResult(0).
				setMaxResults(1).
				getResultList();

		return list.isEmpty() ? null : list.get(0);

	}

	public Integer getLatterIdentifierByCompanyDevice(CompanyDevice companyDevice){

		Integer identifier = getSession().
				createNamedQuery(CompanyDeviceDataset.GET_LATTER_IDENTIFIER_BY_COMPANY_DEVICE, Integer.class).
				setParameter("companyDevice", companyDevice).
				getSingleResult();

		return identifier == null ? 0 : identifier;

	}

}