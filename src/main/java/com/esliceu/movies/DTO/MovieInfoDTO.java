package com.esliceu.movies.DTO;

import com.esliceu.movies.Entities.Genre;
import com.esliceu.movies.Entities.Movie;
import com.esliceu.movies.Entities.Person;

import java.sql.Date;
import java.util.List;

public record MovieInfoDTO(Long movieId, String title, Integer budget, String homepage, String overview, Double popularity,
                           Date releaseDate, Long revenue, Integer runtime, String movieStatus, String tagline, Double voteAverage,
                           Integer voteCount, List<Genre> genres, List<Person> director) {

    public static MovieInfoDTO fromMovie(Movie movie, List<Genre> genres, List<Person> director) {
        return new MovieInfoDTO(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getBudget(),
                movie.getHomepage(),
                movie.getOverview(),
                movie.getPopularity(),
                movie.getReleaseDate(),
                movie.getRevenue(),
                movie.getRuntime(),
                movie.getMovieStatus(),
                movie.getTagline(),
                movie.getVoteAverage(),
                movie.getVoteCount(),
                genres,
                director  // Asignar el valor del director
        );
    }
}
