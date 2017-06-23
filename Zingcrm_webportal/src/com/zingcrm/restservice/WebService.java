package com.zingcrm.restservice;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zingcrm.entity.Activity;
import com.zingcrm.entity.ActivityReference;
import com.zingcrm.entity.ForgotPassword;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.ActivityForms;
import com.zingcrm.forms.LeadForms;
import com.zingcrm.forms.OpportunityForms;
import com.zingcrm.forms.RegistrationForm;
import com.zingcrm.service.ActivityService;
import com.zingcrm.service.BusinessPartnerService;
import com.zingcrm.service.ChangePwdService;
import com.zingcrm.service.ForgotPasswordService;
import com.zingcrm.service.LoginMemberService;
import com.zingcrm.service.OpportunityService;
import com.zingcrm.utility.PropertiesHolder;


@Component
@Path("/service")
public class WebService {

	private static Logger LOG = Logger.getLogger(WebService.class.getName());

	@Autowired
	LoginMemberService loginService;
	
	@Autowired
	BusinessPartnerService leadService;
	
	@Autowired
	OpportunityService oppService;
	
	@Autowired
	ChangePwdService changePwd;
	
	 @Autowired 
	 private PropertiesHolder properties;
	 
	 @Autowired 
	 private ForgotPasswordService forgotpwdService;
	 
	 @Autowired 
	 private ActivityService actService;
	
	@POST
	@Path("/chkValidation")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseBody
	public String chkValidation(RegistrationForm inputJsonObj) {
		String strEmail = "",strPassword="";
		String strData = "";
		try {
			strEmail=inputJsonObj.getUserId();
			strPassword=inputJsonObj.getPassword();
			strData=loginService.verifyUser(strEmail, strPassword);
		} catch (BusinessException e) {
			LOG.error("rest service- login", e);
		} 
		return 	strData;

	}
	
	
	@POST
	@Path("/getLeadDetials")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseBody
	public String getLeadDetials(LeadForms inputJsonObj) {
		String strUserId = "",strOrgId="",strRoleId="";
		String strData = "";
		try {
			strUserId=inputJsonObj.getUserId();
			strOrgId=""+inputJsonObj.getOrg();
			strRoleId=""+inputJsonObj.getRoleId();
			strData=leadService.getDeviceLeadDetails(strUserId, strOrgId,strRoleId);
		} catch (BusinessException e) {
			LOG.error("lead service", e);
		} 
		return 	strData;

	}
	
	@POST
	@Path("/getOpportunity")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseBody
	public String getOpportunity(LeadForms inputJsonObj) throws JSONException {
		int leadId = 0;
		String strData = null,strRoleId="",strUserId="",strOrgId="";
		try {
			leadId=inputJsonObj.getId();
			strRoleId=""+inputJsonObj.getRoleId();
			strUserId=inputJsonObj.getUserId();
			strOrgId=""+inputJsonObj.getOrg();
			strData=oppService.getDeviceOpportunityDetails(leadId,strRoleId,strUserId,strOrgId);
		} catch (BusinessException e) {
			LOG.error("Opportunity service", e);
		} 
		return 	strData;
	}
	
	@POST
	@Path("/changePassword")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseBody
	public String changePwd(RegistrationForm inputJsonObj) throws JSONException {
		 JSONObject jsonbuild = new JSONObject();
		try {
			String strUserId=inputJsonObj.getUserId();
			if (changePwd.isCurrentPwdExists(strUserId,
					inputJsonObj.getCurrentpassword())) 
			{
                 changePwd.changePassword(strUserId,inputJsonObj.getPassword());
                 jsonbuild.put(properties.getProperty("status"),
	                        properties.getProperty("success"));
                
            } else {
                
                jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("oldpwdnotexists"));
            }
			
		} catch (BusinessException e) {
			LOG.error("Opportunity service", e);
		} 
		return 	jsonbuild.toString();
	}
	
	
	@POST
	@Path("/forgetPassword")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseBody
	public String forgetPassword(ForgotPassword forgotPassword) throws JSONException {
		 JSONObject jsonbuild = new JSONObject();
		try {
			if (forgotpwdService.isUserNameExists(forgotPassword.getUsername())) {
				forgotpwdService.resetpassword(forgotPassword);
				jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("success"));
			} else {
				jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("emailnotexists"));
			}
			
		} catch (BusinessException e) {
			LOG.error("Opportunity service", e);
		} 
		return 	jsonbuild.toString();
	}
	
	@POST
	@Path("/getBusinessPartner")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseBody
	public String getBusinessPartner(LeadForms inputJsonObj) throws JSONException {
		String strData = null,strRoleId="",strUserId="",strOrgId="";
		try {
			strRoleId=""+inputJsonObj.getRoleId();
			strUserId=inputJsonObj.getUserId();
			strOrgId=""+inputJsonObj.getOrg();
			strData=oppService.getDeviceBusinessPartner(strOrgId,strRoleId,strUserId);
		} catch (BusinessException e) {
			LOG.error("Opportunity service", e);
		} 
		return 	strData;
	}
	
	
	@POST
	@Path("/createOpportunity")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseBody
	public String createOpportunity(OpportunityForms oppForm) throws JSONException {
		 JSONObject jsonbuild = new JSONObject();
		try {
			oppForm.setDefaultstate(false);
			oppForm.setModifiedBy(oppForm.getUserId());
			oppService.saveOpp(oppForm);
			jsonbuild.put(properties.getProperty("status"),
                    properties.getProperty("success"));
		} catch (BusinessException e) {
			LOG.error("Opportunity service", e);
			jsonbuild.put(properties.getProperty("status"),
                    properties.getProperty("error"));
		} 
		return 	jsonbuild.toString();
	}
	
	@POST
	@Path("/getActivity")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseBody
	public String getActivity(ActivityForms inputJsonObj) throws JSONException {
		try {
			return actService.getActivity(inputJsonObj).toString();
		} catch (Exception e) {
			LOG.error("Opportunity service", e);
		}
		return null; 
	}
	
	@POST
	@Path("/getDefinedData2")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseBody
	public String getDefinedData2(ActivityForms inputJsonObj) throws JSONException {
		try {
			return actService.getDefinedData2(inputJsonObj.getUserField1()).toString();
		} catch (Exception e) {
			LOG.error("Opportunity service", e);
		}
		return null; 
	}
	
	
	
