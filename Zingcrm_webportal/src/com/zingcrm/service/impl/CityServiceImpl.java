package com.zingcrm.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zingcrm.dao.CityDAO;
import com.zingcrm.entity.City;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.service.CityService;


@Service
public class CityServiceImpl implements CityService {

	private static Logger LOG = Logger.getLogger(CityServiceImpl.class
			.getName());

	@Autowired
	private CityDAO cityDAO;

	/*
	 * @Autowired PropertiesHolder properties;
	 */
	@Override
	@Transactional
	public String cityService(String stateId) throws BusinessException, JSONException {

		JSONObject jsonbuild = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		List<City> value;
		try {
			value = (List<City>) cityDAO.readCityCode(stateId);
			if (value.size() != 0) {
				Iterator<City> i = value.iterator();
				jsonbuild.put("status","success");
				while (i.hasNext()) {
					JSONObject info = new JSONObject();
					City city = i.next();
						
						info.put("key", city.getId());
						info.put("value",StringEscapeUtils.escapeHtml(city.getName()));
						
					jsonarray.put(info);
				}
				jsonbuild.put("result", jsonarray);
			}
		} catch (Exception e) {
			LOG.fatal("CITSER_001", e);
			throw new BusinessException(e.getMessage());
		}
		return jsonbuild.toString();
	}
}
