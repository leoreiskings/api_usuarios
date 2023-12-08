package br.com.cotiinformatica.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.domain.models.Usuario;

@Repository
public interface IUsuarioRepository extends MongoRepository<Usuario, String>{
	
	@Query("{email : ?0}") // email tem que ser o mesmo nome que está na entidade "usuario do domain.model"
	Optional<Usuario> findByEmail(String email); // o ttipo optional devolve 1 ou nenhum "usuario"
	
	@Query("{email : ?0, senha : ?1}") // a  virgula aqui representa o "and" para a query do mongodb 
	Optional<Usuario> findByEmailAndSenha(String email, String senha);
}
