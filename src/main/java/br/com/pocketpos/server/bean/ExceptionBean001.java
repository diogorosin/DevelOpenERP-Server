package br.com.pocketpos.server.bean;

import java.util.ArrayList;
import java.util.List;

public class ExceptionBean001 {

	private List<MessageBean001> messages;

	public ExceptionBean001(){}

	public ExceptionBean001(String message){

		getMessages().add(new MessageBean001(message));

	}

	public ExceptionBean001(MessageBean001 message){

		getMessages().add(message);

	}

	public List<MessageBean001> getMessages() {

		if (messages == null)

			messages = new ArrayList<MessageBean001>();

		return messages;

	}

	public void setMessages(List<MessageBean001> messages) {

		this.messages = messages;

	}

}