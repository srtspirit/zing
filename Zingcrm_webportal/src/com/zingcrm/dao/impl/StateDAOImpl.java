package com.zingcrm.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zingcrm.dao.StateDAO;
import com.zingcrm.entity.State;
import com.zingcrm.exception.BusinessException;


@Repository
public class StateDAOImpl implements StateDAO {

    private static Logger LOG = Logger.getLogger(StateDAOImpl.class.getName());

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
	@Override
    public List<State> readStateCode(String countryId) throws BusinessException {
        try {
            return (List<State>) sessionFactory.getCurrentSession()
                    .createQuery("from State where CountryId like :countryId order by StateName asc")
                    .setString("countryId", countryId).list();
        } catch (HibernateException e) {

            LOG.fatal("STADAO_002", e);
            throw new BusinessException("StateDAOImpl ::readStateCode() ");
        }
    }

}
