package com.zingcrm.forms;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;


public class ActivityForms {
	
	private int id;
	
	private int org;
	
	private int lead;
	
	private int opportunity;
	
	private String name;
	
	private String type;
	
	private String assignedTo;
	
	private String dueDate;
	
	private String createdBy;
	
	private String createdDate;
	
	private String modifiedBy;
	
	private String modifiedDate;
	
	private String fileName;
	
	private String userField1;
	
	private String userField2;
	
	private String status;
	
	private String activityStatus;
	
	private String statusName;
	
	private String desc;
	
	private String userFieldText1;
	
	private String userFieldText2;
	
	private String roleId;
	
	private String userId;
	
	private byte[] imageData;
	
	private String filesList;
	
	private String actEditstatus;
	
	private String editActId;
	
	/*private List<?> filesList;
	private List<MultipartFile> filesCover;*/

	/**
	 * @return the actEditstatus
	 */
	public final String getActEditstatus() {
		return actEditstatus;
	}

	/**
	 * @return the editActId
	 */
	public final String getEditActId() {
		return editActId;
	}

	/**
	 * @param actEditstatus the actEditstatus to set
	 */
	public final void setActEditstatus(String actEditstatus) {
		this.actEditstatus = actEditstatus;
	}

	/**
	 * @param editActId the editActId to set
	 */
	public final void setEditActId(String editActId) {
		this.editActId = editActId;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}


	 
	    private byte[] bytes;
	
/*	public List<?> getFile() {
		return filesList;
	}

	public void setFiles(List<?> filesList) {
		this.filesList = filesList;
	}
*/
	/**
	 * @return the files
	 */
	public final byte[] getFiles() {
		return imageData;
	}

	/**
	 * @param imageData the files to set
	 */
	public final void setFiles(byte[] imageData) {
		this.imageData = imageData;
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
	 * @return the userFieldText1
	 */
	public final String getUserFieldText1() {
		return userFieldText1;
	}

	/**
	 * @return the userFieldText2
	 */
	public final String getUserFieldText2() {
		return userFieldText2;
	}

	/**
	 * @param userFieldText1 the userFieldText1 to set
	 */
	public final void setUserFieldText1(String userFieldText1) {
		this.userFieldText1 = userFieldText1;
	}

	/**
	 * @param userFieldText2 the userFieldText2 to set
	 */
	public final void setUserFieldText2(String userFieldText2) {
		this.userFieldText2 = userFieldText2;
	}

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
	 * @return the status
	 */
	public final String getStatus() {
		return status;
	}

	/**
	 * @return the activityStatus
	 */
	public final String getActivityStatus() {
		return activityStatus;
	}

	/**
	 * @param status the status to set
	 */
	public final void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @param activityStatus the activityStatus to set
	 */
	public final void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
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
	 * @return the id
	 */
	public final int getId() {
		return id;
	}

	/**
	 * @return the org
	 */
	public final int getOrg() {
		return org;
	}

	/**
	 * @return the lead
	 */
	public final int getLead() {
		return lead;
	}

	/**
	 * @return the opportunity
	 */
	public final int getOpportunity() {
		return opportunity;
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
	public final String getType() {
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
	public final String getDueDate() {
		return dueDate;
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
	public final String getCreatedDate() {
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
	public final String getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(int id) {
		this.id = id;
	}

	/**
	 * @param org the org to set
	 */
	public final void setOrg(int org) {
		this.org = org;
	}

	/**
	 * @param lead the lead to set
	 */
	public final void setLead(int lead) {
		this.lead = lead;
	}

	/**
	 * @param opportunity the opportunity to set
	 */
	public final void setOpportunity(int opportunity) {
		this.opportunity = opportunity;
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
	public final void setType(String type) {
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
	public final void setDueDate(String dueDate) {
		this.dueDate = dueDate;
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
	public final void setCreatedDate(String createdDate) {
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
	public final void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getFilesList() {
		return filesList;
	}

	public void setFilesList(String filesList) {
		this.filesList = filesList;
	}

	/*public List<MultipartFile> getFilesCover() {
		return filesCover;
	}

	public void setFilesCover(List<MultipartFile> filesCover) {
		this.filesCover = filesCover;
	}
	*/
	
	
	

}
