package br.com.developen.erp.orm;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="\"Company\"")
@NamedQueries({
	@NamedQuery(
			name = Company.FIND_ALL, 
			query = "FROM Company C ORDER BY C.denomination"
	),
	@NamedQuery(
			name = Company.ROW_COUNT, 
			query = "SELECT COUNT(C) FROM Company C"
	)
})
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String COUPON_TITLE_DEFAULT = "COMPANY_FANCYNAME_PROPERTY";

	public static final String FIND_ALL = "Company.findAll";

	public static final String ROW_COUNT = "Company.rowCount";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer identifier;

	@Column(name="\"active\"", nullable=false)
	private Boolean active;

	@Size(min=1, max=200)
	@Column(name="\"image\"", nullable=true)
	private String image;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="address")
	private Address address;

	@Size(min=1, max=100)
	@Column(name="\"denomination\"", nullable=false)
	private String denomination;

	@Size(min=1, max=32)
	@Column(name="\"fancyName\"", nullable=true)
	private String fancyName;

	@Size(min=1, max=32)
	@Column(name="\"couponTitle\"", nullable=true)
	private String couponTitle;

	@Size(min=1, max=32)
	@Column(name="\"couponSubtitle\"", nullable=true)
	private String couponSubtitle;

	@OneToMany(
			fetch=FetchType.LAZY,
			mappedBy="company", 
			cascade={CascadeType.ALL}, 
			orphanRemoval=true)
	private List<User> users; 

	@OneToMany(
			fetch=FetchType.LAZY,
			mappedBy="identifier.company",
			cascade={CascadeType.ALL}, 
			orphanRemoval=true)
	private List<CompanyDevice> devices;

	@OneToMany(
			fetch=FetchType.LAZY,
			mappedBy="company",
			cascade={CascadeType.ALL},
			orphanRemoval=true)
	private List<Progeny> progenies;

	@OneToMany(
			fetch=FetchType.LAZY,
			mappedBy="company", 
			cascade={CascadeType.ALL}, 
			orphanRemoval=true)
	private List<Catalog> catalogs;

	@OneToMany(
			fetch=FetchType.LAZY,
			mappedBy="identifier.company",
			cascade={CascadeType.ALL}, 
			orphanRemoval=true)
	private List<CompanyReceiptMethod> receiptMethods;

	@OneToMany(
			fetch=FetchType.LAZY,
			mappedBy="identifier.company",
			cascade={CascadeType.ALL}, 
			orphanRemoval=true)
	private List<CompanyPaymentMethod> paymentMethods;

	public Integer getIdentifier() {

		return this.identifier;

	}

	public void setIdentifier(Integer identifier) {

		this.identifier = identifier;

	}

	public Boolean getActive() {

		return this.active;

	}

	public void setActive(Boolean active) {

		this.active = active;

	}

	public String getImage() {

		return image;

	}

	public void setImage(String image) {

		this.image = image;

	}

	public Address getAddress() {
		
		return this.address;

	}

	public void setAddress(Address address) {

		this.address = address;

	}

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

	public String getCouponTitle() {

		return couponTitle;

	}

	public void setCouponTitle(String couponTitle) {

		this.couponTitle = couponTitle;

	}

	public String getCouponSubtitle() {

		return couponSubtitle;

	}

	public void setCouponSubtitle(String couponSubtitle) {

		this.couponSubtitle = couponSubtitle;

	}

	public List<User> getUsers() {
		
		return users;
		
	}

	public void setUsers(List<User> users) {
		
		this.users = users;
		
	}
	
	public List<CompanyDevice> getDevices() {

		return devices;

	}

	public void setDevices(List<CompanyDevice> devices) {

		this.devices = devices;

	}	

	public List<Progeny> getProgenies() {

		return progenies;

	}

	public void setProgenies(List<Progeny> progenies) {

		this.progenies = progenies;

	}

	public List<Catalog> getCatalogs() {

		return catalogs;

	}

	public void setCatalogs(List<Catalog> catalogs) {

		this.catalogs = catalogs;

	}

	public List<CompanyReceiptMethod> getReceiptMethods() {

		return receiptMethods;

	}

	public void setReceiptMethods(List<CompanyReceiptMethod> receiptMethods) {

		this.receiptMethods = receiptMethods;

	}

	public List<CompanyPaymentMethod> getPaymentMethods() {

		return paymentMethods;

	}

	public void setPaymentMethods(List<CompanyPaymentMethod> paymentMethods) {

		this.paymentMethods = paymentMethods;

	}

	public String toString(){

		return "(" + getIdentifier() + ") " + getDenomination();

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
		Company other = (Company) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}
	
}