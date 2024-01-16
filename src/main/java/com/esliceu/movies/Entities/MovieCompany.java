package com.esliceu.movies.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "movie_company",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"movie_id", "company_id"})
        )
public class MovieCompany {
    @Id
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Id
    @ManyToOne
    @JoinColumn(name = "company_id")
    private ProductionCompany company;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public ProductionCompany getCompany() {
        return company;
    }

    public void setCompany(ProductionCompany company) {
        this.company = company;
    }
}
