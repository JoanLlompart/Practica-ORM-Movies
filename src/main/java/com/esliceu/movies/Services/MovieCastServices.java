package com.esliceu.movies.Services;

import com.esliceu.movies.DTO.ActorDTO;
import com.esliceu.movies.DTO.ActorsMovieDTO;
import com.esliceu.movies.Entities.Gender;
import com.esliceu.movies.Entities.Movie;
import com.esliceu.movies.Entities.MovieCast;
import com.esliceu.movies.Entities.Person;
import com.esliceu.movies.Repos.GenderRepo;
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
    @Autowired
    GenderRepo genderRepo;

    public void deleteByMovieId(Long movieId) {
        List<MovieCast> allMovieCast = movieCastRepo.findAllByMovie_MovieId(movieId);
        movieCastRepo.deleteAll(allMovieCast);
    }

    public List<ActorsMovieDTO> getActorsByMovieId(Long movieId) {
        List<MovieCast> actorsInMovie= movieCastRepo.findByMovie_MovieId(movieId);
        List<ActorsMovieDTO> actorsDTO = new ArrayList<>();
        for (MovieCast movieCast : actorsInMovie) {
            String personName =movieCast.getPerson().getPersonName();
            String gender = movieCast.getGender().getGender();
            Long genderId = movieCast.getGender().getGenderId();
            String characterName = movieCast.getCharacterName();
            Long personId = movieCast.getPerson().getPersonId();
            //Ficam en el DTO
            actorsDTO.add(new ActorsMovieDTO(personName,gender,characterName,personId,genderId));
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
            Long personId = movieCast.getPerson().getPersonId();

            //Ficam dins el record de ActorDTO
            jobActor.add(new ActorDTO(personId,personName,characterName,title));
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
        Long genderId = Long.valueOf(data.get("genderId"));

        // Pas 1:  Recuperar la persona amb el id
        Person person = personRepo.findById(personId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + personId));

        // Pas 2: Recuperar la pelicula amb el id
        Movie movie = movieSearchRepo.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Película no encontrada con ID: " + movieId));


        Integer maxCastOrder = findMaxCastOrderForMovie(movieId);
        int castOrder = maxCastOrder+1;
        System.out.println("Castorder " +castOrder);
        MovieCast movieCast = new MovieCast();
        Gender gender = genderRepo.getReferenceById(genderId);
        movieCast.setPerson(person);
        movieCast.setMovie(movie);
        movieCast.setCharacterName(characterName);
        movieCast.setCastOrder(castOrder);
        movieCast.setGender(gender);

        movieCastRepo.save(movieCast);
        return "Person assigned to the movie successfully";
    }

    public int findMaxCastOrderForMovie(Long movieId) {
        Integer maxCastOrder = movieCastRepo.findMaxCastOrderForMovie(movieId);
        return maxCastOrder != null ? maxCastOrder : 0;
    }

    public String updateMovieCast(Map<String, String> data) {
        Long personId= Long.valueOf(data.get("personId"));
        Long movieId = Long.valueOf(data.get("movieId"));
        String characterName = data.get("characterName");
        Long genderId = Long.valueOf(data.get("genderId"));

        MovieCast movieCastBD=movieCastRepo.findByMovie_MovieIdAndPerson_PersonId(movieId,personId);
        MovieCast movieCast = new MovieCast();
        if (movieCastBD.getMovie().getMovieId().equals(movieId) &&
                movieCastBD.getPerson().getPersonId().equals(personId)) {

            movieCast.setCharacterName(characterName);
            movieCast.setCastOrder(movieCastBD.getCastOrder());
            Gender gender = genderRepo.getReferenceById(genderId);
            System.out.println(gender.toString());
            movieCast.setGender(gender);

            Person person = personRepo.findById(personId)
                    .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + personId));

            Movie movie = movieSearchRepo.findById(movieId)
                    .orElseThrow(() -> new RuntimeException("Película no encontrada con ID: " + movieId));
            movieCast.setMovie(movie);
            movieCast.setPerson(person);

            movieCastRepo.save(movieCast);
            return "Update value successfully";
        } else {
            return "Update Failed";
        }
    }




}
