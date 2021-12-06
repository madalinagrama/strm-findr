package com.example.demo.genres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @CrossOrigin
    @GetMapping
    public List<Genre> getGenres() {
        return genreService.getGenres();
    }

    @CrossOrigin
    @GetMapping(path = "{name}")
    public Optional<Genre> getGenre(@PathVariable("name") String name) {
        return genreService.getGenre(name);
    }
}
