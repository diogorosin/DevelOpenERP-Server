package br.com.pocketpos.server.bean;

import java.math.BigDecimal;

public class CatalogItemBean001 {

	private Integer position;

	private Integer code;

	private String denomination;

	private Integer product;

	private Integer measureUnit;

	private BigDecimal price;

	public Integer getPosition() {

		return position;

	}

	public void setPosition(Integer position) {

		this.position = position;

	}

	public Integer getCode() {

		return code;

	}

	public void setCode(Integer code) {

		this.code = code;

	}

	public String getDenomination() {

		return denomination;

	}

	public void setDenomination(String denomination) {

		this.denomination = denomination;

	}

	public Integer getProduct() {

		return product;

	}

	public void setProduct(Integer product) {

		this.product = product;

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