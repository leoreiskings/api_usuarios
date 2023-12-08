package br.com.cotiinformatica.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.application.dtos.CriarContaDTO;
import br.com.cotiinformatica.application.dtos.CriarContaResponseDTO;
import br.com.cotiinformatica.application.interfaces.IUsuarioAppService;
import jakarta.validation.Valid;

@RestController
public class CriarContaController {

	@Autowired
	private IUsuarioAppService usuarioAppService;	

	@PostMapping("/api/usuarios/criar-conta")
	public ResponseEntity<CriarContaResponseDTO> post(@Valid @RequestBody CriarContaDTO dto) {
		
		CriarContaResponseDTO response =  usuarioAppService.criarConta(dto);		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}
	
	
	
	
}
