package br.com.pocketpos.server.bean;

import java.math.BigDecimal;

public class CatalogItemBean001 {
	
	private Integer position;
	
	private Integer code;
	
	private Integer product;
	
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

	public Integer getProduct() {

		return product;

	}

	public void setProduct(Integer product) {

		this.product = product;

	}

	public BigDecimal getPrice() {

		return price;

	}

	public void setPrice(BigDecimal price) {

		this.price = price;

	}

}