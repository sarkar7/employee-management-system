package com.codeonist.ems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeonist.ems.beans.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
