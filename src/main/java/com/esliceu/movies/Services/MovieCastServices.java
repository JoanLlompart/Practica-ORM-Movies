package com.esliceu.movies.Services;

import com.esliceu.movies.DTO.ActorDTO;
import com.esliceu.movies.Entities.MovieCast;
import com.esliceu.movies.Entities.MovieLanguages;
import com.esliceu.movies.Entities.Person;
import com.esliceu.movies.Repos.MovieCastRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        List<Object[]>actorsInfo = movieCastRepo.findPersonAndCharacterAndMovieByPersonPersonNameContaining(keyword,pageable);
        List<ActorDTO> actorDTOList = new ArrayList<>();
        for (Object[] actor : actorsInfo) {
            String personName = (String) actor[0];
            String character = (String) actor[1];
            String title = (String) actor[2];

            //afegim el registre a actors
            actorDTOList.add(new ActorDTO(personName,character,title));
        }
        //todo
        return actorsInfo;
    }
}
