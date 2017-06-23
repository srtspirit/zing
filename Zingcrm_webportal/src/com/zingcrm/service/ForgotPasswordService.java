package com.zingcrm.service;

import com.zingcrm.entity.ForgotPassword;
import com.zingcrm.exception.BusinessException;


public interface ForgotPasswordService {


	public boolean isUserNameExists(String username) throws BusinessException;
	
	public void resetpassword(ForgotPassword forgotpwd) throws BusinessException;
	

}