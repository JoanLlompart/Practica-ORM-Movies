package com.esliceu.movies.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "movie_company")
public class MovieCompany {
    @Id
    @Column(name = "movie_id")
    private Long movieId;
}
