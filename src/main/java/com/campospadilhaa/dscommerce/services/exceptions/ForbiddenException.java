package com.campospadilhaa.dscommerce.services.exceptions;

// classe criada para controlar as exceções do sistema
public class ForbiddenException extends RuntimeException {

	public ForbiddenException(String mensagem) {

		super(mensagem);
	}
}