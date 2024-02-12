package com.esliceu.movies.Repos;


import com.esliceu.movies.Entities.MovieCrew;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieCrewRepo extends JpaRepository<MovieCrew, Long> {

    List<MovieCrew> findAllByMovie_MovieId(Long movieId);

    List<MovieCrew> findAllByPerson_PersonId(Long personId);

    List<MovieCrew> findAllPersonByMovie_MovieIdAndJob(Long movieId, String job);

    boolean existsByMovie_MovieId(Long movieId);

    void deleteByPerson_PersonIdAndMovie_MovieIdAndDepartment_DepartmentId(Long personId, Long movieId, Long departmentId);

    boolean existsByPerson_PersonIdAndMovie_MovieIdAndDepartment_DepartmentId(Long personId, Long movieId, Long departmentId);
}