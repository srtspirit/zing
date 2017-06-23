/**
 *
 */
package com.zingcrm.dao;

import java.util.List;

import com.zingcrm.entity.TimeZone;
import com.zingcrm.exception.BusinessException;


/**
 * @author NeshTech
 * 
 */
public interface TimeZoneDAO {

    /**
     * @return TimeZone List
     * @throws BusinessException
     *             Returns 404 page
     */
    List<TimeZone> getTimeZoneList() throws BusinessException;

	List<TimeZone> getTimeZone(String strUserId) throws BusinessException;

}
