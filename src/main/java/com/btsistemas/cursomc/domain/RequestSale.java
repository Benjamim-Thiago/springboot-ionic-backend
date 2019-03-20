package com.btsistemas.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author ben
 */
@Entity
public class RequestSale implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date instant;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "requestSale")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "address_delivery_id")
    private Address addressDelivery;

    @OneToMany(mappedBy = "id.requestSale")
    private Set<ItemRequestSale> items = new HashSet<>();

    public RequestSale() {
    }

    public RequestSale(Integer id, Date instant, Client client, Address addressDelivery) {
        this.id = id;
        this.instant = instant;
        this.client = client;
        this.addressDelivery = addressDelivery;
    }

    public BigDecimal getValueTotal() {
    	 BigDecimal addValue = new BigDecimal("0.00");
    	 for (ItemRequestSale irs : items) {
    		 addValue = addValue.add(irs.getSubTotal());
    	 }
    	 return addValue;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstant() {
        return instant;
    }

    public void setInstant(Date instant) {
        this.instant = instant;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address getAddressDelivery() {
        return addressDelivery;
    }

    public void setAddressDelivery(Address addressDelivery) {
        this.addressDelivery = addressDelivery;
    }

    public Set<ItemRequestSale> getItems() {
        return items;
    }

    public void setItems(Set<ItemRequestSale> items) {
        this.items = items;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final RequestSale other = (RequestSale) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    @Override
	public String toString() {
    	NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    	SimpleDateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido número: ");
		builder.append(getId());
		builder.append(", Instante: ");
		builder.append(dtFormat.format(getInstant()));
		builder.append(", Cliente: ");
		builder.append(getClient().getName());
		builder.append(", Situação do pagamento: ");
		builder.append(getPayment().getStatus().getDescription());
		builder.append("\nDetalhes:\n");
		for(ItemRequestSale irs : getItems()) {
			builder.append(irs.toString());
		}
		builder.append("Valor total: ");
		builder.append(nf.format(getValueTotal()));
		return builder.toString();
	}

}
