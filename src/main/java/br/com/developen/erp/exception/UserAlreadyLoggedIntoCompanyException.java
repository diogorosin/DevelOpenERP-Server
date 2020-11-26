package br.com.developen.erp.exception;

import br.com.developen.erp.util.I18N;

public class UserAlreadyLoggedIntoCompanyException extends UnauthorizedException{

	private static final long serialVersionUID = 1L;

	public UserAlreadyLoggedIntoCompanyException(){

		super(I18N.get(I18N.USER_ALREADY_LOGGED_INTO_COMPANY));

	}

}