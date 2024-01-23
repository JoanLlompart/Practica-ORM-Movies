package com.esliceu.movies.Services;

import com.esliceu.movies.Entities.Country;
import com.esliceu.movies.Entities.Genre;
import com.esliceu.movies.Entities.Language;
import com.esliceu.movies.Entities.LanguageRole;
import com.esliceu.movies.Repos.*;
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
    @Autowired
    GenreRepo genreRepo;


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

    public String insertLanguageRole(Map<String, String> data) {
        String languageRole = data.get("value1");
        if (isValidInput(languageRole) && languageRole != null) {
            LanguageRole lRole = new LanguageRole();
            lRole.setLanguageRole(languageRole);
            //Long lastRoleId =languageRoleRepo.findFirstByOrderByRoleIdDesc();
            Long lastRoleId=languageRoleRepo.lastRoleId();

            lRole.setRoleId(lastRoleId +1);
            System.out.println("Id darrer de LanguageRole es : " + lRole.getRoleId());
            languageRoleRepo.save(lRole);
            return "Language Role added successfully";
        } else {
            return "Problema a Language Role add";
        }

    }

    public String insertNewGenre(Map<String, String> data) {
        String genreName = data.get("value1");
        if (isValidInput(genreName) && genreName != null) {
            Genre g = new Genre();
            g.setGenreName(genreName);
            Long lastId =genreRepo.lasGenreId();
            g.setGenreId(lastId+1);
            genreRepo.save(g);
            return "Genre added successfully";
        } else {
            return "Genre Insert FAILED";
        }
    }
}
