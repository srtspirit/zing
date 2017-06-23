/**
 *
 */
package com.zingcrm.sendemail;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * @author NeshTech
 * 
 */
@Service
public class SendEmailServiceImpl implements SendEmailService {

    /**
     *
     */
    @Autowired
    private MailSender mailSender;

    /**
     *
     */
    private static Logger log = Logger.getLogger(SendEmailServiceImpl.class
            .getName());

    @Override
    @Async
    public final void sendEmail(final EmailParams emailParams)
            throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(emailParams.getEmailFrom());
       //message.setTo(emailParams.getEmailTo());
        message.setTo(emailParams.getEmailTos());
        message.setBcc(emailParams.getEmailFrom());
        message.setSubject(emailParams.getEmailSubject());
        message.setText(emailParams.getEmailBody());
        mailSender.send(message);

        log.info("Send Email to " + emailParams.getEmailTo());
    }
}
