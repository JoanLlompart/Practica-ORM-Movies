package com.esliceu.movies.Entities;

import java.io.Serializable;

public class ProductionCountryId implements Serializable {
    private Movie movie;
    private Country country;

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    private Movie getMovie(){
        return movie;
    }
}
