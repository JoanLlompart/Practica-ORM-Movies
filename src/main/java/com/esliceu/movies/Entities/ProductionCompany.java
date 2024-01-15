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

    @OneToMany(mappedBy = "company")
    private Set<MovieCompany> movieCompanies;

}
