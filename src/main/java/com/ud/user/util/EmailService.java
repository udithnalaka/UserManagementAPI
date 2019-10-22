package com.ud.user.util;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.ud.user.entity.Mail;

@Component
public class EmailService {

	private JavaMailSender mailSender;

	@Autowired
	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	private MimeMessagePreparator prepareEmail(Mail mail) {
		return mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(
					mimeMessage, true, StandardCharsets.UTF_8.name());

			messageHelper.setFrom(mail.getSender());
			messageHelper.setTo(mail.getRecipient());
			messageHelper.setSubject(mail.getSubject());
			messageHelper.setText(mail.getMessage(), true);
		};
	}

	public void sendEmail(Mail mail) throws MailException {
		mailSender.send(prepareEmail(mail));
	}

}
