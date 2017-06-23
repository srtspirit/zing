package com.zingcrm.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zingcrm.dao.ActivityDAO;
import com.zingcrm.dao.LeadRequestDAO;
import com.zingcrm.entity.Activity;
import com.zingcrm.entity.ActivityType;
import com.zingcrm.entity.BusinessPartnerContact;
import com.zingcrm.entity.LeadRequest;
import com.zingcrm.entity.Source;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.ActivityForms;
import com.zingcrm.forms.LeadRequestForms;
import com.zingcrm.jqgrid.xml.GridRow;
import com.zingcrm.jqgrid.xml.GridRows;
import com.zingcrm.jqgrid.xml.GridUserData;
import com.zingcrm.sendemail.EmailParams;
import com.zingcrm.sendemail.SendEmailService;
import com.zingcrm.service.CrudButtonService;

import com.zingcrm.service.LeadRequestService;
import com.zingcrm.utility.PropertiesHolder;

@Service
public  class LeadRequestServiceImpl implements LeadRequestService{

	
	@Autowired
	private PropertiesHolder properties;
	
	@Autowired
	private LeadRequestDAO leadreqDAO;

	@Autowired
	private CrudButtonService buttonService;
	
	@Autowired
	 private SendEmailService sendEmailService;
	
	
	private static Logger log = Logger.getLogger(ActivityServiceImpl.class
			.getName());
	
	private String xml;

