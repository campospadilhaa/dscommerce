package com.campospadilhaa.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campospadilhaa.dscommerce.dto.OrderDTO;
import com.campospadilhaa.dscommerce.entities.Order;
import com.campospadilhaa.dscommerce.repositories.OrderRepository;
import com.campospadilhaa.dscommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Transactional(readOnly = true) // configuração para lock de somente leitura, a transanção fica mais rápida
	public OrderDTO findById(Long id) {

		Optional<Order> optionalOrder = orderRepository.findById(id);

		// get( substituído pelo orElseThrow() para controlar exceção. Interceptando a exceção do Optional e lançando a minha exceção 'Pedido não encontrado'
		// Order order = optionalOrder.get();
		Order order = optionalOrder.orElseThrow(
				() -> new ResourceNotFoundException("Pedido não encontrado"));

		OrderDTO orderDTO = new OrderDTO(order);

		return orderDTO;
	}
}