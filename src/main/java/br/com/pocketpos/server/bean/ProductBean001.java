package br.com.pocketpos.server.bean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ProductBean001 extends ProgenyBean001{

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

	//PARTES
	private Map<Integer, PartBean001> parts;

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

	public void setParts(Map<Integer, PartBean001> parts) {
		
		this.parts = parts;
		
	}

	public Map<Integer, PartBean001> getParts() {

		if (parts == null)

			parts = new HashMap<Integer, PartBean001>();

		return parts;

	}

	public void setPart(Map<Integer, PartBean001> parts) {

		this.parts = parts;

	}

}