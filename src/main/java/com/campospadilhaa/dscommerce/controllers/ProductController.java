package com.campospadilhaa.dscommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	/* substituído pelo método abaixo mais completo, com a possibilidade de aplicar paginação
	@GetMapping
	public List<ProductDTO> findAll() {

		List<ProductDTO> listaProductDTO = productService.findAll();

		return listaProductDTO;
	}*/

	// http://localhost:8080/products?size=12&page=0&sort=name,asc
	// size: quantidade de itens retornados por página
	// page: o número da página que deverá ser exibida
	// sort: ordenação + asc/desc
	@GetMapping
	public Page<ProductDTO> findAll(Pageable pageable) { // parametro para gerar paginação

		Page<ProductDTO> listaProductDTO = productService.findAll(pageable);

		return listaProductDTO;
	}

	@PostMapping
	public ProductDTO insert(@RequestBody ProductDTO productDTO) {

		productDTO = productService.insert(productDTO);

		return productDTO;
	}
}