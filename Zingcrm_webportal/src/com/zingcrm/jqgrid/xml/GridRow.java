package com.zingcrm.jqgrid.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class GridRow {
	
	@XmlAttribute
    private int id;
	
	@XmlElement(name = "cell")
	private List<Object> gridCell;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the userid
	 */
	public List<Object> getGridCell() {
		return gridCell;
	}

	/**
	 * @param cell the cell to set
	 */
	public void setGridCell(List<Object> gridCell) {
		this.gridCell = gridCell;
	}
}