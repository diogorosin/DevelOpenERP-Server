package br.com.pocketpos.server.exception;

import br.com.pocketpos.server.util.I18N;

public class UserNotActiveException extends UnauthorizedException {

	private static final long serialVersionUID = 1L;

	public UserNotActiveException(){

		super(I18N.get(I18N.USER_NOT_ACTIVE));

	}

}