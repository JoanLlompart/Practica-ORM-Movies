package com.esliceu.movies.Repos;

import com.esliceu.movies.DTO.ActorDTO;
import com.esliceu.movies.Entities.MovieCast;
import com.esliceu.movies.Entities.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface MovieCastRepo extends JpaRepository<MovieCast,Long> {
    List<MovieCast> findAllByMovie_MovieId(Long movieId);

   /* @Query("SELECT p.personName, g.gender, mc.characterName " +
            "FROM MovieCast mc JOIN Person p ON p.personId=mc.p.personId " +
            "JOIN Gender g ON mc.g.genderId=g.genderId " +
            "WHERE movieId =:movieId")
    List<> findAllActorsByMovieId(@Param("movieId") Long movieId);
    */
    @Query("SELECT p.personName, g.gender, mc.characterName " +
            "FROM MovieCast mc JOIN Person p ON p.personId=mc.person.personId " +
            "JOIN Gender g ON mc.gender.genderId=g.genderId " +
            "WHERE mc.movie.movieId =:movieId")
    List<ActorDTO> findAllActorsByMovieId(@Param("movieId") Long movieId);

    //List<Object[]> findPersonAndCharacterAndMovieByPersonPersonNameContaining(String keyword, Pageable pageable);

    //FUNCIONA PERO NO FEIM BE EL CAST
    //List<?> findPersonAndCharacterAndMovieByPersonPersonNameContaining(String keyword, Pageable pageable);

    @Query("SELECT p.personName, mc.characterName, m.title " +
            "FROM MovieCast mc JOIN Person p ON p.personId=mc.person.personId " +
            "JOIN Movie m ON m.movieId=mc.movie.movieId WHERE p.personName LIKE %:keyword%")
    List<?> actorsAndMovies(@Param("keyword") String keyword,Pageable pageable);

}