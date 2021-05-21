package com.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	public Role findByName(String name);
}
