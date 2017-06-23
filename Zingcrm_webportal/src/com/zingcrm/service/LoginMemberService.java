package com.zingcrm.service;

import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.LoginMember;

public interface LoginMemberService {

	LoginMember getLogin() throws BusinessException;

	String loginMember() throws BusinessException;

	String getMenu(int roleId, String orgId) throws BusinessException;

	String verifyUser(String strEmail, String strPassword)
			throws BusinessException;

	String getChartData(String roleId, String userId, String orgId)
			throws BusinessException;


	String getAllTasks(String sidx, String sord, String roleId,
			String orgId, int intPage, int intLimit, String status, String string) throws BusinessException;;
}
