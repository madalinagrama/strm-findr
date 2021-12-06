package com.example.demo.movie;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    private List<Movie> movies = new ArrayList<>(Arrays.asList());

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieByOriginalTitle(String originalTitle) {
        return movieRepository.findMovieByOriginalTitle(originalTitle);
    }
}
