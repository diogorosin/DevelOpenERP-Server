package br.com.pocketpos.server.orm;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name="\"Product\"")  
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer identifier;

	@NotNull
	private Boolean active;

	@NotNull
	@Size(min=1,max=5)
	@Column(name="\"code\"", nullable=false)
	private String code;

	@NotNull
	@Size(min=1,max=100)
	@Column(name="\"longDenomination\"", nullable=false)
	private String longDenomination;

	@NotNull
	@Size(min=1,max=20)
	@Column(name="\"shortDenomination\"", nullable=false)
	private String shortDenomination;

	@NotNull
	@Column(name="\"price\"", nullable=false)
	private BigDecimal price;

	@ManyToOne(optional=true, fetch=FetchType.LAZY)
	@JoinColumn(name = "\"tariff\"")	
	private Tariff tariff;

	@ManyToOne(optional=false)
	@JoinColumn(name = "company")
	private Company company;

	@OneToMany(
			fetch=FetchType.LAZY,
			mappedBy="identifier.product",
			cascade={CascadeType.ALL}, 
			orphanRemoval=true)
	private List<ProductProduct> parts;

	public Integer getIdentifier() {

		return identifier;

	}

	public void setIdentifier(Integer identifier) {

		this.identifier = identifier;

	}

	public Boolean getActive() {

		return active;

	}

	public void setActive(Boolean active) {

		this.active = active;

	}	

	public String getCode() {

		return code;

	}

	public void setCode(String code) {

		this.code = code;

	}

	public String getLongDenomination() {

		return longDenomination;

	}

	public void setLongDenomination(String longDenomination) {

		this.longDenomination = longDenomination;

	}

	public String getShortDenomination() {

		return shortDenomination;

	}

	public void setShortDenomination(String shortDenomination) {

		this.shortDenomination = shortDenomination;

	}

	public BigDecimal getPrice() {
		
		return price;
		
	}

	public void setPrice(BigDecimal price) {
		
		this.price = price;
		
	}
	
	public Tariff getTariff() {
		
		return tariff;
		
	}

	public void setTariff(Tariff tariff) {
		
		this.tariff = tariff;
		
	}

	public Company getCompany() {

		return company;

	}

	public void setCompany(Company company) {

		this.company = company;

	}

	public List<ProductProduct> getParts() {

		return parts;

	}

	public void setParts(List<ProductProduct> parts) {

		this.parts = parts;

	}

	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((getIdentifier() == null) ? 0 : getIdentifier().hashCode());
		return result;

	}

	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (getIdentifier() == null) {
			if (other.getIdentifier() != null)
				return false;
		} else if (!getIdentifier().equals(other.getIdentifier()))
			return false;
		return true;

	}

}