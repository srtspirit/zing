package com.zingcrm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
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
import com.zingcrm.service.ChangePwdService;
import com.zingcrm.utility.PropertiesHolder;


/**
 * @author NeshTech Handles and retrieves Change Password page
 *         <p>
 *         This utilizes just the standard JSP pages
 * 
 */
@Controller
@RequestMapping(value = "/changepwd")
public class ChangePwdController {

    private static Logger log = Logger.getLogger(ChangePwdController.class
            .getName());

    @Autowired
    private PropertiesHolder properties;

    @Autowired
    private ChangePwdService changepasswordService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public final ModelAndView pwdHome(final ModelMap model,
            final HttpSession session) throws BusinessException {

    	model.addAttribute("name", session.getAttribute("name"));
        return new ModelAndView("/changepwd", "command",
                new RegistrationForm());
    }

    /**
     * @param request
     *            Current Password value
     * @return Current Password exists or not
     */
    @RequestMapping(value = "/chkCurrentPassword", method = RequestMethod.GET)
    @ResponseBody
    final String chkCurrentPassword(final HttpServletRequest request,
            final HttpSession session) {
        String strData = "";
        String sessionUserId = (String) session.getAttribute("userId");
        try {
            if (changepasswordService.isCurrentPwdExists(sessionUserId,
                    request.getParameter("currentpassword"))) {
                strData = properties.getProperty("oldpwdexists");
            } else {
                strData = properties.getProperty("oldpwdnotexists");
            }
        } catch (BusinessException e) {
            log.fatal("CHANGEPWD_CON_001", e);
        }
        return strData;
    }

    /**
     * @param session
     *            Request for session variables
     * @param registerForm
     *            Takes Form Values
     * @return Registration Form page
     * @throws BusinessException
     *             Returns 404 page
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    @ResponseBody
    final ModelAndView deleteappointments(
            final HttpSession session,
            @ModelAttribute("command") @Valid final RegistrationForm registerForm,
            final HttpServletRequest request) throws BusinessException {
        try {
            String sessionUserId = (String) session.getAttribute("userId");
            changepasswordService.changePassword(sessionUserId,
                    registerForm.getConfirmPassword());
            return new ModelAndView("/changepwdsuccess");
        } catch (BusinessException e) {
            log.fatal("CHANGEPWD_CON_002", e);
            throw new BusinessException(e.getMessage());
        }
    }

}
