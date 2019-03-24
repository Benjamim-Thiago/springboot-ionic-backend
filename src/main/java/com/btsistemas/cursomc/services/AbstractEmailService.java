package com.btsistemas.cursomc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.btsistemas.cursomc.domain.RequestSale;

public abstract class AbstractEmailService implements EmailService {
	@Value("${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	JavaMailSender javaMailSender;

	@Override
	public void sendOrderConfirmationEmail(RequestSale obj) {
		SimpleMailMessage simpleMailMessage = prepareSimpleMailMessage(obj);
		sendEmail(simpleMailMessage);
	}

	protected SimpleMailMessage prepareSimpleMailMessage(RequestSale obj) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(obj.getClient().getEmail());
		simpleMailMessage.setFrom(sender);
		simpleMailMessage.setSubject("Pedido confirmado| Código: " + obj.getId());
		simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
		simpleMailMessage.setText(obj.toString());

		return simpleMailMessage;
	}

	protected String htmlFromTemplateRequestSale(RequestSale obj) {
		Context context = new Context();
		context.setVariable("request", obj);

		return templateEngine.process("email/requestSaleConfirmation", context);
	}

	@Override
	public void sendOrderConfirmationHtmlEmail(RequestSale obj) {
		try {
			MimeMessage mimeMessage = prepareMimeMessageFormRequestSale(obj);
			sendHtmlEmail(mimeMessage);
			
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
	}

	private MimeMessage prepareMimeMessageFormRequestSale(RequestSale obj) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setTo(obj.getClient().getEmail());
		helper.setFrom(sender);
		helper.setSubject("Pedido confirmado| Código: " + obj.getId());
		helper.setSentDate(new Date(System.currentTimeMillis()));
		helper.setText(htmlFromTemplateRequestSale(obj),true);
		
		return  message;
	}
}
