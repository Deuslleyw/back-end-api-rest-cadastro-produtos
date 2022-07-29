package com.deusley.novoProjeto.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deusley.novoProjeto.domain.Cliente;
import com.deusley.novoProjeto.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer>  {

Page<Pedido> findByCliente(Cliente cliente, PageRequest pageRequest);
}
