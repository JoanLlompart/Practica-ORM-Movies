package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.Movie;
import com.esliceu.movies.Entities.ProductionCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductionCompanyRepo extends JpaRepository<ProductionCompany, Long> {
    @Query("SELECT MAX(companyId) FROM ProductionCompany")
    Long lastCompanyId();

    boolean existsByCompanyId(Long companyId);
}
