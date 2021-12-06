package com.example.demo.genres;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Genre {

    @Id
    private Long id;
    private String name;

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
