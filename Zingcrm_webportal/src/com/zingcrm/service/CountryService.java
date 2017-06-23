/**
 *
 */
package com.zingcrm.service;

import org.json.JSONException;

import com.zingcrm.exception.BusinessException;

/**
 * @author NeshTech
 * 
 */
public interface CountryService {

    /**
     * @return Calling Code List
     * @throws BusinessException
     *             Returns 404 page
     * @throws JSONException 
     */
    String callingcodeList() throws BusinessException, JSONException;

	String getCountry() throws BusinessException, JSONException;
	
	String countryCurrency() throws BusinessException, JSONException;

}
