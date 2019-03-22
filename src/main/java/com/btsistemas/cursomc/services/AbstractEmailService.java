package com.btsistemas.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.btsistemas.cursomc.domain.RequestSale;

public abstract class AbstractEmailService implements EmailService {
	@Value("${default.sender}")
	private String send;
	
	@Override
	public void sendOrderConfirmationEmail(RequestSale obj) {
		SimpleMailMessage simpleMailMessage = prepareSimpleMailMessage(obj);
		sendEmail(simpleMailMessage);
	}

	protected SimpleMailMessage prepareSimpleMailMessage(RequestSale obj) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(obj.getClient().getEmail());
		simpleMailMessage.setFrom(send);
		simpleMailMessage.setSubject("Pedido confirmado| Código: " + obj.getId());
		simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
		simpleMailMessage.setText(obj.toString());
		
		return simpleMailMessage;
	}
}
