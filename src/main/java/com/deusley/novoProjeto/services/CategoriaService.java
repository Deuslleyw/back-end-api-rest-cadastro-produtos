package com.deusley.novoProjeto.services;

import java.util.List;

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


	public Categoria find(Integer id) {
		return rep.findById(id)
				.orElseThrow(() -> new ObjectNotFoundExcepition(" Categoria  de ID: " + id + ", não encontrado!"));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);                                                  
		return rep.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return rep.save(obj);
}
	public void delete(Integer id) {
		find(id);
		
		try {
		rep.deleteById(id);
	
		}catch(DataIntegrityViolationException erDelete) {
		throw new DataIntegrityExcepition("A categoria possui produtos, não é possivel excluir!");
	
}
	}
		public List<Categoria> findAll(){
			return rep.findAll();
		}

}