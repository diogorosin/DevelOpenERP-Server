package br.com.pocketpos.server.exception;

import br.com.pocketpos.server.util.I18N;

public class UserNotLinkedToTheCompanyException extends UnauthorizedException {

	private static final long serialVersionUID = 1L;

	public UserNotLinkedToTheCompanyException(){

		super(I18N.get(I18N.USER_NOT_LINKED_TO_THE_COMPANY));

	}

}