package com.zingcrm.dao;

import java.util.List;

import com.zingcrm.entity.User;
import com.zingcrm.exception.BusinessException;


/**
 * @author NeshTech
 * 
 */
public interface ChangePwdDAO {

    
    /**
     * @param userid
     *            Takes UserId
     * @param pwd
     *            Takes Pwd
     * @return Password Exist or Not
     * @throws BusinessException
     *             Returns 404 page
     */
    public List<User> readCurrentPassword(String userId, String pwd)
            throws BusinessException;

    /**
     * @param userid
     *            Takes UserId
     * @param pwd
     *            Takes Pwd
     * @return Password Exist or Not
     * @throws BusinessException
     *             Returns 404 page
     */
    public void changePassword(String userId, String pwd)
            throws BusinessException;

    

}
