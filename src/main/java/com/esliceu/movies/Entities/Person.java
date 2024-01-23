package com.esliceu.movies.Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "person")
public class Person {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long personId;

    @Column(name = "person_name")
    private String personName;

    @OneToMany(mappedBy = "person")
    private Set<MovieCast> moviecast;

    @OneToMany(mappedBy = "person")
    private Set<MovieCrew> movieCrews;


    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Set<MovieCast> getMoviecast() {
        return moviecast;
    }

    public void setMoviecast(Set<MovieCast> moviecast) {
        this.moviecast = moviecast;
    }

    public Set<MovieCrew> getMovieCrews() {
        return movieCrews;
    }

    public void setMovieCrews(Set<MovieCrew> movieCrews) {
        this.movieCrews = movieCrews;
    }
}
