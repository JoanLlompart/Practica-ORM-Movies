package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.MovieCast;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieCastRepo extends JpaRepository<MovieCast,Long> {
    List<MovieCast> findAllByMovie_MovieId(Long movieId);

   /* @Query("SELECT p.personName, g.gender, mc.characterName " +
            "FROM MovieCast mc JOIN Person p ON p.personId=mc.p.personId " +
            "JOIN Gender g ON mc.g.genderId=g.genderId " +
            "WHERE movieId =:movieId")
    List<> findAllActorsByMovieId(@Param("movieId") Long movieId);
    */



   //Aquest es el normal
/*
    @Query("SELECT p.personName, g.gender, mc.characterName " +
            "FROM MovieCast mc JOIN Person p ON p.personId=mc.person.personId " +
            "JOIN Gender g ON mc.gender.genderId=g.genderId " +
            "WHERE mc.movie.movieId =:movieId")
    List<ActorDTO> findAllActorsByMovieId(@Param("movieId") Long movieId);
*/

    List<MovieCast> findByMovie_MovieId(Long movieId);

    //List<MovieCast> findPersonNameAndGenderAndCharacterNameByMovieId(Long movieId);

/*
Amem si es la consulta
    @Query("SELECT p.personName, g.gender, mc.characterName " +
            "FROM MovieCast mc JOIN Person p ON p.personId=mc.person.personId " +
            "JOIN Gender g ON mc.gender.genderId=g.genderId " +
            "WHERE mc.movie.movieId =:movieId")
    List<MovieCast> findAllActorsByMovieId(@Param("movieId") Long movieId);

 */






    //List<Object[]> findPersonAndCharacterAndMovieByPersonPersonNameContaining(String keyword, Pageable pageable);

    //FUNCIONA PERO NO FEIM BE EL CAST
    //List<?> findPersonAndCharacterAndMovieByPersonPersonNameContaining(String keyword, Pageable pageable);

 /*   @Query("SELECT p.personName, mc.characterName, m.title " +
            "FROM MovieCast mc JOIN Person p ON p.personId=mc.person.personId " +
            "JOIN Movie m ON m.movieId=mc.movie.movieId WHERE p.personName LIKE %:keyword%")
    List<?> actorsAndMovies(@Param("keyword") String keyword,Pageable pageable);

  */

    List<MovieCast> findAllByPerson_PersonNameContaining(String personName , Pageable pageable);

    List<MovieCast> findAllByPerson_PersonId(Long personId);

   // boolean deleteByMovie_MovieIdAndPerson_PersonId(Long movieId, Long personId);
   void deleteByMovie_MovieIdAndPerson_PersonId(Long movieId, Long personId);

    @Query("SELECT COALESCE(MAX(mc.castOrder), 0) FROM MovieCast mc WHERE mc.movie.movieId = :movieId")
    int findMaxCastOrderForMovie(@Param("movieId") Long movieId);
    MovieCast findFirstByMovie_MovieIdAndPerson_PersonId(Long movieId, Long personId);


    //proba per actualitzar mes exacte la relacio de movieCrew
    MovieCast findFirstByMovie_MovieIdAndPerson_PersonIdAndGender_GenderId(Long movieId, Long personId, Long genderId);

    MovieCast findByMovie_MovieIdAndPerson_PersonIdAndCharacterName(Long movieId, Long personId, String characterName);

    void deleteByCharacterNameAndMovie_MovieIdAndPerson_PersonId(String characterName,Long movieId, Long personId);

    void deleteByCharacterNameAndMovie_MovieIdAndPerson_PersonIdAndGender_GenderId(String characterName, Long movieId, Long personId, Long genderId);
}