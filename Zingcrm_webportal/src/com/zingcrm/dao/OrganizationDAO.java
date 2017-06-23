package com.zingcrm.dao;

import java.util.List;

import com.zingcrm.entity.OrganizationInfo;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.SettingForms;


/**
 * @author NeshTech
 */
public interface OrganizationDAO {


    /**
     * @param orgId
     *            takes org id
     * @return organization list
     * @throws BusinessException
     *             returns 404 page
     */
    List<OrganizationInfo> getOrgList(String orgId) throws BusinessException;

    OrganizationInfo getOrgUserDetails(String orgId) throws BusinessException;

	void editUserField(SettingForms setForm) throws BusinessException;


}
