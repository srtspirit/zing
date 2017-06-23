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

import com.zingcrm.dao.TimeZoneDAO;
import com.zingcrm.entity.TimeZone;
import com.zingcrm.exception.BusinessException;


/**
 * @author NeshTech
 * 
 */
@Repository
public class TimeZoneDAOImpl implements TimeZoneDAO {

    /**
     *
     */
    private static Logger log = Logger.getLogger(TimeZoneDAOImpl.class
            .getName());

    /**
     *
     */
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public final List<TimeZone> getTimeZoneList() throws BusinessException {
        try {
            return (List<TimeZone>)sessionFactory.getCurrentSession()
                    .createQuery("from TimeZone").list();
        } catch (HibernateException e) {
            log.fatal("TIME_DAO_001", e);
            throw new BusinessException("TimeZoneDAOImpl :: getTimeZoneList()");
        }
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public final List<TimeZone> getTimeZone(String strUserId) throws BusinessException {
        try {
            return (List<TimeZone>) sessionFactory.getCurrentSession()
                    .createSQLQuery("select zone.TimeZoneCode,zone.TimeZoneValue from timezone zone join userinfo usr on usr.TimeZoneId=zone.TimeZoneID where usr.UserId=:userId").setParameter("userId", strUserId).list();
        } catch (HibernateException e) {
            log.fatal("TimeZoneDAOImpl :: getTimeZoneList()", e);
            throw new BusinessException("TimeZoneDAOImpl :: getTimeZoneList()");
        }
    }


}
