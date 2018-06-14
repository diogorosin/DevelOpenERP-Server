package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="\"SubjectSubject\"")
public class SubjectSubject implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SubjectSubjectPK identifier;

	@NotNull
	private Boolean active;

	@Enumerated(EnumType.ORDINAL)
	private Level level;

	@ManyToOne(optional=true, fetch=FetchType.LAZY)
	@JoinColumn(name = "\"tariff\"")	
	private Tariff tariff;		

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="address")
	private Address address;

	public SubjectSubjectPK getIdentifier() {

		return this.identifier;

	}

	public void setIdentifier(SubjectSubjectPK identifier) {

		this.identifier = identifier;

	}

	public Boolean getActive() {

		return active;

	}

	public void setAllow(Boolean active) {

		this.active = active;

	}

	public Level getLevel() {

		return level;

	}

	public void setLevel(Level level) {

		this.level = level;

	}

	public Tariff getTariff() {

		return tariff;

	}

	public void setTariff(Tariff tariff) {

		this.tariff = tariff;

	}

	public Address getAddress() {

		return this.address;

	}

	public void setAddress(Address address) {

		this.address = address;

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
		SubjectSubject other = (SubjectSubject) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}