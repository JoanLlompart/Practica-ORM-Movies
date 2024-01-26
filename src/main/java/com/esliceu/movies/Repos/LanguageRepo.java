package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.Language;
import com.esliceu.movies.Entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepo  extends JpaRepository<Language, Long> {
    boolean existsByLanguageId(Long languageId);

    List<Language> findByLanguageNameContainingIgnoreCase(String keyword, Pageable pageable);
}
