package br.com.developen.erp.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name="\"Ticket\"")
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Ticket.findAll";

	@Id
	@Size(min=64, max=64)
	@Pattern(regexp = "[a-zA-Z0-9]{64,64}")
	private String identifier;

	@Column(name="\"active\"", nullable=false)
	private Boolean active;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="\"date\"", nullable=false)
	private Date date;

	@ManyToOne(optional=false)
	@JoinColumn(name="\"to\"", nullable=false)
	private User to;

	public String getIdentifier() {
		
		return identifier;
		
	}

	public void setIdentifier(String identifier) {
		
		this.identifier = identifier;
		
	}

	public Boolean getActive() {
		
		return active;
		
	}

	public void setActive(Boolean active) {
		
		this.active = active;
		
	}

	public Date getDate() {
		
		return date;
		
	}

	public void setDate(Date date) {
		
		this.date = date;
		
	}

	public User getTo() {
		
		return to;
		
	}

	public void setTo(User to) {
		
		this.to = to;
		
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
		Ticket other = (Ticket) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}