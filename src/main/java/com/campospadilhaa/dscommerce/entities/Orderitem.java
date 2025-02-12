package com.campospadilhaa.dscommerce.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class Orderitem {

	@EmbeddedId
	private OrderItemPK id = new OrderItemPK(); // necessario instanciar o atributo quando representação de chave PK

	private Integer quantity;
	private Double price;

	public Orderitem() {

	}

	public Orderitem(Order order, Product product, Integer quantity, Double price) {

		// atribuição manual dos atributos para configurar a PK
		id.setOrder(order);
		id.setProduct(product);

		this.quantity = quantity;
		this.price = price;
	}

	//// manipulação dos atributos que configuram a PK
	// busca manual dos atributos que configuram PK
	public Order getOrder() {
		return id.getOrder();
	}

	// atribuição manual dos atributos para configurar a PK
	public void setOrder(Order order) {
		id.setOrder(order);
	}

	// busca manual dos atributos que configuram PK
	public Product getProduct() {
		return id.getProduct();
	}

	// atribuição manual dos atributos para configurar a PK
	public void setProduct(Product product) {
		id.setProduct(product);
	}
	////

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}	
}