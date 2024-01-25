package com.esliceu.movies.Services;

import com.esliceu.movies.Entities.Country;
import com.esliceu.movies.Entities.Department;
import com.esliceu.movies.Repos.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CountryServices {
    @Autowired
    CountryRepo countryRepo;
    @Autowired
    AdminAddServices adminAddServices;


    public String deleteCountry(Map<String, String> data) {
        Long countryId = Long.valueOf(data.get("value1"));
        if (countryRepo.existsByCountryId(countryId)) {;
            countryRepo.deleteById(countryId);
            return "Country by id ," + countryId + " ,Delete successfully";
        } else {
            return "Country Delete Error";
        }
    }
}
