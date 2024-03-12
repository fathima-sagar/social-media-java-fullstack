package com.org.social.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.org.social.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByEmailId(String email);

	@Query("select u from User u where u.firstName LIKE %:query% OR u.lastName LIKE %:query% OR u.emailId LIKE %:query%")
	List<User> searchUser(String query);
}
