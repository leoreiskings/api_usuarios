package br.com.cotiinformatica;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import br.com.cotiinformatica.application.dtos.AutenticarDTO;
import br.com.cotiinformatica.application.dtos.CriarContaDTO;
 

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApiUsuariosApplicationTests {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ObjectMapper objectMapper;

	private static String email;
	private static String senha;
	
	
	
	@Test
	@Order(1)
	public void criarContaTest() throws Exception {

		CriarContaDTO dto = new CriarContaDTO();
		Faker faker = new Faker();
		dto.setNome(faker.name().fullName());
		dto.setEmail(faker.internet().emailAddress());
		dto.setSenha("@Teste1234");
		
		mock.perform(post("/api/usuarios/criar-conta")
				.contentType("application/json") // passando um dado do tipo json
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status()
				.isCreated()); // status esperado
		
		email = dto.getEmail();
		senha = dto.getSenha();
	}

	@Test
	@Order(2)
	public void autenticarTest() throws Exception {
		
		AutenticarDTO dto = new AutenticarDTO();		
		
		dto.setEmail(email);
		dto.setSenha(senha);
		
		mock.perform(post("/api/usuarios/autenticar")
				.contentType("application/json") // passando um dado do tipo json
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status()
				.isOk()); // status esperado
	}

	@Test
	@Order(3)
	public void atualizarDadosTest() throws Exception {
		fail("Não implementado.");

	}

	@Test
	@Order(4)
	public void recuperarSenhaTest() throws Exception {
		fail("Não implementado.");

	}

}
