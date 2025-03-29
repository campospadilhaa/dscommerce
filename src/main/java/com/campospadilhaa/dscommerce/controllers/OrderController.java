package com.campospadilhaa.dscommerce.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.campospadilhaa.dscommerce.dto.OrderDTO;
import com.campospadilhaa.dscommerce.services.OrderService;

import jakarta.validation.Valid;

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

	@PreAuthorize("hasRole('ROLE_CLIENT')") // restrito para CLIENT
	@PostMapping
	public ResponseEntity<OrderDTO> insert(@Valid @RequestBody OrderDTO orderDTO) { // anotation '@Valid' considera as validações definidas no DTO

		orderDTO = orderService.insert(orderDTO);

		// a criação de uma URI faz com que no header do response conste a URL para a busca do Order
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orderDTO.getId()).toUri();

		// ResponseEntity com 'created' retorna o status 201 (created)
		return ResponseEntity.created(uri).body(orderDTO);
	}
}