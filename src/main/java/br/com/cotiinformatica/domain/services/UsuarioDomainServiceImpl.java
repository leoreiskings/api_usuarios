package br.com.cotiinformatica.domain.services;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.interfaces.IUsuarioDomainService;
import br.com.cotiinformatica.domain.models.Usuario;
import br.com.cotiinformatica.infrastructure.components.MD5Component;
import br.com.cotiinformatica.infrastructure.repositories.IUsuarioRepository;
import br.com.cotiinformatica.infrastructure.security.TokenCreator;

@Service
public class UsuarioDomainServiceImpl implements IUsuarioDomainService {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Autowired
	private MD5Component md5Component;
	
	@Autowired
	private TokenCreator tokenCreator;
	
	
	@Override
	public void criarConta(Usuario usuario) {
		
		Optional<Usuario> optional = usuarioRepository.findByEmail(usuario.getEmail());
		
		if (optional.isPresent()) { // se retornou algum registro, retorna a msg
			throw new IllegalArgumentException("O email informado já está cadastrado.");
		}
		
		usuario.setSenha(md5Component.encrypt(usuario.getSenha()));
		
		usuario.setDataHoraCriacao(Instant.now());
		usuario.setDataHoraUltimaAlteracao(Instant.now());
		
		usuarioRepository.save(usuario);
		
	}


	@Override
	public Usuario autenticar(String email, String senha) {
		
		Optional<Usuario> optional = usuarioRepository.findByEmailAndSenha(email, md5Component.encrypt(senha));
		
		if(optional.isEmpty()) {
			
			throw new IllegalArgumentException("Acesso negado. Usuário não encontrado.");
			
		}

		Usuario usuario = optional.get();
		
		usuario.setAccessToken(tokenCreator.generateToken(usuario.getEmail()));
		return usuario;
	}


	@Override
	public Usuario recuperarSenha(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
