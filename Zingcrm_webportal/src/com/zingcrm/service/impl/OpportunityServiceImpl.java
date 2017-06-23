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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zingcrm.dao.ActivityDAO;
import com.zingcrm.dao.OpportunityDAO;
import com.zingcrm.entity.Country;
import com.zingcrm.entity.ExpectedValue;
import com.zingcrm.entity.Opportunity;
import com.zingcrm.entity.Status;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.OpportunityForms;
import com.zingcrm.jqgrid.xml.GridRow;
import com.zingcrm.jqgrid.xml.GridRows;
import com.zingcrm.jqgrid.xml.GridUserData;
import com.zingcrm.service.CountryService;
import com.zingcrm.service.CrudButtonService;
import com.zingcrm.service.BusinessPartnerService;
import com.zingcrm.service.OpportunityService;
import com.zingcrm.service.OrgService;
import com.zingcrm.service.TimeZoneService;
import com.zingcrm.utility.CalendarTimeZone;
import com.zingcrm.utility.PropertiesHolder;

@Service
public class OpportunityServiceImpl implements OpportunityService{

	
	 private static Logger log = Logger.getLogger(OpportunityServiceImpl.class
	            .getName());
	 
	 @Autowired
	 private OrgService orgService;
	 
	 @Autowired
	 private BusinessPartnerService leadService;
	 
	 
	 @Autowired
	 private OpportunityDAO oppDAO;
	 
	 @Autowired
	 private CrudButtonService buttonService;
	 
	 @Autowired 
	 private PropertiesHolder properties;
	 
	 @Autowired
	 private ActivityDAO actDAO;
	 
	 @Autowired
	CountryService countryService;
	 
	 @Autowired
    CalendarTimeZone calendar;
    
    @Autowired
    TimeZoneService timezone;
	    
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
	public String orgList(String orgId, String roleId) throws JSONException, BusinessException {
		try{
			return orgService.orgList(orgId, roleId);
		}
		catch (BusinessException e) {
            log.fatal("OPP_SER_001", e);
            throw new BusinessException(e.getMessage());
        }
	}

	@Override
	@Transactional
	public String companylist(String orgId,String roleId,String userId) throws BusinessException, JSONException {
			try{
				return leadService.companylist(orgId,roleId,userId);
			}
			catch (BusinessException e) {
	            log.fatal("OPP_SER_002", e);
	            throw new BusinessException(e.getMessage());
	        }
		}

