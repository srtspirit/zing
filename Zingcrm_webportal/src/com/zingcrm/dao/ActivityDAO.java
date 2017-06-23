package com.zingcrm.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.zingcrm.entity.Activity;
import com.zingcrm.entity.ActivityReference;
import com.zingcrm.entity.ActivityType;
import com.zingcrm.entity.BusinessPartner;
import com.zingcrm.entity.DefinedData1;
import com.zingcrm.entity.DefinedData2;
import com.zingcrm.entity.Status;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.ActivityForms;

public interface ActivityDAO {

	List<?> gridView(String orgId, String leadId, String oppId, String sortId,
			String sortDir, String property, String userId, String actuserId, String activitykey) throws BusinessException;

	void deleteActivity(String activityId,String status) throws BusinessException;

	List<Activity> getActivityNameValidation(String oppId, String actName,
			String property) throws BusinessException;

	List<Activity> getEditActivityNameValidation(String oppId, String actName,
			String actId, String property) throws BusinessException;

	String saveActivity(ActivityForms actForm, String status, String activeStatus, String realPath, String activityKey) throws BusinessException, IOException;

	List<?> getActivityDetails(String actId) throws BusinessException;

	List<ActivityReference> getActivityImage(String actId) throws BusinessException;

	String editActivity(ActivityForms actForm,String strPath, String activityKey) throws BusinessException, IOException;

	void removeAttachment(String parameter) throws BusinessException;

	List<Status> getStatusList(String key) throws BusinessException;

	List<?> getChartData(String roleId, String userId, String orgId, String status, String key) throws BusinessException;

	List<?> getAllTasks(String leadUserId, String actUserId, String orgId,
			String property, String actstatus, String actClosed, String sortID, String sortDir) throws BusinessException;

	List<?> getActivityEmail(String createdBy, String assignedTo) throws BusinessException;

	void statusUpdate(String id, String status, String modifiedUserId, String activityKey) throws BusinessException;

	List<DefinedData1> getDefinedData(String orgId) throws BusinessException;

	List<DefinedData2> getDefinedData2(String data) throws BusinessException;

	List<ActivityType> getActivityType(String orgId) throws BusinessException;



	String editActivity(ActivityForms actForm, String property) throws BusinessException, IOException;

	HashMap<String, List> getActivityDetailDevice(ActivityForms actForms) throws BusinessException;

	List<?> getCompanyList(String orgId, String status,
			String userId) throws BusinessException;

	ActivityReference getActivityImagelink(String actrefId) throws BusinessException;

	int saveActivityDevice(ActivityForms actForm, String status,
			String activityStatus, String activitykey)
			throws BusinessException, IOException;


}
