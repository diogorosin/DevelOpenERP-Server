package br.com.developen.erp.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="\"Level\"")
@NamedQueries({
	@NamedQuery(
			name = Level.FIND_ALL,
			query = "FROM Level L ORDER BY L.identifier"
	),
	@NamedQuery(
			name = Level.COUNT_ALL,
			query = "SELECT COUNT(L) FROM Level L"
	)
})
public class Level implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Level.findAll";

	public static final String COUNT_ALL = "Level.countAll";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer identifier;

	@Size(min=1, max=50)
	@Column(name="\"denomination\"", nullable=false)
	private String denomination;

	public Integer getIdentifier() {

		return this.identifier;

	}

	public void setIdentifier(Integer identifier) {

		this.identifier = identifier;

	}

	public String getDenomination() {

		return this.denomination;

	}

	public void setDenomination(String denomination) {

		this.denomination = denomination;

	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		return result;

	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Level other = (Level) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}