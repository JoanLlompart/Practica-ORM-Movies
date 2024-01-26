package com.esliceu.movies.Repos;
import com.esliceu.movies.Entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MovieSearchRepo extends JpaRepository<Movie, Long> {
    List<Movie> findAll();

    //Per paginar les pagines
    Page<Movie> findAll(Pageable pageable);

    @Query("SELECT m FROM Movie m " +
            "JOIN MovieCast mc ON m.movieId = mc.movie.movieId " +
            "JOIN Person p ON p.personId = mc.person.personId " +
            "WHERE p.personName LIKE %:personName%")
    List<Movie> findMovieByActor(@Param("personName") String keyword);

    /*
    //Antigua
    @Query("SELECT m FROM Movie m " +
        "JOIN MovieCast mc ON m.movieId = mc.movie.movieId " +
        "JOIN Person p ON p.personId = mc.person.personId " +
        "WHERE p.personName LIKE %:personName%")
    Page<Movie> findMovieByActor(@Param("personName") String keyword,Pageable pageable);
     */

    /*@Query("SELECT m FROM Movie m WHERE title LIKE %:keyword%")
       //Page<Movie> findMoviesByTitle(@Param("keyword")String keyword, Pageable pageable);
    List<Movie> findMoviesByTitle(@Param("keyword")String keyword);
     */



    List<Movie> findByTitleContainingIgnoreCase(String keyword);



    @Query("SELECT m FROM Movie m " +
            "JOIN MovieCrew mc ON m.movieId = mc.movie.movieId " +
            "JOIN Person p ON p.personId = mc.person.personId " +
            "WHERE mc.job = 'Author' AND p.personName LIKE %:personName%")
    List<Movie> findMovieByAuthor(@Param("personName") String keyword);




    //Format utilizant SQL
   /* @Query("SELECT m " +
            "FROM Movie m " +
            "JOIN MovieCast cast ON m.movieId = cast.movie.movieId " +
            "WHERE cast.characterName LIKE %:character% ")
    List<Movie> findMovieByCharacter(@Param("character") String character);
    */

    //Torna les peliculas que hi ha aquest personatge de una pelicula.
    List<Movie> findMovieByMoviecastCharacterNameContaining(String keyword);


   /* @Query("SELECT m " +
            "FROM Movie m " +
            "JOIN MovieGenres mg ON m.movieId=mg.movie.movieId " +
            "JOIN Genre g ON g.genreId= mg.genre.genreId " +
            "WHERE g.genreName LIKE %:keyword%")
    List<Movie> findMovieByGenre(@Param("keyword") String keyword);
    */


    //Peliculas que tenen aquest genre
    List<Movie> findMovieByMovieGenres_GenreGenreNameContaining(String genreName);

/*
    @Query("SELECT m FROM Movie m " +
            "JOIN MovieCrew mc ON m.movieId = mc.movie.movieId " +
            "JOIN Person p ON p.personId = mc.person.personId " +
            "WHERE mc.job = 'Director' AND p.personName LIKE %:keyword%")
    List<Movie> findMovieByDirector(@Param("keyword") String keyword);

 */

    List<Movie> findDistincMovieByMovieCrewsJobAndMovieCrews_PersonPersonNameContaining(String director,String keyword);

    boolean existsByMovieId(Long movieId);

}