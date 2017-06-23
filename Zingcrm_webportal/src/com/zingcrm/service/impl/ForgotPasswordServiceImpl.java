package com.zingcrm.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zingcrm.controller.ForgotPasswordController;
import com.zingcrm.dao.ForgotPasswordDAO;
import com.zingcrm.entity.ForgotPassword;
import com.zingcrm.entity.User;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.sendemail.EmailParams;
import com.zingcrm.sendemail.SendEmailService;
import com.zingcrm.service.ForgotPasswordService;
import com.zingcrm.utility.Encrypt;
import com.zingcrm.utility.PropertiesHolder;


@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {
	
	private static Logger LOG = Logger.getLogger(ForgotPasswordServiceImpl.class.getName());


	@Autowired
	private ForgotPasswordDAO forgotpasswordDAO;

	
	


	@Autowired
	PropertiesHolder properties;

	 @Autowired
	  private SendEmailService sendEmailService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ewallet.service.ForgotPasswordService#resetpassword(com.ewallet.entity
	 * .ForgotPassword)
	 */
	@Override
	@Transactional
	public void resetpassword(ForgotPassword forgotpwd) {
		UUID uuid = UUID.randomUUID();
		String trimuid = trimUUID(uuid.toString());
		forgotpwd.setPassword(Encrypt.encryptPwd(trimuid.toString()));
		List<User> value;
		try {
			forgotpasswordDAO.resetpassword(forgotpwd);
			value=forgotpasswordDAO.readUserName(forgotpwd.getUsername());
		
			if(value.size()!=0)
			{
				Iterator<User> i = value.iterator();
				if (i.hasNext()) {
						String emailFrom = properties.getProperty("emailFrom");
			            String accountEmailTo = forgotpwd.getUsername();
			            String subject = properties.getProperty("forgotPwdSubject");
			            String content1 = properties.getProperty("forgotPwdContent1");
			            String content2 = properties.getProperty("forgotPwdContent2");
			            String content3 = properties.getProperty("forgotPwdContent3");
			            sendEmail(emailFrom, accountEmailTo, subject, content1, content2,
			            		trimuid,content3);
				}
					
			}
			
			
		} catch (BusinessException e) {
			
			LOG.fatal("FORGOT_SER_001", e);
			LOG.fatal(" ForgotPasswordServiceImpl -resetpassword ", e);
		}

	}

	private void sendEmail(String emailFrom, String accountEmailTo,
			String subject, String content1, String content2, String password,String content3) {
		  String[] array=new String[1];
	        try {
	            final EmailParams emailParams = new EmailParams();
	            emailParams.setEmailFrom(emailFrom);
	            array[0]=accountEmailTo;
	            emailParams.setEmailTos(array);
	            emailParams.setEmailSubject(subject);
	            emailParams.setEmailBody(content1 + " " + password + content2+content3);

	            Thread PictureLoader = new Thread(new Runnable() {
	                public void run() {
	                	try {
	                		 sendEmailService.sendEmail(emailParams);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                }
	            });

	            PictureLoader.start();
	        } catch (Exception e) {
	        	LOG.fatal("FORGOT_SER_001", e);
	            e.printStackTrace();
	        } finally {
	           // saveEmailLog(orgId, emailFrom, accountEmailTo, subject, status);
	        }
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ewallet.service.ForgotPasswordService#isUserNameExists(java.lang.
	 * String)
	 */
	@Override
	@Transactional
	public boolean isUserNameExists(String username) {
		List<User> value;
		boolean isUserNameExists = false;
		try {
			value = forgotpasswordDAO.readUserName(username);
			if(value.size()!=0)
			{
				Iterator<User> i = value.iterator();
				if (i.hasNext()) {
					isUserNameExists = true;
				} else {
					isUserNameExists = false;
				}
			}
		} catch (BusinessException e) {
			LOG.fatal("FORGOT_SER_002", e);
			LOG.fatal(" ForgotPasswordServiceImpl -isUserNameExists ", e);
		}
		return isUserNameExists;

	}

	public static String trimUUID(String uuid) {
		int index = uuid.indexOf("-");
		return uuid.substring(0, index);
	}


}