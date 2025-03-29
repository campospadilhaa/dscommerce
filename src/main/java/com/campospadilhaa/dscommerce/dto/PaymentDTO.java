package com.campospadilhaa.dscommerce.dto;

import java.time.Instant;
import java.util.Objects;

import com.campospadilhaa.dscommerce.entities.Payment;

public class PaymentDTO {

	private Long id;
	private Instant moment;

	public PaymentDTO(Long id, Instant moment) {

		this.id = id;
		this.moment = moment;
	}

	public PaymentDTO(Payment payment) {

		this.id = payment.getId();
		this.moment = payment.getMoment();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		PaymentDTO other = (PaymentDTO) obj;

		return Objects.equals(id, other.id);
	}
}