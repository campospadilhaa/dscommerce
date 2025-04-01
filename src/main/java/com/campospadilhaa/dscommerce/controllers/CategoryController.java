package com.campospadilhaa.dscommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campospadilhaa.dscommerce.dto.CategoryDTO;
import com.campospadilhaa.dscommerce.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() { // parametro para gerar paginação

		List<CategoryDTO> listaCategoryDTO = categoryService.findAll();

		return ResponseEntity.ok( listaCategoryDTO ); // ResponseEntity retorna o status 200
	}
}