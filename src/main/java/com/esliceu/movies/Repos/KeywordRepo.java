package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface KeywordRepo  extends JpaRepository<Keyword, Long>  {
    @Query("SELECT MAX(keywordId) FROM Keyword")
    Long lasKeywordId();

}
