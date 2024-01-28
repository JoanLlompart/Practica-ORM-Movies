package com.esliceu.movies.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "gender")
public class Gender {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gender_id")
    private Long genderId;

    @Column(name = "gender")
    private String gender;

    @OneToMany(mappedBy = "gender")
    @JsonIgnore
    private Set<MovieCast> moviecast;


    public Long getGenderId() {
        return genderId;
    }

    public void setGenderId(Long genderId) {
        this.genderId = genderId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<MovieCast> getMoviecast() {
        return moviecast;
    }

    public void setMoviecast(Set<MovieCast> moviecast) {
        this.moviecast = moviecast;
    }
}
