package com.esliceu.movies.Services;

import com.esliceu.movies.Entities.ProductionCountry;
import com.esliceu.movies.Repos.ProductionCountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionCountryServices {
    @Autowired
    ProductionCountryRepo productionCountryRepo;

    public void deleteByCountryId(Long countryId) {
        List<ProductionCountry> productionCountryList = productionCountryRepo.findAllByCountry_CountryId(countryId);
        productionCountryRepo.deleteAll(productionCountryList);
    }


    public void deleteByMovieId(Long movieId) {
        List<ProductionCountry> allMovieCountry = productionCountryRepo.findAllByMovie_MovieId(movieId);
        productionCountryRepo.deleteAll(allMovieCountry);
    }
}
