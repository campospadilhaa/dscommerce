package com.campospadilhaa.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campospadilhaa.dscommerce.dto.ProductDTO;
import com.campospadilhaa.dscommerce.entities.Product;
import com.campospadilhaa.dscommerce.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true) // configuração para lock de somente leitura, a transanção fica mais rápida
	public ProductDTO findById(Long id) {

		Optional<Product> optionalProduct = productRepository.findById(id);

		Product product = optionalProduct.get();

		ProductDTO productDTO = new ProductDTO(product);

		return productDTO;
	}
}