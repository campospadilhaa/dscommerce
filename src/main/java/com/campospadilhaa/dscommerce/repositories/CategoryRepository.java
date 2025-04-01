package com.campospadilhaa.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campospadilhaa.dscommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}