package br.com.developen.erp.orm;


import java.util.List;

import org.hibernate.Session;

public class DeviceDAO extends DAO<Device, Integer>{

	public DeviceDAO(Session session) {

		super(session, Device.class);

	}

	public Device retrieveBySerialNumber(String serialNumber){

		List<Device> list = getSession().
				createNamedQuery(Device.FIND_BY_SERIAL_NUMBER, Device.class).
				setParameter("serialNumber", serialNumber).
				setMaxResults(1).
				getResultList(); 

		return list.isEmpty() ? null : list.get(0); 

	}

}