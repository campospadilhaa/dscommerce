package com.campospadilhaa.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campospadilhaa.dscommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}