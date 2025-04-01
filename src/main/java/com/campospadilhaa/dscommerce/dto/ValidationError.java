package com.campospadilhaa.dscommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/*
 * classes: ValidationError e FieldMessage, criadas para formatar as mensagens emitidas pelas validações
 */

public class ValidationError extends CustomError {

	private List<FieldMessage> listaErros = new ArrayList<>();

	public ValidationError(Instant timestamp, Integer status, String error, String path) {
		super(timestamp, status, error, path);
	}

	public List<FieldMessage> getListaErros() {
		return listaErros;
	}

	public void addError(String fieldName, String message) {

		// para evitar duplicação de erro, remove eventuais menssagens que contenha o 'fieldName'
		listaErros.removeIf( itemmessage -> itemmessage.getFieldName().equals("fieldName") );

		listaErros.add(new FieldMessage(fieldName, message));
	}
}