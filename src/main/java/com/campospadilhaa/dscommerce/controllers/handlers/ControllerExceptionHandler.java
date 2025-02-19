package com.campospadilhaa.dscommerce.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.campospadilhaa.dscommerce.dto.CustomError;
import com.campospadilhaa.dscommerce.dto.ValidationError;
import com.campospadilhaa.dscommerce.services.exceptions.DatabaseException;
import com.campospadilhaa.dscommerce.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

/*
 * O console deverá ser consultado para identificar qual a exceção emitida pelo Spring, no caso dos exemplos que estamos trabalhando, se:
 * ResourceNotFoundException, ResourceDatabaseException ou MethodArgumentNotValidException 
 */

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class) // configurada a classe de exceção criada: ResourceNotFoundException
	public ResponseEntity<CustomError> resourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;

		CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DatabaseException.class) // configurada a classe de exceção criada: ResourceDatabaseException
	public ResponseEntity<CustomError> databaseException(DatabaseException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;

		CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class) // configurada a classe de exceção criada: MethodArgumentNotValidException
	public ResponseEntity<CustomError> validationException(MethodArgumentNotValidException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

		//CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
		ValidationError err = new ValidationError(Instant.now(), status.value(), "Dados inválidos", request.getRequestURI());

		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {

			err.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}

		return ResponseEntity.status(status).body(err);
	}
}