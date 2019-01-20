package br.com.developen.erp.orm;


import java.util.List;

import org.hibernate.Session;

public class ProductProductDAO extends DAO<ProductProduct, ProductProductPK>{

	public ProductProductDAO(Session session) {

		super(session, ProductProduct.class);

	}

	public List<ProductProduct> list(){

		return getSession().
				createNamedQuery(ProductProduct.FIND_ALL, ProductProduct.class).
				getResultList();  

	}

}