package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.MovieCast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieCastRepo extends JpaRepository<MovieCast,Long> {
    List<MovieCast> findAllByMovie_MovieId(Long movieId);
}
