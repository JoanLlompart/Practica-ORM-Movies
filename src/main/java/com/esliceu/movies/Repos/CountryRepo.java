package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.Country;
import com.esliceu.movies.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<Country, Long> {

}
