package com.esliceu.movies.Services;

import com.esliceu.movies.Entities.*;
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
    @Autowired
    KeywordRepo keywordRepo;
    @Autowired
    GenderRepo genderRepo;
    @Autowired
    DepartmentRepo departmentRepo;
    @Autowired
    PersonRepo personRepo;


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

    public String insertNewKeyword(Map<String, String> data) {
        String keywordName = data.get("value1");
        System.out.println("keywordname " + keywordName);
        if (isValidInput(keywordName) && keywordName != null) {
            Keyword k = new Keyword();
            k.setKeywordName(keywordName);
            Long lastId =keywordRepo.lasKeywordId();
            k.setKeywordId(lastId+1);
            keywordRepo.save(k);
            return "Keyword Add successfully";
        } else {
            return "Keyword Insert FAILED";
        }
    }

    public String insertNewMovieCompany(Map<String, String> data) {
        String movieId = data.get("value1");
        String companyId = data.get("value2");
        if (isNumeric(movieId) && isNumeric(companyId)) {
            MovieCompany mc = new MovieCompany();
            return "NOT MAKE AT THE MOMENT";
        } else {
            return "Invalid ID type";
        }

    }

    private boolean isNumeric(String cadena) {
        return cadena.matches("-?\\d+");
    }

    public String insertNewGender(Map<String, String> data) {
        String genderName = data.get("value1");

        if (isValidInput(genderName) && genderName != null) {
            Gender ge = new Gender();
            ge.setGender(genderName);
            Long lastId =genderRepo.lastGenderId();
            ge.setGenderId(lastId+1);
            genderRepo.save(ge);
            return "Gender Add successfully";
        } else {
            return "Gender Insert FAILED";
        }
    }

    public String insertNewDepartment(Map<String, String> data) {
        String departmentName = data.get("value1");
        if (isValidInput(departmentName) && departmentName != null) {
            Department dp = new Department();
            dp.setDepartmentName(departmentName);
            departmentRepo.save(dp);
            return "Department Add successfully";
        } else {
            return "Department Insert FAILED";
        }
    }
    public String insertNewPerson(Map<String, String> data) {
        String personName = data.get("value1");
        if (isValidInput(personName) && personName != null) {
            Person person = new Person();
            person.setPersonName(personName);
            Long lastId =personRepo.lastPersonId();
            person.setPersonId(lastId+1);
            personRepo.save(person);
            return "Person Add successfully";
        } else {
            return "Person Insert FAILED";
        }
    }
}
