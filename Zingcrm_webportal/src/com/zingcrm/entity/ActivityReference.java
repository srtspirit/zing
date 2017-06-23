package com.zingcrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "activityreference")
public class ActivityReference {
	
		@Id
	    @GenericGenerator(name = "gen", strategy = "increment")
	    @GeneratedValue(generator = "gen")
	    @Column(name = "ID", unique = true, nullable = false, precision = 15, scale = 0)
	    private int Id;
		
		@Column(name = "ACTID")
	    private int actid;
		
		@Column(name = "Document")
		private byte[] document;
		
		@Column(name = "filename")
		private String filename;
		
		public int getId() {
			return Id;
		}

		public int getActid() {
			return actid;
		}

		public void setActid(int actid) {
			this.actid = actid;
		}

		public String getFilename() {
			return filename;
		}

		public void setFilename(String filename) {
			this.filename = filename;
		}

		public void setId(int id) {
			Id = id;
		}

		public byte[] getDocument() {
			return document;
		}

		public void setDocument(byte[] document) {
			this.document = document;
		}


}
