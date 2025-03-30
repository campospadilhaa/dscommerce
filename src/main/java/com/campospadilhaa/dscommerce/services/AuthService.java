package com.campospadilhaa.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campospadilhaa.dscommerce.entities.User;
import com.campospadilhaa.dscommerce.services.exceptions.ForbiddenException;

@Service
public class AuthService {

	@Autowired
	private UserService userService;

	public void validateSelfAdmin(long userId) {

		// obtém o usuário logado
		User user = userService.authenticated();

		if(!user.hasRole("ROLE_ADMIN") && !user.getId().equals(userId)) {
			throw new ForbiddenException("Acesso negado!");
		}
	}
}