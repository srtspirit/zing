/**
 * 
 */
package com.zingcrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author NeshTech
 * 
 */
@Entity
@Table(name = "timezone")
public class TimeZone {

    @Id
    @Column(name = "TimeZoneID")
    private int timeZoneId;

    @Column(name = "TimeZoneName")
    private String timeZoneName;

    @Column(name = "TimeZoneCode")
    private String timeZoneCode;

    @Column(name = "TimeZoneValue")
    private String timeZoneValue;

    /**
     * @return the timeZoneId
     */
    public final int getTimeZoneId() {
        return timeZoneId;
    }

    /**
     * @param timeZoneId
     *            the timeZoneId to set
     */
    public final void setTimeZoneId(final int timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    /**
     * @return the timeZoneName
     */
    public final String getTimeZoneName() {
        return timeZoneName;
    }

    /**
     * @param timeZoneName
     *            the timeZoneName to set
     */
    public final void setTimeZoneName(final String timeZoneName) {
        this.timeZoneName = timeZoneName;
    }

    /**
     * @return the timeZoneCode
     */
    public final String getTimeZoneCode() {
        return timeZoneCode;
    }

    /**
     * @param timeZoneCode
     *            the timeZoneCode to set
     */
    public final void setTimeZoneCode(final String timeZoneCode) {
        this.timeZoneCode = timeZoneCode;
    }

    /**
     * @return the timeZoneValue
     */
    public final String getTimeZoneValue() {
        return timeZoneValue;
    }

    /**
     * @param timeZoneValue
     *            the timeZoneValue to set
     */
    public final void setTimeZoneValue(final String timeZoneValue) {
        this.timeZoneValue = timeZoneValue;
    }

}
