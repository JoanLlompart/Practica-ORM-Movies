package com.esliceu.movies.Entities;

import java.io.Serializable;

public class MovieCompanyId implements Serializable {
    private Movie movie;
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
