package com.zingcrm.service;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.zingcrm.entity.Activity;
import com.zingcrm.entity.ActivityReference;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.ActivityForms;

public interface ActivityService {

	String orgList(String attribute, String attribute2) throws BusinessException, JSONException;


	String opportunitylist(String leadId) throws BusinessException, JSONException;

	String gridView(String parameter, String parameter2, String attribute,
			String attribute2, int intPage, int intLimit, String parameter3,
			String parameter4, String parameter5, String userId) throws BusinessException;

	void deleteActivity(String parameter) throws BusinessException;

	JSONObject getAssignedList(String orgId, String roleId, String companyId, String userId) throws BusinessException, JSONException;

	String getActivityNameValidation(String parameter, String parameter2) throws BusinessException;

	String getEditActivityNameValidation(String parameter, String parameter2,
			String parameter3) throws BusinessException;

	String getOrgUserDetails(String orgId) throws BusinessException;

	String saveActivity(ActivityForms actForm, String realPath) throws BusinessException;

	String getActivityDetails(String  actId, String roleId) throws BusinessException;

	List<ActivityReference> getActivityImage(String actId) throws BusinessException;

	String editActivity(ActivityForms actForm, String realPath, String userId) throws BusinessException;

	void removeAttachment(String parameter) throws BusinessException;

	String statusList(String sessionUserId, String userId) throws BusinessException, JSONException;

	String getChartData(String roleId, String userId, String orgId) throws BusinessException;


	List<?> getAllTasks(String roleId, String userId, String orgId, String status, String sidx, String sord)
			throws BusinessException;

	String statusUpdate(String id, String status, String modifiedUserId, String createdBy)throws BusinessException;

	JSONObject getDefinedData(String orgId) throws BusinessException, JSONException;

	JSONObject getDefinedData2(String data) throws BusinessException, JSONException;

	JSONObject getActivityType(String attribute) throws BusinessException, JSONException;

	String companylist(String orgId, String roleId, String userId,String flag)
			throws BusinessException, JSONException;


	String createopportunitylist(String parameter, String attribute) throws BusinessException, JSONException;


	JSONObject getActivity(ActivityForms actForms) throws JSONException ;


	String saveActivity(ActivityForms actForm) throws JSONException, BusinessException ;


	String updateActivity(ActivityForms actForm) throws JSONException, BusinessException;


	String getAllTasks(String roleId, String userId, String orgId,
			String actstatus) throws BusinessException;


	JSONObject getActivityDetailsDevice(ActivityForms actForm)
			throws JSONException, BusinessException;


	String getCompanylist(String orgId, String roleId, String userId)
			throws BusinessException, JSONException;


	JSONObject getnewActAssignRepList(String ogrid, String roleid,
			String companyid, String userid) throws BusinessException, JSONException;


	ActivityReference getActivityImageDevice(String actId)
			throws BusinessException;

}
