package com.example.demo.favorites;

import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public Favorite addFavorites(Favorite favorite) {
        if (!favoriteRepository.existsByMovieIdAndUserId(favorite.getMovie().getId(), favorite.getUser().getId())) {
            favoriteRepository.save(favorite);
        }
        return favorite;
    }
}
