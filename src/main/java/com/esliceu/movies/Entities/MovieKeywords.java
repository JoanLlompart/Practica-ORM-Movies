package com.esliceu.movies.Entities;

import jakarta.persistence.*;

@Entity
@Table(
        name = "movie_keywords",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"movie_id", "keyword_id"})
)public class MovieKeywords {
    @Id
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @Id
    @ManyToOne
    @JoinColumn(name = "keyword_id")
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
