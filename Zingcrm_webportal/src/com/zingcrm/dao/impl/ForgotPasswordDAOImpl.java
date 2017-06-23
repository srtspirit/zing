package com.zingcrm.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zingcrm.dao.ForgotPasswordDAO;
import com.zingcrm.entity.ForgotPassword;
import com.zingcrm.entity.User;
import com.zingcrm.exception.BusinessException;


@Transactional
@Repository
class ForgotPasswordDAOImpl implements ForgotPasswordDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private static Logger LOG = Logger.getLogger(ForgotPasswordDAOImpl.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ewallet.dao.ForgotPasswordDAO#resetpassword(com.ewallet.entity.
	 * ForgotPassword)
	 */
	@Override
	public void resetpassword(ForgotPassword forgotpwd) throws BusinessException {
		try {
		Query query = sessionFactory.getCurrentSession().createQuery("update User set Password =:Password where Email = :Email");
		query.setString("Password", forgotpwd.getPassword());
		query.setString("Email", forgotpwd.getUsername());
		query.executeUpdate();		
		} catch (HibernateException e) {

			LOG.fatal("FORGOT_DAO_001", e);
			throw new BusinessException("ForgotPasswordDAOImpl ::resetpassword()");
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> readUserName(String username) throws BusinessException {
		try {
			return (List<User>) sessionFactory.getCurrentSession().createQuery("from User where Email = :AccountId").setString("AccountId", username).list();
		} catch (HibernateException e) {

			LOG.fatal("FORGOT_DAO_002", e);
			throw new BusinessException("ForgotPasswordDAOImpl ::readUserName()");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ewallet.dao.ForgotPasswordDAO#userList()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ForgotPassword> userList() throws BusinessException {
		try {
		return (List<ForgotPassword>) sessionFactory.getCurrentSession().createQuery("from User").list();
		} catch (HibernateException e) {

			LOG.fatal("FORGOT_DAO_003", e);
			throw new BusinessException("ForgotPasswordDAOImpl ::userList()");
		}
	}
	
	/*@Override
	public void resetpasswordrest(String strEmail, String strPassword) throws BusinessException {
		try {
		Query query = sessionFactory.getCurrentSession().createQuery("update User set UserPassword =:UserPassword where username = :UserAccountId");
		query.setString("UserAccountId", strEmail);
		query.setString("UserPassword", strPassword);	
		query.executeUpdate();		
		} catch (HibernateException e) {

			LOG.fatal("FORGOT_DAO_REST_001", e);
			throw new BusinessException("ForgotPasswordDAOImpl ::resetpasswordrest()");
		}

	}
*/
}