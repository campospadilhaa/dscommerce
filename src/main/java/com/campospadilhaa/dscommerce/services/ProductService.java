package com.campospadilhaa.dscommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Transactional(readOnly = true) // configuração para lock de somente leitura, a transanção fica mais rápida
	public List<ProductDTO> findAll() {

		// objeto Page que retorna paginação
		List<Product> listaProduct = productRepository.findAll();

		return listaProduct.stream().map(item -> new ProductDTO(item)).toList();
	}

	@Transactional(readOnly = true) // configuração para lock de somente leitura, a transanção fica mais rápida
	public Page<ProductDTO> findAll(Pageable pageable) {

		// objeto Page que retorna paginação
		Page<Product> listaProduct = productRepository.findAll(pageable);

		return listaProduct.map(item -> new ProductDTO(item));
	}

	@Transactional
	public ProductDTO insert(ProductDTO productDTO) {

		Product product = new Product();

		copyDtoToEntity(productDTO, product);

		product = productRepository.save(product);

		return new ProductDTO(product);
	}

	private void copyDtoToEntity(ProductDTO productDTO, Product product) {

		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());
		product.setImgUrl(productDTO.getImgUrl());
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO productDTO) {

		Product product = productRepository.getReferenceById(id); // método 'getReferenceById' não vai no banco de dados

		copyDtoToEntity(productDTO, product);

		product = productRepository.save(product);

		return new ProductDTO(product);
	}

	@Transactional
	public void delete(Long id) {

		productRepository.deleteById(id);
	}
}