	private static JAXBContext JAXBCONTEXT;
	private static Marshaller MARSHALLER;
	static {
		try {
			JAXBCONTEXT = JAXBContext.newInstance(GridRows.class);
			MARSHALLER = JAXBCONTEXT.createMarshaller();
			MARSHALLER.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
		} catch (PropertyException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	@Transactional
	public String gridView(String sortId, String sortDir, String strRoleId,
			String strOrgId, int intPage, int intLimit, String orgId,
			String leadId, String oppId,String userId) throws BusinessException {

			try {
				String actuserId = "",strUserId="";
				if (strRoleId.equals("1") && orgId.equals("%")) {
				} else {
					orgId = strOrgId;
				}
				
				if(Integer.parseInt(strRoleId)<Integer.parseInt(properties.getProperty("salesrep")))
				{
					strUserId="%";
					actuserId="%";
					
				}
				else 
				{
					strUserId=userId;
					actuserId=userId;
				}
				
				List<?> mList = leadreqDAO.gridView(orgId, leadId, oppId, sortId,
						sortDir, properties.getProperty("active"),strUserId,actuserId,properties.getProperty("activitykey"),strRoleId);

				OutputStream os = new ByteArrayOutputStream();

				int intStart = 0, intCount = 0, totalPages = 0, k = 0;

				List<GridRow> recordList = new ArrayList<GridRow>();
				if (mList != null) {
					intCount = mList.size();
					if (intCount > 0) {
						totalPages = (int) Math.ceil(Double.parseDouble(""
								+ intCount)
								/ Double.parseDouble("" + intLimit));
					} else if (intCount == 0) {
						totalPages = 0;
						intLimit = 0;
					}

					if (intPage > totalPages)
						intPage = totalPages;

					if (intPage == 1) {
						k = intPage;
					} else {
						intStart = intLimit * intPage - intLimit;
						k = intStart + 1;
					}

					int kk = 1;

					for (int i = intStart; i < mList.size()
							&& k <= intStart + intLimit; i++, k++) {
						Object[] array = (Object[]) mList.get(i);
						List<Object> columnList = new ArrayList<Object>();
						columnList.add(array[0]); // activity id
						columnList.add(StringEscapeUtils.escapeHtml(array[1].toString())); // Opp Name
						columnList.add(StringEscapeUtils.escapeHtml(array[2].toString())); // name
						columnList.add(StringEscapeUtils.escapeHtml(array[3].toString())); // type
						columnList.add(StringEscapeUtils.escapeHtml(array[4].toString())); // assigned To
						columnList.add(StringEscapeUtils.escapeHtml(array[5].toString())); // Due date
						columnList.add(StringEscapeUtils.escapeHtml(array[6].toString())); // is BP created?
					
						GridRow row = new GridRow();
						row.setId(kk++);
						row.setGridCell(columnList);
						recordList.add(row);
					}

					GridUserData userdata = new GridUserData();
					userdata.setValue(buttonService.getCRUDButtons(
							Integer.parseInt(strRoleId), "FEA_LEADREQUEST", strOrgId));
					GridRows gridRows = new GridRows();
					gridRows.setPage(intPage);
					gridRows.setRecords(intCount);
					gridRows.setTotal(totalPages);
					gridRows.setGridRow(recordList);
					gridRows.setUserData(userdata);

					MARSHALLER = JAXBCONTEXT.createMarshaller();
					MARSHALLER.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
					MARSHALLER.marshal(gridRows, os);
					xml = os.toString();
					os.close();
				}
			} catch (JAXBException e) {
				log.fatal("LEADREQ_SER_001", e);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return xml;
		}
	@Override
	@Transactional
	public String getLeadRequestEmailValidation(String stremail, String strOrgid,String struserid,String leadid)
			throws BusinessException {
		String strData;
		try {
			List<LeadRequest> li = leadreqDAO.getLeadRequestEmailValidation(stremail,strOrgid,struserid,leadid);
			
			if (li.size() != 0 ) {
				strData = "exists";
				
			} else {
				strData = "notexists";
			}
		} catch (Exception e) {
			log.fatal("LEADREQ_SER_002", e);
			throw new BusinessException(e.getMessage());
		}
		return strData;

	}
	
	
	@Override
	@Transactional
	public String getBusinessNameValidation(String strBP, String strOrgid,String struserid,String leadid)
			throws BusinessException {
		String strData;
		try {
			return leadreqDAO.getBusinessNameValidation(strBP,strOrgid,struserid,leadid);
			
		} catch (Exception e) {
			log.fatal("LEADREQ_SER_002", e);
			throw new BusinessException(e.getMessage());
		}

	}
	
	@Override
	@Transactional
	public JSONObject getSalesRep(Object Orgid) throws BusinessException, JSONException {
		JSONObject jsonbuild = new JSONObject();
        JSONArray jsonarray = new JSONArray();
        List<?> userList;
        JSONObject info = new JSONObject();
        try {
        	userList = (List<?>) leadreqDAO.getAssinedSalesRep(Orgid.toString(),properties.getProperty("active"));
            if (userList.size() != 0) {
                Iterator<?> i = userList.iterator();
                jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("success"));
                while (i.hasNext()) {
                	Object[] cont = (Object[]) i.next();
                	 info = new JSONObject();
                    info.put(properties.getProperty("key"),
                    		cont[0].toString());
                    info.put(properties.getProperty("value"),
                    		cont[2].toString());
                    jsonarray.put(info);
                }
                jsonbuild.put(properties.getProperty("result"), jsonarray);
            } else {
                jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("nodata"));
                jsonbuild.put(properties.getProperty("result"), jsonarray);
            }
        } catch (Exception e) {
            log.fatal("LEADREQ_SER_003", e);
            jsonbuild.put(properties.getProperty("status"),
                    properties.getProperty("error"));
            jsonbuild.put(properties.getProperty("result"), jsonarray);
        }
        return jsonbuild;
	}
	@Override
	@Transactional
	public String saveLeadRequest(LeadRequestForms leadreqform) throws BusinessException {
		try {
			String strData;
			List<LeadRequest> li = leadreqDAO.getLeadRequestEmailValidation(leadreqform.getEmail(),leadreqform.getOrgid(),leadreqform.getCreatedby(),"0");
			if (li.size() != 0) {
				return "exists";
			} else 
			{
				strData=leadreqDAO.getBusinessNameValidation(leadreqform.getBusinessname(),leadreqform.getOrgid(),leadreqform.getCreatedby(),"0");
				if(strData.equals("notexists"))
				{
					leadreqDAO.saveLeadReuest(leadreqform);
					  getLeadRequestEmail(leadreqform.getLeadUserName(),leadreqform.getSalesleadid(),leadreqform.getBusinessname());
					return "success"; 
				}
				else
				{
					return strData;
				}
			}
		} catch (Exception e) {
			log.fatal("LEADREQ_SER_004", e);
			throw new BusinessException(e.getMessage());
		}

	}
	
	private void getLeadRequestEmail(String createdBy, String assignedTo,
			String businessname) {
			
			String emailFrom = properties.getProperty("emailFrom");
			String[] strTo=new String[1];
		 	strTo[0]=assignedTo;
            String[] accountEmailTo = strTo;
            String subject = properties.getProperty("createLeadSubject")+" "+businessname+" "+properties.getProperty("createLeadSubject2");
            String content1 = properties.getProperty("createLead1")+" "+businessname+" ";
            String content2 = properties.getProperty("createLead2")+" "+createdBy+" ";
            String content3 = properties.getProperty("createLead3")+" "+assignedTo+" ";
           // String content4=properties.getProperty("createActivity4")+" "+dueDate+" ";
            String content4=properties.getProperty("createLead4");
            sendEmail(emailFrom, accountEmailTo, subject, content1, content2,
            		content3,content4);
		
		
	}
	
	
	private void getEditLeadRequestEmail(String createdBy, String assignedTo,
			String businessname) {
			
			String emailFrom = properties.getProperty("emailFrom");
			String[] strTo=new String[1];
		 	strTo[0]=assignedTo;
            String[] accountEmailTo = strTo;
            String subject = properties.getProperty("editLeadSubject")+" "+businessname+" "+properties.getProperty("editLeadSubject2");
            String content1 = properties.getProperty("editLead1")+" "+businessname+" ";
            String content2 = properties.getProperty("editLead2")+" "+createdBy+" ";
            String content3 = properties.getProperty("editLead3")+" "+assignedTo+" ";
           // String content4=properties.getProperty("createActivity4")+" "+dueDate+" ";
            String content4=properties.getProperty("editLead4");
            sendEmail(emailFrom, accountEmailTo, subject, content1, content2,
            		content3,content4);
		
		
	}
	
	private void sendEmail(String emailFrom, String[] accountEmailTo,
			String subject, String content1, String content2, String content3,
			String content4) {
		  final EmailParams emailParams = new EmailParams();
	        try {
	          
	            emailParams.setEmailFrom(emailFrom);
	            emailParams.setEmailTos(accountEmailTo);
	            emailParams.setEmailSubject(subject);
	            emailParams.setEmailBody(content1+content2+content3+content4);
	            Thread PictureLoader = new Thread(new Runnable() {
	                public void run() {
	                	try {
	                		 sendEmailService.sendEmail(emailParams);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                }
	            });

	            PictureLoader.start();
	            
	        } catch (Exception e) {
	        	log.fatal("LEADREQ_SER_005", e);
	            e.printStackTrace();
	        } finally {
	           // saveEmailLog(orgId, emailFrom, accountEmailTo, subject, status);
	        }
		
	}

	@Override
	@Transactional
	public String getLeadRequestDetails(String leadrequestid,String OrgId) throws BusinessException {
		JSONObject jsonbuild = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		try {
			List<LeadRequest> list = (List<LeadRequest>) leadreqDAO.getLeadRequestDetails(leadrequestid,OrgId);
			if (list.size() != 0) {
				Iterator<LeadRequest> i = list.iterator();
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("success"));
				while (i.hasNext()) {
					LeadRequest array =  i.next();
					JSONObject info = new JSONObject();
					info.put("LRId", array.getId());
					info.put("Date", array.getCreateddate());
					info.put("BName", array.getBusinessname());
					info.put("CName", array.getContactname());
					info.put("Email", array.getEmail());
					info.put("Phone", array.getPhonenumber());
					info.put("PhoneEx", array.getExtension());
					info.put("Note", array.getNotes());
					info.put("Website", array.getWebsite());
					info.put("Source", array.getSource());
					info.put("SLeadId", array.getSalesleadid());
					info.put("Createdby", array.getLeadReqCreatedby());
					info.put("isBPCreated", array.getIsBPCreated());
					info.put("leadreqQualFlag", array.getLeadreqQualFlag());
					jsonarray.put(info);
				}
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			} else {
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("nodata"));
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			}
		} catch (Exception e) {
			log.fatal("LEADREQ_SER_005", e);
			throw new BusinessException(e.getMessage());
		}
		return jsonbuild.toString();
	}
	
