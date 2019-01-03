package br.com.developen.erp.orm;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="\"MeasureUnit\"")
@NamedQueries({
	@NamedQuery(
			name = MeasureUnit.FIND_ALL,
			query = "FROM MeasureUnit"
	)
})
public class MeasureUnit implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "MeasureUnit.findAll";	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer identifier;

	@NotNull
	@Size(min=1, max=20)
	@Column(name="\"denomination\"")
	private String denomination;

	@NotNull
	@Size(min=1, max=4)
	@Column(name="\"acronym\"")	
	private String acronym;

	@Enumerated(EnumType.ORDINAL)
	@Column(name="\"group\"")
	private MeasureUnitGroup group;

	@OneToMany(
			fetch=FetchType.LAZY,
			mappedBy="identifier.from",
			cascade={CascadeType.ALL}, 
			orphanRemoval=true)
	private List<MeasureUnitMeasureUnit> conversions;

	public Integer getIdentifier() {

		return this.identifier;

	}

	public void setIdentifier(Integer identifier) {

		this.identifier = identifier;

	}

	public String getDenomination() {

		return this.denomination;

	}

	public void setDenomination(String denomination) {

		this.denomination = denomination;

	}

	public String getAcronym() {

		return this.acronym;

	}

	public void setAcronym(String acronym) {

		this.acronym = acronym;

	}

	public MeasureUnitGroup getGroup() {
		
		return group;
		
	}

	public void setGroup(MeasureUnitGroup group) {

		this.group = group;

	}

	public List<MeasureUnitMeasureUnit> getConversions() {

		return conversions;

	}

	public void setConversions(List<MeasureUnitMeasureUnit> conversions) {

		this.conversions = conversions;

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
		MeasureUnit other = (MeasureUnit) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}