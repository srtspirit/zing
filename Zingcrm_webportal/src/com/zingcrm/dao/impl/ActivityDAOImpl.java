package com.zingcrm.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.zingcrm.dao.ActivityDAO;
import com.zingcrm.entity.ActionLog;
import com.zingcrm.entity.Activity;
import com.zingcrm.entity.ActivityReference;
import com.zingcrm.entity.ActivityType;
import com.zingcrm.entity.BusinessPartner;
import com.zingcrm.entity.DefinedData1;
import com.zingcrm.entity.DefinedData2;
import com.zingcrm.entity.OrganizationInfo;
import com.zingcrm.entity.Status;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.ActivityForms;
import com.zingcrm.utility.CalendarTimeZone;
import com.zingcrm.utility.PropertiesHolder;

@Repository
public class ActivityDAOImpl implements ActivityDAO{

    /**
     *
     */
    private static Logger log = Logger.getLogger(ActivityDAOImpl.class
            .getName());

    /**
     *
     */
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private CalendarTimeZone calendar;
    
    @Autowired
    private PropertiesHolder properties;

    
    
	@Override
	public List<?> gridView(String orgId, String leadId, String oppId,
			String sortId, String sortDir, String status,String userId,String actUserId,String key) throws BusinessException {
		try {

			return ((List<?>) sessionFactory.getCurrentSession()
					.createSQLQuery("call getActivity (:orgId,:leadId,:oppId,:sortId,:sortDir,:status,:userId,:actUserId,:keys)")
					.setString("orgId", orgId)
					.setString("leadId", leadId)
					.setString("oppId", oppId)
					.setString("sortId", sortId)
					.setString("sortDir", sortDir)
					.setString("status", status)
					.setString("userId", userId)
					.setString("actUserId", actUserId)
					.setString("keys", key)
					.list());
		} catch (HibernateException e) {

			log.fatal("ACT_DAO_004", e);
			throw new BusinessException(
					"ActivityDAOImpl ::gridView()");
		}
	}

