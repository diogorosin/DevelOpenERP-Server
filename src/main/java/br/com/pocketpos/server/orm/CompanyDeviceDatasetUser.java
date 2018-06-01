package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="\"CompanyDeviceDatasetUser\"")
public class CompanyDeviceDatasetUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CompanyDeviceDatasetUserPK identifier;

	@NotNull
	private Boolean active;

	@NotNull
	@Size(min=1, max=50)
	@Column(name="\"name\"")
	private String name;
	
	@Email
	@NotNull
	@Column(unique=true)
	private String login;

	@NotNull
	private String password;

	@Enumerated(EnumType.ORDINAL)
	private Level level;

	public CompanyDeviceDatasetUserPK getIdentifier() {

		return identifier;

	}

	public void setIdentifier(CompanyDeviceDatasetUserPK identifier) {

		this.identifier = identifier;

	}

	public Boolean getActive() {

		return active;

	}

	public void setActive(Boolean active) {

		this.active = active;

	}

	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}

	public String getLogin() {

		return this.login;

	}

	public void setLogin(String login) {

		this.login = login;

	}

	public String getPassword() {

		return this.password;

	}

	public void setPassword(String password) {

		this.password = password;

	}

	public Level getLevel() {

		return level;

	}

	public void setLevel(Level level) {

		this.level = level;

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
		CompanyDeviceDatasetUser other = (CompanyDeviceDatasetUser) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;

	}

}