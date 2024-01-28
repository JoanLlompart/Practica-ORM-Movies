package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.MovieKeywords;
import com.esliceu.movies.Entities.MovieLanguages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieKeywordsRepo  extends JpaRepository<MovieKeywords, Long> {
    List<MovieKeywords> findAllByMovie_MovieId(Long movieId);
}
