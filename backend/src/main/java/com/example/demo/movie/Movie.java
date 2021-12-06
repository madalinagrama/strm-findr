package com.example.demo.movie;

import com.example.demo.favorites.Favorite;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movie_sequence"
    )
    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    private Long id;
    private String imdbID;
    private String originalTitle;
    private int genres;
    private String countries;
    private String overview;
    private String posterURL;
    private String streamingInfo;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Favorite> favorites;

    public Movie(String imdbID, String originalTitle, int genres, String countries, String overview, String posterURL, String streamingInfo) {
        this.imdbID = imdbID;
        this.originalTitle = originalTitle;
        this.genres = genres;
        this.countries = countries;
        this.overview = overview;
        this.posterURL = posterURL;
        this.streamingInfo = streamingInfo;
    }
}
