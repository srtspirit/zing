package com.zingcrm.dao.impl;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zingcrm.dao.FeatureDAO;
import com.zingcrm.exception.BusinessException;


/**
 * @author NeshTech
 * 
 */
@Repository
public class FeatureDAOImpl implements FeatureDAO {

    private static Logger log = Logger
            .getLogger(FeatureDAOImpl.class.getName());

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public final List<?> getFeatureList(final String roleId,
            final String orgId) throws BusinessException {
        try {
            
            Query query = sessionFactory
            .getCurrentSession()
            .createSQLQuery(
                    "call getFeaOperation(:roleId,:orgId)")
                    .setString("roleId", roleId).setString("orgId", orgId);
            return query.list();
        } catch (HibernateException e) {
            log.fatal("FEA_DAO_001", e);
            throw new BusinessException(
                    "FeatureDAOImpl :: getFeatureListManageAccount() ");
        }
    }
    
    @Override
    public final List<?> getNewFeatureList(final String roleId,
            final String orgId) throws BusinessException {
        try {
            
            Query query = sessionFactory
            .getCurrentSession()
            .createSQLQuery(
                    "select * from rolebasedfunction where role=:roleId")
                    .setString("roleId", roleId);
            return query.list();
        } catch (HibernateException e) {
            log.fatal("FEA_DAO_002", e);
            throw new BusinessException(
                    "FeatureDAOImpl :: getNewFeatureList() ");
        }
    }
}
