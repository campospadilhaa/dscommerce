package com.campospadilhaa.dscommerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.campospadilhaa.dscommerce.entities.User;
import com.campospadilhaa.dscommerce.projections.UserDetailsProjection;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// Query Methods, a execuçãod a query é realizada de acordo com o nome do método 
	// User findByEmail(String email);

	@Query(nativeQuery = true, value = """
			SELECT tb_user.email AS username, tb_user.password, tb_role.id AS roleId, tb_role.authority
			FROM tb_user
			INNER JOIN tb_user_role ON tb_user.id = tb_user_role.user_id
			INNER JOIN tb_role ON tb_role.id = tb_user_role.role_id
			WHERE tb_user.email = :email
		""")
	List<UserDetailsProjection> searchUserAndRolesByEmail(String email);

	// se não existir retorna um 'Optional' vazio
	Optional<User> findByEmail(String email);
}