package com.zingcrm.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zingcrm.dao.CityDAO;
import com.zingcrm.entity.City;
import com.zingcrm.exception.BusinessException;


@Repository
public class CityDAOImpl implements CityDAO {

	private static Logger LOG = Logger.getLogger(CityDAOImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<City> readCityCode(String stateId) throws BusinessException {
		try {
			return (List<City>) sessionFactory.getCurrentSession()
					.createQuery("from City where StateId=:stateId order by CityName asc")
					.setString("stateId", stateId).list();
		} catch (HibernateException e) {

			LOG.fatal("CITDAO_001", e);
			throw new BusinessException("CityDAOImpl ::readCityCode() ");
		}
	}

}
