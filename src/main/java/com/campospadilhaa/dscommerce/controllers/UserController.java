package com.campospadilhaa.dscommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campospadilhaa.dscommerce.dto.UserDTO;
import com.campospadilhaa.dscommerce.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')") // identificação do usuário logado disponível para: 'ROLE_ADMIN', 'ROLE_CLIENT'
	@GetMapping(value = "/me")
	public ResponseEntity<UserDTO> getLogged() {

		UserDTO userDTO = userService.getUserLogged();

		return ResponseEntity.ok( userDTO ); // ResponseEntity retorna o status 200
	}
}