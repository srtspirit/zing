package com.zingcrm.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
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
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zingcrm.dao.BusinessPartnerDAO;
import com.zingcrm.dao.CountryDAO;
import com.zingcrm.dao.StateDAO;
import com.zingcrm.entity.BusinessPartner;
import com.zingcrm.entity.BusinessPartnerContact;
import com.zingcrm.entity.Country;
import com.zingcrm.entity.Source;
import com.zingcrm.entity.State;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.LeadForms;
import com.zingcrm.jms.JmsProducer;
import com.zingcrm.jqgrid.xml.GridRow;
import com.zingcrm.jqgrid.xml.GridRows;
import com.zingcrm.jqgrid.xml.GridUserData;
import com.zingcrm.service.BusinessPartnerService;
import com.zingcrm.service.CityService;
import com.zingcrm.service.CountryService;
import com.zingcrm.service.CrudButtonService;
import com.zingcrm.service.GeocodingService;
import com.zingcrm.service.OpportunityService;
import com.zingcrm.service.OrgService;
import com.zingcrm.service.StateService;
import com.zingcrm.service.UserService;
import com.zingcrm.utility.CalendarTimeZone;
import com.zingcrm.utility.ExportLead;
import com.zingcrm.utility.PropertiesHolder;
import com.zingcrm.utility.ReadExcelFile;

@Service
public class BusinessPartnerServiceImpl implements BusinessPartnerService {

	private static Logger log = Logger.getLogger(BusinessPartnerServiceImpl.class
			.getName());
	
	private static final String UPDATE_BP_QUEUE_NAME = "BUSINESSPARTNER.UPDATE";

	@Autowired
	private OrgService orgService;

	@Autowired
	private BusinessPartnerDAO leadDAO;

	@Autowired
	private CrudButtonService buttonService;

	@Autowired
	private UserService userService;

	@Autowired
	private PropertiesHolder properties;

	@Autowired
	private GeocodingService geocodingService;
	
	@Autowired
	private ExportLead exportLead;
	
	@Autowired
    ReadExcelFile excelFile;
	
	@Autowired
	OpportunityService oppService;
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	StateService stateService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	CountryDAO countryDAO;
	
	@Autowired
	StateDAO stateDAO;
	
	@Autowired
	CalendarTimeZone calendar;
	
	@Autowired
	private JmsProducer jmsTemplate;
	
