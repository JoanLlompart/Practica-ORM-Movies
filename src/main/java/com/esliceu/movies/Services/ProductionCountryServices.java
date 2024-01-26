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

    public void deleteByCountryId(Long id) {
        List<ProductionCountry> productionCountryList = productionCountryRepo.findAllByCountry_CountryId(id);
        productionCountryRepo.deleteAll(productionCountryList);
    }


}
