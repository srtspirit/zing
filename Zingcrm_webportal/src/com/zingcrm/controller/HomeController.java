package com.zingcrm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.LoginMember;
import com.zingcrm.service.LoginMemberService;

/**
 * @author NeshTech Handles and retrieves Login page.
 *         <p>
 *         This utilizes just the standard JSP pages
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

    /**
     *
     */
    private static Logger log = Logger.getLogger(HomeController.class
            .getName());

    /**
     *
     */
    @Autowired
    private LoginMemberService loginService;
    
    

    /**
     * @param map
     *            Returns View Page
     * @param request
     *            Request for session variables
     * @return Login JSP Page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public final String loginPage(final ModelMap map,
            final HttpServletRequest request) {
        return "login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public final String login(final ModelMap map,
            final HttpServletRequest request) {
        return "login";
    }


    /**
     * @param request
     *            Request for session variables
     * @param response
     *            Return View page
     * @return Login JSP Page
     * @throws BusinessException
     *             Returns 404 page
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public final String logout(final HttpServletRequest request,
            final HttpServletResponse response) throws BusinessException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userId") != null) {
            session.invalidate();
        }
        return "login";
    }

    /**
     * @param model
     *            Returns View Page
     * @return Login JSP Page
     */
    @RequestMapping(value = "/LoginError", method = RequestMethod.GET)
    public final String loginError(final ModelMap model) {
        model.addAttribute("error", "true");
        return "login";
    }

    /**
     * @param request
     *            Request for session variables
     * @return Role based Landing Page
     * @throws BusinessException
     *             Returns 404 page
     */
    @RequestMapping(value = "/LoginService", method = RequestMethod.GET)
    public final String loginService(final HttpServletRequest request,HttpSession session)
            throws BusinessException {
        try {
        	LoginMember login = loginService.getLogin();
            session.setAttribute("userId", login.getUserId());
            session.setAttribute("name",  login.getFirstName()+" "+ login.getLastName());
            String strData=loginService.getMenu(login.getRoleId(),login.getOrgId());
			session.setAttribute("menu", strData.toString());
            session.setAttribute("roleId",""+login.getRoleId());
            session.setAttribute("orgId",""+login.getOrgId());
            return "redirect:" + login.getLandingPage();
        } catch (BusinessException e) {
            log.fatal("LOGIN_CON_001", e);
            throw new BusinessException(e.getMessage());
        }
    }
    

}
