package com.btsistemas.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 *
 * @author ben
 */
@Entity
public class ItemRequestSale implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    ItemRequestSalePK id = new ItemRequestSalePK();

    private Double discount;
    private Integer amount;
    private BigDecimal price;

    public ItemRequestSale() {
    }

    public ItemRequestSale(RequestSale requestSale, Product product, Double discount, Integer amount, BigDecimal price) {
        id.setRequestSale(requestSale);
        id.setProduct(product);
        this.discount = discount;
        this.amount = amount;
        this.price = price;
    }


    public BigDecimal getSubTotal() {
    	return (price.subtract(new BigDecimal(discount))).multiply(new BigDecimal(amount)); 
    }
    
    @JsonIgnore
    public RequestSale getRequestSale() {
        return id.getRequestSale();
    }
    
    public void setRequestSale(RequestSale requestSale) {
    	id.setRequestSale(requestSale);
    }

    public Product getProduct() {
        return id.getProduct();
    }
    public void setProduct(Product product) {
		id.setProduct(product);
    }

    public ItemRequestSalePK getId() {
        return id;
    }

    public void setId(ItemRequestSalePK id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final ItemRequestSale other = (ItemRequestSale) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
}
