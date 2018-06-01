package br.com.pocketpos.server.orm;


import java.util.List;

import org.hibernate.Session;

public class CompanyDeviceDAO extends DAO<CompanyDevice, CompanyDevicePK>{

	public CompanyDeviceDAO(Session session) {

		super(session, CompanyDevice.class);

	}

	public List<CompanyDevice> listByCompany(Company company){

		return getSession().
				createNamedQuery(CompanyDevice.FIND_BY_COMPANY, CompanyDevice.class).
				setParameter("company", company).				
				getResultList();

	}

	public List<CompanyDevice> listByDevice(Device device){

		return getSession().
				createNamedQuery(CompanyDevice.FIND_BY_DEVICE, CompanyDevice.class).
				setParameter("device", device).
				getResultList();

	}

}