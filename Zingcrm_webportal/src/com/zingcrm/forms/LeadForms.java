package com.zingcrm.forms;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class LeadForms implements Serializable {

	private static final long serialVersionUID = -6863394476543622601L;
	private String messageId; //used in integration purpose. For message correlation


	private int id;
	
	private int org;

	private String date;

	private int source;

	private String lead;

	private String rep;

	private int city;

	private String zipcode;

	private String address;

	private String primaryName;

	private String secondaryName;

	private String primaryPhone;

	private String secondaryPhone;

	private String primaryMobile;

	private String secondaryMobile;

	private String primaryEmail;

	private String secondaryEmail;

	private String primaryDept;

	private String secondaryDept;

	private String createdDate;

	private String modifiedDate;

	private String CreatedBy;
	
	private String ModifiedBy;
	
	private int contId;
	
	private int State;
	
	private boolean status;
	
	private String GPS;

	private String userId;
	
	private String address2;

	private String repName;
	
	private String sourceName;
	
	private boolean customFlag;
	
	private String roleId;
	
	private int country;
	
	private String customFlagName;
	
	private String Latitude;
	
	private String Longitude;
	
	private String Fax;
	
	private String Website;
	
	private String primaryLastName;

	private String secondaryLastName;
	
	private boolean activeFlag;

	private String statusName; 
	
	private String privateFlag;
	
	private String primaryphoneExtension;
	
	private String leadreqid;
	 
	private String leadreqemail;
	
	private String thirdConName;
	
	private String thirdConLastName;
	
	private String thirdConPhone;
	
	private String thirdConphoneExt;
	
	private String thirdConMobile;
	
	private String thirdConEmail;
	
	private String thirdConDept;
	
	private String CurrentSupplier;
	
	private String leadnotes;
	
	
	
	private String secondaryphoneExtension;
	public String getSecondaryphoneExtension() {
		return secondaryphoneExtension;
	}

	public void setSecondaryphoneExtension(String secondaryphoneExtension) {
		this.secondaryphoneExtension = secondaryphoneExtension;
	}

	/**
	 * @return the privateFlag
	 */
	public final String getPrivateFlag() {
		return privateFlag;
	}

	/**
	 * @param privateFlag the privateFlag to set
	 */
	public final void setPrivateFlag(String privateFlag) {
		this.privateFlag = privateFlag;
	}

	/**
	 * @return the statusName
	 */
	public final String getStatusName() {
		return statusName;
	}

	/**
	 * @param statusName the statusName to set
	 */
	public final void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/**
	 * @return the privateFlag
	 */
	public final boolean isActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param privateFlag the privateFlag to set
	 */
	public final void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the primaryLastName
	 */
	public final String getPrimaryLastName() {
		return primaryLastName;
	}

	/**
	 * @return the secondaryLastName
	 */
	public final String getSecondaryLastName() {
		return secondaryLastName;
	}

	/**
	 * @param primaryLastName the primaryLastName to set
	 */
	public final void setPrimaryLastName(String primaryLastName) {
		this.primaryLastName = primaryLastName;
	}

	/**
	 * @param secondaryLastName the secondaryLastName to set
	 */
	public final void setSecondaryLastName(String secondaryLastName) {
		this.secondaryLastName = secondaryLastName;
	}

	/**
	 * @return the fax
	 */
	public final String getFax() {
		return Fax;
	}

	/**
	 * @return the website
	 */
	public final String getWebsite() {
		return Website;
	}

	/**
	 * @param fax the fax to set
	 */
	public final void setFax(String fax) {
		Fax = fax;
	}

	/**
	 * @param website the website to set
	 */
	public final void setWebsite(String website) {
		Website = website;
	}

	/**
	 * @return the latitude
	 */
	public final String getLatitude() {
		return Latitude;
	}

	/**
	 * @return the longitude
	 */
	public final String getLongitude() {
		return Longitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public final void setLatitude(String latitude) {
		Latitude = latitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public final void setLongitude(String longitude) {
		Longitude = longitude;
	}

	/**
	 * @return the customFlagName
	 */
	public final String getCustomFlagName() {
		return customFlagName;
	}

	/**
	 * @param customFlagName the customFlagName to set
	 */
	public final void setCustomFlagName(String customFlagName) {
		this.customFlagName = customFlagName;
	}

	private String countryName;
	private String stateName;
	private String cityName;
	

	/**
	 * @return the countryName
	 */
	public final String getCountryName() {
		return countryName;
	}

	/**
	 * @return the stateName
	 */
	public final String getStateName() {
		return stateName;
	}

	/**
	 * @return the cityName
	 */
	public final String getCityName() {
		return cityName;
	}

	/**
	 * @param countryName the countryName to set
	 */
	public final void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @param stateName the stateName to set
	 */
	public final void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public final void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the country
	 */
	public final int getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public final void setCountry(int country) {
		this.country = country;
	}

	/**
	 * @return the roleId
	 */
	public final String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public final void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the customFlag
	 */
	public final boolean getCustomFlag() {
		return customFlag;
	}

	/**
	 * @param customFlag the customFlag to set
	 */
	public final void setCustomFlag(boolean customFlag) {
		this.customFlag = customFlag;
	}

	/**
	 * @return the sourceName
	 */
	public final String getSourceName() {
		return sourceName;
	}

	/**
	 * @param sourceName the sourceName to set
	 */
	public final void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	/**
	 * @return the repName
	 */
	public final String getRepName() {
		return repName;
	}

	/**
	 * @param repName the repName to set
	 */
	public final void setRepName(String repName) {
		this.repName = repName;
	}

	/**
	 * @return the userId
	 */
	public final String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public final void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the gPS
	 */
	public final String getGPS() {
		return GPS;
	}

	/**
	 * @param gPS the gPS to set
	 */
	public final void setGPS(String gPS) {
		GPS = gPS;
	}

	/**
	 * @return the status
	 */
	public final boolean getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public final void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the state
	 */
	public final int getState() {
		return State;
	}

	/**
	 * @param state the state to set
	 */
	public final void setState(int state) {
		State = state;
	}

	/**
	 * @return the modifiedBy
	 */
	public final String getModifiedBy() {
		return ModifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public final void setModifiedBy(String modifiedBy) {
		ModifiedBy = modifiedBy;
	}

	/**
	 * @return the org
	 */
	public final int getOrg() {
		return org;
	}

	/**
	 * @return the date
	 */
	public final String getDate() {
		return date;
	}

	/**
	 * @return the source
	 */
	public final int getSource() {
		return source;
	}

	/**
	 * @return the lead
	 */
	public final String getLead() {
		return lead;
	}

	/**
	 * @return the rep
	 */
	public final String getRep() {
		return rep;
	}

	/**
	 * @return the city
	 */
	public final int getCity() {
		return city;
	}

	/**
	 * @return the zipcode
	 */
	public final String getZipcode() {
		return zipcode;
	}

	/**
	 * @return the address
	 */
	public final String getAddress() {
		return address;
	}

	/**
	 * @return the primaryName
	 */
	public final String getPrimaryName() {
		return primaryName;
	}

	/**
	 * @return the secondaryName
	 */
	public final String getSecondaryName() {
		return secondaryName;
	}

	/**
	 * @return the primaryPhone
	 */
	public final String getPrimaryPhone() {
		return primaryPhone;
	}

	/**
	 * @return the secondaryPhone
	 */
	public final String getSecondaryPhone() {
		return secondaryPhone;
	}

	/**
	 * @return the primaryMobile
	 */
	public final String getPrimaryMobile() {
		return primaryMobile;
	}

	/**
	 * @return the secondaryMobile
	 */
	public final String getSecondaryMobile() {
		return secondaryMobile;
	}

	/**
	 * @return the primaryEmail
	 */
	public final String getPrimaryEmail() {
		return primaryEmail;
	}

	/**
	 * @return the secondaryEmail
	 */
	public final String getSecondaryEmail() {
		return secondaryEmail;
	}

	/**
	 * @return the primaryDept
	 */
	public final String getPrimaryDept() {
		return primaryDept;
	}

	/**
	 * @return the secondaryDept
	 */
	public final String getSecondaryDept() {
		return secondaryDept;
	}

	/**
	 * @return the createdDate
	 */
	public final String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public final String getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @return the createdBy
	 */
	public final String getCreatedBy() {
		return CreatedBy;
	}

	/**
	 * @param org the org to set
	 */
	public final void setOrg(int org) {
		this.org = org;
	}

	/**
	 * @param date the date to set
	 */
	public final void setDate(String date) {
		this.date = date;
	}

	/**
	 * @param source the source to set
	 */
	public final void setSource(int source) {
		this.source = source;
	}

	/**
	 * @param lead the lead to set
	 */
	public final void setLead(String lead) {
		this.lead = lead;
	}

	/**
	 * @param rep the rep to set
	 */
	public final void setRep(String rep) {
		this.rep = rep;
	}

	/**
	 * @param city the city to set
	 */
	public final void setCity(int city) {
		this.city = city;
	}

	/**
	 * @return the contId
	 */
	public final int getContId() {
		return contId;
	}

	/**
	 * @param contId the contId to set
	 */
	public final void setContId(int contId) {
		this.contId = contId;
	}

	/**
	 * @param zipcode the zipcode to set
	 */
	public final void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @param address the address to set
	 */
	public final void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param primaryName the primaryName to set
	 */
	public final void setPrimaryName(String primaryName) {
		this.primaryName = primaryName;
	}

	/**
	 * @param secondaryName the secondaryName to set
	 */
	public final void setSecondaryName(String secondaryName) {
		this.secondaryName = secondaryName;
	}

	/**
	 * @param primaryPhone the primaryPhone to set
	 */
	public final void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	/**
	 * @param secondaryPhone the secondaryPhone to set
	 */
	public final void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	/**
	 * @param primaryMobile the primaryMobile to set
	 */
	public final void setPrimaryMobile(String primaryMobile) {
		this.primaryMobile = primaryMobile;
	}

	/**
	 * @param secondaryMobile the secondaryMobile to set
	 */
	public final void setSecondaryMobile(String secondaryMobile) {
		this.secondaryMobile = secondaryMobile;
	}

	/**
	 * @param primaryEmail the primaryEmail to set
	 */
	public final void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}

	/**
	 * @param secondaryEmail the secondaryEmail to set
	 */
	public final void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}

	/**
	 * @param primaryDept the primaryDept to set
	 */
	public final void setPrimaryDept(String primaryDept) {
		this.primaryDept = primaryDept;
	}

	/**
	 * @return the id
	 */
	public final int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(int id) {
		this.id = id;
	}

	/**
	 * @param secondaryDept the secondaryDept to set
	 */
	public final void setSecondaryDept(String secondaryDept) {
		this.secondaryDept = secondaryDept;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public final void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public final void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public final void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the primaryphoneExtension
	 */
	public String getPrimaryphoneExtension() {
		return primaryphoneExtension;
	}

	/**
	 * @param primaryphoneExtension the primaryphoneExtension to set
	 */
	public void setPrimaryphoneExtension(String primaryphoneExtension) {
		this.primaryphoneExtension = primaryphoneExtension;
	}

	public String getLeadreqid() {
		return leadreqid;
	}

	public void setLeadreqid(String leadreqid) {
		this.leadreqid = leadreqid;
	}

	public String getLeadreqemail() {
		return leadreqemail;
	}

	public void setLeadreqemail(String leadreqemail) {
		this.leadreqemail = leadreqemail;
	}

	public String getThirdConName() {
		return thirdConName;
	}

	public void setThirdConName(String thirdConName) {
		this.thirdConName = thirdConName;
	}

	public String getThirdConLastName() {
		return thirdConLastName;
	}

	public void setThirdConLastName(String thirdConLastName) {
		this.thirdConLastName = thirdConLastName;
	}

	public String getThirdConPhone() {
		return thirdConPhone;
	}

	public void setThirdConPhone(String thirdConPhone) {
		this.thirdConPhone = thirdConPhone;
	}

	public String getThirdConphoneExt() {
		return thirdConphoneExt;
	}

	public void setThirdConphoneExt(String thirdConphoneExt) {
		this.thirdConphoneExt = thirdConphoneExt;
	}

	public String getThirdConMobile() {
		return thirdConMobile;
	}

	public void setThirdConMobile(String thirdConMobile) {
		this.thirdConMobile = thirdConMobile;
	}

	public String getThirdConEmail() {
		return thirdConEmail;
	}

	public void setThirdConEmail(String thirdConEmail) {
		this.thirdConEmail = thirdConEmail;
	}

	public String getThirdConDept() {
		return thirdConDept;
	}

	public void setThirdConDept(String thirdConDept) {
		this.thirdConDept = thirdConDept;
	}

	public String getCurrentSupplier() {
		return CurrentSupplier;
	}

	public void setCurrentSupplier(String currentSupplier) {
		CurrentSupplier = currentSupplier;
	}

	public String getLeadnotes() {
		return leadnotes;
	}

	public void setLeadnotes(String leadnotes) {
		this.leadnotes = leadnotes;
	}

	public String getMessageId(){
		return messageId;
	}

	public void setMessageId(String messageId){
		this.messageId = messageId;
	}


}
