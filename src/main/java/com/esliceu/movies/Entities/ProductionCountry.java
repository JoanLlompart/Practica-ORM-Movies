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
    @JoinColumn(name = "movie_id",  referencedColumnName = "movie_id")
    private Movie movie;

    @Id
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
