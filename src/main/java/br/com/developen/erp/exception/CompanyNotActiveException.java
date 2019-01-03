package br.com.developen.erp.exception;

import br.com.developen.erp.util.I18N;

public class CompanyNotActiveException extends UnauthorizedException {

	private static final long serialVersionUID = 1L;

	public CompanyNotActiveException(){

		super(I18N.get(I18N.COMPANY_NOT_ACTIVE));

	}

}