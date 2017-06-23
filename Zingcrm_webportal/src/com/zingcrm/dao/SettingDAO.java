package com.zingcrm.dao;

import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.SettingForms;

public interface SettingDAO {
	
	void saveUserField1(SettingForms setForm)  throws BusinessException;

	void saveUserField2(SettingForms setForm)  throws BusinessException;


	void changeUser(String orgId, String fromUserId, String toUserId,
			String userID) throws BusinessException;
}
