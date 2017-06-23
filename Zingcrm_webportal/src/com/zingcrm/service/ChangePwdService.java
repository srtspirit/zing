package com.zingcrm.service;

import com.zingcrm.exception.BusinessException;


/**
 * @author NeshTech
 * 
 */
public interface ChangePwdService {

    /**
     * @param userid
     *            Takes UserId
     * @param pwd
     *            Takes Pwd
     * @return Password Exist or Not
     * @throws BusinessException
     *             Returns 404 page
     */
    boolean isCurrentPwdExists(String userId, String pwd)
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
