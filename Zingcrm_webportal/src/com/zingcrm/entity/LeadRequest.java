package com.zingcrm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="leadrequest")

public class LeadRequest {

	@Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen")
    @Column(name = "LeadRequestId", unique = true, nullable = false, precision = 15, scale = 0)
    private int Id;
	
	@Column(name="Createdate")
	private Date createddate;
	
	@Column(name="Source")
	private String Source;
	
	@Column(name="Businessname")
	private String businessname;
	
	@Column(name="Contactname")
	private String contactname;
	
	@Column(name="Phone")
	private String phonenumber;
	
	@Column(name="PhoneExtension")
	private String extension;
	
	@Column(name="email")
	private String email;
	
	@Column(name="website")
	private String website;
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="SalesleadId")
	private String salesleadid;
	
	@Column(name="Orgid")
	private int orgid;
	
	@Column(name="Createdby")
	private String LeadReqCreatedby;
	
	@Column(name="Modifiedby")
	private String LeadReqModifiedby;
	
	@Column(name="Modifieddate")
	private Date LeadReqModifieddate;
	
	@Column(name="isBPCreated")
	private String isBPCreated;
	
	@Column(name="leadreqQualFlag") // Leadrequest qualified flag
	private int leadreqQualFlag;
	
	public String getIsBPCreated() {
		return isBPCreated;
	}

	public void setIsBPCreated(String isBPCreated) {
		this.isBPCreated = isBPCreated;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
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

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
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

	public void setSalesleadid(String salesleadid) {
		this.salesleadid = salesleadid;
	}

	public int getOrgid() {
		return orgid;
	}

	public void setOrgid(int orgid) {
		this.orgid = orgid;
	}

	public String getLeadReqCreatedby() {
		return LeadReqCreatedby;
	}

	public void setLeadReqCreatedby(String leadReqCreatedby) {
		LeadReqCreatedby = leadReqCreatedby;
	}

	public String getLeadReqModifiedby() {
		return LeadReqModifiedby;
	}

	public void setLeadReqModifiedby(String leadReqModifiedby) {
		LeadReqModifiedby = leadReqModifiedby;
	}

	public Date getLeadReqModifieddate() {
		return LeadReqModifieddate;
	}

	public void setLeadReqModifieddate(Date date) {
		LeadReqModifieddate = date;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public int getLeadreqQualFlag() {
		return leadreqQualFlag;
	}

	public void setLeadreqQualFlag(int leadreqQualFlag) {
		this.leadreqQualFlag = leadreqQualFlag;
	}
}
