package com.zingcrm.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.zingcrm.entity.Activity;
import com.zingcrm.entity.ActivityReference;
import com.zingcrm.entity.ActivityType;
import com.zingcrm.entity.BusinessPartner;
import com.zingcrm.entity.DefinedData1;
import com.zingcrm.entity.DefinedData2;
import com.zingcrm.entity.Status;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.ActivityForms;
import com.zingcrm.jqgrid.xml.GridRow;
import com.zingcrm.jqgrid.xml.GridRows;
import com.zingcrm.jqgrid.xml.GridUserData;
import com.zingcrm.sendemail.EmailParams;
import com.zingcrm.sendemail.SendEmailService;
import com.zingcrm.service.ActivityService;
import com.zingcrm.service.BusinessPartnerService;
import com.zingcrm.service.CrudButtonService;
import com.zingcrm.service.OpportunityService;
import com.zingcrm.service.OrgService;
import com.zingcrm.service.UserService;
import com.zingcrm.utility.PropertiesHolder;

@Service
public class ActivityServiceImpl implements ActivityService {

	private static Logger log = Logger.getLogger(ActivityServiceImpl.class
			.getName());

	@Autowired
	private OrgService orgService;

	@Autowired
	private BusinessPartnerService leadService;

	@Autowired
	private OpportunityService oppService;

	@Autowired
	private CrudButtonService buttonService;

	@Autowired
	private PropertiesHolder properties;

	@Autowired
	private ActivityDAO actDAO;

	@Autowired
	private UserService userService;
	
