/**
 * 
 */
package com.zingcrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author NeshTech
 * 
 */
@Entity
@Table(name = "contactinfo")
public class ContactInfo {

	
	@Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen")
    @Column(name = "UserContactID", unique = true, nullable = false, precision = 15, scale = 0)
    private int Id;
	
    @NotNull
    @Column(name = "UserID")
    private String userId;

    @Column(name = "FirstName")
    private String userFirstName;

    @Column(name = "LastName")
    private String userLastName;

    @Column(name = "PhoneCountryCode")
    private int userPhoneCountryCode;

    @Column(name = "PhoneNumber")
    private String userPhoneNumber;

	/**
	 * @return the id
	 */
	public final int getId() {
		return Id;
	}

	/**
	 * @return the userId
	 */
	public final String getUserId() {
		return userId;
	}

	/**
	 * @return the userFirstName
	 */
	public final String getUserFirstName() {
		return userFirstName;
	}

	/**
	 * @return the userLastName
	 */
	public final String getUserLastName() {
		return userLastName;
	}

	/**
	 * @return the userPhoneCountryCode
	 */
	public final int getUserPhoneCountryCode() {
		return userPhoneCountryCode;
	}

	/**
	 * @return the userPhoneNumber
	 */
	public final String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(int id) {
		Id = id;
	}

	/**
	 * @param userId the userId to set
	 */
	public final void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @param userFirstName the userFirstName to set
	 */
	public final void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	/**
	 * @param userLastName the userLastName to set
	 */
	public final void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	/**
	 * @param userPhoneCountryCode the userPhoneCountryCode to set
	 */
	public final void setUserPhoneCountryCode(int userPhoneCountryCode) {
		this.userPhoneCountryCode = userPhoneCountryCode;
	}

	/**
	 * @param userPhoneNumber the userPhoneNumber to set
	 */
	public final void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

}
