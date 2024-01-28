package com.esliceu.movies.Entities;

import java.io.Serializable;

public class MovieKeywordsId implements Serializable {
    private Movie movie;
    private Keyword keyword;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }
}
