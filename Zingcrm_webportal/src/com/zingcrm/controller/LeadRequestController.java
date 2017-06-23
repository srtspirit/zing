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
import com.zingcrm.forms.LeadRequestForms;
import com.zingcrm.service.LeadRequestService;
import com.zingcrm.service.UserService;
import com.zingcrm.utility.CalendarTimeZone;

@Controller
@RequestMapping(value="/leadrequest")
public class LeadRequestController {

	private static Logger log= Logger.getLogger(LeadRequestController.class.getName());
	
	@Autowired
	public LeadRequestService LeadReqService;
	
	@Autowired
	public UserService Userservice;
	
	@Autowired
	public CalendarTimeZone calendar;
	
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public final ModelAndView leadrequestHome(ModelMap model, HttpSession session, HttpServletRequest httprequest){
		model.addAttribute("name", session.getAttribute("name"));
		
		return new ModelAndView("/leadrequest","command", new LeadRequestForms()); 
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public final ModelAndView leadReqCreateHome(final ModelMap model,
			final HttpSession session, final HttpServletRequest request)
			throws BusinessException
	{
		model.addAttribute("createLeadReq", "createLeadReq");
 		return new ModelAndView("/leadrequest", "command", new LeadRequestForms());
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public final ModelAndView leadRequestHomePOST(ModelMap model, HttpSession session, HttpServletRequest request){
		model.addAttribute("createLeadReq",request.getParameter("createLeadReq"));
		return new ModelAndView("/leadrequest", "command", new LeadRequestForms());
	}
	
	
	@RequestMapping(value="/gridview",method=RequestMethod.GET)
	public @ResponseBody 
	String gridview(HttpServletRequest request) throws BusinessException{
		String strData="";
		int intLimit=0,intPage=0;
		try{
			HttpSession session = request.getSession(false);
			if (request.getParameter("page") != null)
				intPage = Integer.parseInt(request.getParameter("page"));

			if (request.getParameter("rows") != null)
				intLimit = Integer.parseInt(request.getParameter("rows"));
			strData = LeadReqService.gridView(request.getParameter("sidx"),
					request.getParameter("sord"),
					(String) session.getAttribute("roleId"),
					(String) session.getAttribute("orgId"), intPage, intLimit,
					request.getParameter("orgId"),
					request.getParameter("leadId"),
					request.getParameter("oppId"),
					(String) session.getAttribute("userId"));
		}
		catch(BusinessException e){
			log.fatal("LEADREQ_CON_001");
			throw new BusinessException("LEADREQ_CON_001");
		}
		return strData;
	}
	@RequestMapping(value = "/defaultSalesRep", method = RequestMethod.GET)
	@ResponseBody
	final String OrgSalesRep(final HttpServletRequest request,
			HttpSession session) throws BusinessException, JSONException {
		try {
			return LeadReqService.getSalesRep(session.getAttribute("orgId")).toString();
		} catch (BusinessException e) {
			log.fatal("LEADREQ_CON_002", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/getLeadReqEmailValidation", method = RequestMethod.GET)
	@ResponseBody
	final String getLeadRequestEmailValidation(LeadRequestForms leadreqfrm, HttpSession session)
			throws BusinessException, JSONException {
		try {
			return LeadReqService.getLeadRequestEmailValidation(leadreqfrm.getEmail(), (String) session.getAttribute("orgId"),(String) session.getAttribute("userId"),""+leadreqfrm.getId());
		} catch (BusinessException e) {
			log.fatal("ACT_CON_OO7", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/getBusinessNameValidation", method = RequestMethod.GET)
	@ResponseBody
	final String getBusinessNameValidation(LeadRequestForms leadreqfrm, HttpSession session)
			throws BusinessException, JSONException {
		try {
			return LeadReqService.getBusinessNameValidation(leadreqfrm.getBusinessname(), (String) session.getAttribute("orgId"),(String) session.getAttribute("userId"),""+leadreqfrm.getId());
		} catch (BusinessException e) {
			log.fatal("ACT_CON_OO7", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	
	@RequestMapping(value = "/createLeadRequest", method = RequestMethod.POST)
	@ResponseBody
	final String createLeadrequest(
			@ModelAttribute("command") @Valid final LeadRequestForms leadreqfrm,
			HttpSession session, HttpServletRequest request)
			throws BusinessException {
		try {
			leadreqfrm.setCreatedby((String) session.getAttribute("userId"));
			leadreqfrm.setLeadUserName((String) session.getAttribute("name"));
			leadreqfrm.setOrgid((String) session.getAttribute("orgId"));
			return LeadReqService.saveLeadRequest(leadreqfrm);
		} catch (BusinessException e) {
			log.fatal("LEADREQ_CON_O03", e);
			throw new BusinessException(e.getMessage());
		}
	}
	@RequestMapping(value = "/getLeadRequestDetails", method = RequestMethod.GET)
	@ResponseBody
	final String getLeadRequestDetails(HttpServletRequest request,
			HttpSession session) throws BusinessException, JSONException {
		try {
			return LeadReqService.getLeadRequestDetails(
					request.getParameter("LeadRequestId"),
					((String) session.getAttribute("orgId")));
		} catch (BusinessException e) {
			log.fatal("LEADREQ_CON_O04", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/editLeadRequest", method = RequestMethod.POST)
	@ResponseBody
	final String editLeadRequest(
			@ModelAttribute("command") @Valid final LeadRequestForms leadreqForm,
			HttpSession session, HttpServletRequest request)
			throws BusinessException, JSONException {
		String result="";
		try {
			
			leadreqForm.setOrgid((String) session.getAttribute("orgId"));
			leadreqForm.setLeadUserName((String) session.getAttribute("name"));
			leadreqForm.setModifiedby((String) session.getAttribute("userId"));
			result= LeadReqService.editLeadRequest(leadreqForm);

		} catch (BusinessException e) {
			log.fatal("LEADREQ_CON_O05", e);
			throw new BusinessException(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/getSalesRepDetails", method = RequestMethod.GET)
	@ResponseBody
	final String OrgSalesRepdetail(final HttpServletRequest request,
			HttpSession session) throws BusinessException, JSONException {
		try {
			return LeadReqService.getSalesRepdetails((String) session.getAttribute("orgId")).toString();
		} catch (BusinessException e) {
			log.fatal("LEADREQ_CON_006", e);
			throw new BusinessException(e.getMessage());
		}
	}
	@RequestMapping(value = "/sourcedetail", method = RequestMethod.GET)
	@ResponseBody
	final String sourcedetails(final HttpServletRequest request, HttpSession session) throws BusinessException, JSONException {
		try{
			return LeadReqService.sourcelist().toString();
		}catch (BusinessException e) {
			log.fatal("LEADREQ_CON_007", e);
			throw new BusinessException(e.getMessage());
		}
	}
}
