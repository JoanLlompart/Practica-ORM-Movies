package com.esliceu.movies.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "movie")
@DynamicUpdate
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId; //se ha cambiat antes se deia movie_id

    @Column(name = "title", nullable = true)
    private String title;

    @Column(name = "budget", nullable = true)
    private Integer budget;
    @Column(name = "homepage", nullable = true)
    private String homepage;

    @Column(name = "overview", nullable = true)
    private String overview;

    @Column(name = "popularity", nullable = true)
    private Double popularity;

    @Column(name = "release_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @Column(name = "revenue", nullable = true)
    private Long revenue;

    @Column(name = "runtime", nullable = true)
    private Integer runtime;

    @Column(name = "movie_status", nullable = true)
    private String movieStatus;

    @Column(name = "tagline", nullable = true)
    private String tagline;

    @Column(name = "vote_average", nullable = true)
    private Double voteAverage;
    @Column(name = "vote_count", nullable = true)
    private Integer voteCount;


    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private Set<MovieCast> moviecast;
    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private Set<ProductionCountry> productionCountries;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private Set<MovieCompany> movieCompanies;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private Set<MovieCrew> movieCrews;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private Set<MovieGenres> movieGenres;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private Set<MovieKeywords> movieKeywords;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private Set<MovieLanguages> movieLanguages;

    public Movie(Long movieId, String title, Integer budget, String homepage, String overview, Double popularity,
                 Date releaseDate, Long revenue, Integer runtime, String movieStatus, String tagline, Double voteAverage,
                 Integer voteCount, Set<MovieCast> moviecast, Set<ProductionCountry> productionCountries,
                 Set<MovieCompany> movieCompanies, Set<MovieCrew> movieCrews, Set<MovieGenres> movieGenres,
                 Set<MovieKeywords> movieKeywords, Set<MovieLanguages> movieLanguages) {
        this.movieId = movieId;
        this.title = title;
        this.budget = budget;
        this.homepage = homepage;
        this.overview = overview;
        this.popularity = popularity;
        this.releaseDate = releaseDate;
        this.revenue = revenue;
        this.runtime = runtime;
        this.movieStatus = movieStatus;
        this.tagline = tagline;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.moviecast = moviecast;
        this.productionCountries = productionCountries;
        this.movieCompanies = movieCompanies;
        this.movieCrews = movieCrews;
        this.movieGenres = movieGenres;
        this.movieKeywords = movieKeywords;
        this.movieLanguages = movieLanguages;
    }

    public Movie() {
    }

    public Movie(String title, Integer budget, String homepage, String overview, Double popularity, Date releaseDate,
                 Long revenue, Integer runtime, String movieStatus, String tagline, Double voteAverage,
                 Integer voteCount, Set<MovieCast> moviecast, Set<ProductionCountry> productionCountries,
                 Set<MovieCompany> movieCompanies, Set<MovieCrew> movieCrews, Set<MovieGenres> movieGenres,
                 Set<MovieKeywords> movieKeywords, Set<MovieLanguages> movieLanguages) {
        this.title = title;
        this.budget = budget;
        this.homepage = homepage;
        this.overview = overview;
        this.popularity = popularity;
        this.releaseDate = releaseDate;
        this.revenue = revenue;
        this.runtime = runtime;
        this.movieStatus = movieStatus;
        this.tagline = tagline;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.moviecast = moviecast;
        this.productionCountries = productionCountries;
        this.movieCompanies = movieCompanies;
        this.movieCrews = movieCrews;
        this.movieGenres = movieGenres;
        this.movieKeywords = movieKeywords;
        this.movieLanguages = movieLanguages;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", budget=" + budget +
                ", homepage='" + homepage + '\'' +
                ", overview='" + overview + '\'' +
                ", popularity=" + popularity +
                ", releaseDate=" + releaseDate +
                ", revenue=" + revenue +
                ", runtime=" + runtime +
                ", movieStatus='" + movieStatus + '\'' +
                ", tagline='" + tagline + '\'' +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                '}';
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Long getRevenue() {
        return revenue;
    }

    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getMovieStatus() {
        return movieStatus;
    }

    public void setMovieStatus(String movieStatus) {
        this.movieStatus = movieStatus;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Set<MovieCast> getMoviecast() {
        return moviecast;
    }

    public void setMoviecast(Set<MovieCast> moviecast) {
        this.moviecast = moviecast;
    }

    public Set<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(Set<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public Set<MovieCompany> getMovieCompanies() {
        return movieCompanies;
    }

    public void setMovieCompanies(Set<MovieCompany> movieCompanies) {
        this.movieCompanies = movieCompanies;
    }

    public Set<MovieCrew> getMovieCrews() {
        return movieCrews;
    }

    public void setMovieCrews(Set<MovieCrew> movieCrews) {
        this.movieCrews = movieCrews;
    }

    public Set<MovieGenres> getMovieGenres() {
        return movieGenres;
    }

    public void setMovieGenres(Set<MovieGenres> movieGenres) {
        this.movieGenres = movieGenres;
    }

    public Set<MovieKeywords> getMovieKeywords() {
        return movieKeywords;
    }

    public void setMovieKeywords(Set<MovieKeywords> movieKeywords) {
        this.movieKeywords = movieKeywords;
    }

    public Set<MovieLanguages> getMovieLanguages() {
        return movieLanguages;
    }

    public void setMovieLanguages(Set<MovieLanguages> movieLanguages) {
        this.movieLanguages = movieLanguages;
    }
}