package com.esliceu.movies.Entities;

import java.io.Serializable;

public class MovieCastId implements Serializable {
    private Movie movie;

    private Person person;

    private Gender gender;

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
