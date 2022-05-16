package com.deusley.novoProjeto.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deusley.novoProjeto.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listar() {
	
		Categoria cat1 = new Categoria(1,"Informatica");
		Categoria cat2 = new Categoria(2, "Escritorio");
		
		List<Categoria> listTeste = new ArrayList<>();
		
		listTeste.add(cat1);
		listTeste.add(cat2);
		
		return listTeste;
	}

}
