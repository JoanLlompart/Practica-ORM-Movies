package com.esliceu.movies.Services;

import com.esliceu.movies.Entities.Movie;
import com.esliceu.movies.Entities.MovieKeywords;
import com.esliceu.movies.Repos.MovieKeywordsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieKeywordsServices {
    @Autowired
    MovieKeywordsRepo movieKeywordsRepo;

    public void deleteByMovieId(Long movieId) {
        List<MovieKeywords> allMovieKeywords = movieKeywordsRepo.findAllByMovie_MovieId(movieId);
        movieKeywordsRepo.deleteAll(allMovieKeywords);
    }
}