/*	@POST
	@Path("/saveActivity")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_JSON,"multipart/mixed"})
	@ResponseBody
	public String saveActivity(ActivityForms actForm,MultiPart multiPart) throws JSONException {
		try {

			
			// get the second part which is the project logo
		    BodyPartEntity bpe = (BodyPartEntity) multiPart.getBodyParts().get(0).getEntity();
		      InputStream source = bpe.getInputStream();
		      BufferedImage bi = ImageIO.read(source);
		      ByteArrayOutputStream baos = new ByteArrayOutputStream();
		     ImageIO.write(bi, "png", baos);
		     byte[] imageData = baos.toByteArray();
		     actForm.setFiles(imageData);
			return actService.saveActivity(actForm);
		} catch (Exception e) {
			LOG.error("Opportunity service", e);
		}
		return null; 
	}
	*/
	@POST
	@Path("/getTasks")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseBody
	public String getTasks(ActivityForms actForm) throws JSONException {
		try {
			return actService.getAllTasks(actForm.getRoleId(), actForm.getUserId(), ""+actForm.getOrg(), actForm.getActivityStatus());
		} catch (Exception e) {
			LOG.error("Opportunity service", e);
		}
		return null; 
	}
	
	@POST
	@Path("/editOpportunity")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseBody
	public String editOpportunity(OpportunityForms oppForm) throws JSONException {
		 JSONObject jsonbuild = new JSONObject();
		try {
			oppForm.setModifiedBy(oppForm.getUserId());
			oppService.updateOpp(oppForm);
			jsonbuild.put(properties.getProperty("status"),
                    properties.getProperty("success"));
		} catch (BusinessException e) {
			LOG.error("Opportunity service", e);
			jsonbuild.put(properties.getProperty("status"),
                    properties.getProperty("error"));
		} 
		return 	jsonbuild.toString();
	}
	
	@POST
	@Path("/currencyList")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseBody
	public String currencyList() throws JSONException {
		String strData = null;
		try {
			strData=oppService.currencylist();
		} catch (BusinessException e) {
			LOG.error("Opportunity service", e);
		} 
		return 	strData;
	}
	
	@POST
	@Path("/getActivityDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseBody
	public String getActivityDetails(ActivityForms actForm) throws JSONException {
		 String strData="";
		try {
			strData=actService.getActivityDetails(""+actForm.getId(),actForm.getUserId());
		} catch (BusinessException e) {
			LOG.error("Activity service", e);
		} 
		return 	strData;
	}
	
	
	@GET
	@Path("/getActivityImage/{id}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getActivityImage(@PathParam("id") String Id) {
		
		try {
			ActivityReference actref = actService.getActivityImageDevice(Id);
			final InputStream bigInputStream = new ByteArrayInputStream(
					actref.getDocument());
			return Response.ok(bigInputStream).build();
		} catch (Exception e) {
			return Response.noContent().build();
		}
	}
	
	
	@POST
	@Path("/removeAttachment")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseBody
	public String removeAttachment(ActivityForms actForm) throws JSONException {
		 
		 JSONObject jsonbuild = new JSONObject();
		try {
			actService.removeAttachment(""+actForm.getId());
			jsonbuild.put(properties.getProperty("status"),
                    properties.getProperty("success"));
		} 
		catch (Exception e) {
			LOG.error("Activity service", e);
			jsonbuild.put(properties.getProperty("status"),
                    properties.getProperty("error"));
		}
		return jsonbuild.toString();
	}
	
	
	@POST
	@Path("/getStatus")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseBody
	public String getStatus(ActivityForms actForm) throws JSONException {
		try {
			return actService.statusList(actForm.getUserId(),actForm.getCreatedBy());
		} catch (BusinessException e) {
			LOG.error("Activity service", e);
		}
		return null; 
	}
	@POST
	@Path("/getActivityDetailsDevice")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseBody
	public String getActivityDetailsDevice(ActivityForms inputJsonObj) throws JSONException {
		try {
			return actService.getActivityDetailsDevice(inputJsonObj).toString();
		} catch (Exception e) {
			LOG.error("Opportunity service", e);
		}
		return null; 
	}
}

