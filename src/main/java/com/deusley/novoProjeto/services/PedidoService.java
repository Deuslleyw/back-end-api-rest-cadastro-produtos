package com.deusley.novoProjeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deusley.novoProjeto.domain.Pedido;
import com.deusley.novoProjeto.repositories.PedidoRepository;
import com.deusley.novoProjeto.services.Exceptions.ObjectNotFoundExcepition;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository rep;


	public Pedido buscar(Integer id) {
		return rep.findById(id)
				.orElseThrow(() -> new ObjectNotFoundExcepition(" Pedido  de ID: " + id + ", não encontrado!"));
	}

}
