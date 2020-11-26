package br.com.developen.erp.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name="\"Individual\"")
@PrimaryKeyJoinColumn(name="subject") 
public class Individual extends Subject {

	private static final long serialVersionUID = 1L;

	@Size(min=1, max=50)
	@Column(name="\"name\"", nullable=false)
	private String name;

	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}

}