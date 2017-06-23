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
import com.zingcrm.forms.LeadForms;
import com.zingcrm.forms.OpportunityForms;
import com.zingcrm.service.OpportunityService;

@Controller
@RequestMapping(value = "/project")
public class OpportunityController {
	private static Logger log = Logger.getLogger(OpportunityController.class
			.getName());

	@Autowired
	private OpportunityService oppService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public final ModelAndView oppHome(final ModelMap model,
			final HttpSession session,final HttpServletRequest request) throws BusinessException {

		model.addAttribute("name", session.getAttribute("name"));
		model.addAttribute("roleid", session.getAttribute("roleId"));
		return new ModelAndView("/project", "command",
				new OpportunityForms());
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public final ModelAndView oppCreateHome(final ModelMap model,
			final HttpSession session, final HttpServletRequest request)
			throws BusinessException
	{
		model.addAttribute("createOpp", "createOpps");
 		return new ModelAndView("/project", "command", new OpportunityForms());
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public final ModelAndView oppPostHome(final ModelMap model,
			final HttpSession session,final HttpServletRequest request) throws BusinessException {

		model.addAttribute("name", session.getAttribute("name"));
		model.addAttribute("leadId",request.getParameter("leadId"));
		model.addAttribute("createOpp",request.getParameter("createOpp"));
		return new ModelAndView("/project", "command",
				new OpportunityForms());
	}

	@RequestMapping(value = "/orglist", method = RequestMethod.GET)
	@ResponseBody
	final String organizationList(final HttpSession session)
			throws BusinessException, JSONException {
		try {
			return oppService.orgList((String) session.getAttribute("orgId"),
					(String) session.getAttribute("roleId"));
		} catch (BusinessException e) {
			log.fatal("OPP_CON_OO1", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/companylist", method = RequestMethod.GET)
	@ResponseBody
	final String companylist(final HttpServletRequest request,final HttpSession session)
			throws BusinessException, JSONException {
		try {
			return oppService.companylist(request.getParameter("orgId"),(String) session.getAttribute("roleId"),(String) session.getAttribute("userId"));
		} catch (BusinessException e) {
			log.fatal("OPP_CON_OO2", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/currencylist", method = RequestMethod.GET)
	@ResponseBody
	final String currencylist(final HttpServletRequest request,final HttpSession session)
			throws BusinessException, JSONException {
		try {
			return oppService.currencylist();
		} catch (BusinessException e) {
			log.fatal("OPP_SER_007", e);
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
			strData = oppService.gridView(request.getParameter("sidx"),
					request.getParameter("sord"),
					(String) session.getAttribute("roleId"),
					(String) session.getAttribute("orgId"), intPage, intLimit,
					request.getParameter("companyName"),
					request.getParameter("orgId"),(String) session.getAttribute("userId"));
		} catch (BusinessException e) {
			log.fatal("OPP_CON_OO3", e);
			throw new BusinessException("OPP_CON_OO3");
		}
		return strData;
	}

	@RequestMapping(value = "/createOpp", method = RequestMethod.POST)
	@ResponseBody
	final String createOpp(
			@ModelAttribute("command") @Valid final OpportunityForms oppForm,
			HttpSession session) throws BusinessException, JSONException {
		try {
			oppForm.setCreatedBy((String) session.getAttribute("userId"));
			oppForm.setDefaultstate(false);
			return oppService.saveOpp(oppForm);
		} catch (BusinessException e) {
			log.fatal("OPP_CON_OO4", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/getOppDetails", method = RequestMethod.GET)
	@ResponseBody
	final String getOppDetails(HttpServletRequest request)
			throws BusinessException, JSONException {
		try {
			return oppService.getOppDetails(request.getParameter("oppId"));
		} catch (BusinessException e) {
			log.fatal("OPP_CON_OO5", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/updateOpp", method = RequestMethod.POST)
	@ResponseBody
	final String updateOpp(
			@ModelAttribute("command") @Valid final OpportunityForms oppForm,
			HttpSession session) throws BusinessException, JSONException {
		try {
			oppForm.setModifiedBy((String) session.getAttribute("userId"));
			return oppService.updateOpp(oppForm);
		} catch (BusinessException e) {
			log.fatal("OPP_CON_OO6", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	  @RequestMapping(value = "/deleteOpp", method = RequestMethod.POST)
	    @ResponseBody
	    final String deleteOpp(HttpServletRequest request)
	            throws BusinessException, JSONException {
	        try {
	        	oppService.deleteOpp(request.getParameter("oppId"));
	             return "success";
	        } catch (BusinessException e) {
	            log.fatal("OPP_CON_OO7", e);
	            throw new BusinessException(e.getMessage());
	        }
	    }
	  
	  
	  @RequestMapping(value = "/getOppNameValidation", method = RequestMethod.GET)
	    @ResponseBody
	    final String getOppNameValidation(HttpServletRequest request)
	            throws BusinessException, JSONException {
	        try {
	        	return oppService.getOppNameValidation(request.getParameter("leadId"),request.getParameter("oppName"));
	        } catch (BusinessException e) {
	            log.fatal("OPP_CON_OO8", e);
	            throw new BusinessException(e.getMessage());
	        }
	    }
	  
	  @RequestMapping(value = "/getEditOppNameValidation", method = RequestMethod.GET)
	    @ResponseBody
	    final String getEditOppNameValidation(HttpServletRequest request)
	            throws BusinessException, JSONException {
	        try {
	        	return oppService.getEditOppNameValidation(request.getParameter("leadId"),request.getParameter("oppName"),request.getParameter("oppId"));
	        } catch (BusinessException e) {
	            log.fatal("OPP_CON_OO9", e);
	            throw new BusinessException(e.getMessage());
	        }
	    }
	  
	  
	  @RequestMapping(value = "/getStatus", method = RequestMethod.GET)
	    @ResponseBody
	    final String getDocumentId(HttpServletRequest request)
	            throws BusinessException, JSONException {
	        try {
	        	return oppService.getStatus(request.getParameter("orgId"));
	        } catch (BusinessException e) {
	            log.fatal("OPP_CON_O10", e);
	            throw new BusinessException(e.getMessage());
	        }
	    }

	  @RequestMapping(value = "/expectedValuelist", method = RequestMethod.GET)
		@ResponseBody
		final String expectedvaluelist(final HttpServletRequest request,final HttpSession session)
				throws BusinessException, JSONException {
			try {
				return oppService.expectedvaluelist();
			} catch (BusinessException e) {
				log.fatal("OPP_CON_O11", e);
				throw new BusinessException(e.getMessage());
			}
		}

	  
}
