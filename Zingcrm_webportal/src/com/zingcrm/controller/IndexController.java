package com.zingcrm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zingcrm.exception.BusinessException;
import com.zingcrm.service.CrudButtonService;
import com.zingcrm.service.LoginMemberService;

@Controller
@RequestMapping(value = "/index")
public class IndexController {

	private static Logger LOG = Logger.getLogger(IndexController.class
			.getName());

	@Autowired
	LoginMemberService loginservice;
	
	@Autowired
	CrudButtonService buttonService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView home() {

		return new ModelAndView("index");
	}

	@RequestMapping(value = "/loginDetails", method = RequestMethod.GET)
	public @ResponseBody
	String loginDetails() throws BusinessException {
		String strData = "";
		try {
			// registrationService.startReg();
			strData = loginservice.loginMember();
		} catch (BusinessException e) {
			LOG.fatal("INDEX_CON_001", e);
			throw new BusinessException("INDEX_OO1");
		}
		return strData;
	}

	@RequestMapping(value = "/getChartData", method = RequestMethod.GET)
	public @ResponseBody
	String getChartData(final HttpSession session) throws BusinessException {
		String strData = "";
		try {
			strData = loginservice.getChartData(
					(String) session.getAttribute("roleId"),
					(String) session.getAttribute("userId"),
					(String) session.getAttribute("orgId"));
		} catch (BusinessException e) {
			LOG.fatal("INDEX_CON_002", e);
			throw new BusinessException("INDEX_CON_002");
		}
		return strData;
	}

	@RequestMapping(value = "/getAllTasks", method = RequestMethod.GET)
	public @ResponseBody
	String getAllTasks1(final HttpSession session,
			final HttpServletRequest request) throws BusinessException {
		String strData = "";
		int intPage = 0, intLimit = 0;
		try {
			if (request.getParameter("page") != null)
				intPage = Integer.parseInt(request.getParameter("page"));

			if (request.getParameter("rows") != null)
				intLimit = Integer.parseInt(request.getParameter("rows"));
			
			strData = loginservice.getAllTasks(
					request.getParameter("sidx"),
					request.getParameter("sord"),
					(String) session.getAttribute("roleId"),
					(String) session.getAttribute("orgId"), intPage, intLimit,request.getParameter("status"),(String) session.getAttribute("userId"));
		} catch (BusinessException e) {
			LOG.fatal("INDEX_CON_003", e);
			throw new BusinessException("INDEX_CON_003");
		}
		return strData;
	}
	

	@RequestMapping(value = "/getAllTaskCreate", method = RequestMethod.GET)
	public @ResponseBody
	String getAllTaskCreate(final HttpSession session,
			final HttpServletRequest request) throws BusinessException {
		String strData = "";
		try {
			strData = buttonService.getAllTaskCreate(
					(String) session.getAttribute("roleId"),
					(String) session.getAttribute("userId"),
					(String) session.getAttribute("orgId"));
		} catch (Exception e) {
			LOG.fatal("INDEX_CON_004", e);
			throw new BusinessException("INDEX_CON_004");
		}
		return strData;
	}
}
