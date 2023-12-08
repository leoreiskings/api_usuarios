package br.com.cotiinformatica.application.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CriarContaDTO {
	
	@Size(min = 8, max = 150, message = "Nome do usuário deve ter de 8 a 150 caracteres.")
	@NotBlank(message = "Nome do usuário é obrigatório.")
	private String nome;
	
	@Email(message = "Informe um endereço de email válido.")
	@NotBlank(message = "Email do usuário é obrigatório.")
	private String email;
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
			 message = "A senha deve ter pelo menos 8 caracteres, uma letra maiúscula, uma letra minúscula, um número e um caractere especial")
	@NotBlank(message = "Senha do usuário é obrigatória.")
	private String senha;

	
}
