package com.campospadilhaa.dscommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campospadilhaa.dscommerce.dto.OrderDTO;
import com.campospadilhaa.dscommerce.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PreAuthorize("hasRole('ROLE_ADMIN')") // restrito para ADMIN
	// @PathVariable: parâmetro de rota, obrigatório
	@GetMapping(value = "/{id}")
	public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {

		OrderDTO orderDTO = orderService.findById(id);

		return ResponseEntity.ok( orderDTO ); // ResponseEntity retorna o status 200
	}
}