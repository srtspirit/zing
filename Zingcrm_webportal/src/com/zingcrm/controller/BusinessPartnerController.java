package com.zingcrm.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zingcrm.entity.LeadRequest;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.LeadForms;
import com.zingcrm.forms.LeadRequestForms;
import com.zingcrm.service.BusinessPartnerService;
import com.zingcrm.service.LeadRequestService;
import com.zingcrm.utility.CalendarTimeZone;

@Controller
@RequestMapping(value = "/business")
public class BusinessPartnerController {

	private static Logger log = Logger
			.getLogger(BusinessPartnerController.class.getName());

	@Autowired
	private BusinessPartnerService leadService;

	@Autowired
	private LeadRequestService leadReqService;
	
	@Autowired
	private CalendarTimeZone calendar;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public final ModelAndView leadHome(final ModelMap model,
			final HttpSession session, final HttpServletRequest request)
			throws BusinessException {

		model.addAttribute("name", session.getAttribute("name"));
		model.addAttribute("date","");
		model.addAttribute("lead","");
		model.addAttribute("primaryName","");
		model.addAttribute("primaryEmail","");
		model.addAttribute("primaryPhone","");
		model.addAttribute("primaryphoneExtension","");
		model.addAttribute("rep","");
		model.addAttribute("source","");
		model.addAttribute("Website","");
		return new ModelAndView("/business", "command", new LeadForms());
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public final ModelAndView leadCreateHome(final ModelMap model,
			final HttpSession session, final HttpServletRequest request)
			throws BusinessException
	{
		model.addAttribute("createLead", "createLead");
 		return new ModelAndView("/business", "command", new LeadForms());
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public final ModelAndView leadHomePost(final ModelMap model,
			final HttpSession session, final HttpServletRequest request)
			throws BusinessException {
		LeadForms leadfrm=new LeadForms();
		if (request.getParameter("leadreqid") != null) {
			model.addAttribute("leadrequestId",
					request.getParameter("leadreqid"));
		
		LeadRequest leadReq = leadReqService.getLeadRequestDetailsList(
				request.getParameter("leadreqid"),
				((String) session.getAttribute("orgId")));
		if(leadReq!=null)
		{
			model.addAttribute("date",calendar.getDateTimeToString(leadReq.getCreateddate()));
			model.addAttribute("lead",leadReq.getBusinessname());
			model.addAttribute("primaryName",leadReq.getContactname());
			model.addAttribute("primaryEmail",leadReq.getEmail());
			model.addAttribute("primaryPhone",leadReq.getPhonenumber());
			model.addAttribute("primaryphoneExtension",leadReq.getExtension());
			model.addAttribute("rep",leadReq.getSalesleadid());
			model.addAttribute("source",leadReq.getSource());
			model.addAttribute("Website",leadReq.getWebsite());
		}
		}
		
		model.addAttribute("createLead", request.getParameter("createLead"));
		return new ModelAndView("/business", "command",leadfrm);
	}

	@RequestMapping(value = "/orglist", method = RequestMethod.GET)
	@ResponseBody
	final String organizationList(final HttpSession session)
			throws BusinessException, JSONException {
		try {
			return leadService.orgList((String) session.getAttribute("orgId"),
					(String) session.getAttribute("roleId"));
		} catch (BusinessException e) {
			log.fatal("LEA_CON_OO1", e);
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
			strData = leadService.gridView(request.getParameter("sidx"),
					request.getParameter("sord"),
					(String) session.getAttribute("roleId"),
					(String) session.getAttribute("orgId"), intPage, intLimit,
					request.getParameter("companyName"),
					request.getParameter("orgId"),
					(String) session.getAttribute("userId"));
		} catch (BusinessException e) {
			log.fatal("LEA_CON_OO2", e);
			throw new BusinessException("USER_CON_010");
		}
		return strData;
	}

	@RequestMapping(value = "/sourceList", method = RequestMethod.GET)
	@ResponseBody
	final String sourceList(final HttpServletRequest request)
			throws BusinessException, JSONException {
		try {
			return leadService.sourceList();
		} catch (BusinessException e) {
			log.fatal("LEA_CON_OO3", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/repList", method = RequestMethod.GET)
	@ResponseBody
	final String repList(final HttpServletRequest request, HttpSession session)
			throws BusinessException, JSONException {
		try {
			return leadService.repList(request.getParameter("orgId"),
					(String) session.getAttribute("roleId"),
					(String) session.getAttribute("userId"));
		} catch (BusinessException e) {
			log.fatal("LEA_CON_OO4", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/createLead", method = RequestMethod.POST)
	@ResponseBody
	final String createLead(
			@ModelAttribute("command") @Valid LeadForms leadForm,
			HttpSession session, HttpServletRequest request)
			throws BusinessException, JSONException {
		String strData = "";
		try {
			leadForm.setCreatedBy((String) session.getAttribute("userId"));
			leadForm.setOrg(Integer.parseInt((String) session
					.getAttribute("orgId")));

			strData = leadService.saveLead(leadForm);

			/*
			 * else if (strData.equals("success")) { } return "success"; }
			 */
		} catch (BusinessException e) {
			log.fatal("LEA_CON_OO5", e);
			throw new BusinessException(e.getMessage());
		}
		return strData;
	}

	@RequestMapping(value = "/deleteLead", method = RequestMethod.POST)
	@ResponseBody
	final String deleteLead(HttpServletRequest request)
			throws BusinessException, JSONException {
		try {
			leadService.deleteLead(request.getParameter("leadId"));
			return "success";
		} catch (BusinessException e) {
			log.fatal("LEA_CON_OO6", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/getLeadDetails", method = RequestMethod.GET)
	@ResponseBody
	final String getLeadDetails(HttpServletRequest request, HttpSession session)
			throws BusinessException, JSONException {
		try {
			return leadService.getLeadDetails(request.getParameter("leadId"),
					(String) session.getAttribute("roleId"));
		} catch (BusinessException e) {
			log.fatal("LEA_CON_OO7", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/updateLead", method = RequestMethod.POST)
	@ResponseBody
	final String updateLead(
			@ModelAttribute("command") @Valid final LeadForms leadForm,
			HttpSession session) throws BusinessException, JSONException {
		try {
			leadForm.setModifiedBy((String) session.getAttribute("userId"));
			return leadService.updateLead(leadForm);
		} catch (BusinessException e) {
			log.fatal("LEA_CON_OO8", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/getLeadNameValidation", method = RequestMethod.GET)
	@ResponseBody
	final String getLeadNameValidation(HttpServletRequest request)
			throws BusinessException, JSONException {
		try {
			return leadService.getLeadNameValidation(
					request.getParameter("orgId"),
					request.getParameter("leadName"));
		} catch (BusinessException e) {
			log.fatal("LEA_CON_O10", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/getEditLeadNameValidation", method = RequestMethod.GET)
	@ResponseBody
	final String getEditLeadNameValidation(HttpServletRequest request)
			throws BusinessException, JSONException {
		try {
			return leadService.getEditLeadNameValidation(
					request.getParameter("orgId"),
					request.getParameter("leadName"),
					request.getParameter("leadId"));
		} catch (BusinessException e) {
			log.fatal("LEA_CON_O11", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/exportBP", method = RequestMethod.GET)
	final void exportLead(final HttpServletRequest request,
			HttpSession session, HttpServletResponse response)
			throws BusinessException, IOException {
		try {
			ByteArrayInputStream in = leadService.exportLead(
					(String) session.getAttribute("roleId"),
					(String) session.getAttribute("orgId"),
					request.getParameter("companyName"),
					request.getParameter("orgId"));

			if (in != null) {
				String strFileName = "Exported Business partner file.xls";
				strFileName = strFileName.replaceAll(" ", "_");
				response.setContentType("application/excel");
				response.setHeader("Content-Disposition",
						"attachment; filename=" + strFileName);
				ServletOutputStream out = response.getOutputStream();
				org.apache.poi.util.IOUtils.copy(in, out);
				out.flush();
				out.close();

			}
		} catch (Exception e) {
			log.fatal("LEA_CON_O14", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/importLeads", method = RequestMethod.GET)
	@ResponseBody
	final String importLeads(HttpServletRequest request, HttpSession session,
			HttpServletResponse response) throws BusinessException {
		try {
			String strPath = request.getSession().getServletContext()
					.getRealPath("/");
			String strUserId = (String) session.getAttribute("userId");
			String strOrgId = (String) session.getAttribute("orgId");
			String fileName = request.getParameter("fileName").replaceAll(" ",
					"-");
			Object b = leadService.importLeads(fileName, strPath, strOrgId,
					strUserId);
			File f = new File(strPath + fileName);
			f.delete();
			if (b instanceof String) {
				if (b.equals("success")) {
					return "success";
				} else if (b.equals("nodata")) {
					return "nodata";
				}
			} else {
				ByteArrayInputStream in = (ByteArrayInputStream) b;
				if (in != null) {
					int size = 0;
					byte[] bytearray = new byte[in.available()];
					OutputStream outStream = new FileOutputStream(strPath
							+ "/error_log_" + fileName);
					while ((size = in.read(bytearray)) != -1) {
						outStream.write(bytearray, 0, size);
					}
					outStream.flush();
					outStream.close();
					return "failure";
				}

			}
		} catch (Exception e) {
			log.fatal("LEA_CON_O15", e);
			throw new BusinessException(e.getMessage());
		}
		return null;
	}

	@RequestMapping(value = "/getErrorLogFile", method = RequestMethod.GET)
	final void getErrorLogFile(final HttpServletRequest request,
			HttpSession session, HttpServletResponse response)
			throws BusinessException, IOException {
		String strPath = request.getSession().getServletContext()
				.getRealPath("/");
		String fileName = request.getParameter("fileName").replaceAll(" ", "-");
		try {
			FileInputStream in = new FileInputStream(new File(strPath
					+ "/error_log_" + fileName));
			if (in != null) {
				String strFileName = "Imported file.xls";
				strFileName = strFileName.replaceAll(" ", "_");
				response.setContentType("application/excel");
				response.setHeader("Content-Disposition",
						"attachment; filename=" + strFileName);
				ServletOutputStream out = response.getOutputStream();
				org.apache.poi.util.IOUtils.copy(in, out);
				out.flush();
				out.close();

			}
		} catch (Exception e) {
			log.fatal("LEA_CON_O15", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/countrylist", method = RequestMethod.GET)
	@ResponseBody
	final String countrylist() throws BusinessException, JSONException {
		try {
			return leadService.countrylist();
		} catch (BusinessException e) {
			log.fatal("LEA_CON_O16", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/citylist", method = RequestMethod.GET)
	@ResponseBody
	final String citylist(final HttpServletRequest request)
			throws BusinessException, JSONException {
		try {
			return leadService.citylist(request.getParameter("stateId"));
		} catch (BusinessException e) {
			log.fatal("LEA_CON_O18", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/statelist", method = RequestMethod.GET)
	@ResponseBody
	final String statelist(final HttpServletRequest request)
			throws BusinessException, JSONException {
		try {
			return leadService.statelist(request.getParameter("countryId"));
		} catch (BusinessException e) {
			log.fatal("LEA_CON_O17", e);
			throw new BusinessException(e.getMessage());
		}
	}

}
