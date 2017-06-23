package com.zingcrm.service;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.RegistrationForm;

public interface UserService {


	String orgList(String attribute, String attribute2) throws BusinessException, JSONException;

	String gridView(String parameter, String parameter2, String attribute,
			String attribute2, int intPage, int intLimit, String parameter3,
			String parameter4) throws BusinessException;

	String getUserDetails(String parameter) throws BusinessException;

	String getRole(String roleId) throws BusinessException;

	String getPhoneCode() throws BusinessException;

	String getTimeZone() throws BusinessException;

	String getEmailNameValidation(String email) throws BusinessException;

	String getEditEmailNameValidation(String email, String userId)
			throws BusinessException;

	void saveUser(RegistrationForm regForm) throws BusinessException;

	void deleteUser(String userId) throws BusinessException;

	void editUser(RegistrationForm regForm) throws BusinessException;


	String getEditRole(String attribute) throws BusinessException;

/*	String getRep(String orgId, String roleId, String userId) throws BusinessException, JSONException;

	List<?> getRepList(String orgId) throws BusinessException, JSONException;

	


	JSONObject getActivitySalesAssigedTo(String orgId, String userId)
			throws BusinessException, JSONException;

	
	/*String getAllRepList(String orgId) throws BusinessException, JSONException;

	String getAllRepList(String orgId, String property) throws BusinessException, JSONException;*/

	JSONObject getActRep(String orgId, String string, String userId, String roleId) throws BusinessException, JSONException;

	JSONObject getnewActivityRep(String orgId, String companyId, String userId)
			throws BusinessException, JSONException;

	JSONObject getDeviceActivitySalesAssigedTo(String orgId, String userId,
			String roleID) throws BusinessException, JSONException;
	JSONObject getActivityRep(String orgId, String companyId, String userId) throws BusinessException, JSONException;

	String getRep(String orgId, String roleId, String userId) throws BusinessException, JSONException;

	String getAllRepList(String orgId) throws BusinessException, JSONException;

	String getAllRepList(String orgId, String status) throws BusinessException,
			JSONException;
	
	List<?> getRepList(String orgId) throws BusinessException, JSONException;

}
