package com.zingcrm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "activity")
public class Activity {
	
	@Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen")
    @Column(name = "ActivityID", unique = true, nullable = false, precision = 15, scale = 0)
    private int Id;
	
	@Column(name = "OpportunityID")
    private int Opportunity;
	
	@Column(name = "Name")
    private String name;
	
	@Column(name = "TypeID")
    private int type;
	
	@Column(name = "AssignedTo")
    private String assignedTo;
	
	@Column(name = "DueDate")
    private Date dueDate;
	
	@Column(name = "ActivityStatusID")
    private String activityStatus;
	
	
	 @Column(name = "CreatedBy")
	 private String createdBy;
	 
	 @Column(name = "CreatedDate")
	 private Date createdDate;
	 
	 @Column(name = "ModifiedBy")
	 private String modifiedBy;
	 
	 @Column(name = "ModifiedDate")
	 private Date modifiedDate;
	 
	 @Column(name = "DeleteFlag")
	 private boolean status;
	 
	 @Column(name = "Document")
	 private byte[] document;
	 
	 @Column(name = "DefinedData1Id")
	 private String column1;
	 
	 @Column(name = "FileName")
	 private String fileName;
	 
	 @Column(name = "Description")
	 private String desc;
	 

	/**
	 * @return the desc
	 */
	public final String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public final void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the fileName
	 */
	public final String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public final void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the document
	 */
	public final byte[] getDocument() {
		return document;
	}

	/**
	 * @return the column1
	 */
	public final String getColumn1() {
		return column1;
	}

	/**
	 * @return the column2
	 */
	public final String getColumn2() {
		return column2;
	}

	/**
	 * @param document the document to set
	 */
	public final void setDocument(byte[] document) {
		this.document = document;
	}

	/**
	 * @param column1 the column1 to set
	 */
	public final void setColumn1(String column1) {
		this.column1 = column1;
	}

	/**
	 * @param column2 the column2 to set
	 */
	public final void setColumn2(String column2) {
		this.column2 = column2;
	}

	@Column(name = "DefinedData2Id")
	 private String column2;
	 

	/**
	 * @return the id
	 */
	public final int getId() {
		return Id;
	}

	/**
	 * @return the opportunity
	 */
	public final int getOpportunity() {
		return Opportunity;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @return the type
	 */
	public final int getType() {
		return type;
	}

	/**
	 * @return the assignedTo
	 */
	public final String getAssignedTo() {
		return assignedTo;
	}

	/**
	 * @return the dueDate
	 */
	public final Date getDueDate() {
		return dueDate;
	}

	/**
	 * @return the activityStatus
	 */
	public final String getActivityStatus() {
		return activityStatus;
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
	 * @return the status
	 */
	public final boolean getStatus() {
		return status;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(int id) {
		Id = id;
	}

	/**
	 * @param opportunity the opportunity to set
	 */
	public final void setOpportunity(int opportunity) {
		Opportunity = opportunity;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @param type the type to set
	 */
	public final void setType(int type) {
		this.type = type;
	}

	/**
	 * @param assignedTo the assignedTo to set
	 */
	public final void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public final void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @param activityStatus the activityStatus to set
	 */
	public final void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
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
	 * @param status the status to set
	 */
	public final void setStatus(boolean status) {
		this.status = status;
	}
	
}
