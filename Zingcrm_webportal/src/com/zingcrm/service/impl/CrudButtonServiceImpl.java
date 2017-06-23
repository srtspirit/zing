/**
 * 
 */
package com.zingcrm.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zingcrm.dao.CrudButtonDAO;
import com.zingcrm.entity.RoleButtonOperation;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.service.CrudButtonService;
import com.zingcrm.utility.PropertiesHolder;


/**
 * @author NeshTech
 * 
 */
@Service
public class CrudButtonServiceImpl implements CrudButtonService {

    /**
     * 
     */
    private static Logger log = Logger.getLogger(CrudButtonServiceImpl.class
            .getName());

    /**
     * 
     */
    
    @Autowired
    private CrudButtonDAO crudButtonDAO;
    
    @Autowired
    private PropertiesHolder properties;

    @Override
    @Transactional
    public final String getCRUDButtons(final int roleId,
            final String featureId, final String orgId)
            throws BusinessException {
        StringBuffer strData = new StringBuffer();
        try {
            List<?> value = crudButtonDAO.getCrudButtons(roleId, featureId,
                    orgId);
            if (value.size() != 0) {
                Iterator<?> iterator = value.iterator();

                while (iterator.hasNext()) {
                    RoleButtonOperation roleBtn = (RoleButtonOperation) iterator
                            .next();
                    if(StringEscapeUtils.escapeHtml(roleBtn.getButtonTitle()).equals("View") || StringEscapeUtils.escapeHtml(roleBtn.getButtonTitle()).equals("Edit") || StringEscapeUtils.escapeHtml(roleBtn.getButtonTitle()).equals("Project") || StringEscapeUtils.escapeHtml(roleBtn.getButtonTitle()).equals("Activity")){
                    	 strData.append("<input class=\"button hideall\" "
                                 + "type=\"BUTTON\" "
                                 + "id=\""
                                 + roleBtn.getButtonId()
                                 + "\" value=\""
                                 + StringEscapeUtils.escapeHtml(roleBtn
                                         .getButtonTitle())
                                 + "\" title=\""
                                 + StringEscapeUtils.escapeHtml(roleBtn.getToolTip())
                                 + "\" alt=\"" + roleBtn.getToolTip()
                                 + "\" />&nbsp;");
                    }else{
                    strData.append("<input class=\"button\" "
                            + "type=\"BUTTON\" "
                            + "id=\""
                            + roleBtn.getButtonId()
                            + "\" value=\""
                            + StringEscapeUtils.escapeHtml(roleBtn
                                    .getButtonTitle())
                            + "\" title=\""
                            + StringEscapeUtils.escapeHtml(roleBtn.getToolTip())
                            + "\" alt=\"" + roleBtn.getToolTip()
                            + "\" />&nbsp;");
                    }
                }
            }

        } catch (BusinessException e) {
            log.fatal("CRUD_SER_001", e);
            throw new BusinessException(e.getMessage());
        }
        return strData.toString();
    }

	@Override
	@Transactional
	public String getAllTaskCreate(String roleId, String userId, String orgId)
			throws BusinessException, JSONException {
		   JSONObject jsonbuild = new JSONObject();
			JSONArray jsonarray = new JSONArray();
	        try {
	            List<?> value = crudButtonDAO.getCreateCrudButtons(roleId,orgId);
	            if (value.size() != 0) {
	                Iterator<?> iterator = value.iterator();
	                jsonbuild.put(properties.getProperty("status"),
	                        properties.getProperty("success"));
	                while (iterator.hasNext()) {
	                    Object[] roleBtn = (Object[]) iterator.next();
	                    JSONObject info = new JSONObject();
	                    
	                	info.put(properties.getProperty("key"),properties.getProperty(roleBtn[0].toString()));
						info.put(properties.getProperty("value"),roleBtn[1].toString());
						jsonarray.put(info);
						
	                }
	                jsonbuild.put(properties.getProperty("result"), jsonarray);
	            }
	            else
	            {
	            	 jsonbuild.put(properties.getProperty("status"),
	                         properties.getProperty("nodata"));
	            }

	        } catch (BusinessException e) {
	            log.fatal("CRUD_SER_002", e);
	            jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("error"));
	            throw new BusinessException(e.getMessage());
	        }
	        return jsonbuild.toString();
	}

}
