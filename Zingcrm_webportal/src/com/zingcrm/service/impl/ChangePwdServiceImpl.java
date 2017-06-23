package com.zingcrm.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zingcrm.dao.ChangePwdDAO;
import com.zingcrm.entity.User;
import com.zingcrm.exception.BusinessException;
import com.zingcrm.service.ChangePwdService;
import com.zingcrm.utility.Encrypt;


/**
 * @author NeshTech
 * 
 */
@Service
public class ChangePwdServiceImpl implements ChangePwdService {

    /**
     * 
     */
    private Logger log = Logger.getLogger(ChangePwdServiceImpl.class.getName());

    /**
     * 
     */
    @Autowired
    private ChangePwdDAO changepasswordDAO;

    /**
     * 
     */
    @Autowired
    private Encrypt encrypt;

    
    @Override
    @Transactional
    public boolean isCurrentPwdExists(String userId, String pwd)
            throws BusinessException {
        List<User> value;
        boolean isCurrentPwdExists = false;
        try {
            value = changepasswordDAO.readCurrentPassword(userId, pwd);
            if (value.size() != 0) {
                Iterator<User> i = value.iterator();
                if (i.hasNext()) {
                    isCurrentPwdExists = true;
                } else {
                    isCurrentPwdExists = false;
                }
            }
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return isCurrentPwdExists;
    }
    
    @SuppressWarnings("static-access")
    @Override
    @Transactional
    public void changePassword(String userId, String pwd)
            throws BusinessException {
        String Encryptpwd = encrypt.encryptPwd(pwd);
        try {
            changepasswordDAO.changePassword(userId, Encryptpwd);
        } catch (BusinessException e) {
            log.fatal("CHANGEPWD_SER_001", e);
            throw new BusinessException(e.getMessage());
        }
    }


}
