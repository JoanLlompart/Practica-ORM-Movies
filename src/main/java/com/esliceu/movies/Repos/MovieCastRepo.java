package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.MovieCast;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieCastRepo extends JpaRepository<MovieCast, Long> {
    List<MovieCast> findAllByMovie_MovieId(Long movieId);
    List<MovieCast> findByMovie_MovieId(Long movieId);
    List<MovieCast> findAllByPerson_PersonNameContaining(String personName, Pageable pageable);

    List<MovieCast> findAllByPerson_PersonId(Long personId);

    @Query("SELECT COALESCE(MAX(mc.castOrder), 0) FROM MovieCast mc WHERE mc.movie.movieId = :movieId")
    int findMaxCastOrderForMovie(@Param("movieId") Long movieId);

    //Torna MoviCast que coincideix amb MovieId ,PersonId i GenderId
    MovieCast findFirstByMovie_MovieIdAndPerson_PersonIdAndGender_GenderId(Long movieId, Long personId, Long genderId);

    //Borra MoviCast que coincideix amb MovieId ,PersonId i characterName
    void deleteByCharacterNameAndMovie_MovieIdAndPerson_PersonId(String characterName, Long movieId, Long personId);
   //Borra MoviCast que coincideix amb MovieId ,PersonId i GenderId
    void deleteByCharacterNameAndMovie_MovieIdAndPerson_PersonIdAndGender_GenderId(String characterName, Long movieId, Long personId, Long genderId);

    //Torna MoviCast que coincideix amb MovieId ,PersonId i characterName
    MovieCast findByCharacterNameAndMovie_MovieIdAndPerson_PersonId(String characterName, Long movieId, Long personId);
}