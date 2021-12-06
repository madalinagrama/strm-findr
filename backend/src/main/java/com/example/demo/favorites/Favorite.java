package com.example.demo.favorites;

import com.example.demo.appuser.AppUser;
import com.example.demo.movie.Movie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Favorite {
        @Id
        @SequenceGenerator(name = "favorite_sequence", sequenceName = "favorite_sequence", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favorite_sequence")
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        @JsonIgnore
        private AppUser user;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "movie_id")
        @JsonIgnore
        private Movie movie;
}
