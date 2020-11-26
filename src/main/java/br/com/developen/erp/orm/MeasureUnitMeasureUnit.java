package br.com.developen.erp.orm;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="\"MeasureUnitMeasureUnit\"")
@NamedQueries({
	@NamedQuery(
			name = MeasureUnitMeasureUnit.FIND_ALL,
			query = "FROM MeasureUnitMeasureUnit"
	)
})
public class MeasureUnitMeasureUnit implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String FIND_ALL = "MeasureUnitMeasureUnit.findAll";

	@EmbeddedId
	private MeasureUnitMeasureUnitPK identifier;

	@Column(name="\"factor\"", nullable=false)
	private BigDecimal factor;

	public MeasureUnitMeasureUnitPK getIdentifier() {
		
		return identifier;
		
	}

	public void setIdentifier(MeasureUnitMeasureUnitPK identifier) {
		
		this.identifier = identifier;
		
	}

	public BigDecimal getFactor() {
		
		return factor;
		
	}

	public void setFactor(BigDecimal factor) {
		
		this.factor = factor;
		
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
		MeasureUnitMeasureUnit other = (MeasureUnitMeasureUnit) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}