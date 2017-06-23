/**
 * 
 */
package com.zingcrm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "userinfo")

public class User  {
	
	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "UserID")
	private String userid;
	
	@Column(name="Email")
	private String email;
	
	@NotNull
	@Size(min = 1)
	@Column(name = "OrgID", unique = true)
	private String orgId;
	
	@NotNull
	@Size(min = 1)
	@Column(name = "Password")
	private String password;
	
	
	@NotNull
	@Column(name = "RoleID")
	private String roleId;
	
	
	@Column(name = "DeleteFlag")
	private boolean status;
	
	
	@Column(name = "Enabled")
	private boolean enable;
	
	@Column(name = "TimeZoneID")
	private int timeZoneId;
	
	@Column(name="LanguageID")
	private String languageId;
	
	@Column(name="CreatedDate", columnDefinition = "DATETIME")
	private Date createdDate;

	@Column(name="ModifiedDate", columnDefinition = "DATETIME")
	private Date modifiedDate;

	
	@Column(name="CreatedBy")
	private String createdBy;


	/**
	 * @return the userid
	 */
	public final String getUserid() {
		return userid;
	}


	/**
	 * @return the email
	 */
	public final String getEmail() {
		return email;
	}


	/**
	 * @return the orgId
	 */
	public final String getOrgId() {
		return orgId;
	}


	/**
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}


	/**
	 * @return the roleId
	 */
	public final String getRoleId() {
		return roleId;
	}


	/**
	 * @return the status
	 */
	public final boolean getStatus() {
		return status;
	}


	/**
	 * @return the enable
	 */
	public final boolean getEnable() {
		return enable;
	}


	/**
	 * @return the timeZoneId
	 */
	public final int getTimeZoneId() {
		return timeZoneId;
	}



	/**
	 * @return the createdDate
	 */
	public final Date getCreatedDate() {
		return createdDate;
	}


	/**
	 * @return the modifiedDate
	 */
	public final Date getModifiedDate() {
		return modifiedDate;
	}


	/**
	 * @return the createdBy
	 */
	public final String getCreatedBy() {
		return createdBy;
	}


	/**
	 * @param userid the userid to set
	 */
	public final void setUserid(String userid) {
		this.userid = userid;
	}


	/**
	 * @param email the email to set
	 */
	public final void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @param orgId the orgId to set
	 */
	public final void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	/**
	 * @param password the password to set
	 */
	public final void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @param roleId the roleId to set
	 */
	public final void setRoleId(String roleId) {
		this.roleId = roleId;
	}


	/**
	 * @param status the status to set
	 */
	public final void setStatus(boolean status) {
		this.status = status;
	}


	/**
	 * @param enable the enable to set
	 */
	public final void setEnable(boolean enable) {
		this.enable = enable;
	}


	/**
	 * @param timeZoneId the timeZoneId to set
	 */
	public final void setTimeZoneId(int timeZoneId) {
		this.timeZoneId = timeZoneId;
	}




	/**
	 * @return the languageId
	 */
	public final String getLanguageId() {
		return languageId;
	}


	/**
	 * @param languageId the languageId to set
	 */
	public final void setLanguageId(String languageId) {
		this.languageId = languageId;
	}


	/**
	 * @param date the createdDate to set
	 */
	public final void setCreatedDate(Date date) {
		this.createdDate = date;
	}


	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public final void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	/**
	 * @param createdBy the createdBy to set
	 */
	public final void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	
	
}