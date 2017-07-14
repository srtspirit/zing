package com.zingcrm.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zingcrm.dao.BusinessPartnerDAO;
import com.zingcrm.entity.ActionLog;
import com.zingcrm.entity.City;
import com.zingcrm.entity.Document;
import com.zingcrm.entity.BusinessPartner;
import com.zingcrm.entity.BusinessPartnerContact;
import com.zingcrm.entity.LeadRequest;
import com.zingcrm.entity.Opportunity;
import com.zingcrm.entity.Source;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.LeadForms;
import com.zingcrm.utility.CalendarTimeZone;
import com.zingcrm.utility.PropertiesHolder;
import com.zingcrm.utility.ReadExcelFile;
@Repository
public class BusinessPartnerDAOImpl implements BusinessPartnerDAO {
	
	  private static Logger log = Logger
	            .getLogger(BusinessPartnerDAOImpl.class.getName());

	    @Autowired
	    private SessionFactory sessionFactory;

	    @Autowired
	    private CalendarTimeZone calendar;
	    
	    @Autowired
	    private PropertiesHolder properties;
	    
	    @Autowired
	    private ReadExcelFile excelFile;
	    
		@Override
		public List<?> gridView(String companyName, String orgId,
				String sortId, String sortDir,String status,String userId) throws BusinessException {
			try {

				return ((List<?>) sessionFactory.getCurrentSession()
						.createSQLQuery("call getBusinesspartner (:orgId,:companyName,:sortId,:sortDir,:status,:userId)")
						.setString("orgId", orgId)
						.setString("companyName", companyName)
						.setString("sortId", sortId)
						.setString("sortDir", sortDir)
						.setString("status", status)
						.setString("userId", userId)
						.list());
			} catch (HibernateException e) {
				log.fatal("LEA_DAO_002", e);
				throw new BusinessException(
						"LeadDAOImpl ::gridView()");
			}
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Source> sourceList() throws BusinessException {
			   try {
		            return (List<Source>) sessionFactory.getCurrentSession()
		                    .createQuery("from Source")
		                    .list();
		        } catch (HibernateException e) {
		            log.fatal("LEA_DAO_003", e);
		            throw new BusinessException("LeadDAOImpl :: sourceList()");
		        }
			
		}

		@Override
		public int saveLead(LeadForms leadForm) throws BusinessException {
			  Session session = null;  
			   Transaction tx = null;  
		    try {
		    	
		    	session = sessionFactory.openSession();
				tx = session.beginTransaction();  
		    	BusinessPartner lead=new BusinessPartner();
		    	lead.setAccountId(leadForm.getOrg());
		    	lead.setAddress(leadForm.getAddress());
		    	lead.setAddress2(leadForm.getAddress2());
		    	lead.setAssignedRep(leadForm.getRep());
		    	lead.setCity(leadForm.getCity());
		    	lead.setCreatedBy(leadForm.getCreatedBy());
		    	lead.setCreatedDate(calendar.getCurrentDateTime());
		    	lead.setLeadDate(calendar.getStringToDate(leadForm.getDate()));
		    	lead.setName(leadForm.getLead());
		    	lead.setSource(leadForm.getSource());
		    	lead.setZipCode(leadForm.getZipcode());
		    	lead.setState(leadForm.getState());
		    	//lead.setGpsLocation(leadForm.getGPS());
		    	lead.setStatus(leadForm.getStatus());
		    	lead.setCustomFlag(leadForm.getCustomFlag());
		    	lead.setCountry(leadForm.getCountry());
		    	lead.setLatitude(leadForm.getLatitude());
		    	lead.setLongitude(leadForm.getLongitude());
		    	lead.setFax(leadForm.getFax());
		    	lead.setWebsite(leadForm.getWebsite());
		    	lead.setPrivateFlag(leadForm.isActiveFlag());
		    	lead.setCurrentSupplier(leadForm.getCurrentSupplier());
		    	lead.setLeadnotes(leadForm.getLeadnotes());
	            sessionFactory.getCurrentSession().save(lead);
	            
	            BusinessPartnerContact cont=new BusinessPartnerContact();
	            cont.setPrimaryDepartment(leadForm.getPrimaryDept());
	            cont.setPrimaryLastName(leadForm.getPrimaryLastName());
	            cont.setSecondaryLastName(leadForm.getSecondaryLastName());
	            cont.setLeadId(lead.getId());
	            cont.setPrimaryEmail(leadForm.getPrimaryEmail());
	            cont.setPrimaryMobile(leadForm.getPrimaryMobile());
	            cont.setPrimaryPhone(leadForm.getPrimaryPhone());
	            cont.setPrimaryphoneExtension(leadForm.getPrimaryphoneExtension());
	            cont.setPrimaryFirstName(leadForm.getPrimaryName());
	            cont.setSecondaryDepartment(leadForm.getSecondaryDept());
	            cont.setSecondaryEmail(leadForm.getSecondaryEmail());
	            cont.setSecondaryMobile(leadForm.getSecondaryMobile());
	            cont.setSecondaryFirstName(leadForm.getSecondaryName());
	            cont.setSecondaryPhone(leadForm.getSecondaryPhone());
	            cont.setSecondaryphoneExtension(leadForm.getSecondaryphoneExtension());
	            cont.setThirdConName(leadForm.getThirdConName());
	            cont.setThirdConLastName(leadForm.getThirdConLastName());
	            cont.setThirdConEmail(leadForm.getThirdConEmail());
	            cont.setThirdConDept(leadForm.getThirdConDept());
	            cont.setThirdConPhone(leadForm.getThirdConPhone());
	            cont.setThirdConphoneExt(leadForm.getThirdConphoneExt());
	            cont.setThirdConMobile(leadForm.getThirdConMobile());
	            sessionFactory.getCurrentSession().save(cont);
	 			ActionLog logs=new ActionLog();
		    	logs.setRecordType(properties.getProperty("leadkey"));
		    	logs.setRecordId(lead.getId());
		    	logs.setStatusDate(calendar.getCurrentDateTime());
		    	logs.setUserId(leadForm.getCreatedBy());
		    	logs.setCustomFlag(leadForm.getCustomFlag());
		    	logs.setNewStateId(1);
		    	logs.setComments(properties.getProperty("bpSave"));
		    	if(leadForm.getCustomFlag())
		    	{
		    		logs.setNewStateId(1);
		    	}
		    	else
		    	{
		    		logs.setNewStateId(0);
		    	}
		    	sessionFactory.getCurrentSession().save(logs);
	            Document doc= (Document) sessionFactory.getCurrentSession()
	                    .createQuery("from Document where OrgId like :id")
	                    .setParameter("id", leadForm.getOrg()).list().get(0);
	            Opportunity opp=new Opportunity();
	            opp.setLeadId(lead.getId());
	            opp.setCreatedBy(leadForm.getCreatedBy());
	            opp.setCurrencySymbol(""+leadForm.getCountry());
	            opp.setOpportunityName(leadForm.getLead());
	            opp.setDefaultOpportunity(true);
	            opp.setDescription(leadForm.getLead());
	            opp.setDocument(doc.getDocumentId());
	            opp.setCreatedDate(calendar.getCurrentDateTime());
	            opp.setOppStatus(properties.getProperty("oppOpen"));
	            sessionFactory.getCurrentSession().save(opp);
	            doc.setDocumentId(doc.getDocumentId()+1);
	            sessionFactory.getCurrentSession().update(doc);
	            
	            if(leadForm.getLeadreqid()!=null && leadForm.getLeadreqid()!="" ){
	          int lreq=(int)sessionFactory.getCurrentSession().createSQLQuery("update leadrequest set isBPCreated='1',Modifiedby=:modifiedby,ModifiedDate=:date  where LeadRequestId=:leadreqid and orgid=:Orgid")
	            		.setParameter("modifiedby",leadForm.getCreatedBy())
	            		.setParameter("date", calendar.getCurrentDateTime())
	            		.setParameter("leadreqid",Integer.parseInt(leadForm.getLeadreqid()))
	            		.setParameter("Orgid", leadForm.getOrg())
	            		.executeUpdate();
	            }
	            
	            logs=new ActionLog();
		    	logs.setRecordType(properties.getProperty("oppkey"));
		    	logs.setRecordId(opp.getId());
		    	logs.setStatusDate(calendar.getCurrentDateTime());
		    	logs.setUserId(leadForm.getCreatedBy());
		    	logs.setCustomFlag(leadForm.getCustomFlag());
		    	logs.setComments(properties.getProperty("oppSave"));
		    	logs.setNewStateId(Integer.parseInt(properties.getProperty("oppOpen")));
		    	 sessionFactory.getCurrentSession().save(logs);
		 		tx.commit();  
		 		return lead.getId();
	        } catch (HibernateException e) {
	        	 tx.rollback();  
	            log.fatal("LEA_DAO_003", e);
	            throw new BusinessException(
	                    "LeadDAOImpl ::saveLead()");
	        }finally{session.close();}
		}

		@Override
		public void deleteLead(String leadId,String status) throws BusinessException {
			 try {
				 
				 BusinessPartner lead=(BusinessPartner) sessionFactory.getCurrentSession().createQuery("from BusinessPartner where Id = :id").setParameter("id", Integer.parseInt(leadId)).list().get(0);
				 lead.setStatus(calendar.getFlag(status));
					sessionFactory.getCurrentSession().update(lead);
					
		        } catch (HibernateException e) {
		            log.fatal("LEA_DAO_006", e);
		            throw new BusinessException("LeadDAOImpl ::deleteLead()");
		        }
			
		}

		@Override
		public List<?> getLeadDetails(String leadId) throws BusinessException {
			try {

				return ((List<?>) sessionFactory.getCurrentSession()
						.createSQLQuery("call getBusinesspartnerInfo(:leadId)")
						.setParameter("leadId",Integer.parseInt(leadId))
						.list());
			} catch (HibernateException e) {

				log.fatal("LEA_DAO_007", e);
				throw new BusinessException(
						"LeadDAOImpl ::getLeadDetails()");
			}
		}
		

		@Override
		public void updateLead(LeadForms leadForm) throws BusinessException {
			//AndreyV: commented out session & transaction because transactions are demarcated on the level above (@Transactional)
			//and I need it there because I send jms message and we don't want to commit this transaction here and fail later on jms 
			//Session session = null;  
			   //Transaction tx = null;  
			   boolean flag;
		    try {
		    	//session = sessionFactory.getCurrentSession();
				//tx = session.beginTransaction();  
				BusinessPartner lead=(BusinessPartner) sessionFactory.getCurrentSession().createQuery("from BusinessPartner where Id = :id").setParameter("id",leadForm.getId()).list().get(0);
				flag=lead.getCustomFlag();
		    	lead.setAccountId(leadForm.getOrg());
		    	lead.setAddress(leadForm.getAddress());
		    	lead.setAddress2(leadForm.getAddress2());
		    	lead.setAssignedRep(leadForm.getRep());
		    	lead.setCity(leadForm.getCity());
		    	lead.setCreatedBy(leadForm.getCreatedBy());
		    	lead.setCreatedDate(calendar.getStringToDateTime(leadForm.getCreatedDate()));
		    	lead.setLeadDate(calendar.getStringToDate(leadForm.getDate()));
		    	lead.setName(leadForm.getLead());
		    	lead.setSource(leadForm.getSource());
		    	lead.setZipCode(leadForm.getZipcode());
		    	lead.setModifiedBy(leadForm.getModifiedBy());
		    	lead.setModifiedDate(calendar.getCurrentDateTime());
		    	lead.setId(leadForm.getId());
		    	lead.setState(leadForm.getState());
		    	lead.setStatus(leadForm.getStatus());
		    	lead.setLatitude(leadForm.getLatitude());
		    	lead.setLongitude(leadForm.getLongitude());
		    	lead.setCustomFlag(leadForm.getCustomFlag());
		    	lead.setCountry(leadForm.getCountry());
		    	lead.setFax(leadForm.getFax());
		    	lead.setWebsite(leadForm.getWebsite());
		    	lead.setPrivateFlag(leadForm.isActiveFlag());
		    	lead.setCurrentSupplier(leadForm.getCurrentSupplier());
		    	lead.setLeadnotes(leadForm.getLeadnotes());
	            sessionFactory.getCurrentSession().update(lead);
	            
	            BusinessPartnerContact cont=new BusinessPartnerContact();
	            cont.setId(leadForm.getContId());
	            cont.setPrimaryDepartment(leadForm.getPrimaryDept());
	            cont.setLeadId(leadForm.getId());
	            cont.setPrimaryEmail(leadForm.getPrimaryEmail());
	            cont.setPrimaryMobile(leadForm.getPrimaryMobile());
	            cont.setPrimaryPhone(leadForm.getPrimaryPhone());
	            cont.setPrimaryphoneExtension(leadForm.getPrimaryphoneExtension());
	            cont.setPrimaryFirstName(leadForm.getPrimaryName());
	            cont.setSecondaryDepartment(leadForm.getSecondaryDept());
	            cont.setSecondaryEmail(leadForm.getSecondaryEmail());
	            cont.setSecondaryMobile(leadForm.getSecondaryMobile());
	            cont.setSecondaryFirstName(leadForm.getSecondaryName());
	            cont.setSecondaryPhone(leadForm.getSecondaryPhone());
	            cont.setSecondaryphoneExtension(leadForm.getSecondaryphoneExtension());
	            cont.setPrimaryLastName(leadForm.getPrimaryLastName());
	            cont.setSecondaryLastName(leadForm.getSecondaryLastName());
	            cont.setThirdConName(leadForm.getThirdConName());
	            cont.setThirdConLastName(leadForm.getThirdConLastName());
	            cont.setThirdConEmail(leadForm.getThirdConEmail());
	            cont.setThirdConDept(leadForm.getThirdConDept());
	            cont.setThirdConPhone(leadForm.getThirdConPhone());
	            cont.setThirdConphoneExt(leadForm.getThirdConphoneExt());
	            cont.setThirdConMobile(leadForm.getThirdConMobile());
	            sessionFactory.getCurrentSession().update(cont);
	            
	            
	            if(flag!=leadForm.getCustomFlag())
		 		{
		 			ActionLog log=new ActionLog();
			    	log.setRecordType(properties.getProperty("leadkey"));
			    	log.setRecordId(lead.getId());
			    	log.setStatusDate(calendar.getCurrentDateTime());
			    	log.setUserId(leadForm.getCreatedBy());
			    	log.setCustomFlag(leadForm.getCustomFlag());
			    	log.setComments(properties.getProperty("bpUpdate"));
			    	if(flag)
			    	{
			    		log.setOldStateId(1);
			    	}
			    	else
			    	{
			    		log.setOldStateId(0);
			    	}
			    	if(leadForm.getCustomFlag())
			    	{
			    		log.setNewStateId(1);
			    	}
			    	else
			    	{
			    		log.setNewStateId(0);
			    	}
			    	sessionFactory.getCurrentSession().save(log);
		 		}
	            //tx.commit();  
	        } catch (HibernateException e) {
	        	 //tx.rollback();  
	            log.fatal("LEA_DAO_008", e);
	            throw new BusinessException(
	                    "LeadDAOImpl ::updateLead()");
	        }//finally {session.close();}  
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<BusinessPartner> getCompanyList(String orgId,String status,String userId) throws BusinessException {
		        try {
		        	
		        	if(orgId.equals("%"))
		        	{
		        		 return (List<BusinessPartner>) sessionFactory.getCurrentSession()
				                    .createQuery("from BusinessPartner where status like :statusId and assignedRep like :user order by name asc")
				                   .setBoolean("statusId",calendar.getFlag(status)).setParameter("user",userId).list();
		        	}
		        	else
		        		{
		        		 return (List<BusinessPartner>) sessionFactory.getCurrentSession()
				                    .createQuery("from BusinessPartner where accountId like :id and status like :statusId and assignedRep like :user order by name asc")
				                    .setParameter("id", Integer.parseInt(orgId)).setBoolean("statusId",calendar.getFlag(status)).setParameter("user",userId).list();
		        		}
		           
		        } catch (HibernateException e) {
		            log.fatal("LEA_DAO_009", e);
		            throw new BusinessException("LeadDAOImpl :: getCompanyList()");
		        }
		    }


		@SuppressWarnings("unchecked")
		@Override
		public List<BusinessPartner> getLeadNameValidation(String orgId, String leadName,String status)
				throws BusinessException {
			
			try {
	            return (List<BusinessPartner>) sessionFactory.getCurrentSession()
	                    .createQuery("from BusinessPartner where accountId like :id and status like :status and name like :name")
	                    .setParameter("id", Integer.parseInt(orgId)).setBoolean("status", calendar.getFlag(status)).setParameter("name", leadName).list();
	        } catch (HibernateException e) {
	            log.fatal("LEA_DAO_010", e);
	            throw new BusinessException("LeadDAOImpl :: getLeadNameValidation()");
	        }
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<BusinessPartner> getEditLeadNameValidation(String orgId,
				String leadName, String leadId,String status) throws BusinessException {
			try {
	            return (List<BusinessPartner>) sessionFactory.getCurrentSession()
	                    .createQuery("from BusinessPartner where accountId like :id and status=:status and name like:name and id!=:leadid")
	                    .setParameter("id", Integer.parseInt(orgId)).setBoolean("status",calendar.getFlag(status)).setParameter("name", leadName).setParameter("leadid", Integer.parseInt(leadId)).list();
	        } catch (HibernateException e) {
	            log.fatal("LEA_DAO_011", e);
	            throw new BusinessException("LeadDAOImpl :: getEditLeadNameValidation()");
	        }
		}

		@Override
		public List<?> getDeviceLeadsInfo(String strUserId, String strOrgId,String status)
				throws BusinessException {
			try {

				return ((List<?>) sessionFactory.getCurrentSession()
						.createSQLQuery("call getDeviceLeadsInfo (:userId,:orgId,:status)")
						.setString("userId", strUserId)
						.setString("orgId", strOrgId)
						.setString("status", status)
						.list());
			} catch (HibernateException e) {

				log.fatal("LEA_DAO_012", e);
				throw new BusinessException(
						"LeadDAOImpl ::getDeviceLeadsInfo()");
			}
		}

		@Override
		public List<?> exportLead(String companyName, String orgId,String status)
				throws BusinessException {

				try {

					return ((List<?>) sessionFactory.getCurrentSession()
							.createSQLQuery("call getExportBP(:orgId,:companyName,:status)")
							.setString("orgId", orgId)
							.setString("companyName", companyName)
							.setString("status", status)
							.list());
				} catch (HibernateException e) {

					log.fatal("LEA_DAO_014", e);
					throw new BusinessException(
							"LeadDAOImpl ::exportLead()");
				}
		}


		@Override
		@Transactional
		public void updateLatitude(int leadId, String latitude, String longitude)
				throws BusinessException {
			try{
				BusinessPartner lead=(BusinessPartner) sessionFactory.getCurrentSession().createQuery("from BusinessPartner where BPID=:id").setParameter("id",leadId).list().get(0);
				lead.setLatitude(latitude);
				lead.setLongitude(longitude);
				sessionFactory.getCurrentSession().update(lead);
			}catch (HibernateException e) { 
			
	          log.fatal("LEA_SER_005", e);
	          throw new BusinessException(
	                  "LeadDAOImpl ::updateLatitude() ");
	      } 
		}
	   @Override
	    public Object setUploadExcelFile(List<BusinessPartner> leadList,
				List<BusinessPartnerContact> leadContacts, List<LeadForms> leadbean, String strPath) throws BusinessException {
			  Session session = null;  
			   Transaction tx = null; 
			  
				session = sessionFactory.openSession();  
				 tx = session.beginTransaction();  
		   try {
	        	
	            int count = 2, typeCount = 0, successfile = 0;
	            boolean isErrorLog = false;
	            ArrayList<?> errlist = null, errListLog = null;
	            String strCompanyData = "";
	            String strCityData = "";
	        	 
	            if (leadList.size() != 0) {
	                Iterator<BusinessPartner> i = leadList.iterator();
	                while (i.hasNext()) 
	                {
	                	BusinessPartner leads = i.next();
	                    BusinessPartnerContact leadContact = leadContacts.get(typeCount);
	                    LeadForms bean = leadbean.get(typeCount++);
	                    
	                    @SuppressWarnings("unchecked")
						List<BusinessPartner> leadlist = (List<BusinessPartner>) sessionFactory
	                            .getCurrentSession()
	                            .createQuery(
	                                    "from BusinessPartner where name like :name and status=:status")
	                            .setParameter("name", leads.getName())
	                            .setBoolean("status", calendar.getFlag(properties.getProperty("active")))
	                            .list();
	                    strCompanyData="";
	                    if (leadlist.size() != 0) {
	                    	strCompanyData=properties.getProperty("duplicatename");
	                    }
	                    strCityData="";
	                    @SuppressWarnings("unchecked")
						List<City> citylist =  (List<City>) sessionFactory
	                            .getCurrentSession()
	                            .createQuery(
	                                    "from City where stateId like :name and Name like :cityName")
	                            .setParameter("name", leads.getState())
	                            .setParameter("cityName", bean.getCityName())
	                            .list();
	                    if(citylist.size()!=0)
	                    {
	                    	Iterator<City> cityIter=citylist.iterator();
	                    	while(cityIter.hasNext())
	                    	{
	                    		City city=(City) cityIter.next();
	                    		leads.setCity(city.getId());
	                    	}
	                    }
	                    else
	                    {
	                    	strCityData=properties.getProperty("citynamenotavailable");
	                    
	                    }
	                    errlist = excelFile.residentEntityValidation(leads, leadContact,
	                    		bean, count++,strCompanyData,strCityData);
	                   
	                    // implement a private local method to validate with
	                    // Resident Entity
	                    if (errlist == null) 
	                    {
	                    	leads.setStatus(calendar.getFlag(properties.getProperty("active")));
	                    	if(bean.getCustomFlagName()!=null)
	                    	{
		                    	if(bean.getCustomFlagName().toLowerCase().equals("yes"))
		                    	{
		                    		leads.setCustomFlag(true);
		                    	}
		                    	else
		                    	{
		                    		
		                    	}
	                    	}
	                    	else
	                    	{
	                    		leads.setCustomFlag(false);
	                    	}
	                    	//leads.setGpsLocation(geocodingService.searchLoc(leads.getAddress()+","+leads.getAddress2()+","+leads.getCity()+","+leads.getState()+","+leads.getZipCode()));
	                        sessionFactory.getCurrentSession().save(leads);
	                        leadContact.setLeadId(leads.getId());
	                        sessionFactory.getCurrentSession().save(leadContact);
	                        
	                        Document doc= (Document) sessionFactory.getCurrentSession()
	        	                    .createQuery("from Document where OrgId like :id")
	        	                    .setParameter("id", leads.getAccountId()).list().get(0);
	        	            Opportunity opp=new Opportunity();
	        	            opp.setLeadId(leads.getId());
	        	            opp.setCreatedBy(leads.getCreatedBy());
	        	            opp.setCurrencySymbol(""+leads.getCountry());
	        	            opp.setOpportunityName(leads.getName());
	        	            opp.setDefaultOpportunity(true);
	        	            opp.setDescription(leads.getName());
	        	            opp.setDocument(doc.getDocumentId());
	        	            opp.setCreatedDate(calendar.getCurrentDateTime());
	        	            opp.setOppStatus(properties.getProperty("oppOpen"));
	        	            sessionFactory.getCurrentSession().save(opp);
	        	            doc.setDocumentId(doc.getDocumentId()+1);
	        	            sessionFactory.getCurrentSession().update(doc);
	                        
	                        ActionLog log=new ActionLog();
	        		    	log.setRecordType(properties.getProperty("leadkey"));
	        		    	log.setRecordId(leads.getId());
	        		    	log.setStatusDate(calendar.getCurrentDateTime());
	        		    	log.setUserId(leads.getCreatedBy());
	        		    	log.setCustomFlag(false);
	        		    	log.setNewStateId(1);
	        		    	log.setComments(properties.getProperty("bpSave"));
	        		    	if(log.isCustomFlag())
	        		    	{
	        		    		log.setNewStateId(1);
	        		    	}
	        		    	else
	        		    	{
	        		    		log.setNewStateId(0);
	        		    	}
	        		    	
	        		    	sessionFactory.getCurrentSession().save(log);
	        		    	
	        		    	log=new ActionLog();
	        		    	log.setRecordType(properties.getProperty("oppkey"));
	        		    	log.setRecordId(opp.getId());
	        		    	log.setStatusDate(calendar.getCurrentDateTime());
	        		    	log.setUserId(leads.getCreatedBy());
	        		    	log.setCustomFlag(leads.getCustomFlag());
	        		    	log.setComments(properties.getProperty("oppSave"));
	        		    	log.setNewStateId(Integer.parseInt(properties.getProperty("oppOpen")));
	        		    	sessionFactory.getCurrentSession().save(log);
	                        successfile++;
	                    }
	                    else {
	                        errListLog = errlist;
	                        isErrorLog = true;
	                    }
	                }
	                if (isErrorLog) {
	                	// tx.rollback();  
	                    return excelFile.appendErrorsToFile(errListLog, strPath,
	                            successfile);
	                } else {
	                	 tx.commit();  
	                   return "success";
	                }
	            }
	            else
	            {
	                return "nodata";
	            }

	        	
	            
	        } catch (HibernateException e) { 
	        	  tx.rollback();  
	            log.fatal("LEA_DAO_015", e);
	            throw new BusinessException(
	                    "LeadDAOImpl ::setUploadExcelFile() ");
	        }
		   finally {session.close();}  
	    }
	   
	   
	   String formatTeleNumber(String s) 
		{
		    StringBuffer number = new StringBuffer();

		    if (s.length() == 0) {

		        return "(000)000-0000";
		    }

		    // Strip all non-numbers
		    for (int i = 0; i < s.length(); i++) {

		        if (Character.isDigit(s.charAt(i))) {
		           number.append(s.charAt(i));
		        }
		    }

		    // Pad with 0s
		    for (int i = number.length(); i < 10; i++) {

		        number.insert(0, '0');
		    }

		    // Format telephone number to (###)###-####
		    number = new StringBuffer(number.substring(number.length() - 10));
		    number.insert(0, '(');
		    number.insert(4, ')');
		    number.insert(8, '-');

		    return number.toString();
		}

	

}


