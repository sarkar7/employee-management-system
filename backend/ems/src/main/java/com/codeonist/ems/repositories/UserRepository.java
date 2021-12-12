package com.codeonist.ems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeonist.ems.beans.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