	@Autowired
	public JmsTemplate springJms;
	
	
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
	public String orgList(String orgId, String roleId) throws JSONException,
			BusinessException {
		try {
			return orgService.orgList(orgId, roleId);
		} catch (BusinessException e) {
			log.fatal("LEA_SER_001", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public String gridView(String sortId, String sortDir, String strRoleId,
			String strOrgId, int intPage, int intLimit, String companyName,
			String orgId,String userId) throws BusinessException {
		String xml = "";

		try {
			if(strRoleId.equals("1") && orgId.equals("%"))
			{
				//orgId=orgId;
			}
			else
			{
				orgId=strOrgId;
			}
			if(Integer.parseInt(strRoleId)< Integer.parseInt(properties.getProperty("salesrep")))
			{
				userId="%";
			}
			
			
			List<?> mList = leadDAO.gridView(companyName, orgId, sortId,
					sortDir, properties.getProperty("active"),userId);

			ByteArrayOutputStream os = new ByteArrayOutputStream();

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
					columnList.add(array[0]); // lead id
					columnList.add(StringEscapeUtils.escapeHtml(array[1].toString())); // comapny name
					columnList.add(StringEscapeUtils.escapeHtml(array[2].toString())); // contact name
					columnList.add(StringEscapeUtils.escapeHtml(array[3].toString())); // department
					columnList.add(StringEscapeUtils.escapeHtml(array[4].toString())); // City
					columnList.add(StringEscapeUtils.escapeHtml(array[5].toString())); // Email
					columnList.add(StringEscapeUtils.escapeHtml(array[6].toString())); // Mobile No
					columnList.add(StringEscapeUtils.escapeHtml(array[7].toString())); // Phone No
					columnList.add(StringEscapeUtils.escapeHtml(array[8].toString())); // Phone Extension
					GridRow row = new GridRow();
					row.setId(kk++);
					row.setGridCell(columnList);
					recordList.add(row);
				}

				GridUserData userdata = new GridUserData();
				userdata.setValue(buttonService.getCRUDButtons(
						Integer.parseInt(strRoleId), "FEA_LEAD", strOrgId));
				GridRows gridRows = new GridRows();
				gridRows.setPage(intPage);
				gridRows.setRecords(intCount);
				gridRows.setTotal(totalPages);
				gridRows.setGridRow(recordList);
				gridRows.setUserData(userdata);

				JAXBCONTEXT = JAXBContext.newInstance(GridRows.class);
				MARSHALLER = JAXBCONTEXT.createMarshaller();
				MARSHALLER.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				MARSHALLER.marshal(gridRows, os);
				xml = os.toString();
				os.close();
			}
		} catch (JAXBException e) {
			log.fatal("LEA_SER_002", e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}

	@Override
	@Transactional
	public String sourceList() throws BusinessException, JSONException {

		JSONObject jsonbuild = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		List<Source> sourceList;
		try {
			sourceList = (List<Source>) leadDAO.sourceList();
			if (sourceList.size() != 0) {
				Iterator<Source> i = sourceList.iterator();
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("success"));
				while (i.hasNext()) {
					Source source = i.next();
					JSONObject info = new JSONObject();
					info.put(properties.getProperty("key"), source.getId());
					info.put(properties.getProperty("value"), source.getName());
					jsonarray.put(info);
				}
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			} else {
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("nodata"));
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			}
		} catch (Exception e) {
			log.fatal("LEA_SER_003", e);
			jsonbuild.put(properties.getProperty("status"),
					properties.getProperty("error"));
			jsonbuild.put(properties.getProperty("result"), jsonarray);
		}
		return jsonbuild.toString();
	}

	@Override
	@Transactional
	public String repList(String orgId,String roleId,String userId) throws BusinessException, JSONException {
		try {
			return userService.getRep(orgId,roleId,userId);
		} catch (Exception e) {
			log.fatal("LEA_SER_004", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public String saveLead(LeadForms leadForm) throws BusinessException {
		try {
			JSONObject jobj=new JSONObject();
			int data=0;
			List<BusinessPartner> li = leadDAO.getLeadNameValidation(""+leadForm.getOrg(), leadForm.getLead(),
					properties.getProperty("active"));
			if(li.size()!=0)
			{
				//return "exists";
				jobj.put("result", "exists");
				return jobj.toString();
			}
			else
			{
				leadForm.setStatus(getFlag(properties.getProperty("active")));
				leadForm.setGPS(geocodingService.searchLoc(leadForm.getAddress()+","+leadForm.getAddress2()+","+leadForm.getCity()+","+leadForm.getState()+","+leadForm.getZipcode()+","+leadForm.getCountry()));
				leadForm.setLatitude(leadForm.getGPS().split(",")[0]);
				leadForm.setLongitude(leadForm.getGPS().split(",")[1]);
				data=leadDAO.saveLead(leadForm);
				/*data=leadForm.getAddress()+","+leadForm.getAddress2()+","+leadForm.getCityName()+","+leadForm.getStateName()+","+leadForm.getZipcode()+","+leadForm.getCountryName();
				updateLatitude(leadId,data);*/
				jobj.put("result", "success");
				jobj.put("value", String.valueOf(data));
				//return String.valueOf(data) ;
				return jobj.toString();
			}
			
			
		} catch (Exception e) {
			log.fatal("LEA_SER_005", e);
			throw new BusinessException(e.getMessage());
		}
	}
	

	/*public void updateLatitude(final int id, final String data) throws BusinessException {
		 Thread PictureLoader1 = new Thread(new Runnable() {
             public void run() {
             	try {
             		String strData=geocodingService.searchLoc(data);
             		leadDAO.updateLatitude(id,strData.split(",")[0],strData.split(",")[1]);
					} catch (Exception e) {
						e.printStackTrace();
					}
             }
         });
		 PictureLoader1.start();
	}*/

	@Override
	@Transactional
	public void deleteLead(String leadId) throws BusinessException {
		try {
			leadDAO.deleteLead(leadId, properties.getProperty("inactive"));
		} catch (Exception e) {
			log.fatal("LEA_SER_006", e);
			throw new BusinessException(e.getMessage());
		}

	}

	@Override
	@Transactional
	public String getLeadDetails(String leadId,String roleId) throws BusinessException {
		JSONObject jsonbuild = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		try {
			/* List<Beds> list = bedsDAO.getBeds(bedId); */
			List<?> list = leadDAO.getLeadDetails(leadId);
			if (list.size() != 0) {
				Iterator<?> i = list.iterator();
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("success"));
				while (i.hasNext()) {
					Object[] array = (Object[]) i.next();
					JSONObject info = new JSONObject();
					info.put("Id", array[0]);
					info.put("Date", array[1]);
					info.put("SouceId", array[2]);
					info.put("Source", array[3]);
					info.put("Name", array[4]);
					info.put("RepName", array[5]);
					info.put("RepId", array[6]);
					info.put("Address", array[7]);
					info.put("City", array[8]);
					info.put("ZipCode", array[9]);
					info.put("Org", array[10]);
					info.put("CreatedBy", array[11]);
					info.put("CreatedDate", array[12]);
					info.put("PrimaryName", array[13]);
					info.put("PrimaryPhone", array[14]);
					info.put("PrimaryMobile", array[15]);
					info.put("PrimaryEmail", array[16]);
					info.put("PrimaryDept", array[17]);
					info.put("SecondaryName", array[18]);
					info.put("SecondaryPhone", array[19]);
					info.put("SecondaryMobile", array[20]);
					info.put("SecondaryEmail", array[21]);
					info.put("SecondaryDept", array[22]);
					info.put("ContactId", array[23]);
					info.put("State", array[24]);
					info.put("Status", array[25]);
					info.put("Address2", array[26]);
					info.put("Flag", array[27]);
					info.put("RoleId", roleId);
					info.put("Country", array[28]);
					info.put("CityId", array[29]);
					info.put("StateId", array[30]);
					info.put("CountryId", array[31]);
					info.put("Fax", array[32]);
					info.put("Website", array[33]);
					info.put("PrimaryLastName", array[34]);
					info.put("SecondaryLastName", array[35]);
					info.put("ActiveFlag", array[36]);
					info.put("PrimaryPhoneExtension", array[37]);
					info.put("CurrentSup", array[38]);
					info.put("LeadNotes", array[39]);
					info.put("ThirdContName", array[40]);
					info.put("ThirdContLastName", array[41]);
					info.put("ThirdContPhone", array[42]);
					info.put("ThirdContPhoneExt", array[43]);
					info.put("ThirdContMobile", array[44]);
					info.put("ThirdContEmail", array[45]);
					info.put("ThirdContDepart", array[46]);
					
					jsonarray.put(info);
				}
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			} else {
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("nodata"));
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			}
		} catch (Exception e) {
			log.fatal("LEA_SER_007", e);
			throw new BusinessException(e.getMessage());
		}
		return jsonbuild.toString();

	}

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public String updateLead(final LeadForms leadForm) throws BusinessException {
		try {
			String data="";
			List<BusinessPartner> li = leadDAO.getEditLeadNameValidation(""+leadForm.getOrg(), leadForm.getLead(),
					""+leadForm.getId(), properties.getProperty("active"));
			if(li.size()!=0)
			{
				return "exists";
			}
			else
			{
				leadForm.setGPS(geocodingService.searchLoc(leadForm.getAddress()+","+leadForm.getAddress2()+","+leadForm.getCity()+","+leadForm.getState()+","+leadForm.getZipcode()+","+leadForm.getCountry()));
				leadForm.setLatitude(leadForm.getGPS().split(",")[0]);
				leadForm.setLongitude(leadForm.getGPS().split(",")[1]);
				leadDAO.updateLead(leadForm);
				/*data=leadForm.getAddress()+","+leadForm.getAddress2()+","+leadForm.getCityName()+","+leadForm.getStateName()+","+leadForm.getZipcode()+","+leadForm.getCountryName();
				updateLatitude(leadForm.getId(),data);*/
				
//				if (!jmsTemplate.sendObjectMessage(UPDATE_BP_QUEUE_NAME, leadForm)){
//					throw new BusinessException("couldn't send integration message");
//				}
				springJms.send(UPDATE_BP_QUEUE_NAME, new MessageCreator() {
					
					@Override
					public Message createMessage(Session session) throws JMSException {
						return session.createObjectMessage(leadForm);
					}
				});
				
				return "success";
			}
			
			
		} catch (Exception e) {
			log.fatal("LEA_SER_008", e);
			throw new BusinessException(e.getMessage());
		}

	}

	@Override
	@Transactional
	public String companylist(String orgId,String roleId,String userId) throws BusinessException,
			JSONException {
		JSONObject jsonbuild = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		List<BusinessPartner> leadlist;
		try {
			if(roleId.equals(properties.getProperty("salesrep")))
			{
			}
			else if(Integer.parseInt(roleId) < Integer.parseInt(properties.getProperty("salesrep")))
			{
				userId="%";
			}
			leadlist = (List<BusinessPartner>) leadDAO.getCompanyList(orgId,properties.getProperty("active"),userId);
			if (leadlist.size() != 0) {
				Iterator<BusinessPartner> i = leadlist.iterator();
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("success"));
				while (i.hasNext()) {
					BusinessPartner lead = i.next();
					JSONObject info = new JSONObject();
					info.put(properties.getProperty("key"), lead.getId());
					info.put(properties.getProperty("value"), lead.getName());
					jsonarray.put(info);
				}
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			} else {
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("nodata"));
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			}
		} catch (Exception e) {
			log.fatal("LEA_SER_009", e);
			jsonbuild.put(properties.getProperty("status"),
					properties.getProperty("error"));
			jsonbuild.put(properties.getProperty("result"), jsonarray);
		}
		return jsonbuild.toString();
	}

