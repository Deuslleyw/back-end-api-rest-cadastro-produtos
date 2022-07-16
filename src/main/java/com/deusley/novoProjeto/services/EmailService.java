package com.deusley.novoProjeto.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.deusley.novoProjeto.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);           // TextoSimples

	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);     //enviar email HTML
	void sendHtmlEmail(MimeMessage msg);
}
