package com.campospadilhaa.dscommerce.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(unique = true) // definição do campo email como único
	private String email;

	private String phone;
	private LocalDate birthDate;
	private String password;

    // relacionamento muitos para muitos não retorna a lista de roles, relacionametno LAZY
    // para forçar este retorno é possível configurar o relacionamento com FetchType.EAGER
    // porém, não é uma boa prática, isto porque nem sempre desejaremos o User com os seus roles
    //@ManyToMany(fetch = FetchType.EAGER)
    @ManyToMany
    @JoinTable(name = "tb_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>();

	public User() {

	}

	public User(Long id, String name, String email, String phone, LocalDate birthDate, String password) {

		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.birthDate = birthDate;
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
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

		User other = (User) obj;

		return Objects.equals(id, other.id);
	}

	public Set<Role> getRoles() {
		return roles;
	}

    // método para adicionar permissões
    public void addRole(Role role) {
    	roles.add(role);
    }

    // método para verificação se o usuário possui determinado Role passado por argumento
    public boolean hasRole(String roleName) {

    	for (Role role : roles) {
			if(role.getAuthority().equals(roleName)) {
				return true;
			}
		}

    	return false;
    }
}