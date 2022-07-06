package com.deusley.novoProjeto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.deusley.novoProjeto.domain.Categoria;
import com.deusley.novoProjeto.domain.Produto;
import com.deusley.novoProjeto.repositories.CategoriaRepository;
import com.deusley.novoProjeto.repositories.ProdutoRepository;
import com.deusley.novoProjeto.services.Exceptions.ObjectNotFoundExcepition;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository rep;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto buscar(Integer id) {
		return rep.findById(id)
				.orElseThrow(() -> new ObjectNotFoundExcepition(" Produto  de ID: " + id + ", não encontrado!"));
	}

	public Page<Produto> search(String nome , List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
    	PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
    	
	List<Categoria> categorias = categoriaRepository.findAllById(ids);
	
	return rep.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest); 
	}
	
}

