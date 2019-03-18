package com.btsistemas.cursomc.services;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

import com.btsistemas.cursomc.domain.PaymentWithTicket;

@Service
public class TicketService {
	public void sendPaymentWithTicket(PaymentWithTicket paymentWithTicket, Date requestSaleInstant) {
		//Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Fortaleza"));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(requestSaleInstant);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		paymentWithTicket.setPaymentDate(calendar.getTime());
	}
}
