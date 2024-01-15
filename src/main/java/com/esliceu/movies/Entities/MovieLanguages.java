package com.esliceu.movies.Entities;

import jakarta.persistence.*;

@Entity

@Table(
        name = "movie_languages",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"movie_id", "language_id", "language_role_id"})
)
public class MovieLanguages {
    @Id
    private Long movieId;

    @Id
    private Long languageId;

    @Id
    private Long languageRoleId;

    @ManyToOne
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "language_id", insertable = false, updatable = false)
    private Language language;

    @ManyToOne
    @JoinColumn(name = "language_role_id", insertable = false, updatable = false)
    private LanguageRole languageRole;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public Long getLanguageRoleId() {
        return languageRoleId;
    }

    public void setLanguageRoleId(Long languageRoleId) {
        this.languageRoleId = languageRoleId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public LanguageRole getLanguageRole() {
        return languageRole;
    }

    public void setLanguageRole(LanguageRole languageRole) {
        this.languageRole = languageRole;
    }
}
