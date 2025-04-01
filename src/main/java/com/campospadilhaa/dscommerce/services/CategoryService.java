package com.campospadilhaa.dscommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campospadilhaa.dscommerce.dto.CategoryDTO;
import com.campospadilhaa.dscommerce.entities.Category;
import com.campospadilhaa.dscommerce.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true) // configuração para lock de somente leitura, a transanção fica mais rápida
	public List<CategoryDTO> findAll() {

		List<Category> listaCategory = categoryRepository.findAll();

		return listaCategory.stream().map( category -> new CategoryDTO(category) ).toList();
	}
}