	@Override
	@Transactional
	public String editLeadRequest(LeadRequestForms leadreqfrm) throws BusinessException {
		String strdata="";
		try {
			
			List<LeadRequest> li = leadreqDAO.getLeadRequestEmailValidation(leadreqfrm.getEmail(),leadreqfrm.getOrgid(),leadreqfrm.getCreatedby(),String.valueOf(leadreqfrm.getId()));
			if (li.size() != 0) {
				strdata= "exists";
			} 
			else 
			{
				strdata=leadreqDAO.getBusinessNameValidation(leadreqfrm.getBusinessname(),leadreqfrm.getOrgid(),leadreqfrm.getCreatedby(),String.valueOf(leadreqfrm.getId()));
				if(strdata.equals("notexists"))
				{
					strdata=leadreqDAO.editLeadRequest(leadreqfrm); 
					getEditLeadRequestEmail(leadreqfrm.getLeadUserName(),strdata,leadreqfrm.getBusinessname());
					return "success";
				}
				else
				{
					return strdata;
				}
					
		    }
				
		} catch (Exception e) {
			log.fatal("LEADREQ_SER_006", e);
			throw new BusinessException(e.getMessage());
		}
		return strdata;
	}
	
	@Override
	@Transactional
	public JSONObject getSalesRepdetails(String Orgid) throws BusinessException, JSONException {
		JSONObject jsonbuild = new JSONObject();
        JSONArray jsonarray = new JSONArray();
        List<?> userList;
        JSONObject info = new JSONObject();
        try {
        	userList = (List<?>) leadreqDAO.getSalesRepdetails(Orgid.toString());
            if (userList.size() != 0) {
                Iterator<?> i = userList.iterator();
                jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("success"));
                while (i.hasNext()) {
                	Object[] cont = (Object[]) i.next();
                	 info = new JSONObject();
                    info.put(properties.getProperty("key"),
                    		cont[0].toString());
                    info.put(properties.getProperty("value"),
                    		cont[2].toString());
                    jsonarray.put(info);
                }
                jsonbuild.put(properties.getProperty("result"), jsonarray);
            } else {
                jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("nodata"));
                jsonbuild.put(properties.getProperty("result"), jsonarray);
            }
        } catch (Exception e) {
            log.fatal("LEADREQ_SER_007", e);
            jsonbuild.put(properties.getProperty("status"),
                    properties.getProperty("error"));
            jsonbuild.put(properties.getProperty("result"), jsonarray);
        }
        return jsonbuild;
	}
	
	@Override
	@Transactional
	public LeadRequest getLeadRequestDetailsList(String leadReqId,String orgId)
			throws BusinessException {
		LeadRequest lead = null;
		try {
			List<LeadRequest> list = (List<LeadRequest>) leadreqDAO.getLeadRequestDetails(leadReqId,orgId);
			if(list.size()>0)
			{
				Iterator<LeadRequest> i=list.iterator();
				while(i.hasNext())
				{
					lead= i.next();
				}
			}
		} catch (Exception e) {
			log.fatal("LEADREQ_SER_005", e);
			throw new BusinessException(e.getMessage());
		}
		return lead;
	}
	@Override
	@Transactional
	public JSONObject sourcelist() throws JSONException{
		JSONObject jsonbuild = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		List<Source> datalist;
		try{
			datalist = leadreqDAO.getSourcedetails();
			if(datalist.size()!=0){
				Iterator<Source> i=datalist.iterator();
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("success"));
				while(i.hasNext()){
					Source sval=i.next();
					JSONObject info = new JSONObject();
					info.put(properties.getProperty("key"),sval.getId());
					info.put(properties.getProperty("value"), sval.getName());
					jsonarray.put(info);
				}
				jsonbuild.put("result", jsonarray);
			}else {
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("nodata"));
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			}
		}catch(Exception e){
			log.fatal("LEADREQ_SER_006", e);
			jsonbuild.put(properties.getProperty("status"),
					properties.getProperty("error"));
			jsonbuild.put(properties.getProperty("result"), jsonarray);
		}
		return jsonbuild;
	}
}
