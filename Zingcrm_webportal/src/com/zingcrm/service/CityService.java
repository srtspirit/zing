package com.zingcrm.service;

import org.json.JSONException;

import com.zingcrm.exception.BusinessException;


public interface CityService {


	String cityService(String stateId) throws BusinessException, JSONException;

}
