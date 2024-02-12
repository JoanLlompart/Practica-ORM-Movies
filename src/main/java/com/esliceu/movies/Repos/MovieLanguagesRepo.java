package com.esliceu.movies.Repos;

import com.esliceu.movies.Entities.Language;
import com.esliceu.movies.Entities.MovieLanguages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieLanguagesRepo  extends JpaRepository<MovieLanguages, Long> {
    List<MovieLanguages> findAllByMovie_MovieId(Long movieId);

    List<MovieLanguages> findAllByLanguage_LanguageId(Long languageId);

}
