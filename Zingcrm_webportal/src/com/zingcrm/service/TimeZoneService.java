/**
 *
 */
package com.zingcrm.service;

import java.util.List;

import com.zingcrm.exception.BusinessException;

/**
 * @author NeshTech
 * 
 */
public interface TimeZoneService {

    /**
     * @return TimeZone List
     * @throws BusinessException
     *             Returns 404 page
     */
    String timezoneList() throws BusinessException;

	List<?> getTimezone(String strUserId) throws BusinessException;


}
