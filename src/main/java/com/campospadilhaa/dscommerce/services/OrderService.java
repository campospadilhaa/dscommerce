package com.campospadilhaa.dscommerce.services;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campospadilhaa.dscommerce.dto.OrderDTO;
import com.campospadilhaa.dscommerce.dto.OrderItemDTO;
import com.campospadilhaa.dscommerce.entities.Order;
import com.campospadilhaa.dscommerce.entities.OrderStatus;
import com.campospadilhaa.dscommerce.entities.Orderitem;
import com.campospadilhaa.dscommerce.entities.Product;
import com.campospadilhaa.dscommerce.entities.User;
import com.campospadilhaa.dscommerce.repositories.OrderItemRepository;
import com.campospadilhaa.dscommerce.repositories.OrderRepository;
import com.campospadilhaa.dscommerce.repositories.ProductRepository;
import com.campospadilhaa.dscommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthService authService;

	@Transactional(readOnly = true) // configuração para lock de somente leitura, a transanção fica mais rápida
	public OrderDTO findById(Long id) {

		Optional<Order> optionalOrder = orderRepository.findById(id);

		// get( substituído pelo orElseThrow() para controlar exceção. Interceptando a exceção do Optional e lançando a minha exceção 'Pedido não encontrado'
		// Order order = optionalOrder.get();
		Order order = optionalOrder.orElseThrow(
				() -> new ResourceNotFoundException("Pedido não encontrado"));

		// é permitido acessar o pedido somente se o usuário for: ADMIN ou o DONO DO PEDIDO
		authService.validateSelfAdmin(order.getClient().getId());

		OrderDTO orderDTO = new OrderDTO(order);

		return orderDTO;
	}

	@Transactional
	public OrderDTO insert(OrderDTO orderDTO) {

		// instanciando o Pedido
		Order order = new Order();

		order.setMoment(Instant.now());
		order.setStatus(OrderStatus.WAITING_PAYMENT);

		// obtendo o usuário que está realizando o pedido
		User user = userService.authenticated();
		order.setClient(user);

		// instanciando a lista de itens do pedido
		for (OrderItemDTO orderItemDTO : orderDTO.getItems()) {

			// os itens do pedido possuem relacionadom com: Pedido e Produto

			// obtendo o produto
			Product product = productRepository.getReferenceById(orderItemDTO.getProductId());

			// instanciando o iten do pedido
			Orderitem orderitem = new Orderitem(order, product, orderItemDTO.getQuantity(), product.getPrice());

			// adicionando os itens ao pedido para gravação
			order.getOrderitems().add(orderitem);
		}

		// grava o pedido
		orderRepository.save(order);

		// grava os itens do pedido
		orderItemRepository.saveAll(order.getOrderitems());

		return new OrderDTO(order);
	}
}