package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.Movie;
import com.esliceu.movies.Entities.ProductionCountry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductionCountryRepo extends JpaRepository<ProductionCountry, Long> {

    List<ProductionCountry> findAllByCountry_CountryId(Long id);

    List<ProductionCountry> findAllByMovie_MovieId(Long movieId);
}
