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
public interface CrudButtonDAO {

	/**
	 * @param roleId
	 *            Takes RoleId
	 * @param featureId
	 *            Takes FeatureId
	 * @param orgId
	 *            Takes OrgId
	 * @return Button List
	 * @throws BusinessException
	 *             Returns 404 page
	 */
	List<?> getCrudButtons(int roleId, String featureId, String orgId)
			throws BusinessException;

	List<?> getCreateCrudButtons(String roleId, String orgId)
			throws BusinessException;

}
