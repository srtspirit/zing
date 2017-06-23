/**
 * 
 */
package com.zingcrm.service;

import com.zingcrm.exception.BusinessException;


/**
 * @author NeshTech
 * 
 */
public interface FeatureService {

    /**
     * @param strRoleId
     *            Takes RoleId
     * @param strOrgId
     *            Takes OrgId
     * @return Feature Based on Role and Organization
     * @throws BusinessException
     *             Returns 404 page
     */
    String getFeatureList(String strRoleId, String strOrgId)
            throws BusinessException;


}
