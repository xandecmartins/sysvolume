package com.crxmarket.sysvol.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Volume implements Serializable{

	private static final long serialVersionUID = 2739580459449666396L;

	private Integer totalValue;
	
	private List<Integer> stoneColumns;
	
	private List<Integer> waterColumns;
	
	public Volume() {
	}

	public Integer getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Integer totalValue) {
		this.totalValue = totalValue;
	}

	public List<Integer> getStoneColumns() {
		return stoneColumns;
	}

	public void setStoneColumns(List<Integer> stoneColumns) {
		this.stoneColumns = stoneColumns;
	}

	public List<Integer> getWaterColumns() {
		return waterColumns;
	}

	public void setWaterColumns(List<Integer> waterColumns) {
		this.waterColumns = waterColumns;
	}

	
	
	
}
