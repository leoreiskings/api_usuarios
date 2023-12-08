package br.com.cotiinformatica.api.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cotiinformatica.infrastructure.security.JwtAuthenticationFilter;

@Configuration
public class JwtConfig {

	@Bean 
	public FilterRegistrationBean <JwtAuthenticationFilter> jwtFilter() { 
		FilterRegistrationBean<JwtAuthenticationFilter> filter = new FilterRegistrationBean <JwtAuthenticationFilter>(); 
		filter.setFilter(new JwtAuthenticationFilter()); 
		
		// mapear os endpoints da API que precisam de autenticação 
		filter.addUrlPatterns("/api/usuarios/atualizar-dados");		
		return filter; 
		
		}
}
	
	
	

