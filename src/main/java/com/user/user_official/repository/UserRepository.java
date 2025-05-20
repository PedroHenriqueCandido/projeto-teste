package com.user.user_official.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.user_official.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);

}
