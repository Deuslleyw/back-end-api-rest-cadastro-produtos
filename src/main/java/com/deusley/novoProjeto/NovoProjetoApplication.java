package com.deusley.novoProjeto;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.deusley.novoProjeto.domain.Categoria;
import com.deusley.novoProjeto.domain.Cidade;
import com.deusley.novoProjeto.domain.Estado;
import com.deusley.novoProjeto.domain.Produto;
import com.deusley.novoProjeto.repositories.CategoriaRepository;
import com.deusley.novoProjeto.repositories.CidadeRepository;
import com.deusley.novoProjeto.repositories.EstadoRepository;
import com.deusley.novoProjeto.repositories.ProdutoRepository;

@SpringBootApplication
public class NovoProjetoApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRespository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
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
	   
	   
	Estado std1 = new Estado(null, "Santa Catarina");
	Estado std2 = new Estado (null, "Paraná");
	
	Cidade cd1 = new Cidade(null,"Florianópolis", std1);
	Cidade cd2 = new Cidade(null,"Curitiba", std2);
	Cidade cd3 = new Cidade(null, "Blumenau", std1);
	
	std1.getCidades().addAll(Arrays.asList(cd1,cd3));
	std2.getCidades().addAll(Arrays.asList(cd2));
		
	estadoRepository.saveAll(Arrays.asList(std1, std2));
	cidadeRespository.saveAll(Arrays.asList(cd1,cd2, cd3));
		
		
	}

}
                                      
//DeusleyDiego