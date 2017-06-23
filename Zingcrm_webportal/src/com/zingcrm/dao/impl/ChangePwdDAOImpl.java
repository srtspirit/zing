package com.zingcrm.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zingcrm.dao.ChangePwdDAO;
import com.zingcrm.entity.User;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.utility.Encrypt;



/**
 * @author NeshTech
 * 
 */

@Repository
public class ChangePwdDAOImpl implements ChangePwdDAO {

    private static Logger log = Logger.getLogger(ChangePwdDAOImpl.class
            .getName());

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private Encrypt encrypt;

    @SuppressWarnings({ "unchecked", "static-access" })
    @Override
    public List<User> readCurrentPassword(String userId, String pwd)
            throws BusinessException {
        try {
            return (List<User>) sessionFactory
                    .getCurrentSession()
                    .createQuery(
                            "from User where UserId = :userId and Password = :userPassword")
                    .setString("userId", userId)
                    .setString("userPassword", encrypt.encryptPwd(pwd)).list();
        } catch (HibernateException e) {
            log.fatal("CHANGEPWD_DAO_001", e);
            throw new BusinessException("ChangePwdDAOImpl::readCurrentPassword()");
        }
    }
    
    @Override
    public void changePassword(String userId, String pwd)
            throws BusinessException {
        try {
            Query query = sessionFactory
                    .getCurrentSession()
                    .createQuery(
                            "update User set Password =:userPassword where UserId = :userId");
            query.setString("userId", userId);
            query.setString("userPassword", pwd);
            query.executeUpdate();
        } catch (HibernateException e) {
            log.fatal("CHANGEPWD_DAO_002", e);
            throw new BusinessException("ChangePwdDAOImpl::changePassword()");
        }
    }
}
