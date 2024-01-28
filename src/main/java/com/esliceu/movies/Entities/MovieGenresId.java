package com.esliceu.movies.Entities;

import java.io.Serializable;

public class MovieGenresId implements Serializable {
    private Movie movie;
    private Genre genre;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
