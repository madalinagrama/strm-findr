package com.example.demo.roles.repository;

import com.example.demo.roles.models.ERole;
import com.example.demo.roles.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
