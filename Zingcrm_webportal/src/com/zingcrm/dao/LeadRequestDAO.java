package com.zingcrm.dao;

import java.io.IOException;
import java.util.List;

import com.zingcrm.entity.LeadRequest;
import com.zingcrm.entity.Source;
import com.zingcrm.entity.User;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.LeadRequestForms;

public interface LeadRequestDAO {

	List<User> getAssinedSalesRep(String Orgid,String Status) throws BusinessException;

	void saveLeadReuest(LeadRequestForms leadreqForm) throws BusinessException,
			IOException;

	List<LeadRequest> getLeadRequestDetails(String leadrequestId, String orgid)
			throws BusinessException;

	String editLeadRequest(LeadRequestForms leadreqform)
			throws BusinessException, IOException;


	List<User> getSalesRepdetails(String Orgid) throws BusinessException;
	
	List<LeadRequest> getLeadRequestEmailValidation(String strEmail,
			String strorgid, String userid, String leadid)
			throws BusinessException;

	List<?> gridView(String orgId, String leadId, String oppId, String sortId,
			String sortDir, String status, String userId, String actUserId,
			String key, String strRoleid) throws BusinessException;

	String getBusinessNameValidation(String strBP, String strOrgid,
			String struserid, String leadid) throws BusinessException;

	List<Source> getSourcedetails() throws BusinessException;

}
