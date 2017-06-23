/**
 *
 */
package com.zingcrm.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zingcrm.dao.OrganizationDAO;
import com.zingcrm.entity.OrganizationInfo;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.SettingForms;
import com.zingcrm.service.OrgService;
import com.zingcrm.utility.PropertiesHolder;

/**
 * @author NeshTech
 */
@Service
public class OrgServiceImpl implements OrgService {

    /**
     *
     */
    private static Logger log = Logger
            .getLogger(OrgServiceImpl.class.getName());

    /**
     *
     */
    @Autowired
    private OrganizationDAO organizationDAO;
    
    @Autowired
    private PropertiesHolder properties;



    @Override
    @Transactional
    public final String orgList(String orgId, final String roleId)
            throws BusinessException, JSONException {
        JSONObject jsonbuild = new JSONObject();
        JSONArray jsonarray = new JSONArray();
        List<OrganizationInfo> orglist;
        try {
            if (roleId.equals(properties.getProperty("superadmin"))) {
                orgId = properties.getProperty("orgAll");
            }
            orglist = (List<OrganizationInfo>) organizationDAO
                    .getOrgList(orgId);
            if (orglist.size() != 0) {
                Iterator<OrganizationInfo> i = orglist.iterator();
                jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("success"));
                while (i.hasNext()) {
                    OrganizationInfo organizationInfo = i.next();
                    JSONObject info = new JSONObject();
                    info.put(properties.getProperty("key"),
                            organizationInfo.getOrgId());
                    info.put(properties.getProperty("value"),
                            organizationInfo.getName());
                    jsonarray.put(info);
                }
                jsonbuild.put(properties.getProperty("result"), jsonarray);
            } else {
                jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("nodata"));
                jsonbuild.put(properties.getProperty("result"), jsonarray);
            }
        } catch (Exception e) {
            log.fatal("ORG_SER_001", e);
            jsonbuild.put(properties.getProperty("status"),
                    properties.getProperty("error"));
            jsonbuild.put(properties.getProperty("result"), jsonarray);
        }
        return jsonbuild.toString();
    }


	@Override
	@Transactional
	public String getOrgUserDetail(String orgId) throws BusinessException, JSONException {
		 JSONObject jsonbuild = new JSONObject();
		 JSONArray jsonarray = new JSONArray();
		  JSONObject info1 = new JSONObject();
		try
		{
			OrganizationInfo info=organizationDAO.getOrgUserDetails(orgId);
			if(info!=null)
			{
				jsonbuild.put("status","success");
				info1.put("userFiled1",info.getUserField1());
				info1.put("userFiled2",info.getUserField2());
				info1.put("required",info.isRequired());
				jsonarray.put(info1);
				jsonbuild.put(properties.getProperty("result"), jsonarray);
			}
			else
			{
				jsonbuild.put("status","nodata");
			}
			
		}catch(BusinessException e)
		{
			jsonbuild.put("status","error");
			 log.fatal("ORG_SER_002", e);
			 throw new BusinessException(e.getMessage());
		}
		return jsonbuild.toString();
	}

	
//	@Override
//	@Transactional
//	public String getOrgUserDetails(String orgId) throws BusinessException {
//		String strData="";
//		
//		try
//		{
//			OrganizationInfo info=organizationDAO.getOrgUserDetails(orgId);
//			if(info!=null)
//			{
//				
//				if(info.getUserField1()!=null && info.getUserField2()!=null){
//					strData=info.getUserField1()+"|"+info.getUserField2();
//				}
//				else if(info.getUserField1()!=null && info.getUserField2()==null)
//				{
//					strData=info.getUserField1()+"|"+"0";
//				}
//				else if(info.getUserField1()==null && info.getUserField2()!=null)
//				{
//					strData="0"+"|"+info.getUserField2();
//				}
//				else
//				{
//					strData="nodata";
//				}
//				return strData;
//			}
//			
//		}catch(BusinessException e)
//		{
//			 log.fatal("ORG_SER_002", e);
//			 throw new BusinessException(e.getMessage());
//		}
//		return strData;
//	}


	@Override
	@Transactional
	public String editUserField(SettingForms setForm) throws BusinessException {
		try 
		{
			organizationDAO.editUserField(setForm);
		   return "success";
		} catch (Exception e) {
			log.fatal("ORG_SER_003", e);
			throw new BusinessException(e.getMessage());
	}
	}



    
}
