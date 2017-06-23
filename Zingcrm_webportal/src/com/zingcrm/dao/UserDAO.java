package com.zingcrm.dao;

import java.util.List;

import com.zingcrm.entity.Role;
import com.zingcrm.entity.User;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.forms.RegistrationForm;

public interface UserDAO {


	List<?> gridView(String orgId, String userName, String sortId,
			String sortDir, String property) throws BusinessException;

	List<?> getUserDetails(String userId) throws BusinessException;

	List<Role> getRole(String roleId, String superadmin) throws BusinessException;

	List<User> getEmailNameValidation(String email, String status) throws BusinessException;

	List<User> getEditEmailNameValidation(String email, String userId,
			String status) throws BusinessException;


	void deleteUser(String userId, String status, String disabled) throws BusinessException;

	void saveUser(RegistrationForm regForm, String property) throws BusinessException;

	void editUser(RegistrationForm regForm) throws BusinessException;

	List<Role> getEditRole(String roleId) throws BusinessException;

	List<?> getActivityRep(String orgId, String property, String companyId) throws BusinessException;

	List<?> getRep(String orgId, String companyId, String activeflag, String roleId)
			throws BusinessException;

	List<?> getAllRepList(String orgId, String property) throws BusinessException;

	List<?> getAssignedRepList(String orgId, String userid, String companyid) throws BusinessException;

	List<?> getnewActivityAssignRepList(String orgId, String userid,
			String companyid) throws BusinessException;

}
