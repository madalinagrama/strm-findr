package com.example.demo.favorites;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.movie.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@AllArgsConstructor
@RequestMapping(path = "/user/{userId}/favorites")
@Slf4j
public class FavoriteController {

    private final FavoriteRepository favoriteRepository;
    private final AppUserRepository appUserRepository;
    private final MovieRepository movieRepository;
    private final FavoriteService favoriteService;

    @GetMapping
    public int[] getAllFavoritesByUserId(@PathVariable Long userId, FavoriteDto favoriteDto){
        AppUser appUser = appUserRepository.findById(userId).get();
        List<Favorite> favorites = favoriteRepository.getAllByUser(appUser);
        int[] results = new int[favorites.size()];
        for (int i = 0; i < favorites.size(); i++) {
            results[i] = Integer.parseInt(String.valueOf(favorites.get(i).getMovie().getId()));
        }
        log.info(String.valueOf(results));
        return results;
    }

    @PostMapping(path = "/")
    public ResponseEntity<?> addFavorites(@RequestBody FavoriteDto favoriteDto) {
        Favorite favorite = new Favorite();
        log.info(String.valueOf(favoriteDto));
        favorite.setUser(appUserRepository.findById(favoriteDto.getUserId()).get());
        log.info(String.valueOf(favoriteDto.getMovieId()));
        favorite.setMovie(movieRepository.findById(favoriteDto.getMovieId()).get());

        favoriteService.addFavorites(favorite);

        return ResponseEntity.ok("Movie added to database");
    }

    @Transactional
    @DeleteMapping(path = "/{movieId}")
    public ResponseEntity<?> deleteFavoriteById(@PathVariable(name = "movieId") Long movieId, @PathVariable(name = "userId") Long userId) {
        favoriteRepository.deleteByMovieIdAndAppUserId(movieId, userId);
        return ResponseEntity.ok("Movie with id " + movieId + " removed from favorites");
    }
}
