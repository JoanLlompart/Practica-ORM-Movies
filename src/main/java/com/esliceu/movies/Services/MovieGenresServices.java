package com.esliceu.movies.Services;

import com.esliceu.movies.Entities.MovieGenres;
import com.esliceu.movies.Entities.MovieLanguages;
import com.esliceu.movies.Repos.MovieGenresRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieGenresServices {
@Autowired
    MovieGenresRepo movieGenresRepo;
    public void deleteByMovieId(Long movieId) {
        List<MovieGenres> allMovieLanguages = movieGenresRepo.findAllByMovie_MovieId(movieId);
        movieGenresRepo.deleteAll(allMovieLanguages);
    }
}
