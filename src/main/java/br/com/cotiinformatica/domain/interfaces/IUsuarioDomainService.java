package br.com.cotiinformatica.domain.interfaces;

import br.com.cotiinformatica.domain.models.Usuario;

public interface IUsuarioDomainService {
	
	void criarConta(Usuario usuario);
	
	Usuario autenticar(String email, String senha); 
	
	Usuario recuperarSenha(String email);
	
	Usuario atualizarDados(Usuario usuario);
	
	
}
