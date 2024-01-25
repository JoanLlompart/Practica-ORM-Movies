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

    @Autowired
    ProductionCompanyRepo productionCompanyRepo;

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

    public String insertNewProductionCompany(Map<String, String> data) {
        String companyName = data.get("value1");
       // String companyId = data.get("value2");
        if (isValidInput(companyName) && companyName != null) {
            ProductionCompany pc = new ProductionCompany();
            pc.setCompanyName(companyName);
            Long lastId = productionCompanyRepo.lastCompanyId();
            pc.setCompanyId(lastId+1);
            productionCompanyRepo.save(pc);
            return "Company Add successfully";
        } else {
            return "Company Insert FAILED";
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
    public String updateCountry(Map<String, String> data) {
        System.out.println("Data :" + data.toString());
        //Long countryId = Long.valueOf(data.get("value1"));
        //Long countryId = Long.valueOf(285);
        String isoCode = data.get("value1");
        String name = data.get("value2");
        Long countryId = Long.valueOf(data.get("value3"));

        if (!(isoCode == null) && !(name ==null) && !(countryId ==null)) {
            //TODO: Comprobam si existeix el id.
            Country c = new Country();
            c.setCountry_id(countryId);
            c.setCountryName(name);
            c.setCountryIsoCode(isoCode);
            countryRepo.save(c);
            return "Country by id ," + countryId + " , Update successfully";
        } else {
            return "Country Update Error";
        }
    }

    public String updateLanguage(Map<String, String> data) {
        Long languageId = Long.valueOf(data.get("value3"));
        String code = data.get("value1");
        String name = data.get("value2");
        if (!(code == null) && !(name ==null) && !(languageId ==null)) {
            //TODO: Comprobam si existeix el id.
            Language lan = new Language();
            lan.setLanguageId(languageId);
            lan.setLanguageCode(code);
            lan.setLanguageName(name);
            languageRepo.save(lan);
            return "Language by id ," + languageId + " , Update successfully";
        } else {
            return "Language Update Error";
        }
    }

    public String updateLanguageRol(Map<String, String> data) {
            Long roleId = Long.valueOf(data.get("value2"));
            String role = data.get("value1");
            if (!(role == null) && !(role ==null) && !(role ==null)) {
                //TODO: Comprobam si existeix el id.
                LanguageRole lr = new LanguageRole();
                lr.setRoleId(roleId);
                lr.setLanguageRole(role);
                languageRoleRepo.save(lr);
                return "Language Role by id ," + roleId + " , Update successfully";
            } else {
                return "Language Rol Update Error";
            }
    }

    public String updateGenre(Map<String, String> data) {
        Long genreId = Long.valueOf(data.get("value2"));
        String genre = data.get("value1");
        if (isValidInput(genre) && genreRepo.existsByGenreId(genreId)) {
            Genre ge = new Genre();
            ge.setGenreId(genreId);
            ge.setGenreName(genre);
            genreRepo.save(ge);
            return "Genre by id ," + genreId + " , Update successfully";
        } else {
            return "Genre Update Error";
        }
    }

    public String updateKeyword(Map<String, String> data) {
        Long keywordId = Long.valueOf(data.get("value2"));
        String keywordName = data.get("value1");
        if (isValidInput(keywordName) && keywordRepo.existsByKeywordId(keywordId)) {
            Genre ge = new Genre();
            ge.setGenreId(keywordId);
            ge.setGenreName(keywordName);
            genreRepo.save(ge);
            return "Keyword by id ," + keywordId + " , Update successfully";
        } else {
            return "Keyword Update Error";
        }
    }

    public String updateGender(Map<String, String> data) {
        return "FAILED NOT MAKE";
    }

    public String updateProductionCompany(Map<String, String> data) {
        return "FAILED NOT MAKE";
    }
}
