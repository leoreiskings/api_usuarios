package br.com.cotiinformatica.api.config;

import org.springframework.amqp.core.Queue; 
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

	 
	/* * 
	 * Lendo o valor do parametro 'queue.name' 
	 * criado no arquivo * application/properties 
	 * */ 
	@Value("${queue.name}")	
	private String queueName;
	
	
	/* * Configuração para que o projeto possa conectar
	 *   na fila criada no servidor do  RabbitMQ 
	 *   */
	@Bean	
	public Queue queue() { 
		return new Queue(queueName, true); 
	}	
				   
}
