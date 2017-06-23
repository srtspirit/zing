/**
 *
 */
package com.zingcrm.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zingcrm.dao.TimeZoneDAO;
import com.zingcrm.entity.TimeZone;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.service.TimeZoneService;
import com.zingcrm.utility.PropertiesHolder;



/**
 * @author NeshTech
 * 
 */
@Service
public class TimeZoneServiceImpl implements TimeZoneService {

    /**
    *
    */
    private static Logger log = Logger.getLogger(TimeZoneServiceImpl.class
            .getName());

    /**
    *
    */
    @Autowired
    private TimeZoneDAO timeZoneDAO;

    /**
    *
    */
    @Autowired
    private PropertiesHolder properties;

    /**
    *
    */
    @Override
    @Transactional
    public final String timezoneList() throws BusinessException {
        JSONObject jsonbuild = new JSONObject();
        JSONArray jsonarray = new JSONArray();
        try {
        	 List<TimeZone> timezonelist = timeZoneDAO.getTimeZoneList();
            if (timezonelist.size() != 0) {
                Iterator<TimeZone> i = timezonelist.iterator();
                jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("success"));
                while (i.hasNext()) {
                    TimeZone zones = i.next();
                    JSONObject info = new JSONObject();
                    info.put(properties.getProperty("key"),
                    		zones.getTimeZoneId());
                    info.put(properties.getProperty("value"),
                    		zones.getTimeZoneName());
                    jsonarray.put(info);
                }
                jsonbuild.put(properties.getProperty("result"), jsonarray);
            } else {
                jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("nodata"));
                jsonbuild.put(properties.getProperty("result"), jsonarray);
            }
        } catch (Exception e) {
            log.fatal("TIME_SER_001", e);
            throw new BusinessException(e.getMessage());
        }
        return jsonbuild.toString();
    }

    @Override
	public List<?> getTimezone(String strUserId) throws BusinessException 
	{
		List<TimeZone> timezonelist ;
		try{
	            timezonelist = timeZoneDAO.getTimeZone(strUserId);
		} catch (Exception e) {
            log.fatal("TimeZoneServiceImpl-getTimezone", e);
            throw new BusinessException(e.getMessage());
        }
		return timezonelist;
	           
	}

}
