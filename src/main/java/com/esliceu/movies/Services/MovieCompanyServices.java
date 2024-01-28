package com.esliceu.movies.Services;

import com.esliceu.movies.Entities.MovieCompany;
import com.esliceu.movies.Entities.MovieLanguages;
import com.esliceu.movies.Repos.MovieCompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieCompanyServices {
    @Autowired
    MovieCompanyRepo movieCompanyRepo;
    public void deleteByMovieId(Long movieId) {
        List<MovieCompany> allMovieCompany = movieCompanyRepo.findAllByMovie_MovieId(movieId);
        movieCompanyRepo.deleteAll(allMovieCompany);
    }
}
