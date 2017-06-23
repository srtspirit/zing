package com.zingcrm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.RegistrationForm;
import com.zingcrm.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	private static Logger log=Logger.getLogger(UserController.class.getName());
	

	@Autowired
	private UserService usrService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public final ModelAndView userHome(final ModelMap model,
			final HttpSession session,final HttpServletRequest request) throws BusinessException {

		model.addAttribute("name", session.getAttribute("name"));
		return new ModelAndView("/user", "command",
				new RegistrationForm());
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public final ModelAndView profile(final ModelMap model,
			final HttpSession session,final HttpServletRequest request) throws BusinessException {

		model.addAttribute("name", session.getAttribute("name"));
		return new ModelAndView("/profile", "command",
				new RegistrationForm());
	}
	
	
	@RequestMapping(value = "/orglist", method = RequestMethod.GET)
	@ResponseBody
	final String organizationList(final HttpSession session)
			throws BusinessException, JSONException {
		try {
			return usrService.orgList((String) session.getAttribute("orgId"),
					(String) session.getAttribute("roleId"));
		} catch (BusinessException e) {
			log.fatal("USER_CON_OO3", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/gridview", method = RequestMethod.GET)
	public @ResponseBody
	String gridview(HttpServletRequest request) throws BusinessException {
		String strData = "";
		int intPage = 0, intLimit = 0;
		try {
			HttpSession session = request.getSession(false);
			if (request.getParameter("page") != null)
				intPage = Integer.parseInt(request.getParameter("page"));

			if (request.getParameter("rows") != null)
				intLimit = Integer.parseInt(request.getParameter("rows"));
			strData = usrService.gridView(request.getParameter("sidx"),
					request.getParameter("sord"),
					(String) session.getAttribute("roleId"),
					(String) session.getAttribute("orgId"), intPage, intLimit,
					request.getParameter("orgId"),
					request.getParameter("userName"));
		} catch (BusinessException e) {
			log.fatal("USER_CON_OO4", e);
			throw new BusinessException("USER_CON_OO4");
		}
		return strData;
	}
	
	 @RequestMapping(value = "/getUserDetails", method = RequestMethod.GET)
		@ResponseBody
		final String getUserDetails(
				HttpServletRequest request) throws BusinessException, JSONException {
			try {
				return usrService.getUserDetails(request.getParameter("userId"));
			} catch (BusinessException e) {
				log.fatal("USER_CON_OO5", e);
				throw new BusinessException(e.getMessage());
			}
		}
	 
	 @RequestMapping(value = "/getRole", method = RequestMethod.GET)
		@ResponseBody
		final String getRole(
				HttpSession session) throws BusinessException, JSONException {
			try {
				return usrService.getRole((String) session.getAttribute("roleId"));
			} catch (BusinessException e) {
				log.fatal("USER_CON_OO6", e);
				throw new BusinessException(e.getMessage());
			}
		}
	 
	 @RequestMapping(value = "/getEditRole", method = RequestMethod.GET)
		@ResponseBody
		final String getEditRole(
				HttpSession session) throws BusinessException, JSONException {
			try {
				return usrService.getEditRole((String) session.getAttribute("roleId"));
			} catch (BusinessException e) {
				log.fatal("USER_CON_OO6", e);
				throw new BusinessException(e.getMessage());
			}
		}
	 
	 @RequestMapping(value = "/getPhoneCode", method = RequestMethod.GET)
		@ResponseBody
		final String getPhoneCode(
				HttpSession session) throws BusinessException, JSONException {
			try {
				return usrService.getPhoneCode();
			} catch (BusinessException e) {
				log.fatal("USER_CON_OO7", e);
				throw new BusinessException(e.getMessage());
			}
		}
	 
	 @RequestMapping(value = "/getTimeZone", method = RequestMethod.GET)
		@ResponseBody
		final String getTimeZone(
				HttpSession session) throws BusinessException, JSONException {
			try {
				return usrService.getTimeZone();
			} catch (BusinessException e) {
				log.fatal("USER_CON_OO8", e);
				throw new BusinessException(e.getMessage());
			}
		}
	 
	 @RequestMapping(value = "/getEmailNameValidation", method = RequestMethod.GET)
		@ResponseBody
		final String getEmailNameValidation(
				HttpServletRequest request) throws BusinessException, JSONException {
			try {
				return usrService.getEmailNameValidation(request.getParameter("email"));
			} catch (BusinessException e) {
				log.fatal("USER_CON_OO9", e);
				throw new BusinessException(e.getMessage());
			}
		}
	 
	 @RequestMapping(value = "/getEditEmailNameValidation", method = RequestMethod.GET)
		@ResponseBody
		final String getEditEmailNameValidation(
				HttpServletRequest request) throws BusinessException, JSONException {
			try {
				return usrService.getEditEmailNameValidation(request.getParameter("email"),request.getParameter("userId"));
			} catch (BusinessException e) {
				log.fatal("USER_CON_O10", e);
				throw new BusinessException(e.getMessage());
			}
		}
	 
	 @RequestMapping(value = "/createUser", method = RequestMethod.POST)
		@ResponseBody
		final String createUser(
				@ModelAttribute("command") @Valid final RegistrationForm regForm,
				HttpSession session,HttpServletRequest request) throws BusinessException, JSONException {
			try {
				regForm.setCreatedBy((String) session.getAttribute("userId"));
				usrService.saveUser(regForm);
				return "success";
			} catch (BusinessException e) {
				log.fatal("USER_CON_O11", e);
				throw new BusinessException(e.getMessage());
			}
		}
	 
	 
	 	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	    @ResponseBody
	    final String deleteUser(HttpServletRequest request)
	            throws BusinessException, JSONException {
	        try {
	        	usrService.deleteUser(request.getParameter("userId"));
	             return "success";
	        } catch (BusinessException e) {
	            log.fatal("USER_CON_O12", e);
	            throw new BusinessException(e.getMessage());
	        }
	    }
	 	
	 	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	    @ResponseBody
	    final String editUser(@ModelAttribute("command") @Valid final RegistrationForm regForm,
				HttpSession session)
	            throws BusinessException, JSONException {
	        try {
	        	regForm.setModifiedBy((String) session.getAttribute("userId"));
	        	usrService.editUser(regForm);
	             return "success";
	        } catch (BusinessException e) {
	            log.fatal("USER_CON_O13", e);
	            throw new BusinessException(e.getMessage());
	        }
	    }
	  
	 	
	 	 @RequestMapping(value = "/getSessionUserDetails", method = RequestMethod.GET)
			@ResponseBody
			final String getSessionUserDetails(
					HttpSession session) throws BusinessException, JSONException {
				try {
					return usrService.getUserDetails((String) session.getAttribute("userId"));
				} catch (BusinessException e) {
					log.fatal("USER_CON_OO5", e);
					throw new BusinessException(e.getMessage());
				}
			}
	 	
}
	 
