package com.example.demo.favorites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@AllArgsConstructor
public class FavoriteDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("movie_id")
    private Long movieId;

    public FavoriteDto() {
    }
}
