package br.com.cotiinformatica.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.application.dtos.RecuperarSenhaDTO;
import br.com.cotiinformatica.application.dtos.RecuperarSenhaResponseDTO;
import br.com.cotiinformatica.application.interfaces.IUsuarioAppService;
import jakarta.validation.Valid;

@RestController
public class RecuperarSenhaController {

	@Autowired
	private IUsuarioAppService usuarioAppService;

	@PostMapping("/api/usuarios/recuperar-senha")
	public ResponseEntity<RecuperarSenhaResponseDTO> post(@Valid @RequestBody RecuperarSenhaDTO dto) {

		RecuperarSenhaResponseDTO response = usuarioAppService.recuperarSenha(dto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
