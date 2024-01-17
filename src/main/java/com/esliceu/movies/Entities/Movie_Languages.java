package com.esliceu.demoMovies.Entities;

import jakarta.persistence.*;

@Entity
@Table(
        name = "movie_languages",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"movie_id", "language_id", "language_role_id"})
)
public class Movie_Languages {
    @Id
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Id
    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @Id
    @ManyToOne
    @JoinColumn(name = "language_role_id")
    private Language_role languageRole;

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

    public Language_role getLanguageRole() {
        return languageRole;
    }

    public void setLanguageRole(Language_role languageRole) {
        this.languageRole = languageRole;
    }
}