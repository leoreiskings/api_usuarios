package br.com.cotiinformatica.application.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cotiinformatica.application.dtos.AutenticarDTO;
import br.com.cotiinformatica.application.dtos.AutenticarResponseDTO;
import br.com.cotiinformatica.application.dtos.CriarContaDTO;
import br.com.cotiinformatica.application.dtos.CriarContaResponseDTO;
import br.com.cotiinformatica.application.dtos.EmailMessageDTO;
import br.com.cotiinformatica.application.dtos.RecuperarSenhaDTO;
import br.com.cotiinformatica.application.dtos.RecuperarSenhaResponseDTO;
import br.com.cotiinformatica.application.interfaces.IUsuarioAppService;
import br.com.cotiinformatica.domain.interfaces.IUsuarioDomainService;
import br.com.cotiinformatica.domain.models.Usuario;
import br.com.cotiinformatica.infrastructure.producers.MessageProducer;

@Service
public class UsuarioAppServiceImpl implements IUsuarioAppService {

	@Autowired
	private IUsuarioDomainService usuarioDomainService;

	@Autowired
	private MessageProducer messageProducer;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public CriarContaResponseDTO criarConta(CriarContaDTO dto) {

		ModelMapper modelMapper = new ModelMapper();

		Usuario usuario = modelMapper.map(dto, Usuario.class);

		usuarioDomainService.criarConta(usuario);

		CriarContaResponseDTO response = modelMapper.map(usuario, CriarContaResponseDTO.class);

		response.setMensagem("Conta de usuário criada com sucesso.");

		// escrevendo a mensagem para incluir na fila
		EmailMessageDTO emailMessageDTO = new EmailMessageDTO();

		emailMessageDTO.setTo(usuario.getEmail());
		emailMessageDTO.setSubject("Parabéns " + usuario.getNome() + ", sua conta foi criada com sucesso!");
		emailMessageDTO
				.setBody("Olá, sua conta de usuário foi criada com sucesso em nosso sistema!<br/>Att,<br/>APIUsuários");

		try {

			// serializando a mensagem e enviando para a fila
			messageProducer.send(objectMapper.writeValueAsString(emailMessageDTO));

		} catch (JsonProcessingException ex) {

			ex.printStackTrace();

		}
		
		return response;

	}

	@Override
	public AutenticarResponseDTO autenticar(AutenticarDTO dto) {

		ModelMapper modelMapper = new ModelMapper();
		
		Usuario usuario = usuarioDomainService.autenticar(dto.getEmail(), dto.getSenha());
		
		AutenticarResponseDTO response = modelMapper.map(usuario, AutenticarResponseDTO.class);
		
		response.setMensagem("Usuário autenticado com sucesso.");
		
		return response;

	}

	@Override
	public RecuperarSenhaResponseDTO recuperarSenha(RecuperarSenhaDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
}
