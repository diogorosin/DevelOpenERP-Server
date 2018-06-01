package br.com.pocketpos.server.bean;

public class MessageBean001 {

	private String message;

	public MessageBean001(String message) {

		this.message = message;

	}

	public String getMessage() {

		return message;

	}

	public void setMessage(String message) {

		this.message = message;

	}

	public String toString(){

		return getMessage();

	}

}