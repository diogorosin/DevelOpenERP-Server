package br.com.pocketpos.server.exception;

import br.com.pocketpos.server.util.I18N;

public class DeviceNotFoundException extends NotFoundException{

	private static final long serialVersionUID = 1L;

	public DeviceNotFoundException(){

		super(I18N.get(I18N.DEVICE_NOT_FOUND));

	}

}
