package com.zingcrm.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zingcrm.dao.OpportunityDAO;
import com.zingcrm.entity.ActionLog;
import com.zingcrm.entity.ExpectedValue;
import com.zingcrm.entity.Opportunity;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.OpportunityForms;
import com.zingcrm.utility.CalendarTimeZone;
import com.zingcrm.utility.PropertiesHolder;

@Repository
public class OpportunityDAOImpl implements OpportunityDAO {
	
	 private static Logger log = Logger.getLogger(OpportunityDAOImpl.class
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
		public List<?> gridView(String companyName, String orgId,
				String sortId, String sortDir, String property,String userId,String keys)
				throws BusinessException {
			try {

				return ((List<?>) sessionFactory.getCurrentSession()
						.createSQLQuery("call getOpportunity (:orgId,:company,:sortId,:sortDir,:status,:userId,:keys)")
						.setString("orgId", orgId)
						.setString("company", companyName)
						.setString("sortId", sortId)
						.setString("sortDir", sortDir)
							.setString("status", property)
							.setString("userId", userId)
								.setString("keys", keys)
						.list());
			} catch (HibernateException e) {

				log.fatal("OPP_DAO_003", e);
				throw new BusinessException(
						"OpportunityDAOImpl ::gridView()");
			}
		}

		@Override
		public String saveOpp(OpportunityForms oppForm,String status,String oppKey) throws BusinessException {
			 String oppId="0"; 
			try {
				 Date createDate=calendar.getCurrentDateTime();
				 oppId=(String) sessionFactory.getCurrentSession()
					.createSQLQuery("call getSaveOpportunityJava (:leadId,:desc,:oppName,:createdBy,:createdDate,:status,:orgId,:default,:currencySymbol,:currencyValue,:probability,:closeDate,:oppStatus,:oppKey,:saveOPP,:competitorquote)")
					.setParameter("leadId", oppForm.getCompany())
					.setString("desc", oppForm.getDescription())
					.setString("oppName", oppForm.getOpportunity())
				    .setString("createdBy", oppForm.getCreatedBy())
				    .setDate("createdDate",createDate)
				    .setBoolean("status",Boolean.parseBoolean(status))
				    .setParameter("orgId", oppForm.getOrg())
				    .setBoolean("default", oppForm.getDefaultstate())
				    .setString("currencySymbol",oppForm.getExpectedVal())
				    .setParameter("currencyValue",oppForm.getExpectedValue())
				    .setParameter("probability",oppForm.getProbability())
				    .setDate("closeDate",calendar.getStringToDate(oppForm.getCloseDate()))
				    .setParameter("oppStatus", oppForm.getOppStatus())
					.setParameter("oppKey",oppKey)
					.setString("saveOPP",properties.getProperty("oppSave"))
					.setParameter("competitorquote", oppForm.getCompetitorquote()).list().get(0);
					
					
					
					
					/*Opportunity opp= (Opportunity) sessionFactory.getCurrentSession()
			                .createQuery("from Opportunity where leadId=:id and createdBy=:createdby and createdDate=:createddate")
			                .setParameter("id",oppForm.getCompany())
			                .setParameter("createdby", oppForm.getCreatedBy())
			                .setParameter("createddate", createDate).list().get(0);*/
				
	        } catch (HibernateException e) {
	            log.fatal("OPP_DAO_004", e);
	            throw new BusinessException(
	                    "OpportunityDAOImpl ::saveOpp()");
	        }
			return oppId;
		}

		@Override
		public List<?> getOppDetails(String oppId,String key) throws BusinessException {
			try{
			return ((List<?>) sessionFactory.getCurrentSession()
					.createSQLQuery("call getOpportunityInfo (:oppId,:keys)")
					.setParameter("oppId",Integer.parseInt(oppId))
					.setParameter("keys", key)
					.list());
		} catch (HibernateException e) {

			log.fatal("OPP_DAO_005", e);
			throw new BusinessException(
					"OpportunityDAOImpl ::getOppDetails()");
		}
		}

		@Override
		public void updateOpp(OpportunityForms oppForm,String oppKey) throws BusinessException {
			  Session session = null;  
			   Transaction tx = null; 
			   String statusId;
			try {
				 
				Opportunity opp= (Opportunity) sessionFactory.getCurrentSession()
		                .createQuery("from Opportunity where Id like :id")
		                .setParameter("id",oppForm.getId()).list().get(0);
				statusId=opp.getOppStatus();
					session = sessionFactory.openSession();  
					tx = session.beginTransaction();  
			    	opp.setCreatedBy(oppForm.getCreatedBy());
			    	opp.setCreatedDate(calendar.getStringToDates(oppForm.getCreatedDate()));
			    	opp.setLeadId(oppForm.getCompany());
			    	opp.setDocument(oppForm.getDocument());
			    	opp.setDescription(oppForm.getDescription());
			    	opp.setOpportunityName(oppForm.getOpportunity());
			    	opp.setStatus(oppForm.getStatus());
			    	opp.setModifiedBy(oppForm.getModifiedBy());
			    	opp.setModifiedDate(calendar.getCurrentDateTime());
			    	opp.setId(oppForm.getId());
			    	opp.setDefaultOpportunity(oppForm.getDefaultstate());
			    	opp.setCloseDate(calendar.getStringToDate(oppForm.getCloseDate()));
			    	opp.setCurrencySymbol(oppForm.getExpectedVal());
			    	opp.setCurrencyValue(oppForm.getExpectedValue());
			    	opp.setProbability(oppForm.getProbability());
			    	opp.setOppStatus(oppForm.getOppStatus());
			    	opp.setCompetitorquote(oppForm.getCompetitorquote());
		            sessionFactory.getCurrentSession().update(opp);
		            
		            if(!statusId.equals(oppForm.getOppStatus()))
					{
					 	ActionLog log=new ActionLog();
				    	log.setNewStateId(Integer.parseInt(oppForm.getOppStatus()));
				    	log.setRecordType(oppKey);
				    	log.setOldStateId(Integer.parseInt(statusId));
				    	log.setRecordId(oppForm.getId());
				    	log.setStatusDate(calendar.getCurrentDateTime());
				    	log.setUserId(oppForm.getCreatedBy());
				    	log.setComments(properties.getProperty("oppUpdate"));
				    	sessionFactory.getCurrentSession().save(log);
					}
			    	tx.commit();  
		        } catch (HibernateException e) {
		        	tx.rollback();  
		            log.fatal("OPP_DAO_006", e);
		            throw new BusinessException(
		                    "OpportunityDAOImpl ::updateOpp()");
		        }finally {session.close();}  
			
		}

		@Override
		public void deleteOpp(String oppId, String status)
				throws BusinessException {
			try {
			Opportunity opp=(Opportunity) sessionFactory.getCurrentSession().createQuery("from Opportunity where Id = :id").setParameter("id", Integer.parseInt(oppId)).list().get(0);
			opp.setStatus(calendar.getFlag(status));
			sessionFactory.getCurrentSession().update(opp);
			} catch (HibernateException e) {

				log.fatal("OPP_DAO_007", e);
				throw new BusinessException("OpportunityDAOImpl ::deleteOpp()");
			}
			
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Opportunity> getOppNameValidation(String leadId, String oppName,
				String status) throws BusinessException {
			try {
	            return (List<Opportunity>) sessionFactory.getCurrentSession()
	                    .createQuery("from Opportunity where leadId like :id and status=:status and opportunityName like :name")
	                    .setParameter("id",Integer.parseInt(leadId)).setBoolean("status",calendar.getFlag(status)).setParameter("name", oppName).list();
	        } catch (HibernateException e) {
	            log.fatal("OPP_DAO_008", e);
	            throw new BusinessException("OpportunityDAOImpl :: getLeadNameValidation()");
	        }
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Opportunity> getEditOppNameValidation(String leadId,
				String oppName, String oppId, String status)
				throws BusinessException {
			try {
	            return (List<Opportunity>) sessionFactory.getCurrentSession()
	                    .createQuery("from Opportunity where leadId like :id and status like :status and opportunityName like :name and id!=:leadid")
	                    .setParameter("id", Integer.parseInt(leadId)).setBoolean("status",calendar.getFlag(status)).setParameter("name", oppName).setParameter("leadid", Integer.parseInt(oppId)).list();
	        } catch (HibernateException e) {
	            log.fatal("OPP_DAO_009", e);
	            throw new BusinessException("OpportunityDAOImpl :: getEditLeadNameValidation()");
	        }
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Opportunity> getOpportunityList(String leadId,
				String status,String defaultOpp) throws BusinessException {
			 try {
				 if(defaultOpp.equals("%"))
				 {
					 return (List<Opportunity>) sessionFactory.getCurrentSession()
			                    .createQuery("from Opportunity where leadId like :id and status like :statusID")
			                    .setParameter("id",Integer.parseInt(leadId))
			                    .setBoolean("statusID",calendar.getFlag(status))
			                    .list();
				 }
				 else
				 {
		            return (List<Opportunity>) sessionFactory.getCurrentSession()
		                    .createQuery("from Opportunity where leadId like :id and status like :statusID and defaultOpportunity like :default")
		                    .setParameter("id",Integer.parseInt(leadId))
		                    .setBoolean("statusID",calendar.getFlag(status))
		                    .setBoolean("default",calendar.getFlag(defaultOpp)).list();
				 }
		        } catch (HibernateException e) {
		            log.fatal("OPP_DAO_010", e);
		            throw new BusinessException("OpportunityDAOImpl :: getCompanyList()");
		        }
		}
		
		   @SuppressWarnings("unchecked")
		    @Override
		    public final List<ExpectedValue> getExpectedValuelist() throws BusinessException {
		        try {
		            return (List<ExpectedValue>)sessionFactory.getCurrentSession()
		                    .createQuery("from ExpectedValue order by Id asc").list();
		        } catch (HibernateException e) {
		            log.fatal("OPP_DAO_011", e);
		            throw new BusinessException("OpportunityDAOImpl :: getExpectedValuelist()");
		        }
		    }


}
