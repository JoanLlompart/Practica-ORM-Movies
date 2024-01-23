package com.esliceu.movies.Entities;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "language_role")
public class LanguageRole {
    @Id
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "language_role")
    // private String language_role;
    private String languageRole;

    @OneToMany(mappedBy = "languageRole")
    private Set<MovieLanguages> movieLanguages;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getLanguageRole() {
        return languageRole;
    }

    public void setLanguageRole(String languageRole) {
        this.languageRole = languageRole;
    }

    public Set<MovieLanguages> getMovieLanguages() {
        return movieLanguages;
    }

    public void setMovieLanguages(Set<MovieLanguages> movieLanguages) {
        this.movieLanguages = movieLanguages;
    }
}