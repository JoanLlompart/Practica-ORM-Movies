package com.esliceu.movies.Services;

import com.esliceu.movies.DTO.MovieDirectorDTO;
import com.esliceu.movies.Entities.MovieCrew;
import com.esliceu.movies.Entities.Person;
import com.esliceu.movies.Repos.MovieCrewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

   /* public String findDirectorByMovieId(Long movieId) {
        Optional<Person> p =movieCrewRepo.findFirstPersonByMovieIdAndJob(movieId,"Director");
        return p.get().getPersonName();
    }

    */
    public List<Person> findDirectorByMovieId(Long movieId) {
        List<MovieCrew> directorsCrewList =movieCrewRepo.findAllPersonByMovie_MovieIdAndJob(movieId,"Director");
        List<Person> directors = new ArrayList<>();
        for (MovieCrew mc : directorsCrewList) {
            String personName = mc.getPerson().getPersonName();
            Long personId = mc.getPerson().getPersonId();
            directors.add(new Person(personId,personName));
        }
        return directors;
    }

    public List<MovieDirectorDTO> findAllDirectors(Long movieId) {
        List<MovieCrew> directorsMovie = movieCrewRepo.findAllPersonByMovie_MovieIdAndJob(movieId,"Director");
        List<MovieDirectorDTO> directorDTOS = new ArrayList<>();
        for (MovieCrew mc : directorsMovie) {
            String personName = mc.getPerson().getPersonName();
            Long personId = mc.getPerson().getPersonId();
            Long departmentId = mc.getDepartment().getDepartmentId();
            String departmentName =mc.getDepartment().getDepartmentName();
            String job = mc.getJob();
            directorDTOS.add(new MovieDirectorDTO(movieId,personId,personName,departmentId,departmentName,job));
        }
        return directorDTOS;
    }
}
