package br.com.developen.erp.exception;

import br.com.developen.erp.util.I18N;

public class DeviceNotLinkedToTheCompanyException extends UnauthorizedException {

	private static final long serialVersionUID = 1L;

	public DeviceNotLinkedToTheCompanyException(){

		super(I18N.get(I18N.DEVICE_NOT_LINKED_TO_THE_COMPANY));

	}

}