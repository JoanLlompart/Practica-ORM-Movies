package com.esliceu.movies.Entities;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "keyword")
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private Long keywordId;

    @Column(name = "keyword_name")
    private String keywordName;

    @OneToMany(mappedBy = "keyword")
    private Set<MovieKeywords> movieKeywords;

}
