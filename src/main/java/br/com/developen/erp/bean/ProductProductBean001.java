package br.com.developen.erp.bean;

import java.math.BigDecimal;

public class ProductProductBean001 {

	private Integer parent;
	
	private Integer child;
	
	private Boolean active;

	private BigDecimal quantity;

	public Integer getParent() {
		
		return parent;
		
	}

	public void setParent(Integer parent) {

		this.parent = parent;

	}

	public Integer getChild() {

		return child;

	}

	public void setChild(Integer child) {

		this.child = child;

	}

	public Boolean getActive() {

		return active;

	}

	public void setActive(Boolean active) {

		this.active = active;

	}

	public BigDecimal getQuantity() {

		return quantity;

	}

	public void setQuantity(BigDecimal quantity) {

		this.quantity = quantity;

	}

}