/**
 *
 */
package com.zingcrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author NeshTech
 */
/**
 * @author Nesh5
 */
@Entity
@Table(name = "organization")
public class OrganizationInfo{

    /**
     *
     */
    @Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen")
    @Column(name = "OrgID", unique = true, nullable = false, precision = 15, scale = 0)
    private int orgId;
    
    @Column(name = "Address")
    private String address;

    @Column(name = "CityID")
    private String city;

    @Column(name = "CountryID")
    private int countryId;


    @Column(name = "Fax")
    private String fax;


    @Column(name = "Name")
    private String name;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "RegNo")
    private String regNo;

    @Column(name = "StateID")
    private int state;

    @Column(name = "StatusID")
    private int status;

    @Column(name = "TimeZoneID")
    private int timeZoneId;

    @Column(name = "Zip")
    private String zip;
    
    @Column(name = "UserField1")
    private String userField1;
    
    @Column(name = "UserField2")
    private String userField2;
    
    @Column(name = "Required")
    private boolean required;
    
    @Column(name = "AssignedSalesRep")
    private boolean SalesRep;

	/**
	 * @return the required
	 */
	public final boolean isRequired() {
		return required;
	}

	/**
	 * @param required the required to set
	 */
	public final void setRequired(boolean required) {
		this.required = required;
	}

	public boolean isSalesRep() {
		return SalesRep;
	}

	public void setSalesRep(boolean salesRep) {
		SalesRep = salesRep;
	}

	/**
	 * @return the orgId
	 */
	public final int getOrgId() {
		return orgId;
	}

	/**
	 * @return the address
	 */
	public final String getAddress() {
		return address;
	}

	/**
	 * @return the city
	 */
	public final String getCity() {
		return city;
	}

	/**
	 * @return the countryId
	 */
	public final int getCountryId() {
		return countryId;
	}

	/**
	 * @return the fax
	 */
	public final String getFax() {
		return fax;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @return the phone
	 */
	public final String getPhone() {
		return phone;
	}

	/**
	 * @return the regNo
	 */
	public final String getRegNo() {
		return regNo;
	}

	/**
	 * @return the state
	 */
	public final int getState() {
		return state;
	}

	/**
	 * @return the status
	 */
	public final int getStatus() {
		return status;
	}

	/**
	 * @return the timeZoneId
	 */
	public final int getTimeZoneId() {
		return timeZoneId;
	}

	/**
	 * @return the zip
	 */
	public final String getZip() {
		return zip;
	}

	/**
	 * @return the userField1
	 */
	public final String getUserField1() {
		return userField1;
	}

	/**
	 * @return the userField2
	 */
	public final String getUserField2() {
		return userField2;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public final void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	/**
	 * @param address the address to set
	 */
	public final void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param city the city to set
	 */
	public final void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public final void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	/**
	 * @param fax the fax to set
	 */
	public final void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @param phone the phone to set
	 */
	public final void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @param regNo the regNo to set
	 */
	public final void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	/**
	 * @param state the state to set
	 */
	public final void setState(int state) {
		this.state = state;
	}

	/**
	 * @param status the status to set
	 */
	public final void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @param timeZoneId the timeZoneId to set
	 */
	public final void setTimeZoneId(int timeZoneId) {
		this.timeZoneId = timeZoneId;
	}

	/**
	 * @param zip the zip to set
	 */
	public final void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @param userField1 the userField1 to set
	 */
	public final void setUserField1(String userField1) {
		this.userField1 = userField1;
	}

	/**
	 * @param userField2 the userField2 to set
	 */
	public final void setUserField2(String userField2) {
		this.userField2 = userField2;
	}

   
}
