package com.campospadilhaa.dscommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.campospadilhaa.dscommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p " +
		   "WHERE UPPER(p.name) LIKE UPPER( CONCAT('%', :name, '%') )")
	// retorna um 'Page'de product's porque se trata de uma lista paginada
	// quando se cria um método customizado cujo o último parametro é "Pageable" é retornada uma consulta paginada
	Page<Product> findByName(String name, Pageable pageable);
}