package com.zingcrm.service.impl;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zingcrm.dao.SettingDAO;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.SettingForms;
import com.zingcrm.service.ActivityService;
import com.zingcrm.service.OrgService;
import com.zingcrm.service.SettingService;
import com.zingcrm.service.UserService;
import com.zingcrm.utility.PropertiesHolder;

@Service
public class SettingServiceImpl implements SettingService{

	private static Logger log = Logger.getLogger(ActivityServiceImpl.class
			.getName());

	@Autowired
	private OrgService orgService;
	
	@Autowired
	private ActivityService actService;
	
	@Autowired
	private SettingDAO settingDAO;
	

	@Autowired
	private UserService userService;
	
	@Autowired
	private PropertiesHolder properties;
	
	@Override
	@Transactional
	public String orgList(String orgId, String roleId)
			throws BusinessException, JSONException {
		try {
			return orgService.orgList(orgId, roleId);
		} catch (BusinessException e) {
			log.fatal("SET_SER_OO1", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public String getUserField(String orgId) throws BusinessException,
			JSONException {
		try {
			return orgService.getOrgUserDetail(orgId);
		} catch (BusinessException e) {
			log.fatal("SET_SER_OO2", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public String editUserField(SettingForms setForm) throws BusinessException {
		try {
			return orgService.editUserField(setForm);
		} catch (BusinessException e) {
			log.fatal("SET_SER_OO3", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Object getDefinedData(String attribute) throws BusinessException, JSONException {
		try {
			return actService.getDefinedData(attribute);
		} catch (BusinessException e) {
			log.fatal("SET_SER_OO4", e);
			throw new BusinessException(e.getMessage());
		}
		
	}

	@Override
	@Transactional
	public Object getDefinedData2(String parameter) throws BusinessException, JSONException {
		try {
			return actService.getDefinedData2(parameter);
		} catch (BusinessException e) {
			log.fatal("SET_SER_O05", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public String saveUserField1(SettingForms setForm) throws BusinessException,
			JSONException {
		try {
			settingDAO.saveUserField1(setForm);
			return "success";
		} catch (BusinessException e) {
			log.fatal("SET_SER_O06", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public String saveUserField2(SettingForms setForm)
			throws BusinessException, JSONException {
		try {
			settingDAO.saveUserField2(setForm);
			return "success";
		} catch (BusinessException e) {
			log.fatal("SET_SER_O07", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public String getAllRep(String orgId) throws BusinessException,
			JSONException {
		try {
			return userService.getAllRepList(orgId);
		} catch (BusinessException e) {
			log.fatal("SET_SER_O08", e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	

	@Override
	@Transactional
	public String changeUser(String orgId, String fromUserId, String toUserId,String createdBy)
			throws BusinessException, JSONException {
		try {
			 settingDAO.changeUser(orgId,fromUserId,toUserId,createdBy);
			 return "success";
		} catch (BusinessException e) {
			log.fatal("SET_SER_O08", e);
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public String getAllActiveRep(String orgId) throws JSONException, BusinessException {
			try {
				return userService.getAllRepList(orgId,properties.getProperty("active"));
			} catch (BusinessException e) {
				log.fatal("SET_SER_O08", e);
				throw new BusinessException(e.getMessage());
			}
		}
}
