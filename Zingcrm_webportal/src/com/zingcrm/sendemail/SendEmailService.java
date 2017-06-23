/**
 *
 */
package com.zingcrm.sendemail;

/**
 * @author NeshTech
 * 
 */
public interface SendEmailService {

    /**
     * @param emailParams
     *            Takes Email Parameters
     * @throws Exception
     *             Returns 404 page
     */
    void sendEmail(EmailParams emailParams) throws Exception;
}
