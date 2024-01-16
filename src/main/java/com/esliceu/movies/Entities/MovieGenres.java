package com.esliceu.movies.Entities;
import jakarta.persistence.*;

@Entity
@Table(name = "movie_genres" ,
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"movie_id", "genre_id"})
)
public class MovieGenres {
    @Id
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @Id
    @ManyToMany
    @JoinColumn(name = "genre_id")
    private Gender gender;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
