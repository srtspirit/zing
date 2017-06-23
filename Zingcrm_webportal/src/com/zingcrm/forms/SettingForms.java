package com.zingcrm.forms;

public class SettingForms {
 
	private int org;
	
	private String parentField; 
	
	private String childField;
	
	private boolean required;
	
	private int parentId;
	
	private int childId;


	/**
	 * @return the parentId
	 */
	public final int getParentId() {
		return parentId;
	}

	/**
	 * @return the childId
	 */
	public final int getChildId() {
		return childId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public final void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/**
	 * @param childId the childId to set
	 */
	public final void setChildId(int childId) {
		this.childId = childId;
	}

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

	/**
	 * @return the org
	 */
	public final int getOrg() {
		return org;
	}

	/**
	 * @return the parentField
	 */
	public final String getParentField() {
		return parentField;
	}

	/**
	 * @return the childField
	 */
	public final String getChildField() {
		return childField;
	}

	/**
	 * @param org the org to set
	 */
	public final void setOrg(int org) {
		this.org = org;
	}

	/**
	 * @param parentField the parentField to set
	 */
	public final void setParentField(String parentField) {
		this.parentField = parentField;
	}

	/**
	 * @param childField the childField to set
	 */
	public final void setChildField(String childField) {
		this.childField = childField;
	} 
}
