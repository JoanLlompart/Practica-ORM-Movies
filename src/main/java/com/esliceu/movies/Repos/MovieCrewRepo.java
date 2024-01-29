package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.MovieCrew;
import com.esliceu.movies.Entities.MovieLanguages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieCrewRepo extends JpaRepository<MovieCrew, Long> {

    List<MovieCrew> findAllByMovie_MovieId(Long movieId);

    List<MovieCrew> findAllByPerson_PersonId(Long personId);
}
