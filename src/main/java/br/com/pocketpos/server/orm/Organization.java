package br.com.pocketpos.server.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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

	public String toString(){

		return "(" + getIdentifier() + ") " + getDenomination();

	}

}