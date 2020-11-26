package br.com.developen.erp.orm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="\"CompanyDeviceSale\"")
public class CompanyDeviceSale implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CompanyDeviceSalePK identifier;

	@Column(name="\"number\"", nullable=false)
	private Integer number;

	@Enumerated(EnumType.STRING)
	@Column(name="\"status\"", nullable=false)	
	private Status status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="\"dateTime\"", nullable=false)
	private Date dateTime;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"user\"", nullable=false)
	private User user;

	@Column(name="\"note\"", nullable=true)
	private String note;

	@OneToMany(
			fetch=FetchType.LAZY,
			mappedBy="identifier.companyDeviceSale",
			cascade={CascadeType.ALL}, 
			orphanRemoval=true)
	private List<CompanyDeviceSaleItem> items;
	

	public CompanyDeviceSalePK getIdentifier() {

		return identifier;

	}

	public void setIdentifier(CompanyDeviceSalePK identifier) {

		this.identifier = identifier;

	}

	public Integer getNumber() {

		return number;

	}

	public void setNumber(Integer number) {

		this.number = number;

	}

	public Status getStatus() {

		return status;

	}

	public void setStatus(Status status) {

		this.status = status;

	}

	public Date getDateTime() {

		return dateTime;

	}

	public void setDateTime(Date dateTime) {

		this.dateTime = dateTime;

	}

	public User getUser() {

		return user;

	}

	public void setUser(User user) {

		this.user = user;

	}

	public String getNote() {

		return note;

	}

	public void setNote(String note) {

		this.note = note;

	}

	public List<CompanyDeviceSaleItem> getItems() {

		return items;

	}

	public void setItems(List<CompanyDeviceSaleItem> items) {

		this.items = items;

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
		CompanyDeviceSale other = (CompanyDeviceSale) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}