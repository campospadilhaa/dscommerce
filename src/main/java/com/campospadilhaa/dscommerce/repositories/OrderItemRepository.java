package com.campospadilhaa.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campospadilhaa.dscommerce.entities.OrderItemPK;
import com.campospadilhaa.dscommerce.entities.Orderitem;

public interface OrderItemRepository extends JpaRepository<Orderitem, OrderItemPK> {

}