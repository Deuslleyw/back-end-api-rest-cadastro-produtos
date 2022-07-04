package com.deusley.novoProjeto.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.deusley.novoProjeto.domain.Cliente;
import com.deusley.novoProjeto.domain.enums.TipoCliente;
import com.deusley.novoProjeto.dto.ClienteNewDTO;
import com.deusley.novoProjeto.repositories.ClienteRepository;
import com.deusley.novoProjeto.resources.exception.FieldMessage;
import com.deusley.novoProjeto.services.validation.utils.BRZ;



public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository rep;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}
                                     
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {  
		List<FieldMessage> list = new ArrayList<>();
		
     if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BRZ.isValidCPF(objDto.getCpfOuCnpj())) {          //testeCPF
    	 list.add(new FieldMessage("cpfOuCnpj", "CPF INVÁLIDO"));
     }

     if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BRZ.isValidCNPJ(objDto.getCpfOuCnpj())) {      //TestePj
    	 list.add(new FieldMessage("cpfOuCnpj", "CNPJ INVÁLIDO"));
     }	 
		
     Cliente aux = rep.findByEmail(objDto.getEmail());                                                                  //TesteEmail
     if(aux !=null) {
    	 list.add(new FieldMessage("email", "Esse email já existe"));
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
