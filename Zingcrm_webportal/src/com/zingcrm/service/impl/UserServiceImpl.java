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

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zingcrm.dao.UserDAO;
import com.zingcrm.entity.Role;
import com.zingcrm.entity.User;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.RegistrationForm;
import com.zingcrm.jqgrid.xml.GridRow;
import com.zingcrm.jqgrid.xml.GridRows;
import com.zingcrm.jqgrid.xml.GridUserData;
import com.zingcrm.sendemail.EmailParams;
import com.zingcrm.sendemail.SendEmailService;
import com.zingcrm.service.CountryService;
import com.zingcrm.service.CrudButtonService;
import com.zingcrm.service.OrgService;
import com.zingcrm.service.TimeZoneService;
import com.zingcrm.service.UserService;
import com.zingcrm.utility.CalendarTimeZone;
import com.zingcrm.utility.PropertiesHolder;

@Service
public class UserServiceImpl implements UserService {
	
   private static Logger log = Logger
           .getLogger(UserServiceImpl.class.getName());
   
   @Autowired
   private PropertiesHolder properties;
   
   @Autowired
   private UserDAO userDAO;
   
   @Autowired
	private OrgService orgService;

   @Autowired
	private CrudButtonService buttonService;
   
   @Autowired
  	private CountryService county;
   
   @Autowired
 	private TimeZoneService timezone;
   

   @Autowired
   private CalendarTimeZone calendar;