	@Override
	@Transactional
	public String getLeadNameValidation(String orgId, String leadName)
			throws BusinessException {
		String strData;
		try {
			List<BusinessPartner> li = leadDAO.getLeadNameValidation(orgId, leadName,
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
			log.fatal("LEA_SER_010", e);
			throw new BusinessException(e.getMessage());
		}
		return strData;

	}

	@Override
	@Transactional
	public String getEditLeadNameValidation(String orgId, String leadName,
			String leadId) throws BusinessException {
		String strData;
		try {
			List<BusinessPartner> li = leadDAO.getEditLeadNameValidation(orgId, leadName,
					leadId, properties.getProperty("active"));
			if(li.size()!=0)
			{
				strData="exists";
			}
			else
			{
				strData="notexists";
			}
		} catch (Exception e) {
			log.fatal("LEA_SER_011", e);
			throw new BusinessException(e.getMessage());
		}
		return strData;

	}

	@Override
	@Transactional
	public String getDeviceLeadDetails(String strUserId, String strOrgId,String strRoleId)
			throws BusinessException {
		JSONObject jsonbuild = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		try {
			if(Integer.parseInt(strRoleId)< Integer.parseInt(properties.getProperty("salesrep")))
			{
				strUserId="%";
			}
			List<?> list = leadDAO.getDeviceLeadsInfo(strUserId,strOrgId,properties.getProperty("active"));
			if (list.size() != 0) {
				Iterator<?> i = list.iterator();
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("success"));
				while (i.hasNext()) {
					Object[] array = (Object[]) i.next();
					JSONObject info = new JSONObject();
					info.put("id", array[0]);
					info.put("cmpy", array[1]);
					info.put("adrs", array[2]+" "+array[3]+" "+array[4]+" "+array[5]+" "+array[6]);
					
					info.put("contName", array[10]); //BP Contact name
					info.put("phn", array[7]);
					info.put("lat", array[8]);
					info.put("lng", array[9]);
					jsonarray.put(info);
				}
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			} else {
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("nodata"));
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			}
		} catch (Exception e) {
			log.fatal("LEA_SER_007", e);
			throw new BusinessException(e.getMessage());
		}
		return jsonbuild.toString();
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	@Override
	@Transactional
	public ByteArrayInputStream exportLead( String strRoleId,
			String strOrgId, String companyName,
			String orgId) throws BusinessException, IOException, JSONException {
		String strId="0";
		int k=0;
		   try {
			   if(strRoleId.equals("1") && orgId.equals("%"))
				{
					//orgId=orgId;
				}
				else
				{
					orgId=strOrgId;
				}
	            Map<Integer,String> map=new HashMap<Integer,String>();
	            Map<String,String> repmap=new HashMap<String,String>();
	            List<?> list = leadDAO.exportLead(companyName, orgId, properties.getProperty("active"));
	            
 	            List<Source> sourceList=leadDAO.sourceList();
	            Iterator<Source> ii=sourceList.iterator();
	            map.put(0, "NA");
	            while(ii.hasNext())
	            {
	            	Source source=(Source) ii.next();
	                map.put(source.getId(),source.getName());
	            }
	            
	            List<?> contList=userService.getRepList(orgId);
	            Iterator<?> contid=contList.iterator();
	            repmap.put(strId, "NA");
	            while(contid.hasNext())
	            {
	            	Object[] cont=(Object[]) contid.next();
	            	repmap.put(cont[0].toString(),cont[1].toString());
	            }
	            
	            ArrayList arrayList=new ArrayList();
	            Map<Integer, Object[]> currentdata = null ;
	            currentdata = new TreeMap<Integer, Object[]>();
	            currentdata.put(0,  new Object[] {"Date","Source", "Business Partner", "Assigned Rep", "Address 1","Address 2","Country","Province"
	                    ,"City","Zipcode","Fax","Website","Primary First name","Primary Last Name","Primary Phone","Primary Phone Extension","Primary Mobile","Primary Email","Primary Dept","Secondary First name","Secondary Last Name","Secondary Phone","Secondary Phone Extension","Secondary Mobile","Secondary Email","Secondary Dept","Third Contact FirstName","Third Contact LastName","Third Contact Phone","Third Contact Phone Extension","Third Contact Mobile","Third Contact Email","Third Contact Department","Customer","Active","Latitude","Longitude",""});
	            if (list.size() != 0) {
	                Iterator<?> i = list.iterator();
	                while (i.hasNext()) 
	                {
	                    Object[] array= (Object[]) i.next();
	                    strId=array[37].toString();
	                    array[37]="";
	                    array[1]=map.get(array[1]);
	                    if((Boolean) array[33])
	                    {
	                    	array[33]="Y";
	                    }
	                    else
	                    {
	                    	array[33]="N";
	                    }
	                    if((Boolean) array[34])
	                    {
	                    	array[34]="Y";
	                    }
	                    else
	                    {
	                    	array[34]="N";
	                    }
	                    currentdata.put(++k, array);
	                }
	            }
	            return exportLead.getExportLead(currentdata, "Lead");
	        
	   } catch (BusinessException e) {
	        log.fatal("LEA_SER_014", e);
	        throw new BusinessException(e.getMessage());
	    } 
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Object importLeads(String fileName, String strPath,
			String orgId, String strUserId) throws Exception {
	        try {
	        	  Map<String,Integer> sourcemap=new HashMap<String,Integer>();
		            Map<String,String> repmap=new HashMap<String,String>();
		            Map<String,Integer> countrymap=new HashMap<String,Integer>();
		            Map<String,Integer> statemap=new HashMap<String,Integer>();
		            Map<String,Boolean> flagmap=new HashMap<String,Boolean>();
		            List<Source> sourceList=leadDAO.sourceList();
		            Iterator<Source> ii=sourceList.iterator();
		            sourcemap.put("NA", 0);
		            while(ii.hasNext())
		            {
		            	Source source=(Source) ii.next();
		            	sourcemap.put(source.getName().toLowerCase(),source.getId());
		            }
		            
		            List<?> contList=userService.getRepList(orgId);
		            Iterator<?> contid=contList.iterator();
		            repmap.put("NA", "0");
		            while(contid.hasNext())
		            {
		            	Object[] cont=(Object[]) contid.next();
		            	repmap.put(cont[1].toString().toLowerCase(),cont[0].toString());
		            }
		            
		            List<Country> countryList=countryDAO.getCountryList();
		            Iterator<Country> ci=countryList.iterator();
		            countrymap.put("NA", 0);
		            while(ci.hasNext())
		            {
		            	Country country=ci.next();
		            	countrymap.put(country.getCountryName().toLowerCase(),country.getCountryId());
		            }
		            
		            List<State> stateList=stateDAO.readStateCode("%");
		            Iterator<State> si=stateList.iterator();
		            statemap.put("NA", 0);
		            while(si.hasNext())
		            {
		            	State state=si.next();
		            	statemap.put(state.getName().toLowerCase(),state.getId());
		            }
		            flagmap.put("y", true);
		            flagmap.put("n", false);
		            ArrayList<?> excellist = excelFile.SetExcelData(sourcemap, repmap, strPath
	                            + fileName, strUserId,orgId,countrymap,statemap,flagmap);
		            if(excellist.size()!=0)
		            {
		                List<BusinessPartner> leadList = (List<BusinessPartner>) excellist.get(0);
		                List<BusinessPartnerContact> contactList = (List<BusinessPartnerContact>) excellist
		                        .get(1);
		                List<LeadForms>  leadbean = (List<LeadForms>) excellist
		                        .get(2);
		                        
		                return leadDAO.setUploadExcelFile(leadList, contactList, leadbean, strPath
	                            + fileName);
		            }
		            else
		            {
		                return "error";
		            }
	            

	        } catch (BusinessException e) {
	            log.fatal("RES_SER_018", e);
	            throw new BusinessException(e.getMessage());
	        }
	    }
	
	
	@Override
	@Transactional
	public String devicecompanylist(String orgId,String roleId,String userId) throws BusinessException,
			JSONException {
		JSONObject jsonbuild = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		List<BusinessPartner> leadlist;
		try {
			if(roleId.equals(properties.getProperty("salesrep")))
			{
				//userId=userId;
			}
			else
			{
				userId="%";
			}
			leadlist = (List<BusinessPartner>) leadDAO.getCompanyList(orgId,
					properties.getProperty("active"),userId);
			if (leadlist.size() != 0) {
				Iterator<BusinessPartner> i = leadlist.iterator();
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("success"));
				while (i.hasNext()) {
					BusinessPartner lead = i.next();
					JSONObject info = new JSONObject();
					info.put(properties.getProperty("key"), lead.getId());
					info.put(properties.getProperty("value"), lead.getName());
					jsonarray.put(info);
				}
				/*jsonbuild.put(properties.getProperty("docId"), documnetId);*/
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			} else {
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("nodata"));
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			}
		} catch (Exception e) {
			log.fatal("LEA_SER_009", e);
			jsonbuild.put(properties.getProperty("status"),
					properties.getProperty("error"));
			jsonbuild.put(properties.getProperty("result"), jsonarray);
		}
		return jsonbuild.toString();
	}

	@Override
	@Transactional
	public String countrylist() throws BusinessException, JSONException {
		try {
			return countryService.getCountry();
		} catch (BusinessException e) {
			log.fatal("LEA_SER_016", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	@Override
	@Transactional
	public String statelist(String countryId) throws BusinessException, JSONException {
		try {
			return stateService.stateService(countryId);
		} catch (BusinessException e) {
			log.fatal("LEA_SER_017", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	@Override
	@Transactional
	public String citylist(String stateId) throws BusinessException, JSONException {
		try {
			return cityService.cityService(stateId);
		} catch (BusinessException e) {
			log.fatal("LEA_SER_018", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	private boolean getFlag(String status) {
		return Integer.parseInt(status) > 0 ? true : false ;
	}

	public JmsProducer getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsProducer jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	
	
}
