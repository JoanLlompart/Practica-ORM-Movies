package com.esliceu.movies.Entities;
import jakarta.persistence.*;

@Entity
@Table(
        name = "production_country",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"movie_id", "country_id"})
)
public class productionCountry {
    @Id
    @Column(name = "movie_id")
    private int movieId;

    @Id
    @Column(name = "country_id")
    private int countryId;
    @ManyToOne
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "country_id", insertable = false, updatable = false)
    private Country country;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

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
