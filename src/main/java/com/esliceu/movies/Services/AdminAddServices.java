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
        if (!isoCode.isEmpty() && !nameCountry.isEmpty()) {
            System.out.println("iso " + isoCode + " , name " + nameCountry);
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
        if (!value1.isEmpty() && !value2.isEmpty()) {
            Language language = new Language();
            language.setLanguageCode(value1);
            language.setLanguageName(value2);
            languageRepo.save(language);
        }
    }
    protected boolean isValidInput(String keyword) {
        //Si el codi es una cadena buida o te nomes espais
        keyword =keyword.trim();
        //Permet numeros i lletras i espais enmitg ja que els altres se han eliminat
        return keyword.matches("[a-zA-Z0-9_çÇéÉíÍóÓúÚüÜñÑ!?\\s]+");
    }

    public String insertLanguageRole(Map<String, String> data) {
        String languageRole = data.get("value1");
        if (isValidInput(languageRole) && !languageRole.isEmpty()) {
            LanguageRole lRole = new LanguageRole();
            lRole.setLanguageRole(languageRole);
            //Long lastRoleId =languageRoleRepo.findFirstByOrderByRoleIdDesc();
            Long lastRoleId=languageRoleRepo.lastRoleId();

            lRole.setRoleId(lastRoleId +1);
            System.out.println("Id darrer de LanguageRole es : " + lRole.getRoleId());
            languageRoleRepo.save(lRole);
            return "Language Role added successfully";
        } else {
            return "Invalid input in Language Role add, there are characters not supported by the filter";
        }

    }

    public String insertNewGenre(Map<String, String> data) {
        String genreName = data.get("value1");
        if (isValidInput(genreName) && !genreName.isEmpty()) {
            Genre g = new Genre();
            g.setGenreName(genreName);
            Long lastId =genreRepo.lasGenreId();
            g.setGenreId(lastId+1);
            genreRepo.save(g);
            return "Genre added successfully";
        } else {
            return "Genre Insert FAILED, there are characters not supported by the filter";
        }
    }
    public String insertNewKeyword(Map<String, String> data) {
        String keywordName = data.get("value1");
        System.out.println("keywordname " + keywordName);
        //if (isValidInput(keywordName) && !keywordName.isEmpty()) {
        if (!keywordName.isEmpty()){
            Keyword k = new Keyword();
            k.setKeywordName(keywordName);
            Long lastId =keywordRepo.lasKeywordId();
            k.setKeywordId(lastId+1);
            keywordRepo.save(k);
            return "Keyword Add successfully";
        } else {
            return "Keyword Insert FAILED, there are characters not supported by the filter";
        }
    }

    public String insertNewProductionCompany(Map<String, String> data) {
        String companyName = data.get("value1");
       // String companyId = data.get("value2");
        if (isValidInput(companyName) && !companyName.isEmpty()) {
            ProductionCompany pc = new ProductionCompany();
            pc.setCompanyName(companyName);
            Long lastId = productionCompanyRepo.lastCompanyId();
            pc.setCompanyId(lastId+1);
            productionCompanyRepo.save(pc);
            return "Company Add successfully";
        } else {
            return "Company Insert FAILED, there are characters not supported by the filter";
        }

    }

    private boolean isNumeric(String cadena) {
        return cadena.matches("-?\\d+");
    }

    public String insertNewGender(Map<String, String> data) {
        String genderName = data.get("value1");

        if (!genderName.isEmpty()) {
            Gender ge = new Gender();
            ge.setGender(genderName);
            Long lastId =genderRepo.lastGenderId();
            ge.setGenderId(lastId+1);
            genderRepo.save(ge);
            return "Gender Add successfully";
        } else {
            return "Gender Insert FAILED, this input is not valid";
        }
    }

    public String insertNewDepartment(Map<String, String> data) {
        String departmentName = data.get("value1");
        if (isValidInput(departmentName) && !departmentName.isEmpty()) {
            Department dp = new Department();
            dp.setDepartmentName(departmentName);
            departmentRepo.save(dp);
            return "Department Add successfully";
        } else {
            return "Department Insert FAILED, this input DepartmentName contain invalid characters.";
        }
    }
    public String insertNewPerson(Map<String, String> data) {
        String personName = data.get("value1");
        if (isValidInput(personName) && !personName.isEmpty()) {
            Person person = new Person();
            person.setPersonName(personName);
            Long lastId =personRepo.lastPersonId();
            person.setPersonId(lastId+1);
            personRepo.save(person);
            return "Person Add successfully";
        } else {
            return "Person Insert FAILED, there are characters not supported by the filter in input Name";
        }
    }
    public String updateCountry(Map<String, String> data) {
        System.out.println("Data :" + data.toString());
        String isoCode = data.get("value1");
        String name = data.get("value2");
        Long countryId = Long.valueOf(data.get("value3"));


        if (countryRepo.existsByCountryId(countryId)) {
            Country c= countryRepo.getReferenceById(countryId);

           // Country c = new Country();
            if (!isoCode.isEmpty()) {
                c.setCountryIsoCode(isoCode);
            }
            if (!name.isEmpty()) {
                c.setCountryName(name);
            }
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
        if (languageRepo.existsByLanguageId(languageId)) {
            //TODO: Comprobam si existeix el id.
            Language lan = languageRepo.getReferenceById(languageId);
            //Comproba que si ha de actualitzar els camp
            if (!code.isEmpty()) {
                if (!lan.getLanguageCode().equals(code)) {
                    lan.setLanguageCode(code);
                }
            }
            //Comproba que si ha de actualitzar els camp
            if (!name.isEmpty()) {
                if (!lan.getLanguageName().equals(name)) {
                    lan.setLanguageName(name);
                }
            }

            languageRepo.save(lan);
            return "Language by id ," + languageId + " , Update successfully";
        } else {
            return "Language Update Error";
        }
    }

    public String updateLanguageRol(Map<String, String> data) {
            Long roleId = Long.valueOf(data.get("value2"));
            String role = data.get("value1");
            if (languageRoleRepo.existsByRoleId(roleId)) {
                // Comprobam si existeix el id.
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
            Keyword keyword = new Keyword();
            keyword.setKeywordId(keywordId);
            keyword.setKeywordName(keywordName);
            keywordRepo.save(keyword);
            return "Keyword by id ," + keywordId + " , Update successfully";
        } else {
            return "Keyword Update Error";
        }
    }

    public String updateGender(Map<String, String> data) {
        Long genderId = Long.valueOf(data.get("value2"));
        String gender = data.get("value1");
        if (isValidInput(gender) && genderRepo.existsByGenderId(genderId)) {
            Gender ge = new Gender();
            ge.setGenderId(genderId);
            ge.setGender(gender);
            genderRepo.save(ge);

            return "Gender by id ," + genderId + " , Update successfully";
        } else {
            return "Gender Update Error";
        }

    }

    public String updateProductionCompany(Map<String, String> data) {
        Long companyId = Long.valueOf(data.get("value2"));
        String name = data.get("value1");
        if (isValidInput(name) && productionCompanyRepo.existsByCompanyId(companyId)) {
          ProductionCompany pc = new ProductionCompany();
          pc.setCompanyId(companyId);
          pc.setCompanyName(name);
          productionCompanyRepo.save(pc);
            return "Production Company by id ," + companyId + " , Update successfully";
        } else {
            return "Production Company Update Error";
        }
    }

    public String updatePerson(Map<String, String> data) {
        Long personId = Long.valueOf(data.get("value2"));
        String name = data.get("value1");
        if (isValidInput(name) && personRepo.existsByPersonId(personId)) {
            Person p = personRepo.getReferenceById(personId);
            p.setPersonName(name);
            personRepo.save(p);
            return "Person by id ," + personId + " , Update successfully";
        } else {
            if (isValidInput(name)) {
                return "This input is Invalid,has not passed the allowed character filter, plese try a valid name";
            } else if (name.isEmpty()) {
                return "Input invalid: please enter a not empty input";
            }
            return "Person Update Error";
        }
    }

    public String updateDepartment(Map<String, String> data) {
        Long departmentId = Long.valueOf(data.get("value2"));
        String name = data.get("value1");
        if (isValidInput(name) && departmentRepo.existsByDepartmentId(departmentId)) {
            Department dp = new Department();
            dp.setDepartmentId(departmentId);
            dp.setDepartmentName(name);
            departmentRepo.save(dp);
            return "Department by id ," + departmentId + " , Update successfully";
        } else {
            if (isValidInput(name)) {
                return "This input is Invalid,has not passed the allowed character filter, plese try a valid name";
            } else if (name.isEmpty()) {
                return "Input invalid: please enter a not empty input";
            }
            return "Department Update Error";
        }
    }
    public static String quitarEspacios(String texto) {
        return texto.replaceAll("\\s", "");
    }
}
