package br.com.developen.erp.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="\"CompanyUser\"")
@NamedQueries({
	@NamedQuery(
			name = CompanyUser.COMPANIES_OF_USER,
			query = "FROM CompanyUser CU WHERE CU.identifier.user = :user"
	),
	@NamedQuery(
			name = CompanyUser.USERS_OF_COMPANY,
			query = "FROM CompanyUser CU WHERE CU.identifier.company = :company"
	)
})
public class CompanyUser implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String COMPANIES_OF_USER = "CompanyUser.companiesOfUser";
	public static final String USERS_OF_COMPANY = "CompanyUser.usersOfCompany";
	
	@EmbeddedId
	private CompanyUserPK identifier;

	@Enumerated(EnumType.ORDINAL)
	private Level level;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="\"since\"", nullable=false)
	private Date since;

	public CompanyUserPK getIdentifier() {
		
		return identifier;
		
	}

	public void setIdentifier(CompanyUserPK identifier) {
		
		this.identifier = identifier;
		
	}

	public Level getLevel() {
		
		return level;
		
	}

	public void setLevel(Level level) {
		
		this.level = level;
		
	}

	public Date getSince() {
		
		return since;
		
	}

	public void setSince(Date since) {
		
		this.since = since;
		
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
		CompanyUser other = (CompanyUser) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;
		
	}
		
}