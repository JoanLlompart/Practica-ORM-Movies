package com.esliceu.movies.DTO;

import java.sql.Date;

public record MovieInfoDTO(Long movieId, String title, Integer budget, String homepage, String overview, Double popularity,
                           Date releaseDate, Long revenue, Integer runtime, String movieStatus, String tagline, Double voteAverage,
                           Integer voteCount, String director) {

    public MovieInfoDTO(Long movieId, String title, Integer budget, String homepage, String overview, Double popularity, Date releaseDate, Long revenue, Integer runtime, String movieStatus, String tagline, Double voteAverage, Integer voteCount) {
        this(movieId, title, budget, homepage, overview, popularity, releaseDate, revenue, runtime, movieStatus, tagline, voteAverage, voteCount, null);
    }

    public MovieInfoDTO {
    }

    public void director(String director) {

    }
}
