package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.Department;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
    boolean existsByDepartmentId(Long departmentId);

    List<Department> findByDepartmentNameContaining(String keyword, Pageable pageable);

}
