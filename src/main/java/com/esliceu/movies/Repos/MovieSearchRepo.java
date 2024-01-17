package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.Movie;
import com.esliceu.movies.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieSearchRepo extends JpaRepository<Movie, Long> {
    List<Movie> findAll();
}
