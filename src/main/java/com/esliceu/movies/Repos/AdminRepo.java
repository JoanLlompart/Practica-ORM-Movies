package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo  extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
