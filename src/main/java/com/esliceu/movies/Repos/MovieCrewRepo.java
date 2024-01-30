package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.MovieCrew;
import com.esliceu.movies.Entities.MovieLanguages;
import com.esliceu.movies.Entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieCrewRepo extends JpaRepository<MovieCrew, Long> {

    List<MovieCrew> findAllByMovie_MovieId(Long movieId);

    List<MovieCrew> findAllByPerson_PersonId(Long personId);

    //List<Person> findPersonByMovie_MovieIdAndJob(Long movieId, String director);
    //Optional<Person> findFirstPersonByMovie_MovieIdAndJob(Long movieId, String job);

    @Query("SELECT mc.person FROM MovieCrew mc WHERE mc.movie.movieId = :movieId AND mc.job = :job")
    Optional<Person> findFirstPersonByMovieIdAndJob(@Param("movieId") Long movieId, @Param("job") String job);

}
