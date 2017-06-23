/**
 * 
 */
package com.zingcrm.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zingcrm.dao.CrudButtonDAO;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.utility.PropertiesHolder;


/**
 * @author NeshTech
 * 
 */
@Repository
public class CrudButtonDAOImpl implements CrudButtonDAO {

    /**
     *
     */
    private static Logger log = Logger.getLogger(CrudButtonDAOImpl.class
            .getName());

    /**
     *
     */
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private PropertiesHolder properties;

    @Override
    public final List<?> getCrudButtons(final int roleId,
            final String featureId, final String orgId)
            throws BusinessException {
        try {
            Query query = sessionFactory
                    .getCurrentSession()
                    .createQuery(
                            "from RoleButtonOperation where RoleId=:roleId "
                            + "and FeatureId=:featureId and OrgId=:orgId order by ButtonOrder asc")
                    .setParameter("roleId", roleId)
                    .setString("featureId", featureId)
                    .setString("orgId", orgId);
            return query.list();
        } catch (HibernateException e) {
            log.fatal("CRUD_DAO_001", e);
            throw new BusinessException(
                    "CrudButtonDAOImpl :: getCrudButtons() ");
        }
    }

	@Override
	public List<?> getCreateCrudButtons(String roleId, String orgId)
			throws BusinessException {
		try {
			return ((List<?>) sessionFactory.getCurrentSession()
					.createSQLQuery("call getIndexData(:roleId,:orgId,:operation,:feature)")
					.setParameter("roleId",Integer.parseInt(roleId))
                    .setString("orgId", orgId)
                    .setString("operation", properties.getProperty("creatButton"))
                    .setString("feature", properties.getProperty("createFeature"))
					.list());
                    
        } catch (HibernateException e) {
            log.fatal("CRUD_DAO_002", e);
            throw new BusinessException(
                    "CrudButtonDAOImpl :: getCreateCrudButtons() ");
        }
	}

}
