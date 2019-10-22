package com.ud.user.entity;

public class Mail {

	private String sender;
	private String recipient;
	private String subject;
	private String message;

	public Mail(String sender, String recipient, String subject, String message) {
		this.sender = sender;
		this.recipient = recipient;
		this.subject = subject;
		this.message = message;
	}

	public String getSender() {
		return sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public String getSubject() {
		return subject;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "Mail [sender=" + sender + 
				", recipient=" + recipient + 
				", subject=" + subject + 
				", message=" + message + "]";
	}

}
