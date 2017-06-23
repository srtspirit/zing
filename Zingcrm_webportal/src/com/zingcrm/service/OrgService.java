/**
 *
 */
package com.zingcrm.service;

import org.json.JSONException;

import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.SettingForms;

/**
 * @author NeshTech
 */
public interface OrgService {

    /**
     * @param roleId
     *            takes role ID
     * @param orgId
     *            takes org id
     * @return org list
     * @throws BusinessException
     *             return 404 page
     * @throws JSONException
     *             return 404 page
     */
    String orgList(String orgId, String roleId) throws BusinessException,
            JSONException;


	String getOrgUserDetail(String orgId) throws BusinessException, JSONException;

	String editUserField(SettingForms setForm)  throws BusinessException;

    
}
