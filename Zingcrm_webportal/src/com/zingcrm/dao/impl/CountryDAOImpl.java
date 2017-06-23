/**
 *
 */
package com.zingcrm.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zingcrm.dao.CountryDAO;
import com.zingcrm.entity.Country;
import com.zingcrm.exception.BusinessException;

/**
 * @author NeshTech
 * 
 */
@Repository
@Transactional
public class CountryDAOImpl implements CountryDAO {

    /**
     *
     */
    private static Logger log = Logger
            .getLogger(CountryDAOImpl.class.getName());

    /**
     *
     */
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public final List<Country> getCountryList() throws BusinessException {
        try {
            return (List<Country>)sessionFactory.getCurrentSession()
                    .createQuery("from Country order by countryName asc").list();
        } catch (HibernateException e) {
            log.fatal("COUNTRY_DAO_001", e);
            throw new BusinessException("CountryDAOImpl :: getCountryList()");
        }
    }

}
