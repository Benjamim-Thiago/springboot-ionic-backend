package com.btsistemas.cursomc.domain;

import com.btsistemas.cursomc.domain.enums.PaymentStatus;
import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author ben
 */
@Entity
public class PaymentWithTicket extends Payment {

    private static final long serialVersionUID = 1L;

    private Date paymentMaturity;
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
