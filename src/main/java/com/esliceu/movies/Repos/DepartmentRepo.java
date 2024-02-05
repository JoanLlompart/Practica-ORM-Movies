package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
    boolean existsByDepartmentId(Long departmentId);

}
