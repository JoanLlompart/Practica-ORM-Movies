package com.esliceu.movies.Services;

import com.esliceu.movies.Entities.MovieLanguages;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieCrewServices {

    public void deleteByMovieId(Long movieId) {
        List<MovieLanguages> allMovieLanguages = movieLanguagesRepo.findAllByMovie_MovieId(movieId);
        movieLanguagesRepo.deleteAll(allMovieLanguages);
    }
}
