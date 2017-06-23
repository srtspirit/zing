package com.zingcrm.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.json.JSONException;

import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.LeadForms;

public interface BusinessPartnerService {

	String orgList(String attribute, String attribute2) throws JSONException, BusinessException;

	String gridView(String sortId,String sortDir,String sessionRoleId,String sessionUserId,int intPage, int intLimit,String companyName,String orgId, String userId) throws BusinessException;

	String sourceList() throws BusinessException, JSONException;

	String saveLead(LeadForms leadForm) throws BusinessException;

	void deleteLead(String leadId) throws BusinessException;

	String getLeadDetails(String leadId, String roleId) throws BusinessException;

	String updateLead(LeadForms leadForm) throws BusinessException;

	String companylist(String orgId, String roleId, String userId) throws BusinessException, JSONException;

	String getLeadNameValidation(String orgId, String leadName) throws BusinessException;

	String getEditLeadNameValidation(String parameter, String leadName,
			String leadId) throws BusinessException;

	String getDeviceLeadDetails(String strUserId, String strOrgId, String strRoleId) throws BusinessException;

	ByteArrayInputStream exportLead(String strRoleId, String strOrgId,
			String companyName, String orgId) throws BusinessException,
			IOException, JSONException;

	Object importLeads(String fileName, String strPath, String parameter,
			String strUserId) throws Exception;

	String repList(String orgId, String roleId, String userId) throws BusinessException, JSONException;


	String countrylist() throws BusinessException, JSONException;

	String citylist(String stateId) throws BusinessException, JSONException;

	String statelist(String parameter) throws BusinessException, JSONException;

	String devicecompanylist(String orgId, String roleId, String userId)
			throws BusinessException, JSONException;


}
