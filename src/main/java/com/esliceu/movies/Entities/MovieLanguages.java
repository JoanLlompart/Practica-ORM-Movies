package com.esliceu.movies.Entities;

import jakarta.persistence.*;

@Entity
@Table(
        name = "movie_languages",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"movie_id", "language_id", "language_role_id"})
)
@IdClass(MovieLanguagesId.class)
public class MovieLanguages {
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
    private LanguageRole languageRole;

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

    @Override
    public String toString() {
        return "MovieLanguages{" +
                "movie=" + movie +
                ", language=" + language +
                ", languageRole=" + languageRole +
                '}';
    }
}
