package com.zingcrm.service;

import org.json.JSONException;
import org.json.JSONObject;

import com.zingcrm.entity.LeadRequest;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.LeadRequestForms;


public interface LeadRequestService {

	String gridView(String parameter, String parameter2, String attribute,
			String attribute2, int intPage, int intLimit, String parameter3,
			String parameter4, String parameter5, String attribute3) throws BusinessException;

	JSONObject getSalesRep(Object orgid) throws BusinessException, JSONException;

	String getLeadRequestDetails(String leadrequestid, String OrgId)
			throws BusinessException;

	String saveLeadRequest(LeadRequestForms leadreqform)
			throws BusinessException;

	String editLeadRequest(LeadRequestForms leadreqfrm)
			throws BusinessException;

	JSONObject getSalesRepdetails(String Orgid) throws BusinessException,
			JSONException;

	String getLeadRequestEmailValidation(String stremail, String strOrgid,
			String struserid, String leadid) throws BusinessException;

	LeadRequest getLeadRequestDetailsList(String parameter, String string) throws BusinessException;

	String getBusinessNameValidation(String parameter, String attribute,
			String attribute2, String parameter2) throws BusinessException;

	JSONObject sourcelist() throws BusinessException, JSONException;


}
