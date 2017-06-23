package com.zingcrm.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zingcrm.dao.LoginMemberDAO;
import com.zingcrm.entity.User;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.utility.Encrypt;

@Repository
public class LoginMemberDAOImpl implements LoginMemberDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private Encrypt encrypt;
	
	private static Logger LOG = Logger.getLogger(LoginMemberDAOImpl.class
			.getName());

	@Override
	public List<?> readLoginMember(String accountId) throws BusinessException {
		try {

			return ((List<?>) sessionFactory.getCurrentSession()
					.createSQLQuery("call loginMember (:accountId)")
					.setString("accountId", accountId).list());
		} catch (HibernateException e) {

			LOG.fatal("LOGIN_DAO_001", e);
			throw new BusinessException(
					"LoginMemberDAOImpl ::readLoginMember()");
		}
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public List<User> verifyUser(String strEmail, String strPassword)
			throws BusinessException {
		try{
			return (List<User>) sessionFactory.getCurrentSession().createQuery("from User where Email = :username and Password = :password").setString("username", strEmail).setString("password", encrypt.encryptPwd(strPassword)).list();
		} catch (HibernateException e) {
			
			LOG.fatal("LOGIN_DAO_002", e);
			throw new BusinessException("LoginMemberDAOImpl ::verifyUser()");
		}
	}

}
