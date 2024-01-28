package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.MovieCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCompanyRepo extends JpaRepository<MovieCompany,Long> {
}
