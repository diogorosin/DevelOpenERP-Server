package br.com.developen.erp.bean;

import java.math.BigDecimal;

public class MerchandiseBean001 extends ProductBean001 {

	private Integer catalog;
	
	private Integer position;

	private Integer reference;

	private String label;

	private Integer measureUnit;

	private BigDecimal price;

	public Integer getCatalog() {
		
		return catalog;
		
	}

	public void setCatalog(Integer catalog) {
		
		this.catalog = catalog;
		
	}

	public Integer getPosition() {
		
		return position;
		
	}

	public void setPosition(Integer position) {
		
		this.position = position;
		
	}

	public Integer getReference() {
		
		return reference;
		
	}

	public void setReference(Integer reference) {
		
		this.reference = reference;
		
	}

	public String getLabel() {
		
		return label;
		
	}

	public void setLabel(String label) {
		
		this.label = label;
		
	}

	public Integer getMeasureUnit() {
		
		return measureUnit;
		
	}

	public void setMeasureUnit(Integer measureUnit) {
		
		this.measureUnit = measureUnit;
		
	}

	public BigDecimal getPrice() {
		
		return price;
		
	}

	public void setPrice(BigDecimal price) {
		
		this.price = price;

	}

}