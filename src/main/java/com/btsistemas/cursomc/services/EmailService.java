package com.btsistemas.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.btsistemas.cursomc.domain.RequestSale;

public interface EmailService {
	void sendOrderConfirmationEmail(RequestSale obj);
	
	void sendEmail(SimpleMailMessage message);
}