	@Override
	@Transactional
	public String gridView(String sortId, String sortDir, String strRoleId,
			String strOrgId, int intPage, int intLimit,
			String companyName, String orgId,String userId) {
		String xml = "";
		 String timezoneCode = "",timezoneValue="";
		try 
		{
			
			List<?> mListTime = timezone.getTimezone(userId);
            if(mListTime.size()!=0)
            {
            	Iterator i=mListTime.iterator();
            	while(i.hasNext())
            	{
            		Object[] array=(Object[]) i.next(); 
            		timezoneCode=array[0].toString();
            		timezoneValue=array[1].toString();
            	}
            }
			
			if(strRoleId.equals("1") && orgId.equals("%"))
			{
			}
			else
			{
				orgId=strOrgId;
			}
			
			if((Integer.parseInt(strRoleId) < Integer.parseInt(properties.getProperty("salesrep"))) || (Integer.parseInt(strRoleId)== Integer.parseInt(properties.getProperty("supportGeneral"))))
			{
				userId="%";
			}
			
			List<?> mList = oppDAO.gridView(companyName,orgId, sortId, sortDir,properties.getProperty("active"),userId,properties.getProperty("oppkey"));
    		
    		OutputStream os = new ByteArrayOutputStream();
			
			int intStart=0,intCount=0,totalPages=0,k=0;   
			
			List<GridRow> recordList = new ArrayList<GridRow>();
			if(mList!=null)
			{
				intCount=mList.size();
				if(intCount>0)
				{ 
					totalPages = (int)Math.ceil(Double.parseDouble(""+intCount)/Double.parseDouble(""+intLimit));
				}
				else if(intCount==0)
				{
					totalPages=0;
					intLimit=0;
				}	

				if(intPage > totalPages) 
					intPage = totalPages;

				if(intPage==1)
				{
					k=intPage;
				}
				else
				{
					intStart = intLimit*intPage - intLimit;
					k=intStart+1;
				}

				
				int kk = 1;
			
				for(int i=intStart;i<mList.size() && k<=intStart+intLimit;i++,k++)
				{
					Object[] array=(Object[]) mList.get(i);
					List<Object> columnList = new ArrayList<Object>();
					columnList.add(array[0]); //opp id
					columnList.add(StringEscapeUtils.escapeHtml(array[1].toString())); // comapny name
					columnList.add(StringEscapeUtils.escapeHtml(array[2].toString())); // Opportunity
					columnList.add(StringEscapeUtils.escapeHtml(array[3].toString())); // desc
					columnList.add(StringEscapeUtils.escapeHtml(array[4].toString())); // Lead Od
					columnList.add(StringEscapeUtils.escapeHtml(array[8].toString())); // Close date
					columnList.add(StringEscapeUtils.escapeHtml(array[5].toString())); // Document Id
					columnList.add(calendar.getDate(array[11].toString(), timezoneCode,timezoneValue)); // Creation Date
					GridRow row = new GridRow();
					row.setId(kk++);
					row.setGridCell(columnList);
					recordList.add(row);
				}
			
		   GridUserData userdata = new GridUserData();
            userdata.setValue(buttonService.getCRUDButtons(
                    Integer.parseInt(strRoleId), "FEA_OPPORTUNITY", strOrgId));
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
			log.fatal("OPP_SER_003",e);
		} catch (Exception e) {			
			e.printStackTrace();
		} 
		return xml;
	}

	@Override
	@Transactional
	public String saveOpp(OpportunityForms oppForm) throws BusinessException {
		try{
			String opportunityid="";
			JSONObject jobj=new JSONObject();
			List<Opportunity> li = oppDAO.getOppNameValidation(""+oppForm.getCompany(), oppForm.getOpportunity(),
					properties.getProperty("active"));
			if(li.size()!=0)
			{
				//return "exists";
				jobj.put("result", "exists");
				return jobj.toString();
			}
			else
			{
				oppForm.setOppStatus(properties.getProperty("oppOpen"));
				opportunityid=oppDAO.saveOpp(oppForm,properties.getProperty("active"),properties.getProperty("oppkey"));
				//return "success";
				jobj.put("result", "success");
				jobj.put("oppid",opportunityid);
				jobj.put("leadid", String.valueOf(oppForm.getCompany()));
				//return String.valueOf(data) ;
				return jobj.toString();
			}
			
			
		}
		catch (Exception e) {
            log.fatal("OPP_SER_004", e);
            throw new BusinessException(e.getMessage());
        }
		
	}

	@Override
	@Transactional
	public String getOppDetails(String oppId) throws BusinessException {
		 JSONObject jsonbuild = new JSONObject();
	        JSONArray jsonarray = new JSONArray();
	        try {
	         /*   List<Beds> list = bedsDAO.getBeds(bedId);*/
	            List<?> list=oppDAO.getOppDetails(oppId,properties.getProperty("oppkey"));
	            if (list.size() != 0) {
	                Iterator<?> i = list.iterator();
	                jsonbuild.put(properties.getProperty("status"),
	                        properties.getProperty("success"));
	                while (i.hasNext()) {
	                    Object[] array = (Object[]) i.next();
	                    JSONObject info = new JSONObject();
	                   info.put("Id", array[0]);
	                    info.put("LeadId",array[1]);
	                    info.put("LeadName", array[2]);
	                    info.put("OppName", array[3]);
	                    info.put("Document", array[4]);
	                    info.put("Desc", array[5]);
	                    info.put("CreatedDate",array[6]);
	                    info.put("CreatedBy", array[7]);
	                    info.put("Status", array[8]);
	                    info.put("AccountId", array[9]);
	                    info.put("Default", array[10]);
	                    info.put("ExpectedSymbol", array[11]);
	                    info.put("ExpectedValue", array[12]);
	                    info.put("Probability", array[13]);
	                    info.put("CloseDate", array[14]);
	                    info.put("OppStatus", array[15]);
	                    info.put("OppStatusId", array[16]);
	                    info.put("Currency", array[17]);
	                    info.put("Competitorinfo", array[18]);
	                    jsonarray.put(info);
	                }
	                jsonbuild.put(properties.getProperty("result"), jsonarray);
	            } else {
	                jsonbuild.put(properties.getProperty("status"),
	                        properties.getProperty("nodata"));
	                jsonbuild.put(properties.getProperty("result"), jsonarray);
	            }
	        } catch (Exception e) {
	            log.fatal("OPP_SER_005", e);
	            throw new BusinessException(e.getMessage());
	        }
	        return jsonbuild.toString();
		
	}

	@Override
	@Transactional
	public String updateOpp(OpportunityForms oppForm) throws BusinessException {
		try{
			
			List<Opportunity> li = oppDAO.getEditOppNameValidation(""+oppForm.getCompany(), oppForm.getOpportunity(),""+oppForm.getId(),
					properties.getProperty("active"));
			if(li.size()!=0)
			{
				return "exists";
			}
			else
			{
				oppDAO.updateOpp(oppForm,properties.getProperty("oppkey"));
				return "success";
			}
			
			
		}
		catch (Exception e) {
            log.fatal("OPP_SER_006", e);
            throw new BusinessException(e.getMessage());
        }
		
	}
	
	@Override
	@Transactional
	public void deleteOpp(String oppId) throws BusinessException {
		try{
			oppDAO.deleteOpp(oppId,properties.getProperty("inactive"));
		}
		catch (Exception e) {
            log.fatal("OPP_SER_007", e);
            throw new BusinessException(e.getMessage());
        }
		
	}

	@Override
	@Transactional
	public String getOppNameValidation(String leadId, String oppName)
			throws BusinessException {
		String strData;
		try {
			List<Opportunity> li = oppDAO.getOppNameValidation(leadId, oppName,
					properties.getProperty("active"));
			if(li.size()!=0)
			{
				strData="exists";
			}
			else
			{
				strData="notexists";
			}
		} catch (Exception e) {
			log.fatal("OPP_SER_008", e);
			throw new BusinessException(e.getMessage());
		}
		return strData;
	}

	@Override
	@Transactional
	public String getEditOppNameValidation(String leadId, String oppName,
			String oppId) throws BusinessException {
		String strData;
		try {
			List<Opportunity> li = oppDAO.getEditOppNameValidation(leadId, oppName,oppId,
					properties.getProperty("active"));
			if(li.size()!=0)
			{
				strData="exists";
			}
			else
			{
				strData="notexists";
			}
		} catch (Exception e) {
			log.fatal("OPP_SER_009", e);
			throw new BusinessException(e.getMessage());
		}
		return strData;
	}

	@Override
	public String opportunitylist(String leadId,String defaultOpp) throws BusinessException, JSONException {
		JSONObject jsonbuild = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		List<Opportunity> leadlist;
		try {
			leadlist = (List<Opportunity>) oppDAO.getOpportunityList(leadId,
					properties.getProperty("active"),defaultOpp);
			if (leadlist.size() != 0) {
				Iterator<Opportunity> i = leadlist.iterator();
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("success"));
				while (i.hasNext()) {
					Opportunity opp = i.next();
					JSONObject info = new JSONObject();
					info.put(properties.getProperty("key"), opp.getId());
					info.put(properties.getProperty("value"), opp.getOpportunityName());
					jsonarray.put(info);
				}
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			} else {
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("nodata"));
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			}
		} catch (Exception e) {
			log.fatal("OPP_SER_011", e);
			jsonbuild.put(properties.getProperty("status"),
					properties.getProperty("error"));
			jsonbuild.put(properties.getProperty("result"), jsonarray);
		}
		return jsonbuild.toString();
	}

	/*@Override
	@Transactional
	public Opportunity getOpportunity(String oppId) throws BusinessException {
		try{
			return oppDAO.getOpportunity(oppId,properties.getProperty("active"));
		}
		catch (Exception e) {
            log.fatal("OPP_SER_012", e);
            throw new BusinessException(e.getMessage());
        }
	}*/

