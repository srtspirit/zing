package com.zingcrm.dao;

import java.util.List;

import com.zingcrm.entity.BusinessPartner;
import com.zingcrm.entity.BusinessPartnerContact;
import com.zingcrm.entity.Source;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.LeadForms;

public interface BusinessPartnerDAO {

	List<?> gridView(String companyName, String orgId, String sortId,
			String sortDir, String status, String userId) throws BusinessException;

	List<Source> sourceList() throws BusinessException;

	int saveLead(LeadForms leadForm) throws BusinessException;


	List<?> getLeadDetails(String leadId) throws BusinessException;

	void updateLead(LeadForms leadForm) throws BusinessException;

	List<BusinessPartner> getCompanyList(String orgId, String status, String userId) throws BusinessException;

	void deleteLead(String leadId, String status) throws BusinessException;

	List<BusinessPartner> getLeadNameValidation(String orgId, String leadName, String status) throws BusinessException;
 
	List<BusinessPartner> getEditLeadNameValidation(String orgId, String leadName,
			String leadId, String status) throws BusinessException;

	List<?> getDeviceLeadsInfo(String strUserId, String strOrgId, String status) throws BusinessException;

	List<?> exportLead(String companyName, String orgId, String property) throws BusinessException;

	Object setUploadExcelFile(List<BusinessPartner> leadList,
			List<BusinessPartnerContact> resList2, List<LeadForms> leadbean, String strPath) throws BusinessException;

	void updateLatitude(int leadId, String latitude, String longititude) throws BusinessException;

}
