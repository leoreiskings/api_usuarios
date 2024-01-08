package br.com.cotiinformatica;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import br.com.cotiinformatica.application.dtos.AtualizarDadosDTO;
import br.com.cotiinformatica.application.dtos.AutenticarDTO;
import br.com.cotiinformatica.application.dtos.AutenticarResponseDTO;
import br.com.cotiinformatica.application.dtos.CriarContaDTO;
import br.com.cotiinformatica.application.dtos.RecuperarSenhaDTO;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApiUsuariosApplicationTests {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ObjectMapper objectMapper;

	// criando atributos estaticos para capturar email e senha
	private static String id;
	private static String email;
	private static String senha;
	private static String accessToken;

	@Test
	@Order(1)
	public void criarContaTest() throws Exception {

		CriarContaDTO dto = new CriarContaDTO();
		Faker faker = new Faker();

		
		 dto.setNome(faker.name().fullName());
		 dto.setEmail(faker.internet().emailAddress());		
		 dto.setSenha("@Admin123");

		mock.perform(post("/api/usuarios/criar-conta").contentType("application/json") // passando um dado do tipo json
				.content(objectMapper.writeValueAsString(dto))).andExpect(status().isCreated()); // status esperado

		email = dto.getEmail();
		senha = dto.getSenha();
	}

	@Test
	@Order(2)
	public void autenticarTest() throws Exception {

		AutenticarDTO dto = new AutenticarDTO();

		// usando o email e senha capturados no teste de criacao de conta.
		// estes mesmos dados fake serao usados para autenticar logo abaixo
		dto.setEmail(email);
		dto.setSenha(senha);

		MvcResult result = mock.perform(post("/api/usuarios/autenticar")
								.contentType("application/json") // passando um dado do tipo json
								.content(objectMapper.writeValueAsString(dto)))
								.andExpect(status()
								.isOk())
								.andReturn();

		String content = result.getResponse().getContentAsString();

		// deserializando e guardando os valores nas variaveis id, accessToken
		AutenticarResponseDTO response = objectMapper.readValue(content, AutenticarResponseDTO.class);

		id = response.getId();
		accessToken = response.getAccessToken();

	}

	@Test
	@Order(3)
	public void atualizarDadosTest() throws Exception {

		AtualizarDadosDTO dto = new AtualizarDadosDTO();
		Faker faker = new Faker();
		
		dto.setId(id);
		dto.setNome(faker.name().fullName());
		dto.setSenha("@Teste1234");
		
		mock.perform(put("/api/usuarios/atualizar-dados")
			.header("Authorization", "Bearer " + accessToken) //pegando o token do header
			.contentType("application/json")
			.content(objectMapper.writeValueAsString(dto)))
			.andExpect(status()
			.isOk());
	}

	@Test
	@Order(4)
	public void recuperarSenhaTest() throws Exception {
		
		RecuperarSenhaDTO dto = new RecuperarSenhaDTO();
		
		dto.setEmail(email);
		
		mock.perform(post("/api/usuarios/recuperar-senha")
			.contentType("application/json")
			.content(objectMapper.writeValueAsString(dto)))
			.andExpect(status()
			.isOk());
	}

}
