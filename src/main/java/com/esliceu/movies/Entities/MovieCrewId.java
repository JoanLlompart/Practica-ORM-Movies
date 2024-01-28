package com.esliceu.movies.Entities;

import java.io.Serializable;

public class MovieCrewId implements Serializable {
    private Movie movie;
    private Person person;
    private Department department;

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
