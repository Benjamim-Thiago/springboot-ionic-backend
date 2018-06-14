package com.btsistemas.cursomc.domain;

import com.btsistemas.cursomc.domain.enums.PaymentStatus;
import javax.persistence.Entity;

/**
 *
 * @author ben
 */
@Entity
public class PaymentWithCard extends Payment {

    private static final long serialVersionUID = 1L;

    private Integer portionNumber;

    public PaymentWithCard() {
    }

    public PaymentWithCard(Integer id, PaymentStatus status, RequestSale requestSale, Integer portionNumber) {
        super(id, status, requestSale);
        this.portionNumber = portionNumber;
    }

    public Integer getPortionNumber() {
        return portionNumber;
    }

    public void setPortionNumber(Integer portionNumber) {
        this.portionNumber = portionNumber;
    }

}
