package com.campospadilhaa.dscommerce.dto;

import com.campospadilhaa.dscommerce.entities.Orderitem;

public class OrderItemDTO {

	private Long productId;
	private String name;
	private Double price;
	private Integer quantity;
	private String imgUrl;

	public OrderItemDTO(Long productId, String name, Double price, Integer quantity, String imgUrl) {

		this.productId = productId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.imgUrl = imgUrl;
	}

	public OrderItemDTO(Orderitem orderitem) {

		this.productId = orderitem.getProduct().getId();
		this.name = orderitem.getProduct().getName();
		this.price = orderitem.getPrice();
		this.quantity = orderitem.getQuantity();
		this.imgUrl = orderitem.getProduct().getImgUrl();
	}

	public Long getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public Double getSubTotal() {
		return this.price * this.quantity;
	}
}