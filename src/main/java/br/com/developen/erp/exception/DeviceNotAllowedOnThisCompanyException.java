package br.com.developen.erp.exception;

import br.com.developen.erp.util.I18N;

public class DeviceNotAllowedOnThisCompanyException extends UnauthorizedException {

	private static final long serialVersionUID = 1L;

	public DeviceNotAllowedOnThisCompanyException(){

		super(I18N.get(I18N.DEVICE_NOT_ALLOWED_ON_THIS_COMPANY));

	}

}