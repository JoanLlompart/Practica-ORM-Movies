package com.esliceu.movies.Services;

import com.esliceu.movies.Entities.MovieCrew;
import com.esliceu.movies.Entities.MovieLanguages;
import com.esliceu.movies.Repos.MovieCrewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieCrewServices {
    @Autowired
    MovieCrewRepo movieCrewRepo;

    public void deleteByMovieId(Long movieId) {
        List<MovieCrew> allMovieCrew = movieCrewRepo.findAllByMovie_MovieId(movieId);
        movieCrewRepo.deleteAll(allMovieCrew);
    }

    public void deleteByPersonId(Long personId) {
        List<MovieCrew> allPersonMovie = movieCrewRepo.findAllByPerson_PersonId(personId);
        movieCrewRepo.deleteAll(allPersonMovie);
    }
}
