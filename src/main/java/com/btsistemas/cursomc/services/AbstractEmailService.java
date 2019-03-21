package com.btsistemas.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.btsistemas.cursomc.domain.RequestSale;

public abstract class AbstractEmailService implements EmailService {
	@Override
	public void sendOrderConfirmationEmail(RequestSale obj) {
		SimpleMailMessage simpleMailMessage = prepareSimpleMailMessage(obj);
		sendEmail(simpleMailMessage);
	}

	protected SimpleMailMessage prepareSimpleMailMessage(RequestSale obj) {
		return null;
	}
}
