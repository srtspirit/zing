package com.zingcrm.forms;

import java.util.Date;

import javax.persistence.Column;

public class LeadRequestForms {
	
    private int Id;
	
	private String createddate;
	
	private String Source;
	
	private String businessname;
	
	private String contactname;
	
	private String phonenumber;
	
	private String extension;
	
	private String email;
	
	private String website;
	
	private String notes;
	
	private String salesleadid;
	
	private String orgid;
	
	private String createdby;
	
	private String modifiedby;
	
	private String modifieddate;
	
	private String isBPCreated;
	
	private String hdn_salesleadid;
	
	private String leadreqQualFlag;
	
	private String leadUserName;

	/**
	 * @return the leadUserName
	 */
	public final String getLeadUserName() {
		return leadUserName;
	}

	/**
	 * @param leadUserName the leadUserName to set
	 */
	public final void setLeadUserName(String leadUserName) {
		this.leadUserName = leadUserName;
	}

	public String getHdn_salesleadid() {
		return hdn_salesleadid;
	}

	public void setHdn_salesleadid(String hdn_salesleadid) {
		this.hdn_salesleadid = hdn_salesleadid;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	

	public String getSource() {
		return Source;
	}

	public void setSource(String source) {
		Source = source;
	}

	public String getBusinessname() {
		return businessname;
	}

	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}

	public String getContactname() {
		return contactname;
	}

	public void setContactname(String contactname) {
		this.contactname = contactname;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getSalesleadid() {
		return salesleadid;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}



	public void setSalesleadid(String salesleadid) {
		this.salesleadid = salesleadid;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}
	
	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public String getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(String modifieddate) {
		this.modifieddate = modifieddate;
	}

	public String getIsBPCreated() {
		return isBPCreated;
	}

	public void setIsBPCreated(String isBPCreated) {
		this.isBPCreated = isBPCreated;
	}

	public String getLeadreqQualFlag() {
		return leadreqQualFlag;
	}

	public void setLeadreqQualFlag(String leadreqQualFlag) {
		this.leadreqQualFlag = leadreqQualFlag;
	}

}
