package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.LanguageRole;
import com.esliceu.movies.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LanguageRoleRepo  extends JpaRepository<LanguageRole, Long> {
    @Query("SELECT MAX(roleId) FROM LanguageRole")
    Long lastRoleId();

    //@Query("INSERT INTO LanguageRol role (roleId, )")
    //void save(LanguageRole role);


    //Long findFirstByOrderByRoleIdDesc();


}
