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

/*
    @ManyToOne
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "language_id", insertable = false, updatable = false)
    private Language language;

    @ManyToOne
    @JoinColumn(name = "language_role_id", insertable = false, updatable = false)
    private LanguageRole languageRole;


     */
}
