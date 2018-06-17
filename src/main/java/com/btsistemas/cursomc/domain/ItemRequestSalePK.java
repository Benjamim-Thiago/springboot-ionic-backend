package com.btsistemas.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author ben
 */
@Embeddable
public class ItemRequestSalePK implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "request_sale_id")
    private RequestSale requestSale;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public RequestSale getRequestSale() {
        return requestSale;
    }

    public void setRequestSale(RequestSale requestSale) {
        this.requestSale = requestSale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.requestSale);
        hash = 73 * hash + Objects.hashCode(this.product);
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
        final ItemRequestSalePK other = (ItemRequestSalePK) obj;
        if (!Objects.equals(this.requestSale, other.requestSale)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return true;
    }

}
