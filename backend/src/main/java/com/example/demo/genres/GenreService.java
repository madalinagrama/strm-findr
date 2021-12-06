package com.example.demo.genres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    private List<Genre> genres = new ArrayList<>(Arrays.asList());

    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    public Optional<Genre> getGenre(String name){
        return genreRepository.findGenreByName(name);
    }
}
