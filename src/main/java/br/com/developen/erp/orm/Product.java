package br.com.developen.erp.orm;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="\"Product\"")  
@PrimaryKeyJoinColumn(name="progeny")
public class Product extends Progeny {

	private static final long serialVersionUID = 1L;

	//DIMENSOES	
	@ManyToOne(optional=true)
	@JoinColumn(name="\"widthUnit\"")
	private MeasureUnit widthUnit;

	@Column(name="\"widthValue\"", nullable=true)
	private BigDecimal widthValue;

	@ManyToOne(optional=true)
	@JoinColumn(name="\"heightUnit\"")
	private MeasureUnit heightUnit;

	@Column(name="\"heightValue\"", nullable=true)
	private BigDecimal heightValue;

	@ManyToOne(optional=true)
	@JoinColumn(name="\"lengthUnit\"")
	private MeasureUnit lengthUnit;

	@Column(name="\"lengthValue\"", nullable=true)
	private BigDecimal lengthValue;

	//VOLUME
	@ManyToOne(optional=true)
	@JoinColumn(name="\"contentUnit\"")
	private MeasureUnit contentUnit;	

	@Column(name="\"contentValue\"", nullable=true)
	private BigDecimal contentValue;

	//PESO
	@ManyToOne(optional=true)
	@JoinColumn(name="\"grossWeightUnit\"")
	private MeasureUnit grossWeightUnit;

	@Column(name="\"grossWeightValue\"", nullable=true)
	private BigDecimal grossWeightValue;

	@ManyToOne(optional=true)
	@JoinColumn(name="\"netWeightUnit\"")
	private MeasureUnit netWeightUnit;

	@Column(name="\"netWeightValue\"", nullable=true)
	private BigDecimal netWeightValue;

	@OneToMany(
			fetch=FetchType.LAZY,
			mappedBy="identifier.parent",
			cascade={CascadeType.ALL}, 
			orphanRemoval=true)
	private List<ProductProduct> parts;

	public MeasureUnit getWidthUnit() {
		
		return widthUnit;
		
	}

	public void setWidthUnit(MeasureUnit widthUnit) {
		
		this.widthUnit = widthUnit;
		
	}

	public BigDecimal getWidthValue() {
		
		return widthValue;
		
	}

	public void setWidthValue(BigDecimal widthValue) {
		
		this.widthValue = widthValue;
		
	}

	public MeasureUnit getHeightUnit() {
		
		return heightUnit;
		
	}

	public void setHeightUnit(MeasureUnit heightUnit) {
		
		this.heightUnit = heightUnit;
		
	}

	public BigDecimal getHeightValue() {
		
		return heightValue;
		
	}

	public void setHeightValue(BigDecimal heightValue) {
		
		this.heightValue = heightValue;
		
	}

	public MeasureUnit getLengthUnit() {
		
		return lengthUnit;
		
	}

	public void setLengthUnit(MeasureUnit lengthUnit) {
		
		this.lengthUnit = lengthUnit;
		
	}

	public BigDecimal getLengthValue() {
		
		return lengthValue;
		
	}

	public void setLengthValue(BigDecimal lengthValue) {
		
		this.lengthValue = lengthValue;
		
	}

	public MeasureUnit getContentUnit() {
		
		return contentUnit;
		
	}

	public void setContentUnit(MeasureUnit contentUnit) {
		
		this.contentUnit = contentUnit;
		
	}

	public BigDecimal getContentValue() {
		
		return contentValue;
		
	}

	public void setContentValue(BigDecimal contentValue) {
		
		this.contentValue = contentValue;
		
	}

	public MeasureUnit getGrossWeightUnit() {
		
		return grossWeightUnit;
		
	}

	public void setGrossWeightUnit(MeasureUnit grossWeightUnit) {
		
		this.grossWeightUnit = grossWeightUnit;
		
	}

	public BigDecimal getGrossWeightValue() {
		
		return grossWeightValue;
		
	}

	public void setGrossWeightValue(BigDecimal grossWeightValue) {
		
		this.grossWeightValue = grossWeightValue;
		
	}

	public MeasureUnit getNetWeightUnit() {
		
		return netWeightUnit;
		
	}

	public void setNetWeightUnit(MeasureUnit netWeightUnit) {
		
		this.netWeightUnit = netWeightUnit;
		
	}

	public BigDecimal getNetWeightValue() {
		
		return netWeightValue;
		
	}

	public void setNetWeightValue(BigDecimal netWeightValue) {
		
		this.netWeightValue = netWeightValue;
		
	}

	public List<ProductProduct> getParts() {

		return parts;

	}

	public void setParts(List<ProductProduct> parts) {

		this.parts = parts;

	}

}