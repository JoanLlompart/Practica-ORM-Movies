package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.LanguageRole;
import com.esliceu.movies.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRoleRepo  extends JpaRepository<LanguageRole, Long> {

}