	@Override
	public void deleteActivity(String activityId,String status) throws BusinessException {
		try {
			Activity act=(Activity) sessionFactory.getCurrentSession().createQuery("from Activity where Id = :id").setParameter("id", Integer.parseInt(activityId)).list().get(0);
			act.setStatus(calendar.getFlag(status));
			sessionFactory.getCurrentSession().update(act);
			} catch (HibernateException e) {

				log.fatal("ACT_DAO_004", e);
				throw new BusinessException("ActivityDAOImpl ::deleteActivity()");
			}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> getActivityNameValidation(String oppId,
			String actName, String status) throws BusinessException {
		try {
            return (List<Activity>) sessionFactory.getCurrentSession()
                    .createQuery("from Activity where Opportunity like :id and status=:status and name like :name")
                    .setParameter("id", Integer.parseInt(oppId)).setBoolean("status",calendar.getFlag(status)).setParameter("name", actName).list();
        } catch (HibernateException e) {
            log.fatal("ACT_DAO_007", e);
            throw new BusinessException("ActivityDAOImpl :: getActivityNameValidation()");
        }
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> getEditActivityNameValidation(String oppId,
			String actName, String actId, String status)
			throws BusinessException {
		try {
            return (List<Activity>) sessionFactory.getCurrentSession()
                    .createQuery("from Activity where Opportunity like :id and status=:status and name like:name and id!=:actid")
                    .setParameter("id", Integer.parseInt(oppId)).setBoolean("status",calendar.getFlag(status)).setParameter("name", actName).setParameter("actid", Integer.parseInt(actId)).list();
        } catch (HibernateException e) {
            log.fatal("ACT_DAO_008", e);
            throw new BusinessException("ActivityDAOImpl :: getEditActivityNameValidation()");
        }
	}

	@Override
	public String saveActivity(ActivityForms actForm,String status,String activityStatus,String realpath,String activitykey) throws BusinessException, IOException {
		  Session session = null;  
		  String msg="";
		   Transaction tx = null;  
		try {
			 
				Activity act=new Activity();
				ActivityReference ref;
				session = sessionFactory.openSession();  
				tx = session.beginTransaction();  
				if(!actForm.getUserField1().equals("0"))
				{
					act.setColumn1(actForm.getUserField1());
				}
				if(!actForm.getUserField2().equals("0"))
				{
					act.setColumn2(actForm.getUserField2());
				}
		    	act.setActivityStatus(activityStatus);
		    	act.setStatus(calendar.getFlag(status));
		    	act.setAssignedTo(actForm.getAssignedTo());
		    	act.setCreatedBy(actForm.getCreatedBy());
		    	act.setCreatedDate(calendar.getCurrentDateTime());
		    	act.setDueDate(calendar.getStringToDate(actForm.getDueDate()));
		    	act.setName(actForm.getName());
		    	act.setOpportunity(actForm.getOpportunity());
		    	act.setType(Integer.parseInt(actForm.getType()));
		    	act.setDesc(actForm.getDesc());
		        sessionFactory.getCurrentSession().save(act);
		    	String[] fileCover = actForm.getFilesList().split(",");
		        for(int i=0;i<fileCover.length;i++){
		        	if(!fileCover[i].toString().equals(""))
		        	{
		        	ref=new ActivityReference();
		        	 File f=new File(realpath+(fileCover[i].replaceAll(" ", "-")).replace("||||",","));
	                    FileInputStream file = new FileInputStream(f);
	                    byte[] b = new byte[file.available()];
	                    file.read(b);
	                    ref.setDocument(b);
	                    ref.setFilename((fileCover[i].replaceAll(" ","-")).replace("||||",","));
	                    ref.setFilename(ref.getFilename().substring(1,ref.getFilename().length()));
	                    ref.setActid(act.getId());
	                    sessionFactory.getCurrentSession().save(ref);
	                    if(f.exists()){
	                    	 f.delete();
	                    	 file.close();
	                     }
		        	}
		        	
		        	
	                    
		        /*    for (MultipartFile multipartFile : fileCover) {
		            	ref=new ActivityReference();
		            	if(multipartFile.getOriginalFilename()!=null && multipartFile.getBytes()!=null && !multipartFile.getOriginalFilename().equals("") && multipartFile.getBytes().length>0){
		            	ref.setFilename(multipartFile.getOriginalFilename().replaceAll(" ","-"));
		            	ref.setDocument(multipartFile.getBytes());
		            	ref.setActid(act.getId());
		                sessionFactory.getCurrentSession().save(ref);
		            	}
		            }*/
		        }
		    	ActionLog log=new ActionLog();
		    	log.setNewStateId(Integer.parseInt(act.getActivityStatus()));
		    	log.setRecordType(activitykey);
		    	log.setRecordId(act.getId());
		    	log.setStatusDate(calendar.getCurrentDateTime());
		    	log.setUserId(actForm.getCreatedBy());
		     	log.setComments(properties.getProperty("actSave"));
		    	sessionFactory.getCurrentSession().save(log);
		    	tx.commit();  
		    	msg="success";
	            
		   } catch (HibernateException e) {
			   tx.rollback();  
		    	msg="error";
	            log.fatal("ACT_SER_010", e);
	            throw new BusinessException("ActivityDAOImpl :: saveActivity()");
	            
	       }  
	            finally {session.close();}
		return msg;  
	            
	}

	@Override
	public List<?> getActivityDetails(String actId) throws BusinessException {
		try {

			return ((List<?>) sessionFactory.getCurrentSession()
					.createSQLQuery("call getActivityInfo (:actId)")
					.setParameter("actId", Integer.parseInt(actId))
					.list());
		} catch (HibernateException e) {

			log.fatal("ACT_DAO_011", e);
			throw new BusinessException(
					"ActivityDAOImpl ::getActivityDetails()");
		}
	}

	@SuppressWarnings("unchecked")
	public List<ActivityReference> getActivityImage(String actId) throws BusinessException {
		try {
            return (List<ActivityReference>) sessionFactory.getCurrentSession()
                    .createQuery("from ActivityReference where actid like :id")
                    .setParameter("id", Integer.parseInt(actId)).list();
        } catch (HibernateException e) {
            log.fatal("ACT_DAO_013", e);
            throw new BusinessException("ActivityDAOImpl :: getActivityImage()");
        }
		
	}

	@Override
	public String editActivity(ActivityForms actForm,String strPath,String activityKey) throws BusinessException, IOException {
		  Session session = null;  
		   Transaction tx = null;  
		String statusId;
		ActivityReference ref;
		try {
			 
				session = sessionFactory.openSession();
				tx = session.beginTransaction();  
			Activity act= (Activity) sessionFactory.getCurrentSession()
	                .createQuery("from Activity where Id like :id")
	                .setParameter("id",actForm.getId()).list().get(0);
			if(!actForm.getUserField1().equals("0"))
			{
				act.setColumn1(actForm.getUserField1());
			}
			else if(actForm.getUserField1().equals("0")){
				actForm.setUserField1(null);
				act.setColumn1(actForm.getUserField1());
			}
			if(!actForm.getUserField2().equals("0"))
			{
				act.setColumn2(actForm.getUserField2());
			}
			else if(actForm.getUserField2().equals("0")){
				actForm.setUserField2(null);
				act.setColumn2(actForm.getUserField2());
			}
			 
		
		statusId=act.getActivityStatus();
		act.setCreatedDate(calendar.getStringToDateTime(actForm.getCreatedDate()));
		act.setModifiedBy(actForm.getModifiedBy());
		act.setModifiedDate(calendar.getCurrentDateTime());
		act.setActivityStatus(actForm.getActivityStatus());
		act.setStatus(calendar.getFlag(actForm.getStatus()));
		if(actForm.getDueDate()!=null)
		{
			act.setDueDate(calendar.getStringToDate(actForm.getDueDate()));
		}
		if(actForm.getAssignedTo()!=null)
		{
			act.setAssignedTo(actForm.getAssignedTo());
		}
		if(actForm.getDesc()!=null)
		{
			act.setDesc(actForm.getDesc());
		}
		if(actForm.getName()!=null)
		{
			act.setName(actForm.getName());
		}
		if(actForm.getOpportunity()!=0)
		{
			act.setOpportunity(actForm.getOpportunity());
		}
		
		if(actForm.getType()!=null)
		{
			act.setType(Integer.parseInt(actForm.getType()));
		}
		sessionFactory.getCurrentSession().update(act);
		
		String[] fileCover = actForm.getFilesList().split(",");
        for(int i=0;i<fileCover.length;i++){
        	if(!fileCover[i].toString().equals(""))
        	{
        	ref=new ActivityReference();
        	 File f=new File(strPath+(fileCover[i].replaceAll(" ", "-")).replace("||||",","));
                FileInputStream file = new FileInputStream(f);
                byte[] b = new byte[file.available()];
                file.read(b);
                ref.setDocument(b);
                ref.setFilename((fileCover[i].replaceAll(" ","-")).replace("||||",","));
                ref.setFilename(ref.getFilename().substring(1,ref.getFilename().length()));
                ref.setActid(act.getId());
                sessionFactory.getCurrentSession().save(ref);
                if(f.exists()){
               	 f.delete();
               	 file.close();
                }

        	}
        }
		
		/*String fileCover = actForm.getFilesList();
        if (null != fileCover  && fileCover.size() > 0) {
        	
            for (MultipartFile multipartFile : fileCover) {
            	if(multipartFile!=null){
            	ref=new ActivityReference();
            	if(multipartFile.getOriginalFilename()!=null && multipartFile.getBytes()!=null && !multipartFile.getOriginalFilename().equals("") && multipartFile.getBytes().length>0){
            	ref.setFilename(multipartFile.getOriginalFilename().replaceAll(" ","-"));
            	ref.setDocument(multipartFile.getBytes());
            	ref.setActid(act.getId());
                sessionFactory.getCurrentSession().save(ref);
            	}
            	}
            }
        }*/
        
		 if(!statusId.equals(actForm.getActivityStatus()))
			{
			 	ActionLog log=new ActionLog();
		    	log.setNewStateId(Integer.parseInt(actForm.getActivityStatus()));
		    	log.setRecordType(activityKey);
		    	log.setOldStateId(Integer.parseInt(statusId));
		    	log.setRecordId(act.getId());
		    	log.setStatusDate(calendar.getCurrentDateTime());
		    	log.setUserId(actForm.getCreatedBy());
		     	log.setComments(properties.getProperty("actUpdate"));
		    	sessionFactory.getCurrentSession().save(log);
			}
		
	    	tx.commit();  
		 } catch (HibernateException e) {
			 tx.rollback();  
	            log.fatal("ACT_DAO_012", e);
	            throw new BusinessException("ActivityDAOImpl :: editActivity()");
	        }finally {session.close();}  
		return statusId;
	}

	@Override
	public void removeAttachment(String activityId) throws BusinessException {
		try{

			ActivityReference actref= (ActivityReference) sessionFactory.getCurrentSession()
	                .createQuery("from ActivityReference where Id like :id")
	                .setParameter("id",Integer.parseInt(activityId)).list().get(0);
			sessionFactory.getCurrentSession().delete(actref);
			
		} catch (HibernateException e) {
        log.fatal("ACT_DAO_014", e);
        throw new BusinessException("ActivityDAOImpl :: removeAttachment()");
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Status> getStatusList(String key) throws BusinessException {
		try {
            return (List<Status>) sessionFactory.getCurrentSession()
                    .createQuery("from Status where key=:key").setParameter("key", key)
                    .list();
        } catch (HibernateException e) {
            log.fatal("ACT_DAO_016", e);
            throw new BusinessException("ActivityDAOImpl :: getStatusList()");
        }
	}


	@Override
	public List<?> getChartData(String leadUserId, String actUserId,String orgId,String status,String keys)
			throws BusinessException {
		try {

			return ((List<?>) sessionFactory.getCurrentSession()
					.createSQLQuery("call getActivityChart (:leadUserId,:actUserId,:orgId,:status,:keys)")
						.setParameter("leadUserId", leadUserId)
							.setParameter("actUserId", actUserId)
							.setParameter("orgId", Integer.parseInt(orgId))
							.setParameter("status",status)
							
										.setParameter("keys",keys)
					.list());
		} catch (HibernateException e) {

			log.fatal("ACT_DAO_017", e);
			throw new BusinessException(
					"ActivityDAOImpl ::getChartData()");
		}
	}

	@Override
	public List<?> getAllTasks(String leadUserId, String actUserId,
			String orgId, String status, String actstatus,String actClosed,String sortId,String sortDir)
			throws BusinessException {
		try {
			return ((List<?>) sessionFactory.getCurrentSession()
				.createSQLQuery("call getActivityTasks (:leadUserId,:actUserId,:orgId,:status,:actStatus,:time,:actClosed,:sortId,:sortDir)")
					.setParameter("leadUserId", leadUserId)
					.setParameter("actUserId", actUserId)
					.setParameter("orgId", Integer.parseInt(orgId))
					.setParameter("status",status)
					.setParameter("actStatus",actstatus)
					.setParameter("time",calendar.getCurrentDateTime())
					.setParameter("actClosed",actClosed)
					.setParameter("sortId",sortId)
					.setParameter("sortDir",sortDir)
					.list());
		} catch (HibernateException e) {

			log.fatal("ACT_DAO_017", e);
			throw new BusinessException(
					"ActivityDAOImpl ::getChartData()");
		}
	}

	@Override
	public List<?> getActivityEmail(String createdBy, String assignedTo)
			throws BusinessException {
		try {

			return ((List<?>) sessionFactory.getCurrentSession()
				.createSQLQuery("call getActivityEmail (:createdBy,:assignedTo)")
					.setParameter("createdBy", createdBy)
					.setParameter("assignedTo", assignedTo)
					.list());
		} catch (HibernateException e) {

			log.fatal("ACT_DAO_010, e");
			throw new BusinessException(
					"ActivityDAOImpl ::getActivityEmail()");
		}
		
	}

	@Override
	public void statusUpdate(String id, String status, String modifiedUserId,String activityKey)
			throws BusinessException {
		
		  Session session = null;  
		   Transaction tx = null;  
		   String statusId;
		try {
			 
				session = sessionFactory.openSession();
				tx = session.beginTransaction();  
				
		Activity act= (Activity) sessionFactory.getCurrentSession()
                .createQuery("from Activity where Id like :id")
                .setParameter("id",Integer.parseInt(id)).list().get(0);
		statusId=act.getActivityStatus();
		act.setActivityStatus(status);
		act.setModifiedBy(modifiedUserId);
		act.setModifiedDate(calendar.getCurrentDateTime());
		sessionFactory.getCurrentSession().update(act);
		
		 if(!statusId.equals(status))
			{
			 	ActionLog log=new ActionLog();
		    	log.setNewStateId(Integer.parseInt(status));
		    	log.setRecordType(activityKey);
		    	log.setOldStateId(Integer.parseInt(statusId));
		    	log.setRecordId(act.getId());
		    	log.setStatusDate(calendar.getCurrentDateTime());
		    	log.setUserId(modifiedUserId);
		       	log.setComments(properties.getProperty("actUpdate"));
		    	sessionFactory.getCurrentSession().save(log);
			}
		 tx.commit();

		} catch (HibernateException e) {
			tx.rollback();

			log.fatal("ACT_DAO_019, e");
			throw new BusinessException(
					"ActivityDAOImpl ::getActivityEmail()");
		}finally {session.close();}  
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DefinedData1> getDefinedData(String orgId) throws BusinessException {
		try {
			return (List<DefinedData1>) sessionFactory.getCurrentSession().createQuery("from DefinedData1 where OrgId = :id").setParameter("id", Integer.parseInt(orgId)).list();
			} catch (HibernateException e) {

				log.fatal("ACT_DAO_020", e);
				throw new BusinessException("ActivityDAOImpl ::getDefinedData()");
			}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DefinedData2> getDefinedData2(String data1) throws BusinessException {
		try {
			if(data1.equals("%"))
			{
				return (List<DefinedData2>) sessionFactory.getCurrentSession().createQuery("from DefinedData2 where data1 like :id").setString("id", data1).list();
			}
			else
			{
				return (List<DefinedData2>) sessionFactory.getCurrentSession().createQuery("from DefinedData2 where data1 like :id").setParameter("id", Integer.parseInt(data1)).list();
			}
			
			} catch (HibernateException e) {

				log.fatal("ACT_DAO_021", e);
				throw new BusinessException("ActivityDAOImpl ::getDefinedData2()");
			}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityType> getActivityType(String orgId) throws BusinessException {
		try {
			return (List<ActivityType>) sessionFactory.getCurrentSession().createQuery("from ActivityType where orgId = :id").setParameter("id", Integer.parseInt(orgId)).list();
			} catch (HibernateException e) {

				log.fatal("ACT_DAO_022", e);
				throw new BusinessException("ActivityDAOImpl ::getActivityType()");
			}
	}
	
	
	@Override
	public int saveActivityDevice(ActivityForms actForm,String status,String activityStatus,String activitykey) throws BusinessException, IOException {
		  Session session = null;  
		   Transaction tx = null;  
		   int actId=0;
		try {
			 
				Activity act=new Activity();
				session = sessionFactory.openSession();  
				tx = session.beginTransaction();  
				if(!actForm.getUserField1().equals("0"))
				{
					act.setColumn1(actForm.getUserField1());
				}
				if(!actForm.getUserField2().equals("0"))
				{
					act.setColumn2(actForm.getUserField2());
				}
		    	act.setActivityStatus(activityStatus);
		    	act.setStatus(calendar.getFlag(status));
		    	act.setAssignedTo(actForm.getAssignedTo());
		    	act.setCreatedBy(actForm.getCreatedBy());
		    	act.setCreatedDate(calendar.getCurrentDateTime());
		    	act.setDueDate(calendar.getStringToDate(actForm.getDueDate()));
		    	act.setName(actForm.getName());
		    	act.setOpportunity(actForm.getOpportunity());
		    	act.setType(Integer.parseInt(actForm.getType()));
		    	act.setDesc(actForm.getDesc());
		    	sessionFactory.getCurrentSession().save(act);
		    	if(actForm.getFileName()!=null)
		    	{
			    	if(!actForm.getFileName().equals(""))
			    	{
			    		ActivityReference ref=new ActivityReference();
			    		ref.setDocument(actForm.getFiles());
			    		ref.setFilename(actForm.getFileName());
			    		ref.setActid(act.getId());
		                sessionFactory.getCurrentSession().save(ref);
			    	}
		    	}
			
				ActionLog log=new ActionLog();
		    	log.setNewStateId(Integer.parseInt(act.getActivityStatus()));
		    	log.setRecordType(activitykey);
		    	log.setRecordId(act.getId());
		    	log.setStatusDate(calendar.getCurrentDateTime());
		    	log.setUserId(actForm.getCreatedBy());
		       	log.setComments(properties.getProperty("actSave"));
		    	sessionFactory.getCurrentSession().save(log);
		    	tx.commit();  
		    	actId=act.getId();
		   } catch (HibernateException e) {
			   tx.rollback();  
	            log.fatal("ACT_SER_010", e);
	            throw new BusinessException("ActivityDAOImpl :: saveActivity()");
	            
	         }  
	            finally {session.close();}
		return actId;  
	            
	}


	
	@Override
	public String editActivity(ActivityForms actForm, String activityKey)
			throws BusinessException, IOException {
			  Session session = null;  
			   Transaction tx = null;  
			String statusId;
			try {
				 
					session = sessionFactory.openSession();
					tx = session.beginTransaction();  
				Activity act= (Activity) sessionFactory.getCurrentSession()
		                .createQuery("from Activity where Id like :id")
		                .setParameter("id",actForm.getId()).list().get(0);
				if(!actForm.getUserField1().equals("0"))
				{
					act.setColumn1(actForm.getUserField1());
				}
				if(!actForm.getUserField2().equals("0"))
				{
					act.setColumn2(actForm.getUserField2());
				}
			
			statusId=act.getActivityStatus();
			act.setModifiedBy(actForm.getModifiedBy());
			act.setModifiedDate(calendar.getCurrentDateTime());
		
			if(actForm.getStatus()!=null)
			{
				act.setStatus(calendar.getFlag(actForm.getStatus()));
			}
			
			if(actForm.getActivityStatus()!=null)
			{
				act.setActivityStatus(actForm.getActivityStatus());
			}
			if(actForm.getDueDate()!=null)
			{
				act.setDueDate(calendar.getStringToDate(actForm.getDueDate()));
			}
			if(actForm.getAssignedTo()!=null)
			{
				act.setAssignedTo(actForm.getAssignedTo());
			}
			if(actForm.getDesc()!=null)
			{
				act.setDesc(actForm.getDesc());
			}
			if(actForm.getName()!=null)
			{
				act.setName(actForm.getName());
			}
			if(actForm.getOpportunity()!=0)
			{
				act.setOpportunity(actForm.getOpportunity());
			}
			
			if(actForm.getType()!=null)
			{
				act.setType(Integer.parseInt(actForm.getType()));
			}
			sessionFactory.getCurrentSession().update(act);
			
	    	
			
	    	if(actForm.getFileName()!=null)
	    	{
		    	if(!actForm.getFileName().equals(""))
		    	{
		    		ActivityReference ref=new ActivityReference();
		    		ref.setDocument(actForm.getFiles());
		    		ref.setFilename(actForm.getFileName());
		    		ref.setActid(act.getId());
	                sessionFactory.getCurrentSession().save(ref);
		    	}
	    	}
	    	
			 if(!statusId.equals(actForm.getActivityStatus()))
				{
				 	ActionLog log=new ActionLog();
			    	log.setNewStateId(Integer.parseInt(act.getActivityStatus()));
			    	log.setRecordType(activityKey);
			    	log.setOldStateId(Integer.parseInt(statusId));
			    	log.setRecordId(act.getId());
			    	log.setStatusDate(calendar.getCurrentDateTime());
			    	log.setUserId(act.getCreatedBy());
			      	log.setComments(properties.getProperty("actUpdate"));
			    	sessionFactory.getCurrentSession().save(log);
				}
			
		    	tx.commit();  
			 } catch (HibernateException e) {
				 tx.rollback();  
		            log.fatal("ACT_DAO_012", e);
		            throw new BusinessException("ActivityDAOImpl :: editActivity()");
		        }finally {session.close();}  
			return statusId;
		}
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String,List> getActivityDetailDevice(ActivityForms actForm) throws BusinessException {
		HashMap<String,List> act= new HashMap<String,List>();
		List<ActivityType> listact= null;
		List listactinfo;
		List<Status> liststatus=null;
		List<ActivityReference> activityimagelist=null;
		List<DefinedData1> listdefineddata1=null;
		List<DefinedData2> listdefineddata2=null;
		try {
		 listact=  (List<ActivityType>) sessionFactory.getCurrentSession().createQuery("from ActivityType where orgId = :id").setParameter("id", actForm.getOrg()).list();
		 act.put("ActivityType", listact);
		 
		 listactinfo= ((List<?>) sessionFactory.getCurrentSession().createSQLQuery("call getActivityInfo (:actId)")
					.setParameter("actId", actForm.getId())
					.list());
		 act.put("ActivityInfo", listactinfo);
		   
		 liststatus= (List<Status>) sessionFactory.getCurrentSession()
                 .createQuery("from Status where key=:key").setParameter("key", "activity")
                 .list();
		 act.put("StatusList", liststatus);
		 activityimagelist= (List<ActivityReference>) sessionFactory.getCurrentSession()
                 .createQuery("from ActivityReference where actid like :id")
                 .setParameter("id", actForm.getId()).list();
		 act.put("ImageList", activityimagelist);
		 listdefineddata1= (List<DefinedData1>) sessionFactory.getCurrentSession().createQuery("from DefinedData1 where OrgId = :id").setParameter("id", actForm.getOrg()).list();
		 act.put("DataDefined1", listdefineddata1);
		 
		 listdefineddata2=(List<DefinedData2>) sessionFactory.getCurrentSession().createQuery("from DefinedData2 where data1 like :id").setString("id", "%").list();
		 act.put("DataDefined2", listdefineddata2);
			} catch (HibernateException e) {

				log.fatal("ACT_DAO_022", e);
				throw new BusinessException("ActivityDAOImpl ::getActivityType()");
			}
		return act;
	}
	
	@Override
	public List<?> getCompanyList(String orgId,String status,String userId) throws BusinessException {
	        try {
	        	
	        		 return (List<?>) sessionFactory.getCurrentSession()
			                    .createSQLQuery("select distinct bpt.BPID,bpt.Name from activity act join opportunity opp on act.OpportunityID =opp.OpportunityID join businesspartner bpt on bpt.BPID =opp.BPID where  act.AssignedTo=:user and bpt.DeleteFlag=:statusId and bpt.OrgID=:id order by bpt.Name asc")
			                    .setParameter("user",userId).setBoolean("statusId",calendar.getFlag(status)).setParameter("id", Integer.parseInt(orgId)).list();
	           
	        } catch (HibernateException e) {
	            log.fatal("ACT_DAO_023", e);
	            throw new BusinessException("ActivityDAOImpl :: getCompanyList()");
	        }
	    }
	
	@SuppressWarnings("unchecked")
	public ActivityReference getActivityImagelink(String actrefId) throws BusinessException {
		try {
            return (ActivityReference) sessionFactory.getCurrentSession()
                    .createQuery("from ActivityReference where Id like :id")
                    .setParameter("id", Integer.parseInt(actrefId)).list().get(0);
        } catch (HibernateException e) {
            log.fatal("ACT_DAO_024", e);
            throw new BusinessException("ActivityDAOImpl :: getActivityImage()");
        }
		
	}

	}
	
