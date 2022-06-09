package com.deusley.novoProjeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.deusley.novoProjeto.domain.Categoria;
import com.deusley.novoProjeto.repositories.CategoriaRepository;
import com.deusley.novoProjeto.services.Exceptions.DataIntegrityExcepition;
import com.deusley.novoProjeto.services.Exceptions.ObjectNotFoundExcepition;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository rep;


	public Categoria buscar(Integer id) {
		return rep.findById(id)
				.orElseThrow(() -> new ObjectNotFoundExcepition(" Categoria  de ID: " + id + ", não encontrado!"));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);                                                   //ERRO DE INSERÇAO categoria/CRIANDO NOME NULL
		return rep.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		buscar(obj.getId());
		return rep.save(obj);
}
	public void delete(Integer id) {
		buscar(id);
		
		try {
		rep.deleteById(id);
	
		}catch(DataIntegrityViolationException erDelete) {
		throw new DataIntegrityExcepition("A categoria possui produtos, não é possivel excluir!");
	
}
}
}