   @Autowired SendEmailService sendEmailService;

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
	public String getRep(String orgId,String roleID,String userId) throws BusinessException, JSONException {
	     JSONObject jsonbuild = new JSONObject();
	        JSONArray jsonarray = new JSONArray();
	        JSONObject info = new JSONObject();
	        List<?> userList = null;
	        try {
	        	
	            if(Integer.parseInt(roleID)==Integer.parseInt(properties.getProperty("salesrep")))
	        	{
	        		 jsonbuild.put(properties.getProperty("status"),
		                        properties.getProperty("success"));
		                info.put(properties.getProperty("key"),
		                		userId);
		                info.put(properties.getProperty("value"),
		                		properties.getProperty("self"));
		                jsonarray.put(info);
		                jsonbuild.put(properties.getProperty("result"), jsonarray);
	        	}
	            else if(Integer.parseInt(roleID) < Integer.parseInt(properties.getProperty("salesrep")))
	        	{
	        		userList = userDAO.getRep(orgId,properties.getProperty("manRepId"),properties.getProperty("active"),roleID);
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
	 	                    		cont[1].toString()+" - "+cont[2].toString());
	 	                    jsonarray.put(info);
	 	                }
	 	                jsonbuild.put(properties.getProperty("result"), jsonarray);
	        		 } else {
	 	                jsonbuild.put(properties.getProperty("status"),
	 	                        properties.getProperty("nodata"));
	 	                jsonbuild.put(properties.getProperty("result"), jsonarray);
	 	            }
	        	}
	        } catch (Exception e) {
	            log.fatal("USER_SER_001", e);
	            jsonbuild.put(properties.getProperty("status"),
	                    properties.getProperty("error"));
	            jsonbuild.put(properties.getProperty("result"), jsonarray);
	        }
	        return jsonbuild.toString();
	    }

	@Override
	@Transactional
	public String getAllRepList(String orgId) throws BusinessException,
			JSONException {
		  JSONObject jsonbuild = new JSONObject();
	        JSONArray jsonarray = new JSONArray();
	        JSONObject info = new JSONObject();
	        List<?> userList = null;
	        try {
	        	
	         
	        		userList = userDAO.getAllRepList(orgId,properties.getProperty("salesrep"));
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
	 	                    		cont[1].toString()+" - "+cont[2].toString());
	 	                    jsonarray.put(info);
	 	                }
	 	                jsonbuild.put(properties.getProperty("result"), jsonarray);
	        		 } else {
	 	                jsonbuild.put(properties.getProperty("status"),
	 	                        properties.getProperty("nodata"));
	 	                jsonbuild.put(properties.getProperty("result"), jsonarray);
	 	            }
	        } catch (Exception e) {
	            log.fatal("USER_SER_001", e);
	            jsonbuild.put(properties.getProperty("status"),
	                    properties.getProperty("error"));
	            jsonbuild.put(properties.getProperty("result"), jsonarray);
	        }
	        return jsonbuild.toString();
	    }

	@Override
	@Transactional
	public List<?> getRepList(String orgId) throws BusinessException, JSONException {
	        List<?> userList = null;
	        try {
	        	userList =userDAO.getRep(orgId,properties.getProperty("manRepId"),properties.getProperty("active"),"%");
	        } catch (Exception e) {
	            log.fatal("USER_SER_001", e);
	        }
	        return userList;
	    }

	
	
	@Override
	@Transactional
	public String getAllRepList(String orgId, String status)
			throws BusinessException, JSONException 
	{
		JSONObject jsonbuild = new JSONObject();
        JSONArray jsonarray = new JSONArray();
        List<?> userList;
        JSONObject info = new JSONObject();
        try {
        	userList = (List<?>) userDAO.getRep(orgId,properties.getProperty("salesrep"),properties.getProperty("active"),"%");
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
	                    		cont[1].toString()+" - "+cont[2].toString());
	                    jsonarray.put(info);
	                }
	                jsonbuild.put(properties.getProperty("result"), jsonarray);
     		 } else {
	                jsonbuild.put(properties.getProperty("status"),
	                        properties.getProperty("nodata"));
	                jsonbuild.put(properties.getProperty("result"), jsonarray);
	            }
        } catch (Exception e) {
            log.fatal("USER_SER_001", e);
            jsonbuild.put(properties.getProperty("status"),
                    properties.getProperty("error"));
            jsonbuild.put(properties.getProperty("result"), jsonarray);
        }
        return jsonbuild.toString();
	}
	
	@Override
	@Transactional
	public JSONObject getDeviceActivitySalesAssigedTo(String orgId, String userId,String roleID)
			throws BusinessException, JSONException 
	{
		JSONObject jsonbuild = new JSONObject();
        JSONArray jsonarray = new JSONArray();
        List<?> userList;
        JSONObject info = new JSONObject();
        try {
        	userList = (List<?>) userDAO.getRep(orgId,properties.getProperty("gensupId"),properties.getProperty("active"),roleID);
            if (userList.size() != 0) {
                Iterator<?> i = userList.iterator();
                jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("success"));
                info.put(properties.getProperty("key"),
	                		userId);
	                info.put(properties.getProperty("value"),
	                		properties.getProperty("self"));
                
	              jsonarray.put(info);
                while (i.hasNext()) {
                	
                	Object[] cont = (Object[]) i.next();
                	if(!cont[0].toString().equals(userId)){
                	 info = new JSONObject();
                    info.put(properties.getProperty("key"),
                    		cont[0].toString());
                    info.put(properties.getProperty("value"),
                    		cont[1].toString()+" - "+cont[2].toString());
                    jsonarray.put(info);
                	}
                }
                jsonbuild.put(properties.getProperty("result"), jsonarray);
            } else {
                jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("nodata"));
                jsonbuild.put(properties.getProperty("result"), jsonarray);
            }
        } catch (Exception e) {
            log.fatal("USER_SER_001", e);
            jsonbuild.put(properties.getProperty("status"),
                    properties.getProperty("error"));
            jsonbuild.put(properties.getProperty("result"), jsonarray);
        }
        return jsonbuild;
	}
	
	/*
	@Override
	@Transactional
	public JSONObject getActivitySalesAssigedTo(String orgId, String userId)
			throws BusinessException, JSONException 
	{
		JSONObject jsonbuild = new JSONObject();
        JSONArray jsonarray = new JSONArray();
        List<?> userList;
        JSONObject info = new JSONObject();
        try {
        	userList = (List<?>) userDAO.getRep(orgId,properties.getProperty("genSupportId"),properties.getProperty("active"));
            if (userList.size() != 0) {
                Iterator<?> i = userList.iterator();
                jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("success"));
                info.put(properties.getProperty("key"),
	                		userId);
	                info.put(properties.getProperty("value"),
	                		properties.getProperty("self"));
                
	              jsonarray.put(info);
                while (i.hasNext()) {
                	
                	Object[] cont = (Object[]) i.next();
                	
                	 info = new JSONObject();
                    info.put(properties.getProperty("key"),
                    		cont[0].toString());
                    info.put(properties.getProperty("value"),
                    		cont[1].toString()+" - "+cont[2].toString());
                    jsonarray.put(info);
                }
                jsonbuild.put(properties.getProperty("result"), jsonarray);
            } else {
                jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("nodata"));
                jsonbuild.put(properties.getProperty("result"), jsonarray);
            }
        } catch (Exception e) {
            log.fatal("USER_SER_001", e);
            jsonbuild.put(properties.getProperty("status"),
                    properties.getProperty("error"));
            jsonbuild.put(properties.getProperty("result"), jsonarray);
        }
        return jsonbuild;
	}
	*/
	@Override
	@Transactional
	public JSONObject getActivityRep(String orgId,String companyId,String userId) throws BusinessException, JSONException {
	     JSONObject jsonbuild = new JSONObject();
	        JSONArray jsonarray = new JSONArray();
	        List<?> userList;
	        JSONObject info = new JSONObject();
	        try {
	        	//userList = (List<?>) userDAO.getActivityRep(orgId,properties.getProperty("genSupportId"),companyId);
	        	userList = (List<?>) userDAO.getAssignedRepList(orgId, userId, companyId);
	            if (userList.size() != 0) {
	                Iterator<?> i = userList.iterator();
	                jsonbuild.put(properties.getProperty("status"),
	                        properties.getProperty("success"));
	                info.put(properties.getProperty("key"),
  	                		userId);
  	                info.put(properties.getProperty("value"),
  	                		properties.getProperty("self"));
	                
  	              jsonarray.put(info);
	                while (i.hasNext()) {
	                	
	                	Object[] cont = (Object[]) i.next();
	                	
	                	 info = new JSONObject();
	                    info.put(properties.getProperty("key"),
	                    		cont[0].toString());
	                    info.put(properties.getProperty("value"),
	                    		cont[1].toString()+" - "+cont[2].toString());
	                    jsonarray.put(info);
	                }
	                jsonbuild.put(properties.getProperty("result"), jsonarray);
	            } else {
	                jsonbuild.put(properties.getProperty("status"),
	                        properties.getProperty("nodata"));
	                jsonbuild.put(properties.getProperty("result"), jsonarray);
	            }
	        } catch (Exception e) {
	            log.fatal("USER_SER_002", e);
	            jsonbuild.put(properties.getProperty("status"),
	                    properties.getProperty("error"));
	            jsonbuild.put(properties.getProperty("result"), jsonarray);
	        }
	        return jsonbuild;
	    }

	
	
	/*
	@Override
	@Transactional
	public JSONObject getAssigedTo(String orgId,String roleId,String companyId,String userId) throws BusinessException,
			JSONException {
		  JSONObject jsonbuild = new JSONObject();
	        JSONArray jsonarray = new JSONArray();
	        JSONObject info = new JSONObject();
	        List<ContactInfo> userList;
	        try {
	        	
	        	userList = (List<ContactInfo>) userDAO
	                   .getRep(orgId,roleId);
	            if (userList.size() != 0) {
	                Iterator<ContactInfo> i = userList.iterator();
	                jsonbuild.put(properties.getProperty("status"),
	                        properties.getProperty("success"));
	                info.put(properties.getProperty("key"),
  	                		userId);
  	                info.put(properties.getProperty("value"),
  	                		properties.getProperty("self"));
  	              jsonarray.put(info);
	                while (i.hasNext()) {
	                	ContactInfo cont = i.next();
	                    info = new JSONObject();
	                	
	                    info.put(properties.getProperty("key"),
	                    		cont.getUserId());
	                    info.put(properties.getProperty("value"),
	                            cont.getUserFirstName()+" "+cont.getUserLastName() );
	                    jsonarray.put(info);
	                }
	                jsonbuild.put(properties.getProperty("result"), jsonarray);
	            } else {
	                jsonbuild.put(properties.getProperty("status"),
	                        properties.getProperty("nodata"));
	                jsonbuild.put(properties.getProperty("result"), jsonarray);
	            }
	        } catch (Exception e) {
	            log.fatal("USER_SER_002", e);
	            jsonbuild.put(properties.getProperty("status"),
	                    properties.getProperty("error"));
	            jsonbuild.put(properties.getProperty("result"), jsonarray);
	        }
	        return jsonbuild;
		
	}
*/
	@Override
	@Transactional
	public String orgList(String orgId, String roleId)
			throws BusinessException, JSONException {
		try {
			return orgService.orgList(orgId, roleId);
		} catch (BusinessException e) {
			log.fatal("USER_CON_OO3", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public String gridView(String sortId, String sortDir, String strRoleId,
			String strOrgId, int intPage, int intLimit,
			String orgId, String userName) throws BusinessException {
		String xml="";
		try {
			if (strRoleId.equals("1") && orgId.equals("%")) {
				//orgId = orgId;
			} else {
				orgId = strOrgId;
			}
			List<?> mList = userDAO.gridView(orgId, userName, sortId,
					sortDir, properties.getProperty("active"));

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
					columnList.add(array[0]); // user id
					columnList.add(array[1]); // first Name
					columnList.add(array[2]); // last name
					columnList.add(array[3]); // email
					columnList.add(array[4]); // role To
					columnList.add(array[5]); // Phone
					columnList.add(array[6]); // status
					GridRow row = new GridRow();
					row.setId(kk++);
					row.setGridCell(columnList);
					recordList.add(row);
				}

				GridUserData userdata = new GridUserData();
				userdata.setValue(buttonService.getCRUDButtons(
						Integer.parseInt(strRoleId), "FEA_USER", strOrgId));
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
			log.fatal("USER_SER_004", e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}

	@Override
	@Transactional
	public String getUserDetails(String userId) throws BusinessException {
		JSONObject jsonbuild = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		try {
			List<?> list = userDAO.getUserDetails(userId);
			if (list.size() != 0) {
				Iterator<?> i = list.iterator();
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("success"));
				while (i.hasNext()) {
					Object[] array = (Object[]) i.next();
					JSONObject info = new JSONObject();
					info.put("UserId", array[0]);
					info.put("FirstName", array[1]);
					info.put("LastName", array[2]);
					info.put("Email", array[3]);
					info.put("RoleName", array[4]);
					info.put("Phone", array[5]);
					if(array[6].equals(properties.getProperty("active")))
					{
						info.put("Status", "Active");
					}
					else
					{
						info.put("Status", "InActive");
					}
					info.put("TimezoneId", array[7]);
					info.put("Timezone", array[8]);
					info.put("RoleId", array[9]);
					info.put("PhoneCode", array[10]);
					info.put("ContId", array[11]);
					jsonarray.put(info);
				}
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			} else {
				jsonbuild.put(properties.getProperty("status"),
						properties.getProperty("nodata"));
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			}
		} catch (Exception e) {
			log.fatal("USER_SER_005", e);
			throw new BusinessException(e.getMessage());
		}
		return jsonbuild.toString();
	}

	@Override
	@Transactional
	public String getRole(String roleId) throws BusinessException {
		try{
			   JSONObject jsonbuild = new JSONObject();
		        JSONArray jsonarray = new JSONArray();
		        List<Role> rolelist;
		        try {
		        	rolelist = userDAO
		                    .getRole(roleId,properties.getProperty("systemAdmin"));
		            if (rolelist.size() != 0) {
		                Iterator<Role> i = rolelist.iterator();
		                jsonbuild.put(properties.getProperty("status"),
		                        properties.getProperty("success"));
		                while (i.hasNext()) {
		                	Role role = i.next();
		                    JSONObject info = new JSONObject();
		                    info.put(properties.getProperty("key"),
		                    		role.getRoleId());
		                    info.put(properties.getProperty("value"),
		                    		role.getRoleName());
		                    jsonarray.put(info);
		                }
		                jsonbuild.put(properties.getProperty("result"), jsonarray);
		            } else {
		                jsonbuild.put(properties.getProperty("status"),
		                        properties.getProperty("nodata"));
		                jsonbuild.put(properties.getProperty("result"), jsonarray);
		            }
		        } catch (Exception e) {
		            log.fatal("USER_SER_006", e);
		            jsonbuild.put(properties.getProperty("status"),
		                    properties.getProperty("error"));
		            jsonbuild.put(properties.getProperty("result"), jsonarray);
		        }
		        return jsonbuild.toString();
	} catch (Exception e) {
		log.fatal("USER_SER_006", e);
		throw new BusinessException(e.getMessage());
	}
}
	
	
	@Override
	@Transactional
	public String getEditRole(String roleId) throws BusinessException {
		try{
			   JSONObject jsonbuild = new JSONObject();
		        JSONArray jsonarray = new JSONArray();
		        List<Role> rolelist;
		        try {
		        	rolelist = userDAO
		                    .getEditRole(roleId);
		            if (rolelist.size() != 0) {
		                Iterator<Role> i = rolelist.iterator();
		                jsonbuild.put(properties.getProperty("status"),
		                        properties.getProperty("success"));
		                while (i.hasNext()) {
		                	Role role = i.next();
		                    JSONObject info = new JSONObject();
		                    info.put(properties.getProperty("key"),
		                    		role.getRoleId());
		                    info.put(properties.getProperty("value"),
		                    		role.getRoleName());
		                    jsonarray.put(info);
		                }
		                jsonbuild.put(properties.getProperty("result"), jsonarray);
		            } else {
		                jsonbuild.put(properties.getProperty("status"),
		                        properties.getProperty("nodata"));
		                jsonbuild.put(properties.getProperty("result"), jsonarray);
		            }
		        } catch (Exception e) {
		            log.fatal("USER_SER_006", e);
		            jsonbuild.put(properties.getProperty("status"),
		                    properties.getProperty("error"));
		            jsonbuild.put(properties.getProperty("result"), jsonarray);
		        }
		        return jsonbuild.toString();
	} catch (Exception e) {
		log.fatal("USER_SER_006", e);
		throw new BusinessException(e.getMessage());
	}
}

	@Override
	@Transactional
	public String getPhoneCode() throws BusinessException {
		try{
			return county.callingcodeList();
		} catch (Exception e) {
			log.fatal("USER_SER_007", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public String getTimeZone() throws BusinessException {
		try{
			return timezone.timezoneList();
		} catch (Exception e) {
			log.fatal("USER_SER_008", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public String getEmailNameValidation(String email) throws BusinessException {
		String strData;
		try {
			List<User> li = userDAO.getEmailNameValidation(email,
					 properties.getProperty("active"));
			if (li.size() != 0) {
				strData = "exists";
			} else {
				strData = "notexists";
			}
		} catch (Exception e) {
			log.fatal("USER_SER_009", e);
			throw new BusinessException(e.getMessage());
		}
		return strData;
	}
	
	@Override
	@Transactional
	public String getEditEmailNameValidation(String email,String userId) throws BusinessException {
		String strData;
		try {
			List<User> li = userDAO.getEditEmailNameValidation(email,userId,
					 properties.getProperty("active"));
			if (li.size() != 0) {
				strData = "exists";
			} else {
				strData = "notexists";
			}
		} catch (Exception e) {
			log.fatal("USER_SER_010", e);
			throw new BusinessException(e.getMessage());
		}
		return strData;
	}

	@Override
	@Transactional
	public void saveUser(RegistrationForm regForm) throws BusinessException {
		try {
			regForm.setStatus(calendar.getFlag(properties.getProperty("active")));
			userDAO.saveUser(regForm,properties.getProperty("enabled"));
			
	         String emailFrom = properties.getProperty("emailFrom");
	         String accountEmailTo = regForm.getEmail();
	         String subject = properties.getProperty("createOrgSubject");
	         String content1 = properties.getProperty("createOrgContent1");
	         String content2 = properties.getProperty("createOrgContent2");
	         String content3 = properties.getProperty("createOrgContent3");
		         sendEmail(emailFrom, accountEmailTo, subject, content1, content2,
		        		 regForm.getPassword(),regForm.getEmail(),content3);
		} catch (Exception e) {
			log.fatal("USER_SER_011", e);
			throw new BusinessException(e.getMessage());
		}
	}

	private void sendEmail(String emailFrom, String accountEmailTo,
			String subject, String content1, String content2, String password,
			String userName, String content3) {
		String[] status=new String[1];
		status[0]=accountEmailTo;
	      try {
	          final EmailParams emailParams = new EmailParams();
	          emailParams.setEmailFrom(emailFrom);
	          emailParams.setEmailTos(status);
	          emailParams.setEmailSubject(subject);
	          emailParams.setEmailBody(content1+userName+" "+content2+password+content3);
	          
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
	            
	   
	         // status = properties.getProperty("success");
	      } catch (Exception e) {
	          log.fatal("USER_SER_011", e);
	          e.printStackTrace();
	      } finally {
	          //saveEmailLog(orgId, emailFrom, accountEmailTo, subject, status);
	      }
		
	}

	@Override
	@Transactional
	public void deleteUser(String userId) throws BusinessException {
		try {
			userDAO.deleteUser(userId,properties.getProperty("inactive"),properties.getProperty("disabled"));
		} catch (Exception e) {
			log.fatal("USER_SER_012", e);
			throw new BusinessException(e.getMessage());
		}
		
	}

	@Override
	@Transactional
	public void editUser(RegistrationForm regForm) throws BusinessException {
		try {
			userDAO.editUser(regForm);
		} catch (Exception e) {
			log.fatal("USER_SER_013", e);
			throw new BusinessException(e.getMessage());
		}
		
	}



	@Override
	@Transactional
	public JSONObject getActRep(String orgId, String repId, String userId,String roleId)
			throws BusinessException, JSONException {
		 JSONObject jsonbuild = new JSONObject();
	        JSONArray jsonarray = new JSONArray();
	        JSONObject info = new JSONObject();
	        List<?> userList = null;
	        try {
	        	
	        	userList = userDAO.getRep(orgId,repId,properties.getProperty("active"),roleId);
	        		 if (userList.size() != 0) {
	 	                Iterator<?> i = userList.iterator();
	 	                jsonbuild.put(properties.getProperty("status"),
	 	                        properties.getProperty("success"));
	 	               info.put(properties.getProperty("key"),
	                		userId);
	                info.put(properties.getProperty("value"),
	                		properties.getProperty("self"));
	                jsonarray.put(info);
	 	                while (i.hasNext()) {
	 	                	Object[] cont = (Object[]) i.next();
	 	                     info = new JSONObject();
	 	                    info.put(properties.getProperty("key"),
	 	                    		cont[0].toString());
	 	                    info.put(properties.getProperty("value"),
	 	                    		cont[1].toString()+" - "+cont[2].toString());
	 	                    jsonarray.put(info);
	 	                }
	 	                jsonbuild.put(properties.getProperty("result"), jsonarray);
	        		 } else {
	 	                jsonbuild.put(properties.getProperty("status"),
	 	                        properties.getProperty("nodata"));
	 	                jsonbuild.put(properties.getProperty("result"), jsonarray);
	 	            }
	        } catch (Exception e) {
	            log.fatal("USER_SER_001", e);
	            jsonbuild.put(properties.getProperty("status"),
	                    properties.getProperty("error"));
	            jsonbuild.put(properties.getProperty("result"), jsonarray);
	        }
	        return jsonbuild;
	}

	@Override
	@Transactional
	public JSONObject getnewActivityRep(String orgId,String companyId,String userId) throws BusinessException, JSONException {
	     JSONObject jsonbuild = new JSONObject();
	        JSONArray jsonarray = new JSONArray();
	        List<?> userList;
	        JSONObject info = new JSONObject();
	        try {
	        	//userList = (List<?>) userDAO.getActivityRep(orgId,properties.getProperty("genSupportId"),companyId);
	        	userList = (List<?>) userDAO.getnewActivityAssignRepList(orgId, userId, companyId);
	            if (userList.size() != 0) {
	                Iterator<?> i = userList.iterator();
	                jsonbuild.put(properties.getProperty("status"),
	                        properties.getProperty("success"));
	               /* info.put(properties.getProperty("key"),
  	                		userId);
  	                info.put(properties.getProperty("value"),
  	                		properties.getProperty("self"));
	                
  	              jsonarray.put(info);*/
	                while (i.hasNext()) {
	                	
	                	Object[] cont = (Object[]) i.next();
	                	
	                	 info = new JSONObject();
	                    info.put(properties.getProperty("key"),
	                    		cont[0].toString());
	                    info.put(properties.getProperty("value"),
	                    		cont[1].toString()+" - "+cont[2].toString());
	                    jsonarray.put(info);
	                }
	                jsonbuild.put(properties.getProperty("result"), jsonarray);
	            } else {
	                jsonbuild.put(properties.getProperty("status"),
	                        properties.getProperty("nodata"));
	                jsonbuild.put(properties.getProperty("result"), jsonarray);
	            }
	        } catch (Exception e) {
	            log.fatal("USER_SER_002", e);
	            jsonbuild.put(properties.getProperty("status"),
	                    properties.getProperty("error"));
	            jsonbuild.put(properties.getProperty("result"), jsonarray);
	        }
	        return jsonbuild;
	    }

	
}
