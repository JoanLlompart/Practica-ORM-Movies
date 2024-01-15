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

}
