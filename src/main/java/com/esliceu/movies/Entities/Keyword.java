package com.esliceu.movies.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "keyword")
public class Keyword {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private Long keywordId;

    @Column(name = "keyword_name")
    private String keywordName;

    @OneToMany(mappedBy = "keyword")
    @JsonIgnore
    private Set<MovieKeywords> movieKeywords;

    public Long getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(Long keywordId) {
        this.keywordId = keywordId;
    }

    public String getKeywordName() {
        return keywordName;
    }

    public void setKeywordName(String keywordName) {
        this.keywordName = keywordName;
    }

    public Set<MovieKeywords> getMovieKeywords() {
        return movieKeywords;
    }

    public void setMovieKeywords(Set<MovieKeywords> movieKeywords) {
        this.movieKeywords = movieKeywords;
    }
}
