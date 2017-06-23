package com.zingcrm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "actionlog")
public class ActionLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LogID")
    private int Id;
	
	@Column(name = "OldStateID")
    private int oldStateId;
	
	@Column(name = "NewStateID")
    private int newStateId;
	
	@Column(name = "StatusDate")
    private Date statusDate;
	
	@Column(name = "RecordType")
    private String recordType;
	
	@Column(name = "RecordID")
    private int recordId;
	
	@Column(name = "UserID")
    private String userId;
	
	@Column(name = "CustomFlag")
    private boolean customFlag;
	
	@Column(name = "Comments")
    private String Comments;
	
	@Column(name = "OldUserID")
    private String oldUserID;
	
	/**
	 * @return the oldUserID
	 */
	public final String getOldUserID() {
		return oldUserID;
	}

	/**
	 * @return the newUserID
	 */
	public final String getNewUserID() {
		return newUserID;
	}

	/**
	 * @param oldUserID the oldUserID to set
	 */
	public final void setOldUserID(String oldUserID) {
		this.oldUserID = oldUserID;
	}

	/**
	 * @param newUserID the newUserID to set
	 */
	public final void setNewUserID(String newUserID) {
		this.newUserID = newUserID;
	}

	@Column(name = "NewUserID")
    private String newUserID;

	/**
	 * @return the comments
	 */
	public final String getComments() {
		return Comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public final void setComments(String comments) {
		Comments = comments;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		Id = id;
	}

	/**
	 * @return the oldStateId
	 */
	public int getOldStateId() {
		return oldStateId;
	}

	/**
	 * @param oldStateId the oldStateId to set
	 */
	public void setOldStateId(int oldStateId) {
		this.oldStateId = oldStateId;
	}

	/**
	 * @return the newStateId
	 */
	public int getNewStateId() {
		return newStateId;
	}

	/**
	 * @param newStateId the newStateId to set
	 */
	public void setNewStateId(int newStateId) {
		this.newStateId = newStateId;
	}

	/**
	 * @return the statusDate
	 */
	public Date getStatusDate() {
		return statusDate;
	}

	/**
	 * @param statusDate the statusDate to set
	 */
	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	/**
	 * @return the recordType
	 */
	public String getRecordType() {
		return recordType;
	}

	/**
	 * @param recordType the recordType to set
	 */
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	/**
	 * @return the recortId
	 */
	public int getRecordId() {
		return recordId;
	}

	/**
	 * @param recortId the recortId to set
	 */
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	/**
	 * @return the customFlag
	 */
	public boolean isCustomFlag() {
		return customFlag;
	}

	/**
	 * @param customFlag the customFlag to set
	 */
	public void setCustomFlag(boolean customFlag) {
		this.customFlag = customFlag;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
