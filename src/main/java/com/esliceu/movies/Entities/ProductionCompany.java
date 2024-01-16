package com.esliceu.movies.Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "production_company")
public class ProductionCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "company_name")
    private String companyName;
    @OneToMany(mappedBy = "company")
    private Set<MovieCompany> movieCompanies;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Set<MovieCompany> getMovieCompanies() {
        return movieCompanies;
    }

    public void setMovieCompanies(Set<MovieCompany> movieCompanies) {
        this.movieCompanies = movieCompanies;
    }
}
