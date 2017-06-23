package com.zingcrm.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zingcrm.dao.StateDAO;
import com.zingcrm.entity.State;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.service.StateService;
import com.zingcrm.utility.PropertiesHolder;

@Service
public class StateServiceImpl implements StateService {

	private static Logger LOG = Logger.getLogger(StateServiceImpl.class
			.getName());

	@Autowired
	private StateDAO stateDAO;

	
	 @Autowired PropertiesHolder properties;

	/*@Override
	@Transactional
	public String stateService(String countryId) throws BusinessException, JSONException {
	    
	    JSONObject jsonbuild = new JSONObject();
        JSONArray jsonarray = new JSONArray();
        List<State> stateList;
        JSONObject info ;
        try {
            stateList = stateDAO.readStateCode(countryId);
            if (stateList.size() != 0) {
                Iterator<State> i = stateList.iterator();
                jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("success"));
                while (i.hasNext()) {
                    State state = i.next();
                    info= new JSONObject();
                    
                    jsonarray.put(info);
                }
                jsonbuild.put(properties.getProperty("result"), jsonarray);
            } else {
                jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("nodata"));
                jsonbuild.put(properties.getProperty("result"), jsonarray);
            }
        } catch (Exception e) {
            LOG.fatal("STASER_002", e);
            jsonbuild.put(properties.getProperty("status"),
                    properties.getProperty("error"));
            jsonbuild.put(properties.getProperty("result"), jsonarray);
        }
        return jsonbuild.toString();
	}*/
	
	
	
	  @Override
	  public String stateService(String countryId) throws BusinessException, JSONException {
	        JSONObject jsonbuild = new JSONObject();
	        JSONArray jsonarray = new JSONArray();
	        List<State> stateList;
	        try {
	        	stateList = stateDAO.readStateCode(countryId);
	            if (stateList.size() != 0) {
	                Iterator<State> i = stateList.iterator();
	                jsonbuild.put(properties.getProperty("status"),
	                        properties.getProperty("success"));
	                while (i.hasNext()) {
	                    State state = i.next();
	                    JSONObject info = new JSONObject();
	                    info.put(properties.getProperty("key"),
	                            state.getId());
	                    info.put(
	                            properties.getProperty("value"),
	                            state.getName());
	                    jsonarray.put(info);
	                }
	                jsonbuild.put(properties.getProperty("result"), jsonarray);
	            } else {
	                jsonbuild.put(properties.getProperty("status"),
	                        properties.getProperty("nodata"));
	                jsonbuild.put(properties.getProperty("result"), jsonarray);
	            }
	        } catch (Exception e) {
	        	LOG.fatal("STASER_002", e);
	            jsonbuild.put(properties.getProperty("status"),
	                    properties.getProperty("error"));
	            jsonbuild.put(properties.getProperty("result"), jsonarray);
	        }
	        return jsonbuild.toString();
	    }


}
