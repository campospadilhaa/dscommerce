package com.campospadilhaa.dscommerce.dto;

import java.util.Objects;

import com.campospadilhaa.dscommerce.entities.User;

public class ClientDTO {

	private Long id;
	private String name;

	public ClientDTO(Long id, String name) {

		this.id = id;
		this.name = name;
	}

	public ClientDTO(User user) {

		this.id = user.getId();
		this.name = user.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

		ClientDTO other = (ClientDTO) obj;

		return Objects.equals(id, other.id);
	}	
}