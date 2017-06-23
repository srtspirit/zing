package com.zingcrm.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zingcrm.entity.ForgotPassword;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.service.ForgotPasswordService;


@Controller
@RequestMapping(value = "/")
public class ForgotPasswordController {

	private static Logger LOG = Logger.getLogger(ForgotPasswordController.class.getName());

	

	@Autowired
	private ForgotPasswordService forgotpwdService;
	
	@RequestMapping(value = "/forgotpwd", method = RequestMethod.GET)
	public ModelAndView forgotpwdPage(ModelMap model) {
		return new ModelAndView("/forgotpwd", "command", new ForgotPassword());
	}

	@RequestMapping(value = "/forgotpwd", method = RequestMethod.POST)
	public  String resetpassword(@ModelAttribute("command") @Valid ForgotPassword forgotPassword,BindingResult result) {
		
		String strData = "";
		

			try {
				if (forgotpwdService.isUserNameExists(forgotPassword.getUsername())) {
					forgotpwdService.resetpassword(forgotPassword);
					return "/forgotpwdsuccess";
				} else {
					result.rejectValue("username", "notexists.username");
					return "/forgotpwd";				}
			} catch (BusinessException e) {
				LOG.fatal("FORGOT_CON_001", e);
			}
		return strData;
	}
}