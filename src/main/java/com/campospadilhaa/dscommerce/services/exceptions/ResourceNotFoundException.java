package com.campospadilhaa.dscommerce.services.exceptions;

// classe criada para controlar as exceções do sistema
public class ResourceNotFoundException extends RuntimeException {


	public ResourceNotFoundException(String mensagem) {

		super(mensagem);
	}
}