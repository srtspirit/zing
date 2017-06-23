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
import com.zingcrm.forms.SettingForms;
import com.zingcrm.service.SettingService;

@Controller
@RequestMapping(value = "/setting")
public class SettingController 
{

	private static Logger log = Logger.getLogger(SettingController.class
			.getName());
	
	@Autowired
	private SettingService settingService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public final ModelAndView settinghome(final ModelMap model,
			final HttpSession session, final HttpServletRequest request)
			throws BusinessException {

		model.addAttribute("name", session.getAttribute("name"));
		return new ModelAndView("/setting", "command", new SettingForms());
	}
	
	@RequestMapping(value = "/orglist", method = RequestMethod.GET)
	@ResponseBody
	final String organizationList(final HttpSession session)
			throws BusinessException, JSONException {
		try {
			return settingService.orgList((String) session.getAttribute("orgId"),
					(String) session.getAttribute("roleId"));
		} catch (BusinessException e) {
			log.fatal("SET_CON_OO1", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/getUserField", method = RequestMethod.GET)
	@ResponseBody
	final String getUserField(final HttpServletRequest request)
			throws BusinessException, JSONException {
		try {
			return settingService.getUserField(request.getParameter("orgId"));
		} catch (BusinessException e) {
			log.fatal("SET_CON_OO2", e);
			throw new BusinessException(e.getMessage());
		}
	}

	
	@RequestMapping(value = "/editUserField", method = RequestMethod.POST)
	@ResponseBody
	final String editUserField(@ModelAttribute("command") @Valid final SettingForms setForm)
			throws BusinessException, JSONException {
		try {
			return settingService.editUserField(setForm);
		} catch (BusinessException e) {
			log.fatal("SET_CON_OO3", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/definedData", method = RequestMethod.GET)
	@ResponseBody
	final String definedData(final HttpSession session)
			throws BusinessException, JSONException {
		try {
			return settingService.getDefinedData((String) session
					.getAttribute("orgId")).toString();
		} catch (BusinessException e) {
			log.fatal("SET_CON_OO4", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/definedData2", method = RequestMethod.GET)
	@ResponseBody
	final String definedData2(final HttpServletRequest request)
			throws BusinessException, JSONException {
		try {
			return settingService.getDefinedData2(request.getParameter("data")).toString();
		} catch (BusinessException e) {
			log.fatal("SET_CON_OO5", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/saveUserField1", method = RequestMethod.POST)
	@ResponseBody
	final String saveUserField1(@ModelAttribute("command") @Valid final SettingForms setForm)
			throws BusinessException, JSONException {
		try {
			return settingService.saveUserField1(setForm);
		} catch (BusinessException e) {
			log.fatal("SET_CON_OO6", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/saveUserField2", method = RequestMethod.POST)
	@ResponseBody
	final String saveUserField2(@ModelAttribute("command") @Valid final SettingForms setForm)
			throws BusinessException, JSONException {
		try {
			return settingService.saveUserField2(setForm);
		} catch (BusinessException e) {
			log.fatal("SET_CON_OO7", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/getAllActiveRep", method = RequestMethod.GET)
	@ResponseBody
	final String getAllActiveRep(final HttpSession session)
			throws BusinessException{
		try {
			return settingService.getAllActiveRep((String) session.getAttribute("orgId"));
		} catch (Exception e) {
			log.fatal("SET_CON_OO8", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/changeUser", method = RequestMethod.POST)
	@ResponseBody
	final String changeUser(HttpSession session,HttpServletRequest request )
			throws BusinessException, JSONException {
		try {
			return settingService.changeUser((String) session.getAttribute("orgId"),request.getParameter("fromUserId"),request.getParameter("toUserId"),(String) session.getAttribute("userId"));
		} catch (BusinessException e) {
			log.fatal("SET_CON_OO9", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/getAllRep", method = RequestMethod.GET)
	@ResponseBody
	final String getAllRep(final HttpSession session)
			throws BusinessException, JSONException {
		try {
			return settingService.getAllRep((String) session
					.getAttribute("orgId")).toString();
		} catch (BusinessException e) {
			log.fatal("SET_CON_OO8", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	
}
