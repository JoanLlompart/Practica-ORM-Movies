package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.Gender;
import com.esliceu.movies.Entities.LanguageRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GenderRepo extends JpaRepository<Gender, Long> {
    @Query("SELECT MAX(genderId) FROM Gender")
    Long lastGenderId();

    boolean existsByGenderId(Long genderId);
}
