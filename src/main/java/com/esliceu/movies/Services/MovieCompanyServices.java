package com.esliceu.movies.Services;

import com.esliceu.movies.Repos.MovieCompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieCompanyServices {
    @Autowired
    MovieCompanyRepo movieCompanyRepo;
    public void deleteByMovieId(Long movieId) {

    }
}
