package com.esliceu.movies.Services;

import com.esliceu.movies.DTO.ActorDTO;
import com.esliceu.movies.DTO.ActorsMovieDTO;
import com.esliceu.movies.Entities.Gender;
import com.esliceu.movies.Entities.Movie;
import com.esliceu.movies.Entities.MovieCast;
import com.esliceu.movies.Entities.Person;
import com.esliceu.movies.Repos.MovieCastRepo;
import com.esliceu.movies.Repos.MovieSearchRepo;
import com.esliceu.movies.Repos.PersonRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MovieCastServices {
    @Autowired
    MovieCastRepo movieCastRepo;
    @Autowired
    PersonRepo personRepo;
    @Autowired
    MovieSearchRepo movieSearchRepo;



    public void deleteByMovieId(Long movieId) {
        List<MovieCast> allMovieCast = movieCastRepo.findAllByMovie_MovieId(movieId);
        movieCastRepo.deleteAll(allMovieCast);
    }

  /*  public List<ActorDTO> getActorsByMovieId(Long movieId) {
        return movieCastRepo.findAllActorsByMovieId(movieId);
    }
    
   */

    public List<ActorsMovieDTO> getActorsByMovieId(Long movieId) {
        List<MovieCast> actorsInMovie= movieCastRepo.findByMovie_MovieId(movieId);
        List<ActorsMovieDTO> actorsDTO = new ArrayList<>();
        for (MovieCast movieCast : actorsInMovie) {
            String personName =movieCast.getPerson().getPersonName();
            String gender = movieCast.getGender().getGender();
            String characterName = movieCast.getCharacterName();
            Long personId = movieCast.getPerson().getPersonId();
            //Ficam en el DTO
            actorsDTO.add(new ActorsMovieDTO(personName,gender,characterName,personId));
        }
        return actorsDTO;
    }


    public List<ActorDTO> filterByActor(String keyword, Pageable pageable) {

        List<MovieCast> actorMoviesList = movieCastRepo.findAllByPerson_PersonNameContaining(keyword,pageable);
        List<ActorDTO> jobActor = new ArrayList<>();
        for (MovieCast movieCast: actorMoviesList) {
            String personName = movieCast.getPerson().getPersonName();
            String characterName = movieCast.getCharacterName();
            String title = movieCast.getMovie().getTitle();

            //Ficam dins el record de ActorDTO
            jobActor.add(new ActorDTO(personName,characterName,title));
        }
        return jobActor;
    }


    public void deleteByPersonId(Long personId) {
        List<MovieCast> allPersonsMovie = movieCastRepo.findAllByPerson_PersonId(personId);
        movieCastRepo.deleteAll(allPersonsMovie);
    }

    @Transactional
    public String decastActor(Map<String, String> data) {
        Long movieId = Long.valueOf(data.get("movieId"));
        Long personId = Long.valueOf(data.get("actorId"));

       /* if (movieCastRepo.deleteByMovie_MovieIdAndPerson_PersonId(movieId, personId)) {
            return "Actor decast successfully";
        }else {
            return "Actor decast failed";
        }
        */
        movieCastRepo.deleteByMovie_MovieIdAndPerson_PersonId(movieId,personId);
        return "Actor decast successfully";
    }

    @Transactional
    public String castActor(Map<String, String> data) {
        Long movieId = Long.valueOf(data.get("movieId"));
        Long personId = Long.valueOf(data.get("personId"));
        String characterName = data.get("characterName");

        // Pas 1:  Recuperar la persona amb el id
        Person person = personRepo.findById(personId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + personId));

        // Pas 2: Recuperar la pelicula amb el id
        Movie movie = movieSearchRepo.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Película no encontrada con ID: " + movieId));

        //int castOrder =movieCastRepo.findFirstByMovie_MovieIdOrderByCastOrderDesc(movieId);
        //int castOrder = movieCastRepo.findMaxCastOrderForMovie(movieId);
        Integer maxCastOrder = findMaxCastOrderForMovie(movieId);
        int castOrder = maxCastOrder+1;
        System.out.println("Castorder " +castOrder);
        MovieCast movieCast = new MovieCast();
        //Gender gender = new Gender();
       //gender.setGenderId(2L);
        //gender.setGender("Male");

        movieCast.setPerson(person);
        movieCast.setMovie(movie);
        movieCast.setCharacterName(characterName);
        movieCast.setCastOrder(castOrder);

        movieCastRepo.save(movieCast);
        return "Person assigned to the movie successfully";
    }

    public int findMaxCastOrderForMovie(Long movieId) {
        Integer maxCastOrder = movieCastRepo.findMaxCastOrderForMovie(movieId);
        return maxCastOrder != null ? maxCastOrder : 0;
    }
}
