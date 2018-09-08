package br.com.pocketpos.server.orm;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="\"Company\"")
@PrimaryKeyJoinColumn(name="organization")
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
public class Company extends Organization {

	private static final long serialVersionUID = 1L;

	public static final String COUPON_TITLE_DEFAULT = "COMPANY_FANCYNAME_PROPERTY";

	public static final String FIND_ALL = "Company.findAll";

	public static final String ROW_COUNT = "Company.rowCount";	

	@NotNull
	@Size(min=1, max=32)
	@Column(name="\"couponTitle\"")
	private String couponTitle;

	@Size(min=1, max=32)
	@Column(name="\"couponSubtitle\"")
	private String couponSubtitle;

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

}