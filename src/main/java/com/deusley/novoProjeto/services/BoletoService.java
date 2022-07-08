package com.deusley.novoProjeto.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.deusley.novoProjeto.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {  
	
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataPagamento(cal.getTime());
	}
	
}










//Classe-para-demonstração-para-dataDeVencimentoDoBoletoTeste// na faltaDeUmaIntegraçãoCOmWebServices...
