package com.esliceu.movies.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "person")

public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long person_id;

    @Column(name = "title")
    private String title;
}
