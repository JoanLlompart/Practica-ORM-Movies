package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.Genre;
import com.esliceu.movies.Entities.Movie;
import com.esliceu.movies.Entities.MovieGenres;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieGenresRepo extends JpaRepository<MovieGenres, Long> {
    List<MovieGenres> findAllByMovie_MovieId(Long movieId);

    List<MovieGenres> findAllGenreByMovie_MovieId(Long movieId);

    //Elimina MovieGenre que coincideix amb Movie i Genre
    void deleteByMovieAndGenre(Movie movie, Genre genre);
    boolean existsByGenreAndMovie(Genre genre, Movie movie);
}
