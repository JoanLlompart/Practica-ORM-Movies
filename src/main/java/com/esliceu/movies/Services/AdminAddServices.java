package com.esliceu.movies.Services;

import com.esliceu.movies.Entities.Country;
import com.esliceu.movies.Entities.Language;
import com.esliceu.movies.Entities.LanguageRole;
import com.esliceu.movies.Repos.AdminRepo;
import com.esliceu.movies.Repos.CountryRepo;
import com.esliceu.movies.Repos.LanguageRepo;
import com.esliceu.movies.Repos.LanguageRoleRepo;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminAddServices {
    @Autowired
    AdminRepo adminRepo;
    @Autowired
    LanguageRepo languageRepo;
    @Autowired
    CountryRepo countryRepo;
    @Autowired
    LanguageRoleRepo languageRoleRepo;

    @PersistenceContext
    private EntityManager entityManager;
    public boolean insertCountry(String isoCode,String nameCountry) {
        if (!(isoCode == null) && !(nameCountry ==null)) {
            Country c = new Country();
            c.setCountryIsoCode(isoCode);
            c.setCountryName(nameCountry);
            countryRepo.save(c);
            return true;
        } else {
            System.out.println("VALOR DE ENTRADA NULL A InsertCountry");
            return false;
        }
    }

    public void insertLanguage(String value1, String value2) {
        if (!(value1 == null) && !(value2 ==null)) {
            Language language = new Language();
            language.setLanguageCode(value1);
            language.setLanguageName(value2);
            languageRepo.save(language);
        }
    }

    //TODO:per ara no la utilitzarem
    private boolean isValidInput(String keyword) {
        //Si el codi es una cadena buida o te nomes espais
        keyword =keyword.trim();
        //Permet numeros i lletras i espais enmitg ja que els altres se han eliminat
        return keyword.matches("[a-zA-Z0-9 ]+");
    }

    public void insertLanguageRole(Map<String, String> data) {
        String language_role = data.get("value1");
        if (isValidInput(language_role) && language_role != null) {
            LanguageRole lRole = new LanguageRole();
            lRole.setLanguage_role(language_role);
            languageRoleRepo.save(lRole);
        }

    }
}
