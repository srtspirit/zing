package com.zingcrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "document")
public class Document {
	
	@Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen")
    @Column(name = "ID", unique = true, nullable = false, precision = 15, scale = 0)
    private int Id;
	
	 @Column(name = "DocumentID")
     private int documentId;
	 
	 @Column(name = "OrgID")
     private int orgId;

	/**
	 * @return the orgId
	 */
	public final int getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public final void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the id
	 */
	public final int getId() {
		return Id;
	}

	/**
	 * @return the documentId
	 */
	public final int getDocumentId() {
		return documentId;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(int id) {
		Id = id;
	}

	/**
	 * @param documentId the documentId to set
	 */
	public final void setDocumentId(int documentId) {
		this.documentId = documentId;
	}

}
