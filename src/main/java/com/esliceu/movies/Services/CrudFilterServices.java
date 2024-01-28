package com.esliceu.movies.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CrudFilterServices {

    @Autowired
    CountryServices countryServices;
    @Autowired
    LanguageServices languageServices;
    @Autowired
    LanguageRoleServices languageRoleServices;

    @Autowired
    GenreServices genreServices;
    public List<?> filterByEntity(Map<String, String> data) {
       String filter =data.get("filter");
        String keyword =data.get("keyword");
        int page = Integer.parseInt(data.get("page"));
        int size = Integer.parseInt(data.get("size"));
        Pageable pageable = PageRequest.of(page,size);
        switch (filter) {
            case "country":
                return countryServices.filterByCountry(keyword,pageable);
            case "language":
                return languageServices.filterByLanguage(keyword,pageable);
            case "languageRole":
                return languageRoleServices.filterByLanguageRole(keyword,pageable);
            case "genre":
                return genreServices.filterByGenre(keyword,pageable);
            default:
                break;
        }
        return null;
    }
}
