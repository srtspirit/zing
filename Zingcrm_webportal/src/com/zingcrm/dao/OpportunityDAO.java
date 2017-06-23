package com.zingcrm.dao;

import java.util.List;

import com.zingcrm.entity.ExpectedValue;
import com.zingcrm.entity.Opportunity;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.OpportunityForms;

public interface OpportunityDAO {

	List<?> gridView(String companyName, String orgId, String sortId,
			String sortDir, String property, String userId, String string) throws BusinessException;

	String saveOpp(OpportunityForms oppForm, String status, String oppKey) throws BusinessException;

	void updateOpp(OpportunityForms oppForm, String string) throws BusinessException;

	void deleteOpp(String oppId, String status) throws BusinessException;

	List<Opportunity> getOppNameValidation(String leadId, String oppName,
			String property) throws BusinessException;

	List<Opportunity> getEditOppNameValidation(String leadId, String oppName,
			String oppId, String property) throws BusinessException;

	List<Opportunity> getOpportunityList(String leadId, String property,String defaultOpp) throws BusinessException;

	List<?> getOppDetails(String oppId, String key) throws BusinessException;

	List<ExpectedValue> getExpectedValuelist() throws BusinessException;

	/*Opportunity getOpportunity(String oppId, String property) throws BusinessException;*/

	/*Document getDocumnetDetails(String orgId) throws BusinessException;*/


}
