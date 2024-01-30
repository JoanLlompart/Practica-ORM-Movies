package com.esliceu.movies.DTO;

import com.esliceu.movies.Entities.Movie;

import java.sql.Date;

public record MovieInfoDTO(Long movieId, String title, Integer budget, String homepage, String overview, Double popularity,
                           Date releaseDate, Long revenue, Integer runtime, String movieStatus, String tagline, Double voteAverage,
                           Integer voteCount, String director) {

    public static MovieInfoDTO fromMovie(Movie movie, String director) {
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
                director  // Asignar el valor del director
        );
    }
}
