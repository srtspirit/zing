package com.zingcrm.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zingcrm.dao.SettingDAO;
import com.zingcrm.entity.DefinedData1;
import com.zingcrm.entity.DefinedData2;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.SettingForms;
import com.zingcrm.utility.CalendarTimeZone;
import com.zingcrm.utility.PropertiesHolder;

@Repository
public class SettingDAOImpl implements SettingDAO{

	 private static Logger log = Logger.getLogger(SettingDAOImpl.class
	            .getName());
	 
	    @Autowired
	    private SessionFactory sessionFactory;
	    
	    @Autowired
	    private PropertiesHolder properties;
	    
	    @Autowired
	    private CalendarTimeZone calendar;

		@Override
		public void saveUserField1(SettingForms setForm)
				throws BusinessException {
			try{
				DefinedData1 data=new DefinedData1();
				data.setName(setForm.getParentField());
				data.setOrgId(setForm.getOrg());
				if(setForm.getParentId()!=0)
				{
					data.setId(setForm.getParentId());
					  sessionFactory.getCurrentSession().update(data);
				}
				else
				{
					 sessionFactory.getCurrentSession().save(data);
				}
			} catch (HibernateException e) 
			{
		            log.fatal("SET_DAO_OO6", e);
		            throw new BusinessException("SettingDAOImpl :: saveUserField1()");
		     }  
		}

		@Override
		public void saveUserField2(SettingForms setForm)
				throws BusinessException {
			try{
				DefinedData2 data=new DefinedData2();
				data.setName(setForm.getChildField());
				data.setData1(setForm.getParentId());
				if(setForm.getChildId()!=0)
				{
					data.setId(setForm.getChildId());
					  sessionFactory.getCurrentSession().update(data);
				}
				else
				{
					 sessionFactory.getCurrentSession().save(data);
				}
			} catch (HibernateException e) 
			{
		            log.fatal("SET_DAO_OO7", e);
		            throw new BusinessException("SettingDAOImpl :: saveUserField2()");
		     }  
			
		}

		@Override
		public void changeUser(String orgId, String fromUserId,
				String toUserId,String userID)
				throws BusinessException {
				try {
					Query q=sessionFactory.getCurrentSession()
							.createSQLQuery("call changeUser (:orgId,:fromUserId,:toUserId,:leadkey,:status,:userId,:change,:date,:activitykey,:actOpen)")
							.setString("orgId", orgId)
							.setString("fromUserId", fromUserId)
							.setString("toUserId", toUserId)
							.setString("leadkey", properties.getProperty("leadkey"))
							.setString("status",properties.getProperty("active"))
							.setString("userId",userID)
							.setString("change",properties.getProperty("changeUser"))
							.setDate("date",calendar.getCurrentDateTime())
							.setString("activitykey", properties.getProperty("activitykey"))
							.setString("actOpen", properties.getProperty("actOpen"));
					
					q.executeUpdate();
				} catch (HibernateException e) {

					log.fatal("SET_DAO_OO9", e);
					throw new BusinessException("ActivityDAOImpl ::changeUser()");
				}
		}
	    
	  
	    
}
