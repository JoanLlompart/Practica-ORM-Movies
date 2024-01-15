package com.esliceu.movies.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "gender")
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gender_id")
    private Long genderId;

    @Column(name = "gender")
    private String gender;



}
