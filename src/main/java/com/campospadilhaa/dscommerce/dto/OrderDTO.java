package com.campospadilhaa.dscommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.campospadilhaa.dscommerce.entities.Order;
import com.campospadilhaa.dscommerce.entities.OrderStatus;

public class OrderDTO {

	private Long id;
	private Instant moment;
	private OrderStatus status;
	private ClientDTO clientDTO;
	private PaymentDTO paymentDTO;
	private List<OrderItemDTO> listaOrderItemDTO = new ArrayList<>();

	public OrderDTO(Long id, Instant moment, OrderStatus status, ClientDTO clientDTO, PaymentDTO paymentDTO) {

		this.id = id;
		this.moment = moment;
		this.status = status;
		this.clientDTO = clientDTO;
		this.paymentDTO = paymentDTO;
	}

	public OrderDTO(Order order) {

		this.id = order.getId();
		this.moment = order.getMoment();
		this.status = order.getStatus();

		ClientDTO clientDTO = new ClientDTO(order.getClient());
		this.clientDTO = clientDTO;

		PaymentDTO paymentDTO = null;
		if(order.getPayment()!=null) {
			paymentDTO = new PaymentDTO(order.getPayment());
		}
		this.paymentDTO = paymentDTO;
	}

	public Long getId() {
		return id;
	}

	public Instant getMoment() {
		return moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public ClientDTO getClientDTO() {
		return clientDTO;
	}

	public PaymentDTO getPaymentDTO() {
		return paymentDTO;
	}

	public List<OrderItemDTO> getListaOrderItemDTO() {
		return listaOrderItemDTO;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Double getTotal() {

		double total = 0.0;

		for (OrderItemDTO orderItemDTO : listaOrderItemDTO) {

			total += orderItemDTO.getSubTotal();
		}

		return total;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		OrderDTO other = (OrderDTO) obj;

		return Objects.equals(id, other.id);
	}
}