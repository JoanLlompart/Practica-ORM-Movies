package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.Movie;
import com.esliceu.movies.Entities.User;
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
  /*  @Query("SELECT m FROM Movie m " +
            "JOIN MovieCrew mc ON m.movieId = mc.movie.movieId " +
            "JOIN Person p ON p.personId = mc.person.personId " +
            "WHERE mc.job = 'Author' AND p.personName = :personName")
    List<Movie> findMovieByActor(@Param("personName") String keyword);


   */
    @Query("SELECT m FROM Movie m " +
            "JOIN MovieCrew mc ON m.movieId = mc.movie.movieId " +
            "JOIN Person p ON p.personId = mc.person.personId " +
            "WHERE mc.job = 'Author' AND p.personName LIKE %:personName%")
    List<Movie> findMovieByActor(@Param("personName") String keyword);


   // @Query("SELECT m FROM Movie m WHERE m.title LIKE %:keyword%")
    //Page<Movie> findMoviesByTitle(@Param("keyword")String keyword, Pageable pageable);



    //proba
   // List<Movie> findByTitleStartingWithIgnoreCase()


    //todo

    @Query("SELECT m FROM Movie m WHERE m.title LIKE %:keyword%")
    Page<Movie> findMoviesByTitle(@Param("keyword") String keyword, Pageable pageable);
}
