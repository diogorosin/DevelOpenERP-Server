package br.com.developen.erp.orm;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="\"CompanyDeviceSaleItem\"")
public class CompanyDeviceSaleItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CompanyDeviceSaleItemPK identifier;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"progeny\"", nullable=false)
	private Progeny progeny;

	@Column(name="\"quantity\"", nullable=false)
	private BigDecimal quantity;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"measureUnit\"", nullable=false)
	private MeasureUnit measureUnit;

	@Column(name="\"price\"", nullable=false)
	private BigDecimal price;

	@Column(name="\"total\"", nullable=false)
	private BigDecimal total;

	public CompanyDeviceSaleItemPK getIdentifier() {

		return identifier;

	}

	public void setIdentifier(CompanyDeviceSaleItemPK identifier) {

		this.identifier = identifier;

	}

	public Progeny getProgeny() {

		return progeny;

	}

	public void setProgeny(Progeny progeny) {

		this.progeny = progeny;

	}

	public BigDecimal getQuantity() {

		return quantity;

	}

	public void setQuantity(BigDecimal quantity) {

		this.quantity = quantity;

	}

	public MeasureUnit getMeasureUnit() {

		return measureUnit;

	}

	public void setMeasureUnit(MeasureUnit measureUnit) {

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

	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		return result;

	}

	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyDeviceSaleItem other = (CompanyDeviceSaleItem) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}