package br.com.developen.erp.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name="\"Organization\"")
@PrimaryKeyJoinColumn(name="subject")  
public class Organization extends Subject {

	private static final long serialVersionUID = 1L;

	@Size(min=1, max=100)
	@Column(name="\"denomination\"", nullable=false)
	private String denomination;

	@Size(min=1, max=32)
	@Column(name="\"fancyName\"", nullable=true)
	private String fancyName;

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

}