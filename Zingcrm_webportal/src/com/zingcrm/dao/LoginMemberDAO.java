package com.zingcrm.dao;

import java.util.List;

import com.zingcrm.entity.User;
import com.zingcrm.exception.BusinessException;

public interface LoginMemberDAO {

	List<?> readLoginMember(String accountId) throws BusinessException;

	List<User> verifyUser(String strEmail, String strPassword)  throws BusinessException;
}
