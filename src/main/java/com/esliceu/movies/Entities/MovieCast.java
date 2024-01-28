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
@IdClass(MovieCrewId.class)
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


}
