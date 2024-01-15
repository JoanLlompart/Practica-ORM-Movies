package com.esliceu.movies.Entities;

import jakarta.persistence.*;
@Entity
@Table(
        name = "production_country",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"movie_id", "country_id"})
)
public class ProductionCountry {
    @Id
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Id
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
