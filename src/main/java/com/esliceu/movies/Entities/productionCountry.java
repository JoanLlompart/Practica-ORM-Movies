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
    private int movieId;

    @Id
    private int countryId;

    @ManyToOne
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "country_id", insertable = false, updatable = false)
    private Country country;

}
