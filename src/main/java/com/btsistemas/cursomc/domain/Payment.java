/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btsistemas.cursomc.domain;

import com.btsistemas.cursomc.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Inheritance;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

/**
 *
 * @author ben
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private Integer id;
    private Integer status;
    
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "request_sale_id")
    @MapsId
    private RequestSale requestSale;

    public Payment() {
    }

    public Payment(Integer id, PaymentStatus status, RequestSale requestSale) {
        this.id = id;
        this.status = status.getCode();
        this.requestSale = requestSale;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentStatus getStatus() {
        return PaymentStatus.toEnum(status);
    }

    public void setStatus(PaymentStatus status) {
        this.status = status.getCode();
    }

    public RequestSale getRequestSale() {
        return requestSale;
    }

    public void setRequestSale(RequestSale requestSale) {
        this.requestSale = requestSale;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Payment other = (Payment) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    

}
