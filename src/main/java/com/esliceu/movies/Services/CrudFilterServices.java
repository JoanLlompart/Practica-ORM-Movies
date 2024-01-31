package com.esliceu.movies.Services;

import com.esliceu.movies.Entities.Person;
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
    MovieCastServices movieCastServices;

    @Autowired
    GenreServices genreServices;
    public List<?> filterByEntity(Map<String, String> data) {
       String filter =data.get("filter");
        String keyword =data.get("keyword");
        int page = Integer.parseInt(data.get("page"));
        int size = Integer.parseInt(data.get("size"));
        System.out.println("Page :" + page + " , size : " + size);
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
            case "actor":
                return movieCastServices.filterByActor(keyword,pageable);
            default:
                break;
        }
        return null;
    }
}
