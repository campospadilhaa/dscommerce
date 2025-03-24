package com.campospadilhaa.dscommerce.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.campospadilhaa.dscommerce.dto.ProductDTO;
import com.campospadilhaa.dscommerce.dto.ProductMinDTO;
import com.campospadilhaa.dscommerce.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	/* exceção lançada através dos controles try_catch
	 * substituído pelo método abaixo que utiliza ControllerAdvice, não é necessário o bloco try_catch 
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {

		try {
			ProductDTO productDTO = productService.findById(id);

			return ResponseEntity.ok( productDTO ); // ResponseEntity retorna o status 200
			
		} catch (ResourceNotFoundException e) {

			CustomError error = new CustomError(Instant.now(), 404, e.getMessage(), "caminho");

			return ResponseEntity.status(404).body(error);
		}
	}*/

	// @PathVariable: parâmetro de rota, obrigatório
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {

		ProductDTO productDTO = productService.findById(id);

		return ResponseEntity.ok( productDTO ); // ResponseEntity retorna o status 200
	}

	/* substituído pelo método abaixo mais completo, com a possibilidade de aplicar paginação
	@GetMapping
	public List<ProductDTO> findAll() {

		List<ProductDTO> listaProductDTO = productService.findAll();

		return listaProductDTO;
	}*/

	//@RequestParam: parâmetro de requisição (consulta), opcional
	// http://localhost:8080/products?size=12&page=0&sort=name,asc
	// size: quantidade de itens retornados por página
	// page: o número da página que deverá ser exibida
	// sort: ordenação + asc/desc
	@GetMapping
	public ResponseEntity<Page<ProductMinDTO>> findAll(@RequestParam(name = "name", defaultValue = "") String name, Pageable pageable) { // parametro para gerar paginação

		Page<ProductMinDTO> listaProductMinDTO = productService.findAll(name, pageable);

		return ResponseEntity.ok( listaProductMinDTO ); // ResponseEntity retorna o status 200
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')") // restrito para ADMIN
	@PostMapping
	public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO productDTO) { // anotation '@Valid' considera as validações definidas no DTO

		productDTO = productService.insert(productDTO);

		// a criação de uma URI faz com que no header do response conste a URL para a busca do Product
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(productDTO.getId()).toUri();

		// ResponseEntity com 'created' retorna o status 201 (created)
		return ResponseEntity.created(uri).body(productDTO);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')") // restrito para ADMIN
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {

		productDTO = productService.update(id, productDTO);

		return ResponseEntity.ok( productDTO ); // ResponseEntity retorna o status 200
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')") // restrito para ADMIN
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		productService.delete(id);

		return ResponseEntity.noContent().build(); // ResponseEntity retorna o status 204, quando não há corpo no response
	}
}