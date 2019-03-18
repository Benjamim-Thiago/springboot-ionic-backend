package com.btsistemas.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.btsistemas.cursomc.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 *
 * @author ben
 */
@Entity
@JsonTypeName("paymentWithTicket")
public class PaymentWithTicket extends Payment {

    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date paymentMaturity;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date paymentDate;

    public PaymentWithTicket() {
    }

    public PaymentWithTicket(Integer id, PaymentStatus status, RequestSale requestSale, Date paymentMaturity, Date paymentDate) {
        super(id, status, requestSale);
        this.paymentMaturity = paymentMaturity;
        this.paymentDate = paymentDate;
    }

    public Date getPaymentMaturity() {
        return paymentMaturity;
    }

    public void setPaymentMaturity(Date paymentMaturity) {
        this.paymentMaturity = paymentMaturity;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

}
