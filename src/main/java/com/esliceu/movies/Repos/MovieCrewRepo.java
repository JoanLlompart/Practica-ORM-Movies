package com.esliceu.movies.Repos;


import com.esliceu.movies.Entities.MovieCrew;
import com.esliceu.movies.Entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieCrewRepo extends JpaRepository<MovieCrew, Long> {

    List<MovieCrew> findAllByMovie_MovieId(Long movieId);

    List<MovieCrew> findAllByPerson_PersonId(Long personId);

    @Query("SELECT mc.person FROM MovieCrew mc WHERE mc.movie.movieId = :movieId AND mc.job = :job")
    Optional<Person> findFirstPersonByMovieIdAndJob(@Param("movieId") Long movieId, @Param("job") String job);

    /*
    @Query("SELECT p.person_name, g.gender, mc.character_name " +
            "FROM `movie_cast` mc JOIN person p ON p.person_id=mc.person_id " +
            "JOIN gender g ON mc.gender_id=g.gender_id " +
            "WHERE movie_id =:movieId;")
    List<ActorDTO> findAllActorsByMovieId(@Param("movieId") Long movieId);
     */
    //Optional<Person> findFirstByMovie_IdAndJob(Long movieId, String job);
}