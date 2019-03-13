package com.btsistemas.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.btsistemas.cursomc.domain.enums.TypeClient;
import com.btsistemas.cursomc.dto.ClientNewDTO;
import com.btsistemas.cursomc.resources.exception.FieldMessage;
import com.btsistemas.cursomc.services.validation.utils.DocumentCPFCNPJ;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		// inclua os testes aqui, inserindo erros na lista
		if(objDTO.getTypeClient().equals(TypeClient.PESSOA_FISICA.getCode()) && !DocumentCPFCNPJ.isValidCPF(objDTO.getDocument())) {
			list.add(new FieldMessage("document", "CPF inválido."));
		}
		
		if(objDTO.getTypeClient().equals(TypeClient.PESSOA_JURÍDICA.getCode()) && !DocumentCPFCNPJ.isValidCNPJ(objDTO.getDocument())) {
			list.add(new FieldMessage("document", "CNPJ inválido."));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
