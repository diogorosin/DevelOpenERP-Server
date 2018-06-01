package br.com.pocketpos.server.exception;

import br.com.pocketpos.server.util.I18N;

public class InvalidPasswordException extends UnauthorizedException{

	private static final long serialVersionUID = 1L;

	public InvalidPasswordException(){

		super(I18N.get(I18N.INVALID_PASSWORD));

	}

}
