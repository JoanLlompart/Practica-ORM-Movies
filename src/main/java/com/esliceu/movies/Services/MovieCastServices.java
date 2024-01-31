package com.esliceu.movies.Services;

import com.esliceu.movies.DTO.ActorDTO;
import com.esliceu.movies.Entities.MovieCast;
import com.esliceu.movies.Entities.MovieLanguages;
import com.esliceu.movies.Entities.Person;
import com.esliceu.movies.Repos.MovieCastRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MovieCastServices {
    @Autowired
    MovieCastRepo movieCastRepo;



    public void deleteByMovieId(Long movieId) {
        List<MovieCast> allMovieCast = movieCastRepo.findAllByMovie_MovieId(movieId);
        movieCastRepo.deleteAll(allMovieCast);
    }

    public List<ActorDTO> getActorsByMovieId(Long movieId) {
        return movieCastRepo.findAllActorsByMovieId(movieId);
    }

    public List<?> filterByActor(String keyword, Pageable pageable) {
        return movieCastRepo.findPersonAndCharacterAndMovieByPersonPersonNameContaining(keyword,pageable);
    }
}
