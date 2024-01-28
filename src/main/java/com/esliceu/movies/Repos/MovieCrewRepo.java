package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.MovieCrew;
import com.esliceu.movies.Entities.MovieLanguages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCrewRepo extends JpaRepository<MovieCrew, Long> {

}
