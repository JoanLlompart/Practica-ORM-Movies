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
                break;
            case "languageRole":
                break;
            default:
                break;

        }
        return null;
    }
}
