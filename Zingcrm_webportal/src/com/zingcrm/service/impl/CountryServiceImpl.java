/**
 *
 */
package com.zingcrm.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zingcrm.dao.CountryDAO;
import com.zingcrm.entity.Country;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.service.CountryService;
import com.zingcrm.utility.PropertiesHolder;


/**
 * @author NeshTech
 * 
 */
@Service
public class CountryServiceImpl implements CountryService {

    /**
     *
     */
    private static Logger log = Logger.getLogger(CountryServiceImpl.class
            .getName());

    /**
     *
     */
    @Autowired
    private CountryDAO countryDAO;

    /**
     *
     */
    @Autowired
    private PropertiesHolder properties;

    /**
     * @throws JSONException 
     *
     */
    @Override
    public final String callingcodeList() throws BusinessException, JSONException {
        JSONObject jsonbuild = new JSONObject();
        JSONArray jsonarray = new JSONArray();
        List<Country> countrylist;
        try {
            countrylist = countryDAO.getCountryList();
            if (countrylist.size() != 0) {
                Iterator<Country> i = countrylist.iterator();
                jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("success"));
                while (i.hasNext()) {
                    Country country = i.next();
                    JSONObject info = new JSONObject();
                    info.put(properties.getProperty("key"),
                            country.getCountryId());
                    info.put(
                            properties.getProperty("value"),
                            country.getCountryName() + "  ( +"
                                    + country.getCallingCode() + ")");
                    jsonarray.put(info);
                }
                jsonbuild.put(properties.getProperty("result"), jsonarray);
            } else {
                jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("nodata"));
                jsonbuild.put(properties.getProperty("result"), jsonarray);
            }
        } catch (Exception e) {
            log.fatal("COUNTRY_SER_001", e);
            jsonbuild.put(properties.getProperty("status"),
                    properties.getProperty("error"));
            jsonbuild.put(properties.getProperty("result"), jsonarray);
        }
        return jsonbuild.toString();
    }
    
    @Override
    public final String getCountry() throws BusinessException, JSONException {
        JSONObject jsonbuild = new JSONObject();
        JSONArray jsonarray = new JSONArray();
        List<Country> countrylist;
        try {
            countrylist = countryDAO.getCountryList();
            if (countrylist.size() != 0) {
                Iterator<Country> i = countrylist.iterator();
                jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("success"));
                while (i.hasNext()) {
                    Country country = i.next();
                    JSONObject info = new JSONObject();
                    info.put(properties.getProperty("key"),
                            country.getCountryId());
                    info.put(
                            properties.getProperty("value"),
                            country.getCountryName());
                    jsonarray.put(info);
                }
                jsonbuild.put(properties.getProperty("result"), jsonarray);
            } else {
                jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("nodata"));
                jsonbuild.put(properties.getProperty("result"), jsonarray);
            }
        } catch (Exception e) {
            log.fatal("COUNTRY_SER_002", e);
            jsonbuild.put(properties.getProperty("status"),
                    properties.getProperty("error"));
            jsonbuild.put(properties.getProperty("result"), jsonarray);
        }
        return jsonbuild.toString();
    }

    
    @Override
    public final String countryCurrency() throws BusinessException, JSONException {
        JSONObject jsonbuild = new JSONObject();
        JSONArray jsonarray = new JSONArray();
        List<Country> countrylist;
        try {
            countrylist = countryDAO.getCountryList();
            if (countrylist.size() != 0) {
                Iterator<Country> i = countrylist.iterator();
                jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("success"));
                while (i.hasNext()) {
                    Country country = i.next();
                    JSONObject info = new JSONObject();
                    info.put(properties.getProperty("key"),
                            country.getCountryId());
                    info.put(
                            properties.getProperty("value"),
                            country.getCurrencyCode());
                    jsonarray.put(info);
                }
                jsonbuild.put(properties.getProperty("result"), jsonarray);
            } else {
                jsonbuild.put(properties.getProperty("status"),
                        properties.getProperty("nodata"));
                jsonbuild.put(properties.getProperty("result"), jsonarray);
            }
        } catch (Exception e) {
            log.fatal("COUNTRY_SER_001", e);
            jsonbuild.put(properties.getProperty("status"),
                    properties.getProperty("error"));
            jsonbuild.put(properties.getProperty("result"), jsonarray);
        }
        return jsonbuild.toString();
    }
    
}
