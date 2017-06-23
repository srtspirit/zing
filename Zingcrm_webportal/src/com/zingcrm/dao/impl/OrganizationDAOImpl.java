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

import com.zingcrm.dao.OrganizationDAO;
import com.zingcrm.entity.OrganizationInfo;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.SettingForms;


/**
 * @author NeshTech
 */
@Transactional
@Repository
public class OrganizationDAOImpl implements OrganizationDAO {

    /**
     *
     */
    private static Logger log = Logger.getLogger(OrganizationDAOImpl.class
            .getName());

    /**
     *
     */
    @Autowired
    private SessionFactory sessionFactory;



    @SuppressWarnings("unchecked")
	@Override
    public final List<OrganizationInfo> getOrgList(final String orgId)
            throws BusinessException {
        try {
            return (List<OrganizationInfo>) sessionFactory.getCurrentSession()
                    .createQuery("from OrganizationInfo where OrgId like :id")
                    .setParameter("id", orgId).list();
        } catch (HibernateException e) {
            log.fatal("ORG_DAO_001", e);
            throw new BusinessException("OrganizationDAOImpl :: getOrgList()");
        }
    }



	@Override
	public OrganizationInfo getOrgUserDetails(String orgId) throws BusinessException {
		  try {
			 return  (OrganizationInfo) sessionFactory.getCurrentSession()
	                    .createQuery("from OrganizationInfo where OrgId like :id")
	                    .setParameter("id", orgId).list().get(0);
	        } catch (HibernateException e) {
	            log.fatal("ORG_DAO_002", e);
	            throw new BusinessException("OrganizationDAOImpl :: getOrgUserDetails()");
	        }
		
	}



	@Override
	public void editUserField(SettingForms setForm) throws BusinessException {
		  try {
		OrganizationInfo org= (OrganizationInfo) sessionFactory.getCurrentSession()
                .createQuery("from OrganizationInfo where OrgId like :id")
                .setParameter("id", setForm.getOrg()).list().get(0);
		org.setUserField1(setForm.getParentField());
		org.setUserField2(setForm.getChildField());
		org.setRequired(setForm.isRequired());
		sessionFactory.getCurrentSession().update(org);
		  } catch (HibernateException e) {
	            log.fatal("ORG_DAO_003", e);
	            throw new BusinessException("OrganizationDAOImpl :: editUserField()");
	        }
	}
}

   
