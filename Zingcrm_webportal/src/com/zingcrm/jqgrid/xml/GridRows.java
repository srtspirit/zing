package com.zingcrm.jqgrid.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "rows")
@XmlAccessorType(XmlAccessType.FIELD)
public class GridRows {
	
	@XmlElement
	private int page = 1;
	
	@XmlElement
	private int total = 1;
	
	@XmlElement
	private int records = 1;
	
	private GridUserData userdata;
	
	@XmlElement(name="row")
    private List<GridRow> gridRow;

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the records
	 */
	public int getRecords() {
		return records;
	}

	/**
	 * @param records the records to set
	 */
	public void setRecords(int records) {
		this.records = records;
	}
	
	/**
	 * @return the gridRow
	 */
	public List<GridRow> getGridRow() {
		return gridRow;
	}

	/**
	 * @param gridRow the gridRow to set
	 */
	public void setGridRow(List<GridRow> gridRow) {
		this.gridRow = gridRow;
	}

	/**
	 * @return the operation
	 */
	public GridUserData getUserData() {
		return userdata;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setUserData(GridUserData userdata) {
		this.userdata = userdata;
	}
}