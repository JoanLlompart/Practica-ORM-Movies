package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.Keyword;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KeywordRepo  extends JpaRepository<Keyword, Long>  {
    @Query("SELECT MAX(keywordId) FROM Keyword")
    Long lasKeywordId();

    boolean existsByKeywordId(Long keywordId);

    List<Keyword> findByKeywordNameContaining(String keyword, Pageable pageable);
}

