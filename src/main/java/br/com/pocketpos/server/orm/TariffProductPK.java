package br.com.pocketpos.server.orm;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class TariffProductPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	@JoinColumn(name="\"tariff\"", nullable=false)
	private Tariff tariff;

	@ManyToOne(optional=false)
	@JoinColumn(name="\"product\"", nullable=false)
	private Product product;

	public Tariff getTariff() {

		return tariff;

	}

	public void setTariff(Tariff tariff) {

		this.tariff = tariff;

	}

	public Product getProduct() {

		return product;

	}

	public void setProduct(Product product) {

		this.product = product;

	}

	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((tariff == null) ? 0 : tariff.hashCode());
		return result;

	}

	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TariffProductPK other = (TariffProductPK) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (tariff == null) {
			if (other.tariff != null)
				return false;
		} else if (!tariff.equals(other.tariff))
			return false;
		return true;

	}

}