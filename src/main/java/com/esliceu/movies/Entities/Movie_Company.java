package com.esliceu.demoMovies.Entities;
import jakarta.persistence.*;

@Entity
@Table(
        name = "movie_company",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"movie_id", "company_id"})
)
public class Movie_Company {
    @Id
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Id
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Production_Company company;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Production_Company getCompany() {
        return company;
    }

    public void setCompany(Production_Company company) {
        this.company = company;
    }
}