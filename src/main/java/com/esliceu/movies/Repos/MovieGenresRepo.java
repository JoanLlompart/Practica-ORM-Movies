package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.MovieGenres;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieGenresRepo extends JpaRepository<MovieGenres, Long> {
    List<MovieGenres> findAllByMovie_MovieId(Long movieId);
}
