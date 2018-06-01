package br.com.pocketpos.server.orm;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="\"Organization\"")
@PrimaryKeyJoinColumn(name="subject")  
@NamedQueries({
	@NamedQuery(
			name = Organization.ROW_COUNT, 
			query = "SELECT COUNT(O) FROM Organization O"
	)
})
public class Organization extends Subject {

	private static final long serialVersionUID = 1L;

	public static final String ROW_COUNT = "Organization.rowCount";	

	@NotNull
	@Size(min=1, max=100)
	@Column(name="\"denomination\"")
	private String denomination;

	@Size(min=0, max=32)
	@Column(name="\"fancyName\"")
	private String fancyName;

	@ManyToOne(optional=true, fetch=FetchType.LAZY)
	@JoinColumn(name = "\"currentTariff\"")	
	private Tariff currentTariff;

	@OneToMany(
			fetch=FetchType.LAZY,
			mappedBy="organization",
			cascade={CascadeType.ALL},
			orphanRemoval=true)
	private List<Product> products;

	@OneToMany(
			fetch=FetchType.LAZY,
			mappedBy="organization", 
			cascade={CascadeType.ALL}, 
			orphanRemoval=true)
	private List<Tariff> tariffs;

	public String getDenomination() {

		return this.denomination;

	}

	public void setDenomination(String denomination) {

		this.denomination = denomination;

	}

	public String getFancyName() {

		return fancyName;

	}

	public void setFancyName(String fancyName) {

		this.fancyName = fancyName;

	}

	public Tariff getCurrentTariff() {

		return currentTariff;

	}

	public void setCurrentTariff(Tariff currentTariff) {

		this.currentTariff = currentTariff;

	}

	public List<Product> getProducts() {

		return products;

	}

	public void setProducts(List<Product> products) {

		this.products = products;

	}

	public List<Tariff> getTariffs() {

		return tariffs;

	}

	public void setTariffs(List<Tariff> tariffs) {

		this.tariffs = tariffs;

	}

	public String toString(){

		return "(" + getIdentifier() + ") " + getDenomination();

	}

}