	 @Autowired
	 private SendEmailService sendEmailService;
	 
	

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
	public String orgList(String orgId, String roleId)
			throws BusinessException, JSONException {
		try {
			return orgService.orgList(orgId, roleId);
		} catch (BusinessException e) {
			log.fatal("ACT_SER_001", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public String companylist(String orgId,String roleId,String userId,String flag) throws BusinessException,
			JSONException {
		String strData="";
		try {
			if(Integer.parseInt(roleId) > Integer.parseInt(properties.getProperty("salesrep"))){
				if(flag!=null && flag.equals("edit")){
					strData=getCompanylist(orgId,roleId,userId);
				}else if(flag!=null && flag.equals("new")){
					orgId="%";
					userId="%";
					strData=leadService.companylist(orgId,roleId,userId);
				}
				else{
					strData=getCompanylist(orgId,roleId,userId);
				//	strData=leadService.companylist(orgId,roleId,userId);
				}
			}else{
				strData= leadService.companylist(orgId,roleId,userId);
			}
		
		} catch (BusinessException e) {
			log.fatal("ACT_SER_002", e);
			throw new BusinessException(e.getMessage());
		}
		return strData;
	}

	@Override
	@Transactional
	public String opportunitylist(String leadId) throws BusinessException,
			JSONException {
		try {
			return oppService.opportunitylist(leadId,properties.getProperty("orgAll"));
		} catch (BusinessException e) {
			log.fatal("ACT_SER_003", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	@Override
	@Transactional
	public String createopportunitylist(String leadId,String roleId) throws BusinessException,
			JSONException {
		String defaultOpportunity = null;
		try {
		/*	if(roleId.equals(properties.getProperty("salesrep")))
			{
				defaultOpportunity=properties.getProperty("defaultOppNo");
			}
 		else
			{*/
				defaultOpportunity=properties.getProperty("orgAll");
			//}
			return oppService.opportunitylist(leadId,defaultOpportunity);
		} catch (BusinessException e) {
			log.fatal("ACT_SER_003", e);
			throw new BusinessException(e.getMessage());
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
			
			if(Integer.parseInt(strRoleId)==Integer.parseInt(properties.getProperty("salesrep")))
			{
				strUserId=userId;
				actuserId=userId;
			}
			else if(Integer.parseInt(strRoleId)==Integer.parseInt(properties.getProperty("genaralId")))
			{
				strUserId="%";
				actuserId=userId;
			}
			else
			{
				strUserId="%";
				actuserId="%";
			}
			
			List<?> mList = actDAO.gridView(orgId, leadId, oppId, sortId,
					sortDir, properties.getProperty("active"),strUserId,actuserId,properties.getProperty("activitykey"));

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
					columnList.add(StringEscapeUtils.escapeHtml(array[12].toString())); // Bp
					columnList.add(StringEscapeUtils.escapeHtml(array[1].toString())); // Opp Name
					columnList.add(StringEscapeUtils.escapeHtml(array[2].toString())); // name
					columnList.add(StringEscapeUtils.escapeHtml(array[3].toString())); // type
					columnList.add(StringEscapeUtils.escapeHtml(array[4].toString())); // assigned To
					columnList.add(StringEscapeUtils.escapeHtml(array[5].toString())); // Due date
					if(Integer.parseInt(""+array[10]) > 0)
					{
						columnList.add("<img src=\"/resources/images/attachment.png\" class=\"imgAttach\" />"); // Attachment
					}
					else
					{
						columnList.add("");
					}
					columnList.add(array[6]); // status
					columnList.add(""); // Action
					columnList.add(array[7]); // userid
					columnList.add(array[8]); // opp id
					columnList.add(array[9]); // lead Id
					columnList.add(array[11]); // created by
					if(userId.equals(array[7].toString())){
						columnList.add("true");
					}
					else{
						columnList.add("false");
					}
					GridRow row = new GridRow();
					row.setId(kk++);
					row.setGridCell(columnList);
					recordList.add(row);
				}

				GridUserData userdata = new GridUserData();
				userdata.setValue(buttonService.getCRUDButtons(
						Integer.parseInt(strRoleId), "FEA_ACTIVITY", strOrgId));
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
			log.fatal("ACT_SER_004", e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}

	

	@Override
	@Transactional
	public void deleteActivity(String activityId) throws BusinessException {
		try {
			actDAO.deleteActivity(activityId,
					properties.getProperty("inactive"));
		} catch (BusinessException e) {
			log.fatal("ACT_SER_005", e);
			throw new BusinessException(e.getMessage());
		}

	}

	@Override
	public JSONObject getAssignedList(String orgId,String roleId,String companyId,String userId) throws BusinessException,
			JSONException {
		try {
			
        	if(roleId.equals(properties.getProperty("salesrep")))
        	{
        		return userService.getDeviceActivitySalesAssigedTo(orgId,userId,roleId);
        	}
        	else if(Integer.parseInt(roleId) < Integer.parseInt(properties.getProperty("salesrep")))
        	{
        		return userService.getActRep(orgId,properties.getProperty("AllUser"),userId,roleId);
        	}
        	else
        	{
        		return userService.getActivityRep(orgId,companyId,userId);
        	}
			
		} catch (BusinessException e) {
			log.fatal("ACT_SER_006", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public String getActivityNameValidation(String oppId, String actName)
			throws BusinessException {
		String strData;
		try {
			List<Activity> li = actDAO.getActivityNameValidation(oppId,
					actName, properties.getProperty("active"));
			if (li.size() != 0) {
				strData = "exists";
			} else {
				strData = "notexists";
			}
		} catch (Exception e) {
			log.fatal("ACT_SER_007", e);
			throw new BusinessException(e.getMessage());
		}
		return strData;

	}

	@Override
	@Transactional
	public String getEditActivityNameValidation(String oppId, String actName,
			String actId) throws BusinessException {
		String strData;
		try {
			List<Activity> li = actDAO.getEditActivityNameValidation(oppId,
					actName, actId, properties.getProperty("active"));
			if (li.size() != 0) {
				strData = "exists";
			} else {
				strData = "notexists";
			}
		} catch (Exception e) {
			log.fatal("ACT_SER_008", e);
			throw new BusinessException(e.getMessage());
		}
		return strData;

	}

	@Override
	@Transactional
	public String getOrgUserDetails(String orgId) throws BusinessException {
		try {
			return orgService.getOrgUserDetail(orgId);
		} catch (Exception e) {
			log.fatal("ACT_SER_009", e);
			throw new BusinessException(e.getMessage());
		}

	}

	@Override
	@Transactional
	public String saveActivity(final ActivityForms actForm,String realPath) throws BusinessException {
		try {
			String msg="";
			List<Activity> li = actDAO.getActivityNameValidation(""+actForm.getOpportunity(),
					actForm.getName(), properties.getProperty("active"));
			String createdBy=actForm.getCreatedBy();
			if (li.size() != 0) {
				return msg="exists";
			} else {
				msg=actDAO.saveActivity(actForm,
							properties.getProperty("active"),properties.getProperty("open"),realPath,properties.getProperty("activitykey"));
	            getSaveActivityEmail(createdBy,actForm.getAssignedTo(),actForm.getName(),actForm.getDueDate());
				 return msg; 
			}
			
		} catch (Exception e) {
			
			log.fatal("ACT_SER_010", e);
			throw new BusinessException(e.getMessage());
		}

	}

	private void getSaveActivityEmail(String createdBy, String assignedTo, String getName, String dueDate) throws BusinessException {
		 List<?> list= actDAO.getActivityEmail(createdBy,assignedTo);
			
		 if(list.size()!=0)
		 {
			 Iterator<?> i=list.iterator();
			 Object[] array=(Object[]) i.next();
			 	String emailFrom = properties.getProperty("emailFrom");
			 	String[] strTo=new String[2];
			 	strTo[0]=array[1].toString();
			 	strTo[1]=array[3].toString();
            String[] accountEmailTo = strTo;
            String subject = properties.getProperty("createActivitySubject")+" "+getName+" "+properties.getProperty("createActivitySubject2");
            String content1 = properties.getProperty("createActivity1")+" "+getName+" ";
            String content2 = properties.getProperty("createActivity2")+" "+array[0]+" ";
            String content3 = properties.getProperty("createActivity3")+" "+array[2]+" ";
            String content4=properties.getProperty("createActivity4")+" "+dueDate+" ";
            String content5=properties.getProperty("createActivity5");
            sendEmail(emailFrom, accountEmailTo, subject, content1, content2,
            		content3,content4,content5);
		 }
		
	}

	private void sendEmail(String emailFrom, String[] accountEmailTo,
			String subject, String content1, String content2, String content3,
			String content4,String content5) {
		  final EmailParams emailParams = new EmailParams();
	        try {
	          
	            emailParams.setEmailFrom(emailFrom);
	            emailParams.setEmailTos(accountEmailTo);
	            emailParams.setEmailSubject(subject);
	            emailParams.setEmailBody(content1+content2+content3+content4+content5);
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
	        	log.fatal("ACT_SER_010", e);
	            e.printStackTrace();
	        } finally {
	           // saveEmailLog(orgId, emailFrom, accountEmailTo, subject, status);
	        }
		
	}

	@Override
	@Transactional
	public String getActivityDetails(String actId,String roleId) throws BusinessException {
		JSONObject jsonbuild = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		try {
			List<?> list = actDAO.getActivityDetails(actId);
			List<ActivityReference> actImagelist=actDAO.getActivityImage(actId);
			if (list.size() != 0) {
				Iterator<?> i = list.iterator();
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("success"));
				while (i.hasNext()) {
					Object[] array = (Object[]) i.next();
					JSONObject info = new JSONObject();
					String stractid = array[0].toString();
					info.put("Id", array[0]);
					info.put("LeadId", array[1]);
					info.put("OppId", array[2]);
					info.put("Name", array[3]);
					info.put("Type", array[4]);
					info.put("AssignedId", array[5]);
					info.put("DueDate", array[6]);
					info.put("Column1", array[7]);
					info.put("Column2", array[8]);
					info.put("CreatedBy", array[9]);
					info.put("CreatedDate", array[10]);
					/*if(Integer.parseInt(array[11].toString())>0)
					{
						info.put("Document", "/activity/getActivityImage?activityId="+array[0]);
					}
					else
					{
						info.put("Document", "");
					}*/
					info.put("AssignedName", array[12]);
					info.put("Status", array[13]);
					info.put("ActivityStatus", array[14]);
					info.put("Org", array[15]);
					info.put("LeadName", array[16]);
					info.put("OppName", array[17]);
					//info.put("FileName", array[18]);
					info.put("Desc", array[19]);
					info.put("TypeName", array[20]);
					info.put("RoleId", roleId);
					info.put("DefaultOpp", array[21]);
					if(actImagelist.size()!=0){
						info.put("Documentsize", actImagelist.size());
						Iterator<ActivityReference> j=actImagelist.iterator();
						JSONArray arrinfo = new JSONArray();
						while(j.hasNext()){
						ActivityReference act = j.next();
						JSONObject jsonobj= new JSONObject();
						
						if(!act.getDocument().toString().equals("0"))
						{
							jsonobj.put("ImageId", act.getId());
							jsonobj.put("Document","/activity/getActivityImage?activityId="+act.getId());
							jsonobj.put("FileName", act.getFilename());
						}
						else
						{
							jsonobj.put("ImageId", "");
							jsonobj.put("Document", "");
							jsonobj.put("FileName", "");
						}
						arrinfo.put(jsonobj);
						}
						info.put("Documents", arrinfo);
					}
					else{
						info.put("Documentsize", "0");
					}
					jsonarray.put(info);
				}
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			} else {
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("nodata"));
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			}
		} catch (Exception e) {
			log.fatal("ACT_SER_011", e);
			throw new BusinessException(e.getMessage());
		}
		return jsonbuild.toString();
	}

	@Override
	@Transactional
	public List<ActivityReference> getActivityImage(String actId) throws BusinessException {
		try{
			List<ActivityReference> listActImage;
			return listActImage=actDAO.getActivityImage(actId);
			//return actDAO.getActivityImage(actId);
			
		}catch (Exception e) {
			log.fatal("ACT_SER_013", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	@Transactional
	@Async
	public String editActivity(ActivityForms actForm,String strPath,String userId) throws BusinessException {
		try {
			
			List<Activity> li = actDAO.getEditActivityNameValidation(""+actForm.getOpportunity(),
					actForm.getName(),""+actForm.getId(), properties.getProperty("active"));
			if (li.size() != 0) {
				return "exists";
			} 
			else 
			{
				if(userId.equals(actForm.getCreatedBy()))
				{
					   String statusId=actDAO.editActivity(actForm,strPath,properties.getProperty("activitykey"));
					   if(!statusId.equals(actForm.getActivityStatus()))
						{
						
							 List<?> list= actDAO.getActivityEmail(actForm.getModifiedBy(),actForm.getAssignedTo());
							 if(list.size()!=0)
							 {
								 Iterator<?> i=list.iterator();
								 Object[] array=(Object[]) i.next();
			 				 	String emailFrom = properties.getProperty("emailFrom");
			 				 	String[] strTo=new String[2];
			 				 	strTo[0]=array[1].toString();
			 				 	strTo[1]=array[3].toString();
					            String[] accountEmailTo = strTo;
					            String subject = properties.getProperty("editActivitySubject")+" "+actForm.getName()+" "+properties.getProperty("editActivitySubject2");
					            String content1 = properties.getProperty("editActivity1")+" "+actForm.getName()+" ";
					            String content2 = properties.getProperty("editActivity2")+" "+array[0]+" ";
					            String content3 = properties.getProperty("editActivity3")+" "+actForm.getStatusName()+" ";
					            String content4=properties.getProperty("editActivity4");
					            sendEmail(emailFrom, accountEmailTo, subject, content1, content2,
					            		content3,content4,"");
					            return "success";
							 }
				      }
					   else
					   {
						   return "success";
					   }
				} 
				else
				{
					 if(!actForm.getActivityStatus().equals(properties.getProperty("activityClosed")))
					 {
						 String statusId=actDAO.editActivity(actForm,strPath,properties.getProperty("activitykey"));
						   if(!statusId.equals(actForm.getActivityStatus()))
							{
							
								 List<?> list= actDAO.getActivityEmail(actForm.getModifiedBy(),actForm.getAssignedTo());
								 if(list.size()!=0)
								 {
									 Iterator<?> i=list.iterator();
									 Object[] array=(Object[]) i.next();
				 				 	String emailFrom = properties.getProperty("emailFrom");
				 				 	String[] strTo=new String[2];
				 				 	strTo[0]=array[1].toString();
				 				 	strTo[1]=array[3].toString();
						            String[] accountEmailTo = strTo;
						            String subject = properties.getProperty("editActivitySubject")+" "+actForm.getName()+" "+properties.getProperty("editActivitySubject2");
						            String content1 = properties.getProperty("editActivity1")+" "+actForm.getName()+" ";
						            String content2 = properties.getProperty("editActivity2")+" "+array[0]+" ";
						            String content3 = properties.getProperty("editActivity3")+" "+actForm.getStatusName()+" ";
						            String content4=properties.getProperty("editActivity4");
						            sendEmail(emailFrom, accountEmailTo, subject, content1, content2,
						            		content3,content4,"");
						            return "success";
								 }
					      }
						   else
						   {
							   return "success";
						   }
					 }
					 else
					 {
						 return "accessdenied";
					 }
				 }
		      }
				
		} catch (Exception e) {
			log.fatal("ACT_SER_012", e);
			throw new BusinessException(e.getMessage());
		}
		return "error";
	}

	@Override
	@Transactional
	public void removeAttachment(String parameter) throws BusinessException {
		try {
			actDAO.removeAttachment(parameter);
		} catch (Exception e) {
			log.fatal("ACT_SER_014", e);
			throw new BusinessException(e.getMessage());
		}
		
	}
	

	@Override
	@Transactional
	public String statusList(String sessionUserId, String userId)
			throws BusinessException, JSONException {
		JSONObject jsonbuild = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		List<Status> leadlist;
		try {
				leadlist = (List<Status>) actDAO.getStatusList(properties.getProperty("activitykey"));
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
	public String getChartData(String roleId, String userId,String orgId)
			throws BusinessException {
		JSONObject resultObj= new JSONObject();
		try{
			String leadUserId="",actUserId="";
			if(Integer.parseInt(roleId)< Integer.parseInt(properties.getProperty("salesrep")))
			{
				leadUserId="%";
		    	 actUserId="%";
			}
			else if (Integer.parseInt(roleId)==Integer.parseInt(properties.getProperty("salesrep")))
			{
		    	leadUserId=userId;
		    	actUserId=userId;
		    }
		    else if(Integer.parseInt(roleId)>Integer.parseInt(properties.getProperty("salesrep")))
		    {
		    	leadUserId="%";
		    	actUserId=userId;
		    }
		List<?> chartlist= actDAO.getChartData(leadUserId,actUserId,orgId,properties.getProperty("active"),properties.getProperty("activitykey"));
		Iterator<?> chartiter = chartlist.iterator();
		JSONArray colsArr = new JSONArray();
		JSONObject colsobj1= new JSONObject();
		JSONObject colsobj2= new JSONObject();
		colsobj1.put("id", "");
		colsobj1.put("label", "Status");
		colsobj1.put("pattern", "");
		colsobj1.put("type", "string");
		colsobj2.put("id", "");
		colsobj2.put("label", "Count");
		colsobj2.put("pattern", "");
		colsobj2.put("type", "number");
		colsArr.put(colsobj1);
		colsArr.put(colsobj2);
		resultObj.put("cols", colsArr);
		JSONArray rowsArr = new JSONArray();
		JSONArray rowsArr1 ;
		JSONObject rowsobj1;
		JSONObject rowsobj2;
		JSONObject rowsobj3;
		while(chartiter.hasNext()){
		Object[] array=(Object[]) chartiter.next();
		rowsobj1= new JSONObject();
		rowsobj2= new JSONObject();
		rowsobj3= new JSONObject();
		rowsArr1= new JSONArray();
		rowsobj2.put("v", array[1]);
		rowsArr1.put(rowsobj2);
		rowsobj3.put("v", array[0]);
		rowsArr1.put(rowsobj3);
		rowsobj1.put("c", rowsArr1);
		rowsArr.put(rowsobj1);
		}
		resultObj.put("rows", rowsArr);
		}catch(Exception e){
			log.fatal("ACT_SER_017", e);
			return resultObj.toString();
		}
		return resultObj.toString();
	}

	
	@Override
	@Transactional
	public List<?> getAllTasks(String roleId, String userId, String orgId,String actstatus,String sortID,String sortDir)
			throws BusinessException {
		List<?> actlist;
		try{
			String leadUserId="",actUserId="";
			
			if(Integer.parseInt(roleId)< Integer.parseInt(properties.getProperty("salesrep")))
			{
				leadUserId="%";
		    	 actUserId="%";
			}
			/*else if (Integer.parseInt(roleId)==Integer.parseInt(properties.getProperty("salesrep")))
			{
		    	leadUserId=userId;
		    	actUserId=userId;
		    }*/
		    else /*if(Integer.parseInt(roleId)>Integer.parseInt(properties.getProperty("salesrep")))*/
		    {
		    	leadUserId="%";
		    	actUserId=userId;
		    }
		    actlist= actDAO.getAllTasks(leadUserId,actUserId,orgId,properties.getProperty("active"),actstatus,properties.getProperty("activityClosed"),sortID,sortDir);
		 
		}catch(Exception e){
			log.fatal("ACT_SER_018", e);
			throw new BusinessException(e.getMessage());
		}
		return actlist;
	}

	
	@Override
	@Transactional
	public String getAllTasks(String roleId, String userId, String orgId,String actstatus)
			throws BusinessException {
		JSONObject jsonbuild = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		List<?> actlist;
		try{
			String leadUserId="",actUserId="";
			
			
			
			if(Integer.parseInt(roleId)< Integer.parseInt(properties.getProperty("salesrep")))
			{
				leadUserId="%";
		    	 actUserId="%";
			}
			else if (Integer.parseInt(roleId)==Integer.parseInt(properties.getProperty("salesrep")))
			{
		    	leadUserId=userId;
		    	actUserId=userId;
		    }
		    else if(Integer.parseInt(roleId)>Integer.parseInt(properties.getProperty("salesrep")))
		    {
		    	leadUserId="%";
		    	actUserId=userId;
		    }
		    actlist= actDAO.getAllTasks(leadUserId,actUserId,orgId,properties.getProperty("active"),actstatus,properties.getProperty("activityClosed"),"1","asc");
		    if (actlist.size() != 0) {
				Iterator<?> i = actlist.iterator();
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("success"));
				while (i.hasNext()) {
					 	Object[] array = (Object[]) i.next();
	                   JSONObject info = new JSONObject();
	                    info.put("actName", array[0]);
	                    info.put("type",array[1]);
	                    info.put("assignedto", array[2]);
	                    info.put("duedate", array[3]);
	                    info.put("status", array[4]);
	                    info.put("opportunity", array[5]);
	                    info.put("lead", array[6]);
	                    info.put("Id", array[7]);
	                    jsonarray.put(info);
				}
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			} else {
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("nodata"));
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			}
		}catch(Exception e){
			log.fatal("ACT_SER_018", e);
			throw new BusinessException(e.getMessage());
		}
		return jsonbuild.toString();
	}

	@Override
	@Transactional
	public String statusUpdate(String id, String status, String modifiedUserId,String createdBy)
			throws BusinessException {
		try 
		{
			if(createdBy.equals(modifiedUserId))
			{
				 actDAO.statusUpdate(id,status,modifiedUserId,properties.getProperty("activitykey"));
				 return "success";
			}
			else
			{
				if(!status.equals(properties.getProperty("activityClosed")))
				 {
		            actDAO.statusUpdate(id,status,modifiedUserId,properties.getProperty("activitykey"));
					 return "success";
				 }
				else
				{
					 return "accessdenied";
				}
			}
			
			
		} catch (Exception e) {
			log.fatal("ACT_SER_019", e);
			throw new BusinessException(e.getMessage());
		}

	}

	@Override
	@Transactional
	public JSONObject getDefinedData(String orgId) throws BusinessException, JSONException {
		JSONObject jsonbuild = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		List<DefinedData1> datalist;
		try {
			datalist=actDAO.getDefinedData(orgId);
			if (datalist.size() != 0) {
				Iterator<DefinedData1> i = datalist.iterator();
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("success"));
				while (i.hasNext()) {
					DefinedData1 data = i.next();
					JSONObject info = new JSONObject();
					info.put(properties.getProperty("key"),data.getId());
					info.put(properties.getProperty("value"), data.getName());
					jsonarray.put(info);
				}
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			} else {
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("nodata"));
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			}
		} catch (Exception e) {
			log.fatal("ACT_SER_020", e);
			jsonbuild.put(properties.getProperty("status"),
					properties.getProperty("error"));
			jsonbuild.put(properties.getProperty("result"), jsonarray);
		}
		return jsonbuild;
	}
	
	@Override
	@Transactional
	public JSONObject getDefinedData2(String data) throws BusinessException, JSONException {
		JSONObject jsonbuild = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		List<DefinedData2> datalist;
		try {
			datalist=actDAO.getDefinedData2(data);
			if (datalist.size() != 0) {
				Iterator<DefinedData2> i = datalist.iterator();
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("success"));
				while (i.hasNext()) {
					DefinedData2 data2= i.next();
					JSONObject info = new JSONObject();
					info.put(properties.getProperty("key"),data2.getId());
					info.put(properties.getProperty("value"), data2.getName());
					info.put(properties.getProperty("data1"), data2.getData1());
					jsonarray.put(info);
				}
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			} else {
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("nodata"));
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			}
		} catch (Exception e) {
			log.fatal("ACT_SER_021", e);
			jsonbuild.put(properties.getProperty("status"),
					properties.getProperty("error"));
			jsonbuild.put(properties.getProperty("result"), jsonarray);
		}
		return jsonbuild;
	}
	
	@Override
	@Transactional
	public JSONObject getActivityType(String orgId) throws BusinessException, JSONException {
		JSONObject jsonbuild = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		List<ActivityType> datalist;
		try {
			datalist=actDAO.getActivityType(orgId);
			if (datalist.size() != 0) {
				Iterator<ActivityType> i = datalist.iterator();
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("success"));
				while (i.hasNext()) {
					ActivityType type = i.next();
					JSONObject info = new JSONObject();
					info.put(properties.getProperty("key"),type.getId());
					info.put(properties.getProperty("value"), type.getName());
					jsonarray.put(info);
				}
				jsonbuild.put("result", jsonarray);
			} else {
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("nodata"));
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			}
		} catch (Exception e) {
			log.fatal("ACT_SER_022", e);
			jsonbuild.put(properties.getProperty("status"),
					properties.getProperty("error"));
			jsonbuild.put(properties.getProperty("result"), jsonarray);
		}
		return jsonbuild;
	}

	@Override
	@Transactional
	public JSONObject getActivity(ActivityForms actForms) throws JSONException {
		JSONObject jsonbuild = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		try {
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("success"));
					JSONObject info = new JSONObject();
					info.put(properties.getProperty("key"),"ActivityType");
					info.put(properties.getProperty("value"),getActivityType(""+actForms.getOrg()));
					jsonarray.put(info);
					jsonbuild.put(properties.getProperty("result"), jsonarray);
					info = new JSONObject();
					info.put(properties.getProperty("key"),"AssignedTo");
					info.put(properties.getProperty("value"), getAssignedList(""+actForms.getOrg(),actForms.getRoleId(),""+actForms.getLead(),actForms.getUserId()));
					jsonarray.put(info);
					info = new JSONObject();
					 jsonbuild.put(properties.getProperty("result"), jsonarray);
					info.put(properties.getProperty("key"),"OrgDetails");
					info.put(properties.getProperty("value"), orgService.getOrgUserDetail(""+actForms.getOrg()));
					jsonarray.put(info);
					 jsonbuild.put(properties.getProperty("result"), jsonarray);
					 info = new JSONObject();
					info.put(properties.getProperty("key"),"DefinedData1");
					info.put(properties.getProperty("value"), getDefinedData(""+actForms.getOrg()));
					jsonarray.put(info);
					
					info = new JSONObject();
					 jsonbuild.put(properties.getProperty("result"), jsonarray);
					info.put(properties.getProperty("key"),"DefinedData2");
					info.put(properties.getProperty("value"), getDefinedData2("%"));
					jsonarray.put(info);
			 	
					jsonbuild.put(properties.getProperty("result"), jsonarray);
		} catch (Exception e) {
			log.fatal("ACT_SER_023", e);
			jsonbuild.put(properties.getProperty("status"),
					properties.getProperty("error"));
			jsonbuild.put(properties.getProperty("result"), jsonarray);
		}
		return jsonbuild;
	}


	@Override
	@Transactional
	@Async
	public String saveActivity(ActivityForms actForm) throws BusinessException, JSONException {
		 JSONObject jsonbuild = new JSONObject();
		try {
			int activityId=0;
			List<Activity> li = actDAO.getActivityNameValidation(""+actForm.getOpportunity(),
					actForm.getName(), properties.getProperty("active"));
			if (li.size() != 0) {
				jsonbuild.put(properties.getProperty("status"),
	                    properties.getProperty("exists"));
			} else {
				activityId= actDAO.saveActivityDevice(actForm,properties.getProperty("active"),properties.getProperty("open"),properties.getProperty("activitykey"));
				 List<?> list= actDAO.getActivityEmail(actForm.getCreatedBy(),actForm.getAssignedTo());
				 if(list.size()!=0)
				 {
					 Iterator<?> i=list.iterator();
					 Object[] array=(Object[]) i.next();
 				 	String emailFrom = properties.getProperty("emailFrom");
 				 	String[] strTo=new String[2];
 				 	strTo[0]=array[1].toString();
 				 	strTo[1]=array[3].toString();
		            String[] accountEmailTo = strTo;
		            String subject = properties.getProperty("createActivitySubject")+" "+actForm.getName()+" "+properties.getProperty("createActivitySubject2");
		            String content1 = properties.getProperty("createActivity1")+" "+actForm.getName()+" ";
		            String content2 = properties.getProperty("createActivity2")+" "+array[0]+" ";
		            String content3 = properties.getProperty("createActivity3")+" "+array[2]+" ";
		            String content4=properties.getProperty("createActivity4")+" "+actForm.getDueDate()+" ";
		            String content5=properties.getProperty("createActivity5");
		            sendEmail(emailFrom, accountEmailTo, subject, content1, content2,
		            		content3,content4,content5);
				    
				 }
				 jsonbuild.put(properties.getProperty("actid"),activityId );
				 jsonbuild.put(properties.getProperty("status"),
		                    properties.getProperty("success"));
			}
			
			
		} catch (Exception e) {
			jsonbuild.put(properties.getProperty("status"),
                    properties.getProperty("error"));
			log.fatal("ACT_SER_010", e);
			throw new BusinessException(e.getMessage());
		}
		return jsonbuild.toString();

	}

	@Override
	@Transactional
	@Async
	public String updateActivity(ActivityForms actForm) throws JSONException,
			BusinessException {
		
		 JSONObject jsonbuild = new JSONObject();
		try {
			
			List<Activity> li = actDAO.getEditActivityNameValidation(""+actForm.getOpportunity(),
					actForm.getName(),""+actForm.getId(), properties.getProperty("active"));
			if (li.size() != 0) {
				jsonbuild.put(properties.getProperty("status"),
	                    properties.getProperty("exists"));
			} 
			else 
			{
				if(actForm.getUserId().equals(actForm.getCreatedBy()))
				{
					   String statusId=actDAO.editActivity(actForm,properties.getProperty("activitykey"));
					   if(!statusId.equals(actForm.getActivityStatus()))
						{
						
							 List<?> list= actDAO.getActivityEmail(actForm.getModifiedBy(),actForm.getAssignedTo());
							 if(list.size()!=0)
							 {
								 Iterator<?> i=list.iterator();
								 Object[] array=(Object[]) i.next();
			 				 	String emailFrom = properties.getProperty("emailFrom");
			 				 	String[] strTo=new String[2];
			 				 	strTo[0]=array[1].toString();
			 				 	strTo[1]=array[3].toString();
					            String[] accountEmailTo = strTo;
					            String subject = properties.getProperty("editActivitySubject")+" "+actForm.getName()+" "+properties.getProperty("editActivitySubject2");
					            String content1 = properties.getProperty("editActivity1")+" "+actForm.getName()+" ";
					            String content2 = properties.getProperty("editActivity2")+" "+array[0]+" ";
					            String content3 = properties.getProperty("editActivity3")+" "+actForm.getStatusName()+" ";
					            String content4=properties.getProperty("editActivity4");
					            sendEmail(emailFrom, accountEmailTo, subject, content1, content2,
					            		content3,content4,"");
					            jsonbuild.put(properties.getProperty("status"),
					                    properties.getProperty("success"));
							 }
				      }
					   else
					   {
						   jsonbuild.put(properties.getProperty("status"),
				                    properties.getProperty("success"));
					   }
				} 
				else
				{
					 if(!actForm.getActivityStatus().equals(properties.getProperty("activityClosed")))
					 {
						 String statusId=actDAO.editActivity(actForm,properties.getProperty("activitykey"));
						   if(!statusId.equals(actForm.getActivityStatus()))
							{
							
								 List<?> list= actDAO.getActivityEmail(actForm.getModifiedBy(),actForm.getAssignedTo());
								 if(list.size()!=0)
								 {
									 Iterator<?> i=list.iterator();
									 Object[] array=(Object[]) i.next();
				 				 	String emailFrom = properties.getProperty("emailFrom");
				 				 	String[] strTo=new String[2];
				 				 	strTo[0]=array[1].toString();
				 				 	strTo[1]=array[3].toString();
						            String[] accountEmailTo = strTo;
						            String subject = properties.getProperty("editActivitySubject")+" "+actForm.getName()+" "+properties.getProperty("editActivitySubject2");
						            String content1 = properties.getProperty("editActivity1")+" "+actForm.getName()+" ";
						            String content2 = properties.getProperty("editActivity2")+" "+array[0]+" ";
						            String content3 = properties.getProperty("editActivity3")+" "+actForm.getStatusName()+" ";
						            String content4=properties.getProperty("editActivity4");
						            sendEmail(emailFrom, accountEmailTo, subject, content1, content2,
						            		content3,content4,"");
						            jsonbuild.put(properties.getProperty("status"),
						                    properties.getProperty("success"));
								 }
					      }
						   else
						   {
							   jsonbuild.put(properties.getProperty("status"),
					                    properties.getProperty("success"));
						   }
					 }
					 else
					 {
						 jsonbuild.put(properties.getProperty("status"),
				                    properties.getProperty("accessdenied"));
					 }
				 }
		      }
				
		} catch (Exception e) {
			jsonbuild.put(properties.getProperty("status"),
                    properties.getProperty("error"));
			log.fatal("ACT_SER_012", e);
		}
		return jsonbuild.toString();
		
	}

	@Override
	@Transactional
	@Async
	public JSONObject getActivityDetailsDevice(ActivityForms actForms) throws JSONException,
			BusinessException {
		JSONObject jsonbuild = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		HashMap<String,List> activitytype=new HashMap<String,List>();
		List<ActivityType> listact;
		List<ActivityType> listactinfo;
		List<Status> liststatus;
		List<DefinedData1> listDataDef1;
		List<DefinedData2> listDataDef2;
		List<ActivityReference> imageinfo;
		JSONArray jsonarraystatus = new JSONArray();
		JSONObject assinedtoingo=new JSONObject();
		JSONArray jsonarraydata1 = new JSONArray();
		JSONArray jsonarraydata2 = new JSONArray();
		try {
			activitytype =actDAO.getActivityDetailDevice(actForms);
			if(activitytype!=null){
				listact= activitytype.get("ActivityType");
			
			if (listact.size() != 0) {
				Iterator<ActivityType> i = listact.iterator();
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("success"));
				while (i.hasNext()) {
					ActivityType type = i.next();
					JSONObject acttype = new JSONObject();
					acttype.put(properties.getProperty("key"),type.getId());
					acttype.put(properties.getProperty("value"), type.getName());
					jsonarray.put(acttype);
				}
				jsonbuild.put("ActivityType", jsonarray);
			}
			listactinfo= activitytype.get("ActivityInfo");
			imageinfo= activitytype.get("ImageList");
			JSONArray actinfo=new JSONArray();
			if (listactinfo.size() != 0) {
				Iterator<?> i = listactinfo.iterator();
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("success"));
				while (i.hasNext()) {
					Object[] array = (Object[]) i.next();
					JSONObject info = new JSONObject();
					info.put("Id", array[0]);
					info.put("LeadId", array[1]);
					info.put("OppId", array[2]);
					info.put("Name", array[3]);
					info.put("Type", array[4]);
					info.put("AssignedId", array[5]);
					info.put("DueDate", array[6]);
					info.put("Column1", array[7]);
					info.put("Column2", array[8]);
					info.put("CreatedBy", array[9]);
					info.put("CreatedDate", array[10]);
					/*if(Integer.parseInt(array[11].toString())>0)
					{
						info.put("Document", "/activity/getActivityImage?activityId="+array[0]);
					}
					else
					{
						info.put("Document", "");
					}*/
					info.put("AssignedName", array[12]);
					info.put("Status", array[13]);
					info.put("ActivityStatus", array[14]);
					info.put("Org", array[15]);
					info.put("LeadName", array[16]);
					info.put("OppName", array[17]);
					info.put("FileName", array[18]);
					info.put("Desc", array[19]);
					info.put("TypeName", array[20]);
					info.put("RoleId", actForms.getRoleId());
					info.put("DefaultOpp", array[21]);
					if(imageinfo.size()!=0){
						info.put("Documentsize", imageinfo.size());
						Iterator<ActivityReference> j=imageinfo.iterator();
						JSONArray arrinfo = new JSONArray();
						while(j.hasNext()){
						ActivityReference actref = j.next();
						JSONObject jsonobj= new JSONObject();
						
						if(!actref.getDocument().toString().equals("0"))
						{
							jsonobj.put("ImageId", actref.getId());
							jsonobj.put("Document","/activity/getActivityImage?activityId="+actref.getId());
							jsonobj.put("FileName", actref.getFilename());
						}
						else
						{
							jsonobj.put("ImageId", "");
							jsonobj.put("Document", "");
							jsonobj.put("FileName", "");
						}
						arrinfo.put(jsonobj);
						}
						info.put("Documents", arrinfo);
					}
					else{
						info.put("Documentsize", "0");
					}
					actinfo.put(info);
				}
				jsonbuild.put("ActivityInfo", actinfo);
			}
				jsonbuild.put("AssignedTo",  getAssignedList(""+actForms.getOrg(),actForms.getRoleId(),""+actForms.getLead(),actForms.getUserId()));
				jsonbuild.put("OrgDetails", orgService.getOrgUserDetail(""+actForms.getOrg()));
				liststatus= activitytype.get("StatusList");
				if (liststatus.size() != 0) {
					Iterator<Status> j = liststatus.iterator();
					while (j.hasNext()) {
						Status act = j.next();
						JSONObject statusinfo = new JSONObject();
						statusinfo.put(properties.getProperty("key"), act.getId());
						statusinfo.put(properties.getProperty("value"), act.getName());
						jsonarraystatus.put(statusinfo);
					}
					jsonbuild.put("StatusList", jsonarraystatus);
				} 
				listDataDef1= activitytype.get("DataDefined1");
				
				if (listDataDef1.size() != 0) {
					Iterator<DefinedData1> i = listDataDef1.iterator();
					JSONObject datainfo1 = new JSONObject();
					while (i.hasNext()) {
						DefinedData1 data = i.next();
						JSONObject datadefinfo1 = new JSONObject();
						datadefinfo1.put(properties.getProperty("key"),data.getId());
						datadefinfo1.put(properties.getProperty("value"), data.getName());
						jsonarraydata1.put(datadefinfo1);
					}
					jsonbuild.put("DataDefined1", jsonarraydata1);
				}
				listDataDef2= activitytype.get("DataDefined2");
				
				if (listDataDef2.size() != 0) {
					Iterator<DefinedData2> i = listDataDef2.iterator();
					JSONObject datainfo2 = new JSONObject();
					while (i.hasNext()) {
						DefinedData2 data = i.next();
						JSONObject datadefinfo2 = new JSONObject();
						datadefinfo2.put(properties.getProperty("key"),data.getId());
						datadefinfo2.put(properties.getProperty("value"), data.getName());
						jsonarraydata2.put(datadefinfo2);
					}
					jsonbuild.put("DataDefined2", jsonarraydata2);
				}
			}
			
			else {
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("nodata"));
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			}
		}
		catch(Exception e){
			jsonbuild.put(properties.getProperty("status"),
                    properties.getProperty("error"));
			log.fatal("ACT_SER_013", e);
		}
		
		return jsonbuild;
	
	}
	@Override
	@Transactional
	public String getCompanylist(String orgId,String roleId,String userId) throws BusinessException,
			JSONException {
		JSONObject jsonbuild = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		List<?> leadlist;
		try {
			
			leadlist = (List<?>) actDAO.getCompanyList(orgId,properties.getProperty("active"),userId);
			if (leadlist.size() != 0) {
				Iterator<?> i = leadlist.iterator();
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("success"));
				while (i.hasNext()) {
					Object[] lead = (Object[]) i.next();
					JSONObject info = new JSONObject();
					info.put(properties.getProperty("key"), lead[0]);
					info.put(properties.getProperty("value"), lead[1]);
					jsonarray.put(info);
				}
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			} else {
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("nodata"));
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			}
		} catch (Exception e) {
			log.fatal("ACT_SER_014", e);
			jsonbuild.put(properties.getProperty("status"),
					properties.getProperty("error"));
			jsonbuild.put(properties.getProperty("result"), jsonarray);
		}
		return jsonbuild.toString();
	}

	@Override
	public JSONObject getnewActAssignRepList(String orgId, String roleId,
			String companyId, String userId) throws BusinessException,
			JSONException {
		try {
			
			if(roleId.equals(properties.getProperty("salesrep")))
        	{
        		return userService.getDeviceActivitySalesAssigedTo(orgId,userId,roleId);
        	}
        	else if(Integer.parseInt(roleId) < Integer.parseInt(properties.getProperty("salesrep")))
        	{
        		return userService.getActRep(orgId,properties.getProperty("AllUser"),userId,roleId);
        	}
        	else
        	{
        		return userService.getActivityRep(orgId,companyId,userId);
        	}
        /*	if(roleId.equals(properties.getProperty("salesrep")))
        	{
        		return userService.getDeviceActivitySalesAssigedTo(orgId,userId,roleId);
        	}
        	else if(Integer.parseInt(roleId) < Integer.parseInt(properties.getProperty("salesrep")))
        	{
        		return userService.getActRep(orgId,properties.getProperty("AllUser"),userId,roleId);
        	}
        	else
        	{
        		return userService.getActRep(orgId,properties.getProperty("salesrep"),userId,roleId);
        	}
			*/
		} catch (BusinessException e) {
			log.fatal("ACT_SER_006", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	@Override
	@Transactional
	public ActivityReference getActivityImageDevice(String actrefId) throws BusinessException {
		try{
			/*List<ActivityReference> listActImage;
			return listActImage=actDAO.getActivityImagelink(actId);*/
			return actDAO.getActivityImagelink(actrefId);
			
		}catch (Exception e) {
			log.fatal("ACT_SER_013", e);
			throw new BusinessException(e.getMessage());
		}
	}
}
