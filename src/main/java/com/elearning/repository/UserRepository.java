package com.elearning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.entity.Role;
import com.elearning.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {

	  Optional<User> findByEmail(String email);

	  Long countByRole(Role student);
}
