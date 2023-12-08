package br.com.cotiinformatica.application.interfaces;

import br.com.cotiinformatica.application.dtos.AutenticarDTO;
import br.com.cotiinformatica.application.dtos.AutenticarResponseDTO;
import br.com.cotiinformatica.application.dtos.CriarContaDTO;
import br.com.cotiinformatica.application.dtos.CriarContaResponseDTO;
import br.com.cotiinformatica.application.dtos.RecuperarSenhaDTO;
import br.com.cotiinformatica.application.dtos.RecuperarSenhaResponseDTO;

public interface IUsuarioAppService {

	CriarContaResponseDTO criarConta(CriarContaDTO dto);
	
	AutenticarResponseDTO autenticar(AutenticarDTO dto);
	
	RecuperarSenhaResponseDTO recuperarSenha(RecuperarSenhaDTO dto);
	
	
	
}
