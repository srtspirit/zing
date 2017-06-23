package com.zingcrm.service;

import org.json.JSONException;

import com.zingcrm.exception.BusinessException;


public interface StateService {

	String stateService(String countryId) throws BusinessException, JSONException ;

}
