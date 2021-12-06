package com.example.demo.genres;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GenreConfig {

    @Bean
    CommandLineRunner commandLineRunner1(GenreRepository genreRepository) {

        return args -> {
            Genre genre1 = new Genre(
                    28L,
                    "Action"
            );
            Genre genre2 = new Genre(
                    7L,
                    "Adult"
            );
            Genre genre3 = new Genre(
                    12L,
                    "Adventure"
            );
            Genre genre4 = new Genre(
                    16L,
                    "Animation"
            );
            Genre genre5 = new Genre(
                    1L,
                    "Biography"
            );
            Genre genre6 = new Genre(
                    35L,
                    "Comedy"
            );
            Genre genre7 = new Genre(
                    80L,
                    "Crime"
            );
            Genre genre8 = new Genre(
                    99L,
                    "Documentary"
            );
            Genre genre9 = new Genre(
                    18L,
                    "Drama"
            );
            Genre genre10 = new Genre(
                    10751L,
                    "Family"
            );
            Genre genre11 = new Genre(
                    14L,
                    "Fantasy"
            );
            Genre genre12 = new Genre(
                    2L,
                    "Film Noir"
            );
            Genre genre13 = new Genre(
                    3L,
                    "Game Show"
            );
            Genre genre14 = new Genre(
                    36L,
                    "History"
            );
            Genre genre15 = new Genre(
                    27L,
                    "Horror"
            );
            Genre genre16 = new Genre(
                    10402L,
                    "Music"
            );
            Genre genre17 = new Genre(
                    4L,
                    "Musical"
            );
            Genre genre18 = new Genre(
                    9648L,
                    "Mystery"
            );
            Genre genre19 = new Genre(
                    10763L,
                    "News"
            );
            Genre genre20 = new Genre(
                    10764L,
                    "Reality"
            );
            Genre genre21 = new Genre(
                    10749L,
                    "Romance"
            );
            Genre genre22 = new Genre(
                    878L,
                    "Science Fiction"
            );
            Genre genre23 = new Genre(
                    6L,
                    "Short"
            );
            Genre genre24 = new Genre(
                    5L,
                    "Sport"
            );
            Genre genre25 = new Genre(
                    10767L,
                    "Talk Show"
            );
            Genre genre26 = new Genre(
                    53L,
                    "Thriller"
            );
            Genre genre27 = new Genre(
                    10752L,
                    "War"
            );
            Genre genre28 = new Genre(
                    37L,
                    "Western"
            );
            if (genreRepository.findAll().size() == 0) {
                genreRepository.saveAll(List.of(genre1, genre2, genre3, genre4, genre5, genre6, genre7, genre8, genre9, genre10, genre11, genre12, genre13, genre14, genre15, genre16, genre17, genre18, genre19, genre20, genre21, genre22, genre23, genre24, genre25, genre26, genre27, genre28));
            }
        };
    }
}
