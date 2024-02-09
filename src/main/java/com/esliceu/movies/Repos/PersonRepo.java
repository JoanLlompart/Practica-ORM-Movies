package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.Department;
import com.esliceu.movies.Entities.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepo extends JpaRepository<Person, Long>  {
    @Query("SELECT MAX(personId) FROM Person")
    Long lastPersonId();

    boolean existsByPersonId(Long personId);

    List<Person> findByPersonNameContaining(String keyword, Pageable pageable);

}
