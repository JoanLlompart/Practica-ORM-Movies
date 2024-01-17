package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.Movie;
import com.esliceu.movies.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieSearchRepo extends JpaRepository<Movie, Long> {
    List<Movie> findAll();

    //Per paginar les pagines
    Page<Movie> findAll(Pageable pageable);
}
