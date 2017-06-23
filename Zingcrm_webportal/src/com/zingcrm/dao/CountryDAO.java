/**
 *
 */
package com.zingcrm.dao;

import java.util.List;

import com.zingcrm.entity.Country;
import com.zingcrm.exception.BusinessException;


/**
 * @author NeshTech
 * 
 */
public interface CountryDAO {

    /**
     * @return Country List
     * @throws BusinessException
     *             Returns 404 page
     */
    List<Country> getCountryList() throws BusinessException;

}
