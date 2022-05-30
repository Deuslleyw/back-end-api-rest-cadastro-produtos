package com.deusley.novoProjeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deusley.novoProjeto.domain.Cliente;
import com.deusley.novoProjeto.repositories.ClienteRepository;
import com.deusley.novoProjeto.services.Exceptions.ObjectNotFoundExcepition;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository rep;


	public Cliente buscar(Integer id) {
		return rep.findById(id)
				.orElseThrow(() -> new ObjectNotFoundExcepition(" Cliente  de ID: " + id + ", não encontrado!"));
	}

}
