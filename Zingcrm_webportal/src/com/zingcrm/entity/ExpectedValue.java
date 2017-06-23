package com.zingcrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "expectedvalue")
public class ExpectedValue {

	    @Id
	    @Column(name = "Id")
	    private int Id;
	    
	    
		@Column(name = "ExpectedValue")
	    private String expectedvalue;
	    
	   

	    public int getId() {
			return Id;
		}

		public void setId(int id) {
			Id = id;
		}

		public String getExpectedvalue() {
			return expectedvalue;
		}

		public void setExpectedvalue(String expectedvalue) {
			this.expectedvalue = expectedvalue;
		}

	
}
