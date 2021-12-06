package com.example.demo.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findAppUserByEmail(String email);
    Optional<AppUser> findByUsername(String username);
    AppUser findUserByUsername(String username);
    AppUser findUserByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    boolean existsById(Long id);
}
