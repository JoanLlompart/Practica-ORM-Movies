package com.esliceu.demoMovies.Entities;

import jakarta.persistence.*;

@Entity
@Table(
        name = "movie_cast",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"movie_id", "person_id", "gender_id"})
)
public class Movie_Cast {

    @Id
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Id
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    private String character_name;

    @Id
    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;

    private int cast_order;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getCharacter_name() {
        return character_name;
    }

    public void setCharacter_name(String character_name) {
        this.character_name = character_name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getCast_order() {
        return cast_order;
    }

    public void setCast_order(int cast_order) {
        this.cast_order = cast_order;
    }
}