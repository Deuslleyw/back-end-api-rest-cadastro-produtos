package com.deusley.novoProjeto.services;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deusley.novoProjeto.domain.Categoria;
import com.deusley.novoProjeto.domain.Cidade;
import com.deusley.novoProjeto.domain.Cliente;
import com.deusley.novoProjeto.domain.Endereco;
import com.deusley.novoProjeto.domain.Estado;
import com.deusley.novoProjeto.domain.ItemPedido;
import com.deusley.novoProjeto.domain.Pagamento;
import com.deusley.novoProjeto.domain.PagamentoComBoleto;
import com.deusley.novoProjeto.domain.PagamentoComCartao;
import com.deusley.novoProjeto.domain.Pedido;
import com.deusley.novoProjeto.domain.Produto;
import com.deusley.novoProjeto.domain.enums.EstadoPagamento;
import com.deusley.novoProjeto.domain.enums.TipoCliente;
import com.deusley.novoProjeto.repositories.CategoriaRepository;
import com.deusley.novoProjeto.repositories.CidadeRepository;
import com.deusley.novoProjeto.repositories.ClienteRepository;
import com.deusley.novoProjeto.repositories.EnderecoRepository;
import com.deusley.novoProjeto.repositories.EstadoRepository;
import com.deusley.novoProjeto.repositories.ItemPedidoRepository;
import com.deusley.novoProjeto.repositories.PagamentoRepository;
import com.deusley.novoProjeto.repositories.PedidoRepository;
import com.deusley.novoProjeto.repositories.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRespository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	public void instantiateTestDatabase() throws ParseException {
		
		
		
		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Escritório");
		Categoria cat3 = new Categoria(null,"Cozinha");
		Categoria cat4 = new Categoria(null,"Sala");
		Categoria cat5 = new Categoria(null,"Lazer");
		Categoria cat6 = new Categoria(null,"Esporte");
 
		
	Produto p1 = new Produto(null, "Computador", 2000.00);	
	Produto p2 = new Produto (null, "Mesa Gamer", 830.00);
	Produto p3 = new Produto(null, "SSD",120.00);
	
	   cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
	   cat2.getProdutos().addAll(Arrays.asList(p2));
	   
	   p1.getCategorias().addAll(Arrays.asList(cat1));
	   p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
	   p3.getCategorias().addAll(Arrays.asList(cat1));
	   
	   
	   categoriaRepository.saveAll(Arrays.asList(cat1 , cat2,cat3, cat4,cat5,cat6));
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
		
	
	Cliente cli1 = new Cliente(null, "Andreia Neto", "teste@hotmail.com", "36378912377", TipoCliente.PESSOAFISICA);
	cli1.getTelefones().addAll(Arrays.asList("33725540","998475501"));
	
	Endereco end1 = new Endereco(null,"Rua teste", "368", "ap005", "Jardins","37118835", cli1, cd1);
	Endereco end2 = new Endereco(null, "Av.Paulo", "250", "ap101", "Flores", "39554460", cli1, cd2);
		
	cli1.getEnderecos().addAll(Arrays.asList(end1,end2));
	
	clienteRepository.saveAll(Arrays.asList(cli1));
	enderecoRepository.saveAll(Arrays.asList(end1, end2));
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	Pedido ped1 = new Pedido(null, sdf.parse("20/03/2022  15:29"), cli1, end1);
	Pedido ped2 = new Pedido(null,sdf.parse("15/02/2022 16:45"),cli1, end2);
	
	Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 5);
	ped1.setPagamento(pagto1);
	
	Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE,ped2, sdf.parse("26/05/2022 04:32"),null );
	ped2.setPagamento(pagto2);
	
	cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
	
	pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
	pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
	
	ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
	ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
	ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
	
	ped1.getItens().addAll(Arrays.asList(ip1,ip2));
	ped2.getItens().addAll(Arrays.asList(ip3));
	
	p1.getItens().addAll(Arrays.asList(ip1));
	p2.getItens().addAll(Arrays.asList(ip3));
	p3.getItens().addAll(Arrays.asList(ip2));
	
	itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	
	}
		
	}


