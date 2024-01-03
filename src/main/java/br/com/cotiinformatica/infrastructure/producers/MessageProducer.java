package br.com.cotiinformatica.infrastructure.producers;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

	@Autowired 
	private RabbitTemplate rabbitTemplate; 
	
	@Autowired 
	private Queue queue; 
	
	/* * Método para receber uma mensagem e escrever esta mensagem na fila. */ 
	public void send(String message) { 
		
		rabbitTemplate.convertAndSend (this.queue.getName(), message); 
		
	}	
}
