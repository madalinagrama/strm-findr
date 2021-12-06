package com.example.demo.genres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, String> {

    @Query("SELECT g FROM Genre g WHERE g.name = ?1")
    Optional<Genre> findGenreByName(String name);
}
