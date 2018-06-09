package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class TabItemPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	@JoinColumn(name="\"tab\"", referencedColumnName="identifier", nullable=false)
	private Tab tab;

	@Column(name="\"item\"", nullable=false)
	private Integer item;

	public Tab getTab() {

		return tab;

	}

	public void setTab(Tab tab) {

		this.tab = tab;

	}

	public Integer getItem() {

		return item;

	}

	public void setItem(Integer item) {

		this.item = item;

	}

	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((tab == null) ? 0 : tab.hashCode());
		return result;

	}

	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabItemPK other = (TabItemPK) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (tab == null) {
			if (other.tab != null)
				return false;
		} else if (!tab.equals(other.tab))
			return false;
		return true;

	}	

}