package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.Language;
import com.esliceu.movies.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface LanguageRepo  extends JpaRepository<Language, Long> {
}
