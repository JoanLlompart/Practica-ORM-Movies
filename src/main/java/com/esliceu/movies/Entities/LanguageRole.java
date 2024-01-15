package com.esliceu.movies.Entities;
import jakarta.persistence.*;

@Entity
@Table(name = "language_role")
public class LanguageRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "language_role")
    private String language_role;

}
