package br.com.developen.erp.exception;

import br.com.developen.erp.util.I18N;

public class DeviceNotFoundException extends NotFoundException{

	private static final long serialVersionUID = 1L;

	public DeviceNotFoundException(){

		super(I18N.get(I18N.DEVICE_NOT_FOUND));

	}

}
