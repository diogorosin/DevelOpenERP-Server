package br.com.developen.erp.exception;

import br.com.developen.erp.util.I18N;

public class DeviceNotActiveException extends UnauthorizedException{

	private static final long serialVersionUID = 1L;

	public DeviceNotActiveException(){

		super(I18N.get(I18N.DEVICE_NOT_ACTIVE));

	}

}