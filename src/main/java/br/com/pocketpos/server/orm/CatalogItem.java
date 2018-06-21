package br.com.pocketpos.server.orm;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="\"CatalogItem\"")
public class CatalogItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CatalogItemPK identifier;

	@NotNull
	@Column(name="\"position\"", nullable=false)
	private Integer position;

	@NotNull
	@Column(name="\"code\"", nullable=false)
	private Integer code;

	@NotNull
	@Size(min=1, max=32)
	@Column(name="\"denomination\"", nullable=false)
	private String denomination;

	@ManyToOne(optional=false)
	@JoinColumn(name="\"progeny\"", referencedColumnName="identifier", nullable=false)
	private Progeny progeny;

	@ManyToOne(optional=false)
	@JoinColumn(name="\"measureUnit\"")
	private MeasureUnit measureUnit;

	@NotNull
	@Column(name="\"price\"", nullable=false)
	private BigDecimal price;

	public CatalogItemPK getIdentifier() {

		return identifier;

	}

	public void setIdentifier(CatalogItemPK identifier) {

		this.identifier = identifier;

	}

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

	public Progeny getProgeny() {

		return progeny;

	}

	public void setProgeny(Progeny progeny) {

		this.progeny = progeny;

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
		CatalogItem other = (CatalogItem) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}