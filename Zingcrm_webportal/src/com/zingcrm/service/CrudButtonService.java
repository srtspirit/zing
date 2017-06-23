/**
 * 
 */
package com.zingcrm.service;

import org.json.JSONException;

import com.zingcrm.exception.BusinessException;

/**
 * @author NeshTech
 * 
 */
public interface CrudButtonService {

    /**
     * @param roleId
     *            Takes RoleId
     * @param featureId
     *            Takes FeatureIdId
     * @param orgId
     *            Takes OrgId
     * @return Buttons Based on Role and Organization
     * @throws BusinessException
     *             Returns 404 page
     * @throws com.zingcrm.exception.BusinessException 
     */
    String getCRUDButtons(int roleId, String featureId, String orgId)
            throws BusinessException;
    
    String getAllTaskCreate(String roleId, String userId, String orgId)
			throws BusinessException, JSONException;
}
