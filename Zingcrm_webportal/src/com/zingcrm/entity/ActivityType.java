package com.zingcrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "activitytype")
public class ActivityType {
	
	@Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen")
    @Column(name = "TypeID", unique = true, nullable = false, precision = 15, scale = 0)
    private int Id;
	
	@Column(name = "Name")
    private String name;
	
	@Column(name = "OrgID")
	 private int orgId;

	/**
	 * @return the id
	 */
	public final int getId() {
		return Id;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @return the orgId
	 */
	public final int getOrgId() {
		return orgId;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(int id) {
		Id = id;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public final void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	
}
