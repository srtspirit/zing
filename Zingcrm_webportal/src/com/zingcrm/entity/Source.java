package com.zingcrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "source")
public class Source {

	@Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen")
    @Column(name = "SourceID", unique = true, nullable = false, precision = 15, scale = 0)
    private int Id;
    
    @Column(name = "Name")
    private String name;

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

}
