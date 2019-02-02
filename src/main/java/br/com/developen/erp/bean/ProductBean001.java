package br.com.developen.erp.bean;

import java.math.BigDecimal;

public class ProductBean001 extends ProgenyBean001{

	//ESTOQUE
	private Integer stockUnit;	
	
	//DIMENSOES	
	private Integer widthUnit;

	private BigDecimal widthValue;

	private Integer heightUnit;

	private BigDecimal heightValue;

	private Integer lengthUnit;

	private BigDecimal lengthValue;

	//VOLUME
	private Integer contentUnit;	

	private BigDecimal contentValue;

	//PESO
	private Integer grossWeightUnit;

	private BigDecimal grossWeightValue;

	private Integer netWeightUnit;

	private BigDecimal netWeightValue;

	public Integer getStockUnit() {
		
		return stockUnit;
		
	}

	public void setStockUnit(Integer stockUnit) {
		
		this.stockUnit = stockUnit;

	}

	public Integer getWidthUnit() {
		
		return widthUnit;
		
	}

	public void setWidthUnit(Integer widthUnit) {
		
		this.widthUnit = widthUnit;
		
	}

	public BigDecimal getWidthValue() {
		
		return widthValue;
		
	}

	public void setWidthValue(BigDecimal widthValue) {
		
		this.widthValue = widthValue;
		
	}

	public Integer getHeightUnit() {
		
		return heightUnit;
		
	}

	public void setHeightUnit(Integer heightUnit) {
		
		this.heightUnit = heightUnit;
		
	}

	public BigDecimal getHeightValue() {
		
		return heightValue;
		
	}

	public void setHeightValue(BigDecimal heightValue) {
		
		this.heightValue = heightValue;
		
	}

	public Integer getLengthUnit() {
		
		return lengthUnit;
		
	}

	public void setLengthUnit(Integer lengthUnit) {
		
		this.lengthUnit = lengthUnit;
		
	}

	public BigDecimal getLengthValue() {
		
		return lengthValue;
		
	}

	public void setLengthValue(BigDecimal lengthValue) {
		
		this.lengthValue = lengthValue;
		
	}

	public Integer getContentUnit() {
		
		return contentUnit;
		
	}

	public void setContentUnit(Integer contentUnit) {
		
		this.contentUnit = contentUnit;
		
	}

	public BigDecimal getContentValue() {
		
		return contentValue;
		
	}

	public void setContentValue(BigDecimal contentValue) {
		
		this.contentValue = contentValue;
		
	}

	public Integer getGrossWeightUnit() {
		
		return grossWeightUnit;
		
	}

	public void setGrossWeightUnit(Integer grossWeightUnit) {
		
		this.grossWeightUnit = grossWeightUnit;
		
	}

	public BigDecimal getGrossWeightValue() {
		
		return grossWeightValue;
		
	}

	public void setGrossWeightValue(BigDecimal grossWeightValue) {
		
		this.grossWeightValue = grossWeightValue;
		
	}

	public Integer getNetWeightUnit() {
		
		return netWeightUnit;
		
	}

	public void setNetWeightUnit(Integer netWeightUnit) {
		
		this.netWeightUnit = netWeightUnit;
		
	}

	public BigDecimal getNetWeightValue() {
		
		return netWeightValue;
		
	}

	public void setNetWeightValue(BigDecimal netWeightValue) {
		
		this.netWeightValue = netWeightValue;
		
	}

}