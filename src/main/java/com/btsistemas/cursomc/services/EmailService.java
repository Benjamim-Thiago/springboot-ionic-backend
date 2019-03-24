package com.btsistemas.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.btsistemas.cursomc.domain.RequestSale;

public interface EmailService {
	void sendOrderConfirmationEmail(RequestSale obj);
	
	void sendEmail(SimpleMailMessage message);
	
	void sendOrderConfirmationHtmlEmail(RequestSale obj);
	
	void sendHtmlEmail(MimeMessage msg);
}
