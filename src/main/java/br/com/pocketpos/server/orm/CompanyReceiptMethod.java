package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="\"CompanyReceiptMethod\"")
@NamedQueries({
	@NamedQuery(
			name = CompanyReceiptMethod.FIND_BY_COMPANY,
			query = "FROM CompanyReceiptMethod CRM WHERE CRM.identifier.company = :company"
	)
})
public class CompanyReceiptMethod implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_COMPANY = "CompanyReceiptMethod.findByCompany";

	@EmbeddedId
	private CompanyReceiptMethodPK identifier;

	public CompanyReceiptMethodPK getIdentifier() {

		return this.identifier;

	}

	public void setIdentifier(CompanyReceiptMethodPK identifier) {

		this.identifier = identifier;

	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		return result;

	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyReceiptMethod other = (CompanyReceiptMethod) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}