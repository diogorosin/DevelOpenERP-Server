package br.com.pocketpos.server.exception;

import br.com.pocketpos.server.util.I18N;

public class UserNotAllowedException extends UnauthorizedException {

	private static final long serialVersionUID = 1L;

	public UserNotAllowedException(){

		super(I18N.get(I18N.USER_NOT_ALLOWED));

	}

}