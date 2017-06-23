package com.zingcrm.forms;

public class LoginMember {

	
	private String userId;
	
	private String firstName;
	
	private String lastName;
	
	private int roleId;
	
	private String page;
	
	private String orgId;
	
	private String landingPage;
	
	

	/**
	 * @return the userId
	 */
	public final String getUserId() {
		return userId;
	}

	/**
	 * @return the firstName
	 */
	public final String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public final String getLastName() {
		return lastName;
	}

	/**
	 * @return the roleId
	 */
	public final int getRoleId() {
		return roleId;
	}

	/**
	 * @return the page
	 */
	public final String getPage() {
		return page;
	}

	/**
	 * @return the orgUserId
	 */
	public final String getOrgId() {
		return orgId;
	}

	/**
	 * @param userId the userId to set
	 */
	public final void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public final void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * @param page the page to set
	 */
	public final void setPage(String page) {
		this.page = page;
	}

	/**
	 * @param orgUserId the orgUserId to set
	 */
	public final void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	/**
	 * @return the landingPage
	 */
	public final String getLandingPage() {
		return landingPage;
	}


	/**
	 * @param landingPage the landingPage to set
	 */
	public final void setLandingPage(String landingPage) {
		this.landingPage = landingPage;
	}


}
