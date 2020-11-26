package br.com.developen.erp.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name="\"PaymentMethod\"")
public class PaymentMethod implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String identifier;

	@Size(min=1, max=20)
	@Column(name="\"denomination\"", nullable=false)
	private String denomination;

	public String getIdentifier() {

		return identifier;

	}

	public void setIdentifier(String identifier) {

		this.identifier = identifier;

	}

	public String getDenomination() {

		return denomination;

	}

	public void setDenomination(String denomination) {

		this.denomination = denomination;

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
		PaymentMethod other = (PaymentMethod) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}
	
}