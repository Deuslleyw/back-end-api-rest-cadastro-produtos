package com.deusley.novoProjeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deusley.novoProjeto.domain.Categoria;
import com.deusley.novoProjeto.repositories.CategoriaRepository;
import com.deusley.novoProjeto.services.Exceptions.ObjectNotFoundExcepition;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository rep;


	public Categoria buscar(Integer id) {
		return rep.findById(id)
				.orElseThrow(() -> new ObjectNotFoundExcepition(" Categoria  de ID: " + id + ", não encontrado!"));
	}

}
