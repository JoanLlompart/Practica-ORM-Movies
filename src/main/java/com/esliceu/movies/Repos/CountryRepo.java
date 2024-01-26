package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.Country;
import com.esliceu.movies.Entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepo extends JpaRepository<Country, Long> {

    boolean existsByCountryId(Long countryId);

    List<Country> findByCountryNameContainingIgnoreCase(String keyword, Pageable pageable);

}
