package com.esliceu.movies.Services;

import com.esliceu.movies.Entities.Country;
import com.esliceu.movies.Repos.AdminRepo;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminAddServices {
    @Autowired
    AdminRepo adminRepo;
    @PersistenceContext
    private EntityManager entityManager;
    public boolean insertCountry(String isoCode,String nameCountry) {
        if (!(isoCode == null) && !(nameCountry ==null)) {
            Country c = new Country();
            c.setCountryIsoCode(isoCode);
            c.setCountryName(nameCountry);
            adminRepo.save(c);
            return true;
        } else {
            System.out.println("VALOR DE ENTRADA NULL A InsertCountry");
            return false;
        }
    }
}
