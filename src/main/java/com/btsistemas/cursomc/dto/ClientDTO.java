package com.btsistemas.cursomc.dto;

import java.io.Serializable;


import com.btsistemas.cursomc.domain.Client;
import com.btsistemas.cursomc.services.validation.ClientUpdate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@ClientUpdate
public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preencimento obrigatorio.")
	@Length(min = 5, max = 120, message = "Deve conter entre 5 a 120 caracteres.")
	private String name;

	@NotEmpty(message = "Preencimento obrigatorio.")
	@Email(message = "E-mail inválido.")
	private String email;

	public ClientDTO() {

	}

	public ClientDTO(Client obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
