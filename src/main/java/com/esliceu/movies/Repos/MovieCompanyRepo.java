package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.MovieCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieCompanyRepo extends JpaRepository<MovieCompany,Long> {
    List<MovieCompany> findAllByMovie_MovieId(Long movieId);
}
