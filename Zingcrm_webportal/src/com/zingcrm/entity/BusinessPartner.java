package com.zingcrm.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;


@Entity
@Table(name = "businesspartner")
public class BusinessPartner {
	
	@Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen")
    @Column(name = "BPID", unique = true, nullable = false, precision = 15, scale = 0)
    private int Id;
	
	 @Column(name = "Date")
	 private Date leadDate;
	 
	 @Column(name = "OrgID")
	 private int accountId;
	 
	 @Column(name = "SourceID")
	 private int source;
	 
	 @Column(name = "Name")
	 private String name;

	 @Column(name = "AssignedRep")
	 private String assignedRep;
	 
	 @Column(name = "Address1")
	 private String address;
	 
	 @Column(name = "CityID")
	 private int city;
	 
	 @Column(name = "ZipCode")
	 private String zipCode;
	 
	 @Column(name = "CreatedBy")
	 private String createdBy;
	 
	 @Column(name = "CreatedDate")
	 private Date createdDate;
	 
	 @Column(name = "ModifiedBy")
	 private String modifiedBy;
	 
	 @Column(name = "ModifiedDate")
	 private Date modifiedDate;
	 
	 @Column(name = "StateID")
	 private int state;
	 
	 @Type(type = "org.hibernate.type.NumericBooleanType")
	 @Column(name = "DeleteFlag",columnDefinition = "TINYINT")
	 private boolean status;
	 
	 
	 @Column(name = "Address2")
	 private String address2;
	 
	 @Type(type = "org.hibernate.type.NumericBooleanType")
	 @Column(name = "CustomFlag" ,columnDefinition = "TINYINT")
	 private boolean customFlag;
	 
	 @Column(name = "CountryID")
	 private int country;
	 
	 @Column(name = "Fax")
	 private String fax;
	 
	 @Column(name = "Website")
	 private String website;
	 
	 @Column(name = "Latitude")
	 private String latitude;
	 
	 @Column(name = "Longitude")
	 private String longitude;
	 
	 @Column(name = "ActiveFlag" ,columnDefinition = "TINYINT")
	 @Type(type = "org.hibernate.type.NumericBooleanType")
	 private boolean privateFlag;
	 
	 @Column(name = "CurrentSupplier")
	 private String CurrentSupplier;
	 
	 @Column(name = "LeadNotes")
	 private String leadnotes;
	 
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

	@Transient
	 private String leadreqid;
	 
	 @Transient
	 private String leadreqemail;
	 
	/**
	 * @return the privateFlag
	 */
	public final boolean isPrivateFlag() {
		return privateFlag;
	}

	/**
	 * @param privateFlag the privateFlag to set
	 */
	public final void setPrivateFlag(boolean privateFlag) {
		this.privateFlag = privateFlag;
	}

	/**
	 * @return the latitude
	 */
	public final String getLatitude() {
		return latitude;
	}

	/**
	 * @return the longitude
	 */
	public final String getLongitude() {
		return longitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public final void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public final void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the fax
	 */
	public final String getFax() {
		return fax;
	}

	/**
	 * @return the website
	 */
	public final String getWebsite() {
		return website;
	}

	/**
	 * @param fax the fax to set
	 */
	public final void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @param website the website to set
	 */
	public final void setWebsite(String website) {
		this.website = website;
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
	 * @return the visitedFlag
	 */
	public final boolean getCustomFlag() {
		return customFlag;
	}

	/**
	 * @param visitedFlag the visitedFlag to set
	 */
	public final void setCustomFlag(boolean customFlag) {
		this.customFlag = customFlag;
	}

	/**
	 * @return the address2
	 */
	public final String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public final void setAddress2(String address2) {
		this.address2 = address2;
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
	 * @return the id
	 */
	public final int getId() {
		return Id;
	}

	/**
	 * @return the leadDate
	 */
	public final Date getLeadDate() {
		return leadDate;
	}

	/**
	 * @return the accountId
	 */
	public final int getAccountId() {
		return accountId;
	}

	/**
	 * @return the source
	 */
	public final int getSource() {
		return source;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @return the assignedRep
	 */
	public final String getAssignedRep() {
		return assignedRep;
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
	public final int getCity() {
		return city;
	}

	/**
	 * @return the zipCode
	 */
	public final String getZipCode() {
		return zipCode;
	}

	/**
	 * @return the createdBy
	 */
	public final String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public final Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @return the modifiedBy
	 */
	public final String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @return the modifiedDate
	 */
	public final Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(int id) {
		Id = id;
	}

	/**
	 * @param leadDate the leadDate to set
	 */
	public final void setLeadDate(Date leadDate) {
		this.leadDate = leadDate;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public final void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	/**
	 * @param source the source to set
	 */
	public final void setSource(int source) {
		this.source = source;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @param assignedRep the assignedRep to set
	 */
	public final void setAssignedRep(String assignedRep) {
		this.assignedRep = assignedRep;
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
	public final void setCity(int city) {
		this.city = city;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public final void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public final void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public final void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public final void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public final void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the state
	 */
	public final int getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public final void setState(int state) {
		this.state = state;
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


}
