package com.deusley.novoProjeto;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.deusley.novoProjeto.domain.Categoria;
import com.deusley.novoProjeto.domain.Produto;
import com.deusley.novoProjeto.repositories.CategoriaRepository;
import com.deusley.novoProjeto.repositories.ProdutoRepository;

@SpringBootApplication
public class NovoProjetoApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(NovoProjetoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		
	Produto p1 = new Produto(null, "Computador", 2000.00);	
	Produto p2 = new Produto (null, "Mesa Gamer", 830.00);
	Produto p3 = new Produto(null, "SSD",120.00);
	
	   cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
	   cat2.getProdutos().addAll(Arrays.asList(p2));
	   
	   p1.getCategorias().addAll(Arrays.asList(cat1));
	   p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
	   p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1 , cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
	}

}
                                      
//DeusleyDiego