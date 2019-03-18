package com.btsistemas.cursomc.domain;

import javax.persistence.Entity;

import com.btsistemas.cursomc.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 *
 * @author ben
 */
@Entity
@JsonTypeName("paymentWithCard")
public class PaymentWithCard extends Payment {

    private static final long serialVersionUID = 1L;

    private Integer installmentNumber;

    public PaymentWithCard() {
    }

    public PaymentWithCard(Integer id, PaymentStatus status, RequestSale requestSale, Integer installmentNumber) {
        super(id, status, requestSale);
        this.installmentNumber = installmentNumber;
    }

	public Integer getInstallmentNumber() {
		return installmentNumber;
	}

	public void setInstallmentNumber(Integer installmentNumber) {
		this.installmentNumber = installmentNumber;
	}

   
}
