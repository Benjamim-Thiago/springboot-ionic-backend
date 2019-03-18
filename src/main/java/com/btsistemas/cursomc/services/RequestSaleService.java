package com.btsistemas.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btsistemas.cursomc.domain.ItemRequestSale;
import com.btsistemas.cursomc.domain.PaymentWithTicket;
import com.btsistemas.cursomc.domain.RequestSale;
import com.btsistemas.cursomc.domain.enums.PaymentStatus;
import com.btsistemas.cursomc.repositories.ItemRequestSaleRepository;
import com.btsistemas.cursomc.repositories.PaymentRepository;
import com.btsistemas.cursomc.repositories.RequestSaleRepository;
import com.btsistemas.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class RequestSaleService {

	@Autowired
	private RequestSaleRepository repo;
	@Autowired
	private ItemRequestSaleRepository itemRequestSaleRepository;
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ProductService productService;
	@Autowired
	private TicketService ticketService;

	public RequestSale find(Integer id) {
		Optional<RequestSale> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + RequestSale.class.getName()));

	}

	@Transactional
	public RequestSale insert(RequestSale obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.getPayment().setStatus(PaymentStatus.PENDENTE);
		obj.getPayment().setRequestSale(obj);
		if (obj.getPayment() instanceof PaymentWithTicket) {
			PaymentWithTicket paymentWithTicket = (PaymentWithTicket) obj.getPayment();
			ticketService.sendPaymentWithTicket(paymentWithTicket, obj.getInstant());
		}
		obj = repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for (ItemRequestSale irs : obj.getItems()) {
			irs.setDiscount(0.0);
			irs.setPrice(productService.find(irs.getProduct().getId()).getPrice());
			irs.setRequestSale(obj);
		}
		itemRequestSaleRepository.saveAll(obj.getItems());
		return obj;
	}

}
