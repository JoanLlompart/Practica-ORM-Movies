package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.Genre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepo  extends JpaRepository<Genre, Long> {
    @Query("SELECT MAX(genreId) FROM Genre")
    Long lasGenreId();

    boolean existsByGenreId(Long genreId);

    List<Genre> findByGenreNameContainingIgnoreCase(String keyword, Pageable pageable);

}
