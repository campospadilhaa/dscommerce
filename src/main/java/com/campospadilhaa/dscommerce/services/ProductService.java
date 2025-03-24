package com.campospadilhaa.dscommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.campospadilhaa.dscommerce.dto.ProductDTO;
import com.campospadilhaa.dscommerce.dto.ProductMinDTO;
import com.campospadilhaa.dscommerce.entities.Product;
import com.campospadilhaa.dscommerce.repositories.ProductRepository;
import com.campospadilhaa.dscommerce.services.exceptions.DatabaseException;
import com.campospadilhaa.dscommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true) // configuração para lock de somente leitura, a transanção fica mais rápida
	public ProductDTO findById(Long id) {

		Optional<Product> optionalProduct = productRepository.findById(id);

		// get( substituído pelo orElseThrow() para controlar exceção. Interceptando a exceção do Optional e lançando a minha exceção 'Produto não encontrado'
		// Product product = optionalProduct.get();
		Product product = optionalProduct.orElseThrow(
				() -> new ResourceNotFoundException("Produto não encontrado"));

		ProductDTO productDTO = new ProductDTO(product);

		return productDTO;
	}

	@Transactional(readOnly = true) // configuração para lock de somente leitura, a transanção fica mais rápida
	public List<ProductDTO> findAll() {

		// objeto Page que retorna paginação
		List<Product> listaProduct = productRepository.findAll();

		// o lambda 'stream().map' cria cada 'item' da lista (listaProduct) como um 'ProductDTO'    
		return listaProduct.stream().map(item -> new ProductDTO(item)).toList();
	}

	@Transactional(readOnly = true) // configuração para lock de somente leitura, a transanção fica mais rápida
	public Page<ProductMinDTO> findAll(String name, Pageable pageable) {

		// objeto Page que retorna paginação
		//Page<Product> listaProduct = productRepository.findAll(pageable);
		Page<Product> listaProduct = productRepository.findByName(name, pageable);

		return listaProduct.map(item -> new ProductMinDTO(item));
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

		try {
			
			Product product = productRepository.getReferenceById(id); // método 'getReferenceById' não vai no banco de dados

			copyDtoToEntity(productDTO, product);

			product = productRepository.save(product);

			return new ProductDTO(product);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Produto não encontrado para atualização");
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {

		if (!productRepository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}

		try {
			productRepository.deleteById(id);    		
		}catch (DataIntegrityViolationException e) {
	        throw new DatabaseException("Falha de integridade referencial");
	   	}
	}
}