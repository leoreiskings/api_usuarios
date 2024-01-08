package br.com.cotiinformatica.application.dtos;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RecuperarSenhaResponseDTO {

	
	private String mensagem;
	private String id;
	private String nome;
	private String email;
	
	
	
}
