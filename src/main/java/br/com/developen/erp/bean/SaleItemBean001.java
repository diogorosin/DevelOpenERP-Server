package br.com.developen.erp.bean;

import java.io.Serializable;
import java.math.BigDecimal;



public class SaleItemBean001 implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer progeny;

	private BigDecimal quantity;

	private Integer measureUnit;

	private BigDecimal price;

	private BigDecimal total;

	public Integer getProgeny() {

		return progeny;

	}

	public void setProgeny(Integer progeny) {
		
		this.progeny = progeny;
		
	}

	public BigDecimal getQuantity() {
		
		return quantity;
		
	}

	public void setQuantity(BigDecimal quantity) {
		
		this.quantity = quantity;
		
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

	public BigDecimal getTotal() {
		
		return total;
		
	}

	public void setTotal(BigDecimal total) {
		
		this.total = total;

	}	

}