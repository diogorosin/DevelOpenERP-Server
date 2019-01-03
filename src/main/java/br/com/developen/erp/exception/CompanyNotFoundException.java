package br.com.developen.erp.exception;

import br.com.developen.erp.util.I18N;

public class CompanyNotFoundException extends NotFoundException{

	private static final long serialVersionUID = 1L;

	public CompanyNotFoundException(){

		super(I18N.get(I18N.COMPANY_NOT_FOUND));

	}

}