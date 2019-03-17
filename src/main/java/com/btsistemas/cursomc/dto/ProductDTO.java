package com.btsistemas.cursomc.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.btsistemas.cursomc.domain.Product;

public class ProductDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String description;
	private BigDecimal price;

	public ProductDTO() {
	}
	
	public ProductDTO(Product obj) {
		id = obj.getId();
		description = obj.getDescription();
		price = obj.getPrice();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
