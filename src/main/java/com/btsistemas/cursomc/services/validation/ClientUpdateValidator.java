package com.btsistemas.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.btsistemas.cursomc.domain.Client;
import com.btsistemas.cursomc.domain.enums.TypeClient;
import com.btsistemas.cursomc.dto.ClientDTO;
import com.btsistemas.cursomc.repositories.ClientRepository;
import com.btsistemas.cursomc.resources.exception.FieldMessage;
import com.btsistemas.cursomc.services.validation.utils.DocumentCPFCNPJ;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ClientRepository repo;

	@Override
	public void initialize(ClientUpdate ann) {
	}

	@Override
	public boolean isValid(ClientDTO objDTO, ConstraintValidatorContext context) {
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		// inclua os testes aqui, inserindo erros na lista

		Client aux = repo.findByEmail(objDTO.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "E-mail já existe."));
		}
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
