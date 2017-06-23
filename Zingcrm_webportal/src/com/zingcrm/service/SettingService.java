package com.zingcrm.service;

import org.json.JSONException;

import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.SettingForms;

public interface SettingService {

	String orgList(String attribute, String attribute2) throws BusinessException, JSONException;

	String getUserField(String parameter) throws BusinessException, JSONException;

	String editUserField(SettingForms setForm) throws BusinessException ;

	Object getDefinedData(String attribute) throws BusinessException, JSONException;

	Object getDefinedData2(String parameter) throws BusinessException, JSONException;

	String saveUserField1(SettingForms setForm)  throws BusinessException, JSONException;

	String saveUserField2(SettingForms setForm)  throws BusinessException, JSONException;

	String getAllRep(String orgId) throws BusinessException, JSONException;

	String changeUser(String orgId, String fromUserId, String toUserId, String createdBy) throws BusinessException, JSONException;

	String getAllActiveRep(String attribute) throws JSONException, BusinessException;

}
