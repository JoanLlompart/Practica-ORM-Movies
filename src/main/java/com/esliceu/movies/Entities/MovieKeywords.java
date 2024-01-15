package com.esliceu.movies.Entities;

import jakarta.persistence.*;

@Entity
@Table(
        name = "movie_keywords",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"movie_id", "keyword_id"})
)public class MovieKeywords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;

}
