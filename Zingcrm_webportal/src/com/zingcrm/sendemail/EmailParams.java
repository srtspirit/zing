/**
 *
 */
package com.zingcrm.sendemail;

/**
 * @author NeshTech
 * 
 */
public class EmailParams {

    /**
     *
     */
    private String emailFrom;

    /**
     * @return the emailFrom
     */
    public final String getEmailFrom() {
        return emailFrom;
    }

    /**
     * @param emailFrom
     *            the emailFrom to set
     */
    public final void setEmailFrom(final String emailFrom) {
        this.emailFrom = emailFrom;
    }

    /**
     *
     */
    private String emailTo;
    
    private String[] emailTos;

    /**
     * @return the emailTo
     */
    public final String getEmailTo() {
        return emailTo;
    }
    
    /**
     * @return the emailTo
     */
    public final String[] getEmailTos() {
        return emailTos;
    }

    /**
     * @param emailTo
     *            the emailTo to set
     */
    public final void setEmailTo(final String emailTo) {
        this.emailTo = emailTo;
    }

    public final void setEmailTos(final String[] accountEmailTo) {
        this.emailTos = accountEmailTo;
    }
    /**
     *
     */
    private String emailSubject;

    /**
     * @return the emailSubject
     */
    public final String getEmailSubject() {
        return emailSubject;
    }

    /**
     * @param emailSubject
     *            the emailSubject to set
     */
    public final void setEmailSubject(final String emailSubject) {
        this.emailSubject = emailSubject;
    }

    /**
     *
     */
    private String emailBody;

    /**
     * @return the emailBody
     */
    public final String getEmailBody() {
        return emailBody;
    }

    /**
     * @param emailBody
     *            the emailBody to set
     */
    public final void setEmailBody(final String emailBody) {
        this.emailBody = emailBody;
    }

}
