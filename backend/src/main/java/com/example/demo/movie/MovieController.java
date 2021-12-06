package com.example.demo.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @CrossOrigin
    @GetMapping
    public List<Movie> getMovies(){
        return movieService.getMovies();
    }

    @CrossOrigin
    @GetMapping(path={"originalTitle"})
    public Optional<Movie> getMovie(@PathVariable("originalTitle") String originalTitle) {
        return movieService.getMovieByOriginalTitle(originalTitle);
    }
}
