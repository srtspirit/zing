package com.zingcrm.service;

import org.json.JSONException;

import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.OpportunityForms;

public interface OpportunityService {

	String orgList(String attribute, String attribute2) throws BusinessException, JSONException;

	String companylist(String orgId, String roleId, String userId) throws BusinessException, JSONException;

	String gridView(String parameter, String parameter2, String attribute,
			String attribute2, int intPage, int intLimit, String parameter3,
			String parameter4, String userId) throws BusinessException;

	String saveOpp(OpportunityForms oppForm) throws BusinessException;

	String getOppDetails(String parameter) throws BusinessException;

	String updateOpp(OpportunityForms oppForm) throws BusinessException;

	void deleteOpp(String parameter) throws BusinessException;

	String getOppNameValidation(String leadId, String oppName) throws BusinessException;

	String getEditOppNameValidation(String leadId, String oppName,
			String oppId) throws BusinessException;

	String opportunitylist(String leadId, String defaultOpportunity) throws BusinessException, JSONException;

	String getDeviceOpportunityDetails(int leadId, String strRoleId, String strUserId, String strOrgId) throws BusinessException, JSONException;

	String getDeviceBusinessPartner(String strOrgId, String strRoleId,
			String strUserId) throws BusinessException, JSONException;

	String getStatus(String parameter) throws BusinessException, JSONException;

	String currencylist() throws BusinessException, JSONException;

	String expectedvaluelist() throws BusinessException, JSONException;

}
