package com.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entitiy.User;
import com.ecommerce.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findFirstByEmail(String email);

	User findByRole(UserRole userRole);

}
