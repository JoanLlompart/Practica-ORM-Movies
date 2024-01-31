package com.esliceu.movies.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(
        name = "movie_cast",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"movie_id", "person_id", "gender_id"})
)
@IdClass(MovieCast.class)
public class MovieCast {
    @Id
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Id
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "character_name")
    private String characterName;

    @Id
    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;
    @Column(name = "cast_order")
    private int castOrder;

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

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getCastOrder() {
        return castOrder;
    }

    public void setCastOrder(int castOrder) {
        this.castOrder = castOrder;
    }
}
