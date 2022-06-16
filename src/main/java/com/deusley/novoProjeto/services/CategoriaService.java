package com.deusley.novoProjeto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.deusley.novoProjeto.domain.Categoria;
import com.deusley.novoProjeto.dto.CategoriaDTO;
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
    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
    	PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
    	return rep.findAll(pageRequest);
    }
    public Categoria fromDTO(CategoriaDTO objDTO) {
    	return new Categoria(objDTO.getId(), objDTO.getNome());
    }
}