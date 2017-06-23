/**
 * 
 */
package com.zingcrm.dao;

import java.util.List;

import com.zingcrm.exception.BusinessException;


/**
 * @author NeshTech
 * 
 */
public interface FeatureDAO {


    List<?> getFeatureList(String roleId, String orgId)
            throws BusinessException;

	List<?> getNewFeatureList(String roleId, String orgId)
			throws BusinessException;

}
