package br.com.cotiinformatica.api.handlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.cotiinformatica.application.dtos.ExceptionHandlerDTO;

@ControllerAdvice
public class IllegalArgumentExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ExceptionHandlerDTO handleIllegalArgumentException(IllegalArgumentException ex) {
	
		List<String> errors = new ArrayList<String>();
		errors.add(ex.getMessage());
		
		return new ExceptionHandlerDTO(HttpStatus.BAD_REQUEST, errors);		
	}
}
