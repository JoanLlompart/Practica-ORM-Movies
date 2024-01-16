package com.esliceu.movies.Entities;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "language_role")
public class LanguageRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "language_role")
    private String language_role;

    @OneToMany(mappedBy = "languageRole")
    private Set<MovieLanguages> movieLanguages;



    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getLanguage_role() {
        return language_role;
    }

    public void setLanguage_role(String language_role) {
        this.language_role = language_role;
    }

    public Set<MovieLanguages> getMovieLanguages() {
        return movieLanguages;
    }

    public void setMovieLanguages(Set<MovieLanguages> movieLanguages) {
        this.movieLanguages = movieLanguages;
    }
}
