package com.icloud.insurance.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.SessionFactory;

import com.icloud.framework.dao.hibernate.impl.HibernateBaseDaoImpl;

public class InsuranceBaseDao<T> extends HibernateBaseDaoImpl<T> {
	@Resource(name = "insuranceSessionFactory")
	private SessionFactory insuranceSessionFactory;

	@PostConstruct
	public void replaceDatastore() {
		// super.setDatastore(hotelDatastore);
		super.setSessionFactory(insuranceSessionFactory);
	}

}
