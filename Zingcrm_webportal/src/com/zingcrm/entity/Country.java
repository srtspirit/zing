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
@Table(name = "country")
public class Country {

    /**
     *
     */
    @Id
    @Column(name = "CountryID")
    private int countryId;

    /**
     *
     */
    @Column(name = "CountryCode")
    private String countryCode;

    /**
     *
     */
    @Column(name = "CountryName")
    private String countryName;

    /**
     *
     */
    @Column(name = "CallingCode")
    private String callingCode;
    
    
    @Column(name = "CurrencyCode")
    private String currencyCode;

    /**
	 * @return the currencyCode
	 */
	public final String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * @param currencyCode the currencyCode to set
	 */
	public final void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	/**
     * @return the countryId
     */
    public final int getCountryId() {
        return countryId;
    }

    /**
     * @param countryId
     *            the countryId to set
     */
    public final void setCountryId(final int countryId) {
        this.countryId = countryId;
    }

    /**
     * @return the countryCode
     */
    public final String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode
     *            the countryCode to set
     */
    public final void setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return the countryName
     */
    public final String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName
     *            the countryName to set
     */
    public final void setCountryName(final String countryName) {
        this.countryName = countryName;
    }

    /**
     * @return the callingCode
     */
    public final String getCallingCode() {
        return callingCode;
    }

    /**
     * @param callingCode
     *            the callingCode to set
     */
    public final void setCallingCode(final String callingCode) {
        this.callingCode = callingCode;
    }

}
