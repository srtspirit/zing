package com.zingcrm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "opportunity")
public class Opportunity {
	
	
	
	@Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen")
    @Column(name = "OpportunityID", unique = true, nullable = false, precision = 15, scale = 0)
    private int Id;
	
	    @Column(name = "BPID")
		 private int leadId;
	    
	    @Column(name = "DocumentID")
	    private int document;
	    
	    @Column(name = "OpportunityName")
	    private String opportunityName;
	    
	    @Column(name = "Description")
	    private String description;
	    
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
		 
		 @Column(name = "DefaultOpportunity")
		 private boolean defaultOpportunity;
		 
		 @Column(name = "CountryID")
		 private String currencySymbol;
		 
		 @Column(name = "CurrencyValue")
		 private String currencyValue;
		 
		 @Column(name= "CompetitorQuote")
		 private String competitorquote;
		 
		 public String getCompetitorquote() {
			return competitorquote;
		}

		public void setCompetitorquote(String competitorquote) {
			this.competitorquote = competitorquote;
		}

		/**
		 * @return the currencySymbol
		 */
		public final String getCurrencySymbol() {
			return currencySymbol;
		}

		/**
		 * @return the currencyValue
		 */
		public final String getCurrencyValue() {
			return currencyValue;
		}

		/**
		 * @param currencySymbol the currencySymbol to set
		 */
		public final void setCurrencySymbol(String currencySymbol) {
			this.currencySymbol = currencySymbol;
		}

		/**
		 * @param currencyValue the currencyValue to set
		 */
		public final void setCurrencyValue(String currencyValue) {
			this.currencyValue = currencyValue;
		}

		@Column(name = "Probability")
		 private String probability;
		 
		 @Column(name = "CloseDate")
		 private Date closeDate;
		 
		 @Column(name = "OpportunityStatusID")
		 private String oppStatus;
		 
		 
	   

		/**
		 * @return the probability
		 */
		public final String getProbability() {
			return probability;
		}

		/**
		 * @return the closeDate
		 */
		public final Date getCloseDate() {
			return closeDate;
		}

		/**
		 * @return the oppStatus
		 */
		public final String getOppStatus() {
			return oppStatus;
		}


		/**
		 * @param probability the probability to set
		 */
		public final void setProbability(String probability) {
			this.probability = probability;
		}

		/**
		 * @param closeDate the closeDate to set
		 */
		public final void setCloseDate(Date closeDate) {
			this.closeDate = closeDate;
		}

		/**
		 * @param oppStatus the oppStatus to set
		 */
		public final void setOppStatus(String oppStatus) {
			this.oppStatus = oppStatus;
		}

		/**
		 * @return the defaultOpportunity
		 */
		public final boolean getDefaultOpportunity() {
			return defaultOpportunity;
		}

		/**
		 * @param defaultOpportunity the defaultOpportunity to set
		 */
		public final void setDefaultOpportunity(boolean defaultOpportunity) {
			this.defaultOpportunity = defaultOpportunity;
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
		 * @return the leadId
		 */
		public final int getLeadId() {
			return leadId;
		}

		/**
		 * @return the document
		 */
		public final int getDocument() {
			return document;
		}


		/**
		 * @return the description
		 */
		public final String getDescription() {
			return description;
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
		 * @param leadId the leadId to set
		 */
		public final void setLeadId(int leadId) {
			this.leadId = leadId;
		}

		/**
		 * @param document the document to set
		 */
		public final void setDocument(int document) {
			this.document = document;
		}


		/**
		 * @param description the description to set
		 */
		public final void setDescription(String description) {
			this.description = description;
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
		 * @return the opportunityName
		 */
		public final String getOpportunityName() {
			return opportunityName;
		}

		/**
		 * @param opportunityName the opportunityName to set
		 */
		public final void setOpportunityName(String opportunityName) {
			this.opportunityName = opportunityName;
		}

	

}
