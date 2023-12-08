package br.com.cotiinformatica.api.controllers;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.application.dtos.AtualizarDadosDTO;
import jakarta.validation.Valid;

@RestController
public class AtualizarDadosController {

	@PutMapping("/api/usuarios/atualizar-dados")
	public String put(@Valid @RequestBody AtualizarDadosDTO dto) {
		
		return null;
		
		
		
	}
	
	
}