/*	@Override
	@Transactional
	public String getDocumentId(String orgId) throws BusinessException {
		 JSONObject jsonbuild = new JSONObject();
	        JSONArray jsonarray = new JSONArray();
	        try {
	        	Document doc=oppDAO.getDocumnetDetails(orgId);
	          if(doc.getDocumentId()!=0)
	          {
		          jsonbuild.put(properties.getProperty("status"),
		                        properties.getProperty("success"));
		                    JSONObject info = new JSONObject();
		                   info.put("DocumentId", doc.getDocumentId());
		                    jsonarray.put(info);
		                jsonbuild.put(properties.getProperty("result"), jsonarray);
	            }
	          	else 
	            {
	                jsonbuild.put(properties.getProperty("status"),
	                        properties.getProperty("nodata"));
	                jsonbuild.put(properties.getProperty("result"), jsonarray);
	            }
	        } catch (Exception e) {
	            log.fatal("OPP_SER_005", e);
	            throw new BusinessException(e.getMessage());
	        }
	        return jsonbuild.toString();
		
	}
*/
	@Override
	@Transactional
	public String getDeviceOpportunityDetails(int leadId,String strRoleId, String userId, String strOrgId)
			throws BusinessException, JSONException {
			 JSONObject jsonbuild = new JSONObject();
		        JSONArray jsonarray = new JSONArray();
		        List<?> activityList;
		        JSONObject info = null ;
		        String actuserId = "",strUserId="";
		        try {
		        	
		        	if(Integer.parseInt(strRoleId)<Integer.parseInt(properties.getProperty("salesrep")))
					{
						userId="%";
					}
					
		         List<?> list=	oppDAO.gridView(""+leadId,strOrgId, "1", "desc",properties.getProperty("active"),userId,properties.getProperty("oppkey"));
			    if (list.size() != 0) {
	                Iterator<?> i = list.iterator();
	                jsonbuild.put(properties.getProperty("status"),
	                        properties.getProperty("success"));
	                while (i.hasNext()) {
	                    Object[] array = (Object[]) i.next();
	                    info = new JSONObject();
	                    info.put("OppId",array[0]); //opp id
	                    info.put("serviceProvider",array[1]); // company name
	                    info.put("Opportunity",array[2]); // Opportunity
	                    info.put("Desc", array[3]); // desc
	                    info.put("LeadId", array[4]); // Lead Od
	                    info.put("DocumentId",array[5]); // Document Id
	                    info.put("Currency",array[6]); // Expected symbol
	                    info.put("ExpectedValue",array[7]); // Expected symbol
	                    info.put("CloseDate",array[8]); //Close Date
	                    info.put("Probability",array[9]); //Probablitiy
	                    info.put("OppStatus",array[10]); // Opportunity Status
	                    
	                    if(Integer.parseInt(strRoleId)< Integer.parseInt(properties.getProperty("salesrep")))
	        			{
	                    	strUserId="%";
	                    	actuserId="%";
	        			}
	        			else if (Integer.parseInt(strRoleId)==Integer.parseInt(properties.getProperty("salesrep")))
	        			{
	        				strUserId=userId;
	        				actuserId=userId;
	        		    }
	        		    else if(Integer.parseInt(strRoleId)>Integer.parseInt(properties.getProperty("salesrep")))
	        		    {
	        		    	strUserId="%";
	        		    	actuserId=userId;
	        		    }
	                    activityList = actDAO.gridView(strOrgId, ""+leadId, array[0].toString(), "1",
	        					"desc", properties.getProperty("active"),strUserId,actuserId,properties.getProperty("activitykey"));
						if (activityList.size() != 0) {
							Iterator<?> trackIterator = activityList.iterator();
							JSONArray childTrack = new JSONArray();
							while (trackIterator.hasNext()) 
							{
								JSONObject actInfo = new JSONObject();
								Object[] array1 =  (Object[]) trackIterator
										.next();
								
								actInfo.put("ActivityId",array1[0]); // activity id
								actInfo.put("OpportunityName",array1[1]); // Opp Name
								actInfo.put("ActivityName",array1[2]); // name
								actInfo.put("Type",array1[3]); // type
								actInfo.put("AssignedTo",array1[4]); // assigned To
								actInfo.put("DueDate",array1[5]); // Due date
								actInfo.put("ActivityStatus",array1[6]); // status
								childTrack.put(actInfo);
							}
							info.put("Activity",childTrack);
						}
			            jsonarray.put(info);
		            }
	               
		            jsonbuild.put("result", jsonarray);
		          
		    	} else {
					jsonbuild.put("status",
							"nodata");
					jsonbuild.put("result", jsonarray);
				}
		 }
		catch (Exception e) {
			jsonbuild.put("status",
					"error");
			jsonbuild.put("result", jsonarray);
            log.fatal("OPP_SER_006", e);
            //throw new BusinessException(e.getMessage());
        }
		
		return jsonbuild.toString();
	}
	
	@Override
	@Transactional
	public String getDeviceBusinessPartner(String orgId,String roleId,String userId) throws BusinessException, JSONException {
		try{
			return leadService.devicecompanylist(orgId,roleId,userId);
			
		}
		catch (BusinessException e) {
            log.fatal("OPP_SER_002", e);
            throw new BusinessException(e.getMessage());
        }
	}

	@Override
	@Transactional
	public String getStatus(String parameter) throws BusinessException, JSONException {
		JSONObject jsonbuild = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		List<Status> leadlist;
		try{
			leadlist=actDAO.getStatusList(properties.getProperty("oppkey"));
			
			if (leadlist.size() != 0) {
				Iterator<Status> i = leadlist.iterator();
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("success"));
				while (i.hasNext()) {
					Status act = i.next();
					JSONObject info = new JSONObject();
					info.put(properties.getProperty("key"), act.getId());
					info.put(properties.getProperty("value"), act.getName());
					jsonarray.put(info);
				}
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			} else {
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("nodata"));
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			}
		} catch (Exception e) {
			log.fatal("ACT_SER_016", e);
			jsonbuild.put(properties.getProperty("status"),
					properties.getProperty("error"));
			jsonbuild.put(properties.getProperty("result"), jsonarray);
		}
		return jsonbuild.toString();
	}

	@Override
	@Transactional
	public String currencylist() throws BusinessException, JSONException {
		try {
			return countryService.countryCurrency();
		} catch (BusinessException e) {
			log.fatal("OPP_SER_007", e);
			throw new BusinessException(e.getMessage());
		}
	}
	@Override
	@Transactional
	public String expectedvaluelist() throws BusinessException, JSONException {
	
			 JSONObject jsonbuild = new JSONObject();
		        JSONArray jsonarray = new JSONArray();
		        List<ExpectedValue> expectedvallist;
		        try {
		            expectedvallist = oppDAO.getExpectedValuelist();
		            if (expectedvallist.size() != 0) {
		                Iterator<ExpectedValue> i = expectedvallist.iterator();
		                jsonbuild.put(properties.getProperty("status"),
		                        properties.getProperty("success"));
		                while (i.hasNext()) {
		                	ExpectedValue expval = i.next();
		                    JSONObject info = new JSONObject();
		                    info.put(properties.getProperty("key"),
		                    		expval.getId());
		                    info.put(
		                            properties.getProperty("value"),
		                            expval.getExpectedvalue());
		                    jsonarray.put(info);
		                }
		                jsonbuild.put(properties.getProperty("result"), jsonarray);
		            } else {
		                jsonbuild.put(properties.getProperty("status"),
		                        properties.getProperty("nodata"));
		                jsonbuild.put(properties.getProperty("result"), jsonarray);
		            }
		        } catch (Exception e) {
		            log.fatal("OPP_SER_008", e);
		            jsonbuild.put(properties.getProperty("status"),
		                    properties.getProperty("error"));
		            jsonbuild.put(properties.getProperty("result"), jsonarray);
		        }
		        return jsonbuild.toString();
			 
	
	}

}
