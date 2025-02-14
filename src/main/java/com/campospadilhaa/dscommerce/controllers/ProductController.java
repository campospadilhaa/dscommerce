package com.campospadilhaa.dscommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campospadilhaa.dscommerce.dto.ProductDTO;
import com.campospadilhaa.dscommerce.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/{id}")
	public ProductDTO findById(@PathVariable Long id) {

		ProductDTO productDTO = productService.findById(id);

		return productDTO;
	}
}