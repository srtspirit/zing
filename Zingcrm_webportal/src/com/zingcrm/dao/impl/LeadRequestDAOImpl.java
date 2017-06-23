package com.zingcrm.dao.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zingcrm.dao.LeadRequestDAO;
import com.zingcrm.entity.ActivityType;
import com.zingcrm.entity.LeadRequest;
import com.zingcrm.entity.Source;
import com.zingcrm.entity.User;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.LeadRequestForms;
import com.zingcrm.utility.CalendarTimeZone;
import com.zingcrm.utility.PropertiesHolder;

@Repository
public class LeadRequestDAOImpl implements LeadRequestDAO{

   private static Logger log = Logger.getLogger(ActivityDAOImpl.class
           .getName());

   @Autowired
   private SessionFactory sessionFactory;
   
   @Autowired
   private CalendarTimeZone calendar;
   
   @Autowired
   private PropertiesHolder properties;

   
   
	@Override
	public List<?> gridView(String orgId, String leadId, String oppId,
			String sortId, String sortDir, String status,String userId,String actUserId,String key,String strRoleid) throws BusinessException {
		try {
       if(Integer.parseInt(strRoleid)<=3){
			return ((List<?>) sessionFactory.getCurrentSession()
					.createSQLQuery("select LeadRequestId,businessname,contactname,email,phone,PhoneExtension,isBPCreated from leadrequest where isBpCreated=0  and leadreqQualFlag=0").list());
       }
       else{
    	   return ((List<?>) sessionFactory.getCurrentSession()
					.createSQLQuery("select LeadRequestId,businessname,contactname,email,phone,PhoneExtension,isBPCreated from leadrequest where salesleadid like :userid and isBpCreated=0  and leadreqQualFlag=0")
					.setParameter("userid", actUserId).list());
       }
		} catch (HibernateException e) {

			log.fatal("LEADREQUEST_DAO_001", e);
			throw new BusinessException(
					"LeadrequestDAOImpl ::gridView()");
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAssinedSalesRep(String  Orgid, String status)
			throws BusinessException {
		try {
            return (List<User>) sessionFactory.getCurrentSession()
                    .createSQLQuery("select us.* from userinfo as us , organization as org where us.email=org.AssignedSalesRep and org.orgId=:Orgid and us.deleteflag=:Status")
                    .setParameter("Orgid", Integer.parseInt(Orgid))
                    .setBoolean("Status",calendar.getFlag(properties.getProperty("active"))).list();
        } catch (HibernateException e) {
            log.fatal("LEADREQUEST_DAO_002", e);
            throw new BusinessException("LeadrequestDAOImpl :: getAssinedSalesRep()");
        }
	}
	
	@Override
	public void saveLeadReuest(LeadRequestForms leadreqForm) throws BusinessException, IOException {
		  Session session = null;  
		   Transaction tx = null;  
		try {
			 
				LeadRequest leadreq=new LeadRequest();
				session = sessionFactory.openSession();  
				tx = session.beginTransaction();  
				
				leadreq.setSource(leadreqForm.getSource());
				leadreq.setBusinessname(leadreqForm.getBusinessname());
				leadreq.setContactname(leadreqForm.getContactname());
				leadreq.setCreateddate(calendar.getCurrentDateTime());
				leadreq.setPhonenumber(leadreqForm.getPhonenumber());
				leadreq.setEmail(leadreqForm.getEmail());
				leadreq.setExtension(leadreqForm.getExtension());
				leadreq.setWebsite(leadreqForm.getWebsite());
				leadreq.setNotes(leadreqForm.getNotes());
		    	leadreq.setSalesleadid(leadreqForm.getHdn_salesleadid());
		    	leadreq.setLeadReqCreatedby(leadreqForm.getCreatedby());
		    	leadreq.setOrgid(Integer.parseInt(leadreqForm.getOrgid()));
		    	leadreq.setIsBPCreated("0");
		    	sessionFactory.getCurrentSession().save(leadreq);
		    	tx.commit();  
	            
	            
		   } catch (HibernateException e) {
			   tx.rollback();  
	            log.fatal("LEADREQUEST_DAO_003", e);
	            throw new BusinessException("LeadRequestDAOImpl :: saveLeadRequest()");
	         }  
	            finally {session.close();}  
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LeadRequest> getLeadRequestEmailValidation(String strEmail, String strorgid, String userid,String leadid) throws BusinessException {
		try {
			//strEmail=StringEscapeUtils.escapeHtml(strEmail);
			if(leadid!=null && !leadid.equals("") && !leadid.equals("0")){
				return (List<LeadRequest>) sessionFactory.getCurrentSession()
	                       .createQuery("from LeadRequest where email=:Email and orgid=:Orgid and Id!=:leadid")
	                       .setParameter("Email", strEmail)
	                       .setParameter("Orgid",Integer.parseInt(strorgid))
	                       .setParameter("leadid",Integer.parseInt(leadid)).list();
			}
			else{
				 return (List<LeadRequest>) sessionFactory.getCurrentSession()
	                     .createQuery("from LeadRequest where email=:Email and orgid=:Orgid")
	                     .setParameter("Email", strEmail)
	                     .setParameter("Orgid",Integer.parseInt(strorgid))
	                     .list();
			}
            
        } catch (HibernateException e) {
            log.fatal("LEADREQUEST_DAO_004", e);
            throw new BusinessException("LeadRequestDAOImpl :: getLeadRequestEmailValidation()");
        }
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public String getBusinessNameValidation(String strBP, String strorgid, String userid,String leadid) throws BusinessException {
		try {
			
			List li=null;
			if(leadid!=null && !leadid.equals("") && !leadid.equals("0")){
				li=(List<LeadRequest>) sessionFactory.getCurrentSession()
	                       .createQuery("from LeadRequest where businessname=:strBP and orgid=:Orgid and Id!=:leadid")
	                       .setParameter("strBP", strBP)
	                       .setParameter("Orgid",Integer.parseInt(strorgid))
	                       .setParameter("leadid",Integer.parseInt(leadid)).list();
				
			}
			else{
				  li=(List<LeadRequest>) sessionFactory.getCurrentSession()
	                     .createQuery("from LeadRequest where businessname=:strBP and orgid=:Orgid")
	                     .setParameter("strBP", strBP)
	                     .setParameter("Orgid",Integer.parseInt(strorgid))
	                     .list();
			}
			if(li.size()>0)
			  {
				  return "leadexists";
			  }
			  else
			  {
				  li= (List<LeadRequest>) sessionFactory.getCurrentSession()
		                     .createQuery("from BusinessPartner where name=:strBP and accountId=:Orgid")
		                     .setParameter("strBP", strBP)
		                     .setParameter("Orgid",Integer.parseInt(strorgid))
		                     .list();
				  if(li.size()>0)
				  {
					  return "bpexists";
				  }
				  else
				  {
					  return "notexists";
				  }
			  }
            
        } catch (HibernateException e) {
            log.fatal("LEADREQUEST_DAO_004", e);
            throw new BusinessException("LeadRequestDAOImpl :: getLeadRequestEmailValidation()");
        }
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LeadRequest> getLeadRequestDetails(String leadrequestId,String orgid) throws BusinessException {
		try {

			return ((List<LeadRequest>) sessionFactory.getCurrentSession()
					.createQuery("from LeadRequest where Id=:leadrequestid and orgid=:Orgid")
					.setParameter("leadrequestid", Integer.parseInt(leadrequestId))
					.setParameter("Orgid", Integer.parseInt(orgid))
					.list());
		} catch (HibernateException e) {

			log.fatal("LEADREQUEST_DAO_005", e);
			throw new BusinessException(
					"LeadRequestDAOImpl ::getLeadRequestDetails()");
		}
	}
	
	@Override
	public String editLeadRequest(LeadRequestForms leadreqform) throws BusinessException, IOException {
		  String strdata="error";
		try {
				LeadRequest leadreq=(LeadRequest)sessionFactory.getCurrentSession().createQuery( "from LeadRequest where Id=:id").setParameter("id", leadreqform.getId()).list().get(0);;
				leadreq.setLeadReqModifiedby(leadreqform.getModifiedby());
				leadreq.setLeadReqModifieddate(calendar.getCurrentDateTime());
				if(leadreqform.getBusinessname()!=null)
				{
					leadreq.setBusinessname(leadreqform.getBusinessname());
				}
				if(leadreqform.getSalesleadid()!=null)
				{
					leadreq.setSalesleadid(leadreqform.getSalesleadid());
				}
				if(leadreqform.getNotes()!=null)
				{
					leadreq.setNotes(leadreqform.getNotes());
				}
				if(leadreqform.getContactname()!=null)
				{
					leadreq.setContactname(leadreqform.getContactname());
				}
				if(leadreqform.getSource()!=null)
				{
					leadreq.setSource(leadreqform.getSource());
				}
				
				if(leadreqform.getWebsite()!=null)
				{
					leadreq.setWebsite(leadreqform.getWebsite());
				}
				
				if(leadreqform.getPhonenumber()!=null)
				{
					leadreq.setPhonenumber(leadreqform.getPhonenumber());
				}
				if(leadreqform.getExtension()!=null)
				{
					leadreq.setExtension(leadreqform.getExtension());
				}
				if(leadreqform.getEmail()!=null)
				{
					leadreq.setEmail(leadreqform.getEmail());
				}
				
				if(leadreqform.getLeadreqQualFlag() != null && Boolean.parseBoolean(leadreqform.getLeadreqQualFlag()) !=false)
				{
					leadreq.setLeadreqQualFlag(1);
				}
				sessionFactory.getCurrentSession().update(leadreq);
				
				User usr=(User) sessionFactory.getCurrentSession()
						.createQuery("from User where userid=:leadrequestid")
						.setParameter("leadrequestid", leadreqform.getSalesleadid())
						.list().get(0);
			    strdata=usr.getEmail();
		 } catch (HibernateException e) {
	            log.fatal("LEADREQUEST_DAO_005", e);
	            throw new BusinessException("LeadRequestDAOImpl :: editLeadRequest()");
		 }
		return strdata;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getSalesRepdetails(String  Orgid)
			throws BusinessException {
		try {
            return (List<User>) sessionFactory.getCurrentSession()
                    .createSQLQuery("select us.* from userinfo as us , organization as org where us.roleid in('3','4') and org.orgId=:Orgid and us.deleteflag=:Status")
                    .setParameter("Orgid", Integer.parseInt(Orgid))
                    .setBoolean("Status",calendar.getFlag(properties.getProperty("active"))).list();
        } catch (HibernateException e) {
            log.fatal("LEADREQUEST_DAO_006", e);
            throw new BusinessException("LeadrequestDAOImpl :: getAssinedSalesRep()");
        }
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Source> getSourcedetails()
			throws BusinessException {
		try {
			return (List<Source>) sessionFactory.getCurrentSession().createQuery("from Source").list();
			} catch (HibernateException e) {

				log.fatal("LEADREQUEST_DAO_007", e);
				throw new BusinessException("LeadrequestDAOImpl ::getSourcedetails()");
			}
	}
}
