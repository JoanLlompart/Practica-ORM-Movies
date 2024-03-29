package com.esliceu.movies.Entities;
import jakarta.persistence.*;

@Entity
@Table(name = "movie_genres" ,
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"movie_id", "genre_id"})
)
@IdClass(MovieGenresId.class)
public class MovieGenres {
    @Id
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @Id
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public MovieGenres() {
    }

    public MovieGenres(Movie movie, Genre genre) {
        this.movie = movie;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "MovieGenres{" +
                "movie=" + movie +
                ", genre=" + genre +
                '}';
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
