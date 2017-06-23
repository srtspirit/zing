package com.zingcrm.dao;

import java.util.List;

import com.zingcrm.entity.ForgotPassword;
import com.zingcrm.entity.User;
import com.zingcrm.exception.BusinessException;


public interface ForgotPasswordDAO {
	public void resetpassword(ForgotPassword forgotpwd)
			throws BusinessException;

	public List<ForgotPassword> userList() throws BusinessException;

	public List<User> readUserName(String username)
			throws BusinessException;
	
/*	public void resetpasswordrest(String strEmail, String strPassword)
			throws BusinessException;*/
}