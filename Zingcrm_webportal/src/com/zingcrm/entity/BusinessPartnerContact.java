package com.zingcrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "businesspartnercontact")
public class BusinessPartnerContact {

	@Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen")
    @Column(name = "ContactID", unique = true, nullable = false, precision = 15, scale = 0)
    private int Id;
	
	 @Column(name = "BPID")
	 private int LeadId;
	 
	 @Column(name = "PrimaryFirstName")
	 private String primaryFirstName;
	 
	 @Column(name = "PrimaryLastName")
	 private String primaryLastName;

	 @Column(name = "PrimaryPhone")
	 private String primaryPhone;
	 
	 @Column(name = "PrimaryMobile")
	 private String primaryMobile;
	 
	 @Column(name = "PrimaryEmail")
	 private String primaryEmail;
	 
	 @Column(name = "PrimaryDepartment")
	 private String primaryDepartment;
	 
	 @Column(name = "SecondaryFirstName")
	 private String secondaryFirstName;
	 
	 @Column(name = "SecondaryLastName")
	 private String secondaryLastName;

	 @Column(name = "PrimaryPhoneExtension")
	 private String primaryphoneExtension;
	
	 @Column(name = "ThirdContFirstName")
	 private String thirdConName;
	
	 @Column(name = "ThirdContLastName")
	 private String thirdConLastName;
	
	 @Column(name = "ThirdContPhone")
	 private String thirdConPhone;
	
	 @Column(name = "ThirdContPhoneExtension")
	 private String thirdConphoneExt;
	
	 @Column(name = "ThirdContMobile")
	 private String thirdConMobile;
	
	 @Column(name = "ThirdContEmail")
	 private String thirdConEmail;
	
	 @Column(name = "ThirdContDepartment")
	 private String thirdConDept;
	
	

	/**
	 * @return the primaryLastName
	 */
	public final String getPrimaryLastName() {
		return primaryLastName;
	}

	/**
	 * @return the secondaryLastName
	 */
	public final String getSecondaryLastName() {
		return secondaryLastName;
	}

	/**
	 * @param primaryLastName the primaryLastName to set
	 */
	public final void setPrimaryLastName(String primaryLastName) {
		this.primaryLastName = primaryLastName;
	}

	/**
	 * @param secondaryLastName the secondaryLastName to set
	 */
	public final void setSecondaryLastName(String secondaryLastName) {
		this.secondaryLastName = secondaryLastName;
	}

	@Column(name = "SecondaryPhone")
	 private String secondaryPhone;
	
	 @Column(name = "SecondaryPhoneExtension")
	 private String secondaryphoneExtension;
	 
	@Column(name = "SecondaryMobile")
	 private String secondaryMobile;
	 
	 @Column(name = "SecondaryEmail")
	 private String secondaryEmail;
	 
	 @Column(name = "SecondaryDepartment")
	 private String secondaryDepartment;

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
		return LeadId;
	}

	/**
	 * @return the primaryName
	 */
	public final String getPrimaryFirstName() {
		return primaryFirstName;
	}

	/**
	 * @return the primaryPhone
	 */
	public final String getPrimaryPhone() {
		return primaryPhone;
	}


	/**
	 * @return the primaryEmail
	 */
	public final String getPrimaryEmail() {
		return primaryEmail;
	}

	/**
	 * @return the primaryDepartment
	 */
	public final String getPrimaryDepartment() {
		return primaryDepartment;
	}

	/**
	 * @return the secondaryName
	 */
	public final String getSecondaryFirstName() {
		return secondaryFirstName;
	}

	/**
	 * @return the secondaryPhone
	 */
	public final String getSecondaryPhone() {
		return secondaryPhone;
	}

	/**
	 * @return the secondaryMobile
	 */
	public final String getSecondaryMobile() {
		return secondaryMobile;
	}

	/**
	 * @return the secondaryEmail
	 */
	public final String getSecondaryEmail() {
		return secondaryEmail;
	}

	/**
	 * @return the secondaryDepartment
	 */
	public final String getSecondaryDepartment() {
		return secondaryDepartment;
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
		LeadId = leadId;
	}

	/**
	 * @param primaryName the primaryName to set
	 */
	public final void setPrimaryFirstName(String primaryFirstName) {
		this.primaryFirstName = primaryFirstName;
	}

	/**
	 * @param primaryPhone the primaryPhone to set
	 */
	public final void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}


	/**
	 * @return the primaryMobile
	 */
	public final String getPrimaryMobile() {
		return primaryMobile;
	}

	/**
	 * @param primaryMobile the primaryMobile to set
	 */
	public final void setPrimaryMobile(String primaryMobile) {
		this.primaryMobile = primaryMobile;
	}

	/**
	 * @param primaryEmail the primaryEmail to set
	 */
	public final void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}

	/**
	 * @param primaryDepartment the primaryDepartment to set
	 */
	public final void setPrimaryDepartment(String primaryDepartment) {
		this.primaryDepartment = primaryDepartment;
	}

	/**
	 * @param secondaryName the secondaryName to set
	 */
	public final void setSecondaryFirstName(String secondaryFirstName) {
		this.secondaryFirstName = secondaryFirstName;
	}

	/**
	 * @param secondaryPhone the secondaryPhone to set
	 */
	public final void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	/**
	 * @param secondaryMobile the secondaryMobile to set
	 */
	public final void setSecondaryMobile(String secondaryMobile) {
		this.secondaryMobile = secondaryMobile;
	}

	/**
	 * @param secondaryEmail the secondaryEmail to set
	 */
	public final void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}

	/**
	 * @param secondaryDepartment the secondaryDepartment to set
	 */
	public final void setSecondaryDepartment(String secondaryDepartment) {
		this.secondaryDepartment = secondaryDepartment;
	}

	/**
	 * @return the primaryphoneExtension
	 */
	public String getPrimaryphoneExtension() {
		return primaryphoneExtension;
	}

	/**
	 * @param primaryphoneExtension the primaryphoneExtension to set
	 */
	public void setPrimaryphoneExtension(String primaryphoneExtension) {
		this.primaryphoneExtension = primaryphoneExtension;
	}
	
	 public String getSecondaryphoneExtension() {
			return secondaryphoneExtension;
	}

		public void setSecondaryphoneExtension(String secondaryphoneExtension) {
			this.secondaryphoneExtension = secondaryphoneExtension;
	}

		public String getThirdConName() {
			return thirdConName;
		}

		public void setThirdConName(String thirdConName) {
			this.thirdConName = thirdConName;
		}

		public String getThirdConLastName() {
			return thirdConLastName;
		}

		public void setThirdConLastName(String thirdConLastName) {
			this.thirdConLastName = thirdConLastName;
		}

		public String getThirdConPhone() {
			return thirdConPhone;
		}

		public void setThirdConPhone(String thirdConPhone) {
			this.thirdConPhone = thirdConPhone;
		}

		public String getThirdConphoneExt() {
			return thirdConphoneExt;
		}

		public void setThirdConphoneExt(String thirdConphoneExt) {
			this.thirdConphoneExt = thirdConphoneExt;
		}

		public String getThirdConMobile() {
			return thirdConMobile;
		}

		public void setThirdConMobile(String thirdConMobile) {
			this.thirdConMobile = thirdConMobile;
		}

		public String getThirdConEmail() {
			return thirdConEmail;
		}

		public void setThirdConEmail(String thirdConEmail) {
			this.thirdConEmail = thirdConEmail;
		}

		public String getThirdConDept() {
			return thirdConDept;
		}

		public void setThirdConDept(String thirdConDept) {
			this.thirdConDept = thirdConDept;
		}

		
}
