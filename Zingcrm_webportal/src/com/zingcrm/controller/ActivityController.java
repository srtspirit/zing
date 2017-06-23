package com.zingcrm.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.zingcrm.entity.Activity;
import com.zingcrm.entity.ActivityReference;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.ActivityForms;
import com.zingcrm.service.ActivityService;
import com.zingcrm.utility.CalendarTimeZone;

@Controller
@RequestMapping(value = "/activity")
public class ActivityController {

	private static Logger log = Logger.getLogger(ActivityController.class
			.getName());

	@Autowired
	private ActivityService actService;
	
	  
	  @Autowired 
	  private CalendarTimeZone calendar;
	  
		 LinkedList<ActivityForms> files = new LinkedList<ActivityForms>();
		    ActivityForms fileMeta = null;
	  

	@RequestMapping(value = "", method = RequestMethod.GET)
	public final ModelAndView activityHome(final ModelMap model,
			final HttpSession session, final HttpServletRequest request)
			throws BusinessException {

		model.addAttribute("name", session.getAttribute("name"));
		model.addAttribute("roleid", session.getAttribute("roleId"));
		return new ModelAndView("/activity", "command", new ActivityForms());
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public final ModelAndView activityCreateHome(final ModelMap model,
			final HttpSession session, final HttpServletRequest request)
			throws BusinessException
	{
		model.addAttribute("createAct", "createAct");
		model.addAttribute("roleid", session.getAttribute("roleId"));
 		return new ModelAndView("/activity", "command", new ActivityForms());
	}
	@RequestMapping(value = "", method = RequestMethod.POST)
	public final ModelAndView activityPostHome(final ModelMap model,
			final HttpSession session, final HttpServletRequest request)
			throws BusinessException {

		model.addAttribute("name", session.getAttribute("name"));
		model.addAttribute("leadId", request.getParameter("leadId"));
		model.addAttribute("oppId", request.getParameter("oppId"));
		model.addAttribute("actId", request.getParameter("actId"));
		model.addAttribute("actStatusid", request.getParameter("actStatusid"));
		model.addAttribute("createAct", request.getParameter("createAct"));
		model.addAttribute("roleid", session.getAttribute("roleId"));
		return new ModelAndView("/activity", "command", new ActivityForms());
	}

	@RequestMapping(value = "/orglist", method = RequestMethod.GET)
	@ResponseBody
	final String organizationList(final HttpSession session)
			throws BusinessException, JSONException {
		try {
			return actService.orgList((String) session.getAttribute("orgId"),
					(String) session.getAttribute("roleId"));
		} catch (BusinessException e) {
			log.fatal("ACT_CON_OO1", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/companylist", method = RequestMethod.GET)
	@ResponseBody
	final String companylist(final HttpServletRequest request,
			HttpSession session) throws BusinessException, JSONException {
		try {
			return actService.companylist(request.getParameter("orgId"),
					(String) session.getAttribute("roleId"),
					(String) session.getAttribute("userId"),request.getParameter("flag"));
		} catch (BusinessException e) {
			log.fatal("ACT_CON_OO2", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/opportunitylist", method = RequestMethod.GET)
	@ResponseBody
	final String opportunitylist(final HttpServletRequest request)
			throws BusinessException, JSONException {
		try {
			return actService.opportunitylist(request.getParameter("leadId"));
		} catch (BusinessException e) {
			log.fatal("ACT_CON_OO3", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/createopportunitylist", method = RequestMethod.GET)
	@ResponseBody
	final String createopportunitylist(final HttpServletRequest request,
			HttpSession session) throws BusinessException, JSONException {
		try {
			return actService.createopportunitylist(
					request.getParameter("leadId"),
					(String) session.getAttribute("roleId"));
		} catch (BusinessException e) {
			log.fatal("ACT_CON_OO3", e);
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
			strData = actService.gridView(request.getParameter("sidx"),
					request.getParameter("sord"),
					(String) session.getAttribute("roleId"),
					(String) session.getAttribute("orgId"), intPage, intLimit,
					request.getParameter("orgId"),
					request.getParameter("leadId"),
					request.getParameter("oppId"),
					(String) session.getAttribute("userId"));
		} catch (BusinessException e) {
			log.fatal("ACT_CON_OO4", e);
			throw new BusinessException("ACT_CON_OO4");
		}
		return strData;
	}

	@RequestMapping(value = "/deleteActivity", method = RequestMethod.POST)
	@ResponseBody
	final String deleteActivity(HttpServletRequest request)
			throws BusinessException, JSONException {
		try {
			actService.deleteActivity(request.getParameter("activityId"));
			return "success";
		} catch (BusinessException e) {
			log.fatal("ACT_CON_OO5", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/assignedTo", method = RequestMethod.GET)
	@ResponseBody
	final String assignedTo(final HttpServletRequest request,
			HttpSession session) throws BusinessException, JSONException {
		try {
			return actService.getAssignedList(request.getParameter("orgId"),
					(String) session.getAttribute("roleId"),
					request.getParameter("companyId"),(String) session.getAttribute("userId")).toString();
		} catch (BusinessException e) {
			log.fatal("ACT_CON_OO6", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/getActivityNameValidation", method = RequestMethod.GET)
	@ResponseBody
	final String getActivityNameValidation(HttpServletRequest request)
			throws BusinessException, JSONException {
		try {
			return actService.getActivityNameValidation(
					request.getParameter("oppId"),
					request.getParameter("activityName"));
		} catch (BusinessException e) {
			log.fatal("ACT_CON_OO7", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/getEditActivityNameValidation", method = RequestMethod.GET)
	@ResponseBody
	final String getEditActivityNameValidation(HttpServletRequest request)
			throws BusinessException, JSONException {
		try {
			return actService.getEditActivityNameValidation(
					request.getParameter("oppId"),
					request.getParameter("activityName"),
					request.getParameter("activityId"));
		} catch (BusinessException e) {
			log.fatal("ACT_CON_OO8", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/getOrgUserDetails", method = RequestMethod.GET)
	@ResponseBody
	final String getOrgUserDetails(HttpServletRequest request)
			throws BusinessException, JSONException {
		try {
			return actService.getOrgUserDetails(request.getParameter("orgId"));
		} catch (BusinessException e) {
			log.fatal("ACT_CON_OO9", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/createActivity", method = RequestMethod.POST)
	final String createActivity(
			@ModelAttribute("command") @Valid final ActivityForms actForm,
			HttpSession session, HttpServletRequest request,final ModelMap model)
			throws BusinessException, JSONException {
		String strdata="";
		try {
			if(session!=null && session.getAttribute("userId")!=null){
			actForm.setCreatedBy((String) session.getAttribute("userId"));
			actForm.setUserField1(request.getParameter("userField1"));
			actForm.setUserField2(request.getParameter("userField2"));
			strdata= actService.saveActivity(actForm, request.getSession()
					.getServletContext().getRealPath("/"));
			
			model.addAttribute("actCreatestatus", strdata);
			}else{
				return "redirect:/login?user=sessiontimedout";
			}
		} catch (BusinessException e) {
			log.fatal("ACT_CON_O10", e);
		}
		
		return "redirect:/activity";
	}

	@RequestMapping(value = "/getActivityDetails", method = RequestMethod.GET)
	@ResponseBody
	final String getActivityDetails(HttpServletRequest request,
			HttpSession session) throws BusinessException, JSONException {
		try {
			return actService.getActivityDetails(
					request.getParameter("activityId"),
					(String) session.getAttribute("roleId"));
		} catch (BusinessException e) {
			log.fatal("ACT_CON_O11", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/editActivity", method = RequestMethod.POST)
	final String editActivity(
			@ModelAttribute("command") @Valid final ActivityForms actForm,
			HttpSession session, HttpServletRequest request,final ModelMap model)
			throws BusinessException, JSONException {
		String strdata="";
		try {
			model.addAttribute("actEditstatus", "Error Updating. Please try again later");
			if(session!=null && session.getAttribute("userId")!=null){
			actForm.setModifiedBy((String) session.getAttribute("userId"));
			strdata= actService.editActivity(actForm, request.getSession()
					.getServletContext().getRealPath("/"),
					(String) session.getAttribute("userId"));
				model.addAttribute("actEditstatus", strdata);
				model.addAttribute("editActId", actForm.getId());
			}
			else{
				return "redirect:/login?user=sessiontimedout";
			}
		} catch (BusinessException e) {
			log.fatal("ACT_CON_O12", e);
		}
		
		return "redirect:/activity";
	}

	@RequestMapping(value = "/statusUpdate", method = RequestMethod.POST)
	@ResponseBody
	final String statusUpdate(HttpSession session, HttpServletRequest request)
			throws BusinessException, JSONException {
		try {
			return actService.statusUpdate(request.getParameter("aid"),
					request.getParameter("status"),
					(String) session.getAttribute("userId"),
					request.getParameter("createdBy"));

		} catch (BusinessException e) {
			log.fatal("ACT_CON_019", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/getActivityImage", method = RequestMethod.GET)
	public @ResponseBody
	void getActivityImage(HttpServletRequest request,
			HttpServletResponse response) throws BusinessException,
			JSONException {
		try {
			ActivityReference act = actService.getActivityImageDevice(request
					.getParameter("activityId"));
			byte[] image = act.getDocument();
			String p = StringEscapeUtils.escapeHtml(act.getFilename());
			if (p.indexOf(".txt") > -1) {
				response.setContentType("text/plain");
			} else if (p.indexOf(".xls") > -1) {
				response.setContentType("application/vnd.ms-excel");
			} else if (p.indexOf(".pdf") > -1) {
				response.setContentType("application/pdf");
			} else if (p.indexOf(".doc") > -1) {
				response.setContentType("application/msword");
			} else if (p.indexOf(".xlsx") > -1) {
				response.setContentType("application/vnd.ms-excel");
			} else if (p.indexOf(".docx") > -1) {
				response.setContentType("application/msword");
			} else if (p.indexOf(".jpg") > -1) {
				response.setContentType("image/jpg");
			} else if (p.indexOf(".png") > -1) {
				response.setContentType("image/png");
			} else if (p.indexOf(".gif") > -1) {
				response.setContentType("image/gif");
			} else if (p.indexOf(".jpeg") > -1) {
				response.setContentType("image/jpeg");
			} else {
				response.setContentType("application/octet-stream");
			}
			response.setHeader("Content-Disposition", "attachment; filename="
					+ p);
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(image);
			outputStream.close();
			
		} catch (IOException e) {
			log.fatal("ACT_CON_O13", e);
			throw new BusinessException("ACT_CON_O13");
		}
	}

	@RequestMapping(value = "/removeAttachment", method = RequestMethod.POST)
	@ResponseBody
	final String removeAttachment(HttpServletRequest request)
			throws BusinessException, JSONException {
		try {
			actService.removeAttachment(request.getParameter("activityId"));
			return "success";
		} catch (BusinessException e) {
			log.fatal("ACT_CON_O14", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/status", method = RequestMethod.GET)
	@ResponseBody
	final String status(final HttpSession session,
			final HttpServletRequest request) throws BusinessException,
			JSONException {
		try {
			return actService.statusList(
					(String) session.getAttribute("userId"),
					request.getParameter("userId"));
		} catch (BusinessException e) {
			log.fatal("ACT_CON_O16", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/definedData", method = RequestMethod.GET)
	@ResponseBody
	final String definedData(final HttpSession session)
			throws BusinessException, JSONException {
		try {
			return actService.getDefinedData((String) session
					.getAttribute("orgId")).toString();
		} catch (BusinessException e) {
			log.fatal("ACT_CON_O20", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/definedData2", method = RequestMethod.GET)
	@ResponseBody
	final String definedData2(final HttpServletRequest request)
			throws BusinessException, JSONException {
		try {
			return actService.getDefinedData2(request.getParameter("data")).toString();
		} catch (BusinessException e) {
			log.fatal("ACT_CON_O21", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/activityType", method = RequestMethod.GET)
	@ResponseBody
	final String activityType(final HttpSession session)
			throws BusinessException, JSONException {
		try {
			return actService.getActivityType((String) session
					.getAttribute("orgId")).toString();
		} catch (BusinessException e) {
			log.fatal("ACT_CON_O22", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	@RequestMapping(value="/saveActivity" , method = RequestMethod.POST)
    public  @ResponseBody String saveActivity(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("command") @Valid final ActivityForms actForm) throws BusinessException, JSONException
    {
        try
        {
              ServletInputStream is = null;
              String contentType = request.getContentType();
           //   String filename = request.getHeader("X-File-Name");
              if((contentType != null) && (contentType.equals("application/octet-stream")))        //If browser is non-IE
              {
            	  final String uuid = (calendar.getDateToString(new Date())+(UUID.randomUUID().toString().substring(0, 5))).replaceAll("-", "").replaceAll(" ", "");
                is = request.getInputStream();
                
              /*  byte[] imageData =  IOUtils.toByteArray(is);*/
	  		    BufferedImage bi = ImageIO.read(is);
	  		      ByteArrayOutputStream baos = new ByteArrayOutputStream();
	  		     ImageIO.write(bi, "png", baos);
	  		     byte[] imageData = baos.toByteArray();
	  		     actForm.setFiles(imageData);
	  		     actForm.setFileName(uuid+".png");
	  		     is.close();
	  		     bi.flush();
	  		     baos.close();
            }
            return actService.saveActivity(actForm);
        }
        catch (FileNotFoundException e)
        {
            log.fatal("Save Activity",e);
        }
        catch (IOException e)
        {
        	log.fatal("Save Activity",e);
        }
		return "";
    }
	
	
	@RequestMapping(value="/updateActivity" , method = RequestMethod.POST)
    public  @ResponseBody String updateActivity(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("command") @Valid final ActivityForms actForm) throws BusinessException, JSONException
    {
		 String strData = null;
        try
        {
              ServletInputStream is = null;
             
              String contentType = request.getContentType();
              if((contentType != null) && (contentType.equals("application/octet-stream")))        //If browser is non-IE
              {
            	  final String uuid = (calendar.getDateToString(new Date())+(UUID.randomUUID().toString().substring(0, 5))).replaceAll("-", "").replaceAll(" ", "");
                  is = request.getInputStream();
                
	  		    BufferedImage bi = ImageIO.read(is);
	  		      ByteArrayOutputStream baos = new ByteArrayOutputStream();
	  		     ImageIO.write(bi, "png", baos);
	  		     byte[] imageData = baos.toByteArray();
	  		     actForm.setFiles(imageData);
	  		     actForm.setFileName(uuid+".png");
	  		     is.close();
	  		     bi.flush();
	  		     baos.close();
            }
              actForm.setModifiedBy(actForm.getUserId());
  			 strData=actService.updateActivity(actForm);
        }
        catch (FileNotFoundException e)
        {
            log.fatal("updateActivity Activity",e);
        }
        catch (IOException e)
        {
        	log.fatal("Update Activity",e);
        }
		return strData;
    }
	@RequestMapping(value = "/newActassignedTo", method = RequestMethod.GET)
	@ResponseBody
	final String newActivityassignedTo(final HttpServletRequest request,
			HttpSession session) throws BusinessException, JSONException {
		try {
			return actService.getnewActAssignRepList(request.getParameter("orgId"),
					(String) session.getAttribute("roleId"),
					request.getParameter("companyId"),(String) session.getAttribute("userId")).toString();
		} catch (BusinessException e) {
			log.fatal("ACT_CON_OO6", e);
			throw new BusinessException(e.getMessage());
		}
	}
	

	
}

