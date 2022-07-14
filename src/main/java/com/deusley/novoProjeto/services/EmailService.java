package com.deusley.novoProjeto.services;

import org.springframework.mail.SimpleMailMessage;

import com.deusley.novoProjeto.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);
}
