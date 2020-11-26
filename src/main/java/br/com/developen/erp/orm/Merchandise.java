package br.com.developen.erp.orm;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name="\"Merchandise\"")  
@PrimaryKeyJoinColumn(name="product")
public class Merchandise extends Product {

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	@JoinColumn(name="\"catalog\"", referencedColumnName="identifier", nullable=false)
	private Catalog catalog;

	@Column(name="\"position\"", nullable=false)
	private Integer position;

	@Column(name="\"reference\"", nullable=false)
	private Integer reference;

	@Size(min=1, max=32)
	@Column(name="\"label\"", nullable=false)
	private String label;

	@Column(name="\"price\"", nullable=false)
	private BigDecimal price;

	public Catalog getCatalog() {
		
		return catalog;
		
	}

	public void setCatalog(Catalog catalog) {
		
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
	
	public BigDecimal getPrice() {
		
		return price;
		
	}

	public void setPrice(BigDecimal price) {

		this.price = price;

	}

}