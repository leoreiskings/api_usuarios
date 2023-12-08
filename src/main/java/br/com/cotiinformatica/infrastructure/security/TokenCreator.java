package br.com.cotiinformatica.infrastructure.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenCreator {

	@Value("${jwt.secret}")
	private String jwtSecret;

	public String generateToken(String userName) { 
		
		return Jwts.builder() 
				.setSubject(userName) //nome do usuário 
				.setIssuedAt(new Date()) //data de geração 
				.signWith(SignatureAlgorithm.HS256, jwtSecret) //chave antifalsificação 
				.compact(); 
		}
	}
	