package com.deusley.novoProjeto.services;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deusley.novoProjeto.domain.Categoria;
import com.deusley.novoProjeto.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository rep;

	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = rep.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo" + Categoria.class.getName(), null));
				

	}

}
