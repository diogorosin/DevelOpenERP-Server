package br.com.pocketpos.server.orm;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="\"Catalog\"")
public class Catalog implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer identifier;

	@NotNull
	@Column(name="\"position\"", nullable=false)
	private Integer position;

	@NotNull
	@Size(min=1,max=20)
	@Column(name="\"denomination\"", nullable=false)
	private String denomination;

	@ManyToOne(optional=false)
	@JoinColumn(name = "company")
	private Company company;

	@OneToMany(fetch=FetchType.LAZY,
			mappedBy="identifier.catalog",
			cascade={CascadeType.ALL},
			orphanRemoval=true)
	private List<CatalogItem> items;

	public Integer getIdentifier() {

		return identifier;

	}

	public void setIdentifier(Integer identifier) {

		this.identifier = identifier;

	}

	public Integer getPosition() {

		return position;

	}

	public void setPosition(Integer position) {

		this.position = position;

	}

	public String getDenomination() {

		return denomination;

	}

	public void setDenomination(String denomination) {

		this.denomination = denomination;

	}

	public Company getCompany() {

		return company;

	}

	public void setCompany(Company company) {

		this.company = company;

	}

	public List<CatalogItem> getItems() {

		return items;

	}

	public void setItems(List<CatalogItem> items) {

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
		Catalog other = (Catalog) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}