package com.campospadilhaa.dscommerce.dto;

import java.util.Objects;

import com.campospadilhaa.dscommerce.entities.Category;

public class CategoryDTO {

	private Long id;
	private String name;

	public CategoryDTO(Long id, String name) {

		this.id = id;
		this.name = name;
	}

	public CategoryDTO(Category category) {

		this.id = category.getId();
		this.name = category.getName();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		CategoryDTO other = (CategoryDTO) obj;

		return Objects.equals(id, other.id);
	}	
}