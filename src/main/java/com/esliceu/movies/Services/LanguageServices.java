package com.esliceu.movies.Services;

import com.esliceu.movies.Entities.Language;
import com.esliceu.movies.Entities.Movie;
import com.esliceu.movies.Entities.MovieLanguages;
import com.esliceu.movies.Repos.LanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LanguageServices {
    @Autowired
    LanguageRepo languageRepo;
    @Autowired
    AdminAddServices adminAddServices;
    @Autowired
    MovieLanguageServices movieLanguageServices;

    public String deleteLanguage(Map<String, String> data) {
        Long languageId = Long.valueOf(data.get("value1"));
        if (languageRepo.existsByLanguageId(languageId)) {
            //todo:probant de borrar correctament
            movieLanguageServices.deleteByLanguageId(languageId);
            languageRepo.deleteById(languageId);
            return "Language by id ," + languageId + " ,Delete successfully";
        } else {
            return "Language Delete Error";
        }
    }

    public List<Language> filterByLanguage(String keyword, Pageable pageable) {
        return languageRepo.findByLanguageNameContainingIgnoreCase(keyword,pageable);
    }
}

//24653