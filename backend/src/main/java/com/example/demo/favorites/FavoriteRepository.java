package com.example.demo.favorites;

import com.example.demo.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> getAllByUser(AppUser appUser);

    boolean existsByMovieIdAndUserId(Long movie_id, Long user_id);

    @Modifying
    @Query(value = "DELETE FROM favorite WHERE movie_id= :movieId AND user_id= :userId", nativeQuery = true)
    void deleteByMovieIdAndAppUserId(Long movieId, Long userId);



}
