package br.com.developen.erp.orm;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class MeasureUnitMeasureUnitPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	@JoinColumn(name="\"from\"", referencedColumnName="identifier", nullable=false)
	private MeasureUnit from;

	@ManyToOne(optional=false)
	@JoinColumn(name="\"to\"", referencedColumnName="identifier", nullable=false)
	private MeasureUnit to;

	public MeasureUnit getFrom() {
		
		return from;
		
	}

	public void setFrom(MeasureUnit from) {
		
		this.from = from;
		
	}

	public MeasureUnit getTo() {
		
		return to;
		
	}

	public void setTo(MeasureUnit to) {
		
		this.to = to;
		
	}

	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		return result;

	}

	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeasureUnitMeasureUnitPK other = (MeasureUnitMeasureUnitPK) obj;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
		
	}

}