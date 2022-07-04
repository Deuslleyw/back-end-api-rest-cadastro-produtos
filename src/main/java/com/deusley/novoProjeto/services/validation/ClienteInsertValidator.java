package com.deusley.novoProjeto.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.deusley.novoProjeto.domain.enums.TipoCliente;
import com.deusley.novoProjeto.dto.ClienteNewDTO;
import com.deusley.novoProjeto.resources.exception.FieldMessage;
import com.deusley.novoProjeto.services.validation.utils.BRZ;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClienteInsert ann) {
	}
                                     
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {  //listar os testes
		List<FieldMessage> list = new ArrayList<>();
		
     if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BRZ.isValidCPF(objDto.getCpfOuCnpj())) {
    	 list.add(new FieldMessage("cpfOuCnpj", "CPF INVÁLIDO"));
     }

     if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BRZ.isValidCNPJ(objDto.getCpfOuCnpj())) {
    	 list.add(new FieldMessage("cpfOuCnpj", "CNPJ INVÁLIDO"));
     }	 
		
		for (FieldMessage e : list) {

			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
			.addPropertyNode(e.getFieldName())
			.addConstraintViolation();

		}

		return list.isEmpty();
	}
}
