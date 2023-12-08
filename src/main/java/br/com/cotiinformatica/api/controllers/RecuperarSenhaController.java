package br.com.cotiinformatica.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.application.dtos.RecuperarSenhaDTO;
import jakarta.validation.Valid;

@RestController
public class RecuperarSenhaController {

	@PostMapping("/api/usuarios/recuperar-senha")
	public String post(@Valid @RequestBody RecuperarSenhaDTO dto) {
		return null;
	}

}
