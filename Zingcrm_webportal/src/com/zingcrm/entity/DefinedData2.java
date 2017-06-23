package com.zingcrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "defineddata2")
public class DefinedData2 {
	
	@Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen")
    @Column(name = "ID", unique = true, nullable = false, precision = 15, scale = 0)
    private int id;
	
	@Column(name = "Name")
    private String name;
	
	@Column(name = "DataID1")
	 private int data1;

	/**
	 * @return the id
	 */
	public final int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @return the data1
	 */
	public final int getData1() {
		return data1;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(int id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @param data1 the data1 to set
	 */
	public final void setData1(int data1) {
		this.data1 = data1;
	}

	

	
}
