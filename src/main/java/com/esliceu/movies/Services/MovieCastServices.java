package com.esliceu.movies.Services;

import com.esliceu.movies.Entities.MovieCast;
import com.esliceu.movies.Entities.MovieLanguages;
import com.esliceu.movies.Repos.MovieCastRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieCastServices {
    @Autowired
    MovieCastRepo movieCastRepo;
    public void deleteByMovieId(Long movieId) {
        List<MovieCast> allMovieCast = movieCastRepo.findAllByMovie_MovieId(movieId);
        movieCastRepo.deleteAll(allMovieCast);
    }
}