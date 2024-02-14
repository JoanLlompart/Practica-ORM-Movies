package com.esliceu.movies.Controllers;

import com.esliceu.movies.Services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
public class AdminDeleteController {
    @Autowired
    UserServices userServices;
    @Autowired
    CountryServices countryServices;
    @Autowired
    LanguageServices languageServices;

    @Autowired
    LanguageRoleServices languageRoleServices;
    @Autowired
    MovieGenresServices movieGenresServices;
    @Autowired
    MovieKeywordsServices movieKeywordsServices;
    @Autowired
    PersonServices personServices;
    @GetMapping("/adminArea/delete")
    public String adminGet(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        return "adminArea";
    }

    @PostMapping("/adminArea/delete/country")
    public ResponseEntity<Object> adminDeletePostCountry(HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            String successMessage = countryServices.deleteCountry(data);
            return ResponseEntity.ok().body(successMessage);
        }
        return ResponseEntity.ok().body("Acces denied, you do not have admin permissions");
    }

    @PostMapping("/adminArea/delete/language")
    public ResponseEntity<Object> adminDeletePostLanguage(HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            String successMessage = languageServices.deleteLanguage(data);
            return ResponseEntity.ok().body(successMessage);
        }
        return ResponseEntity.ok().body("Acces denied, you do not have admin permissions");
    }


    @PostMapping("/adminArea/delete/languageRole")
    public ResponseEntity<Object> adminDeletePostLanguageRole(HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        // userServices.setEmail(email);
        String successMessage =languageRoleServices.deleteLanguageRole(data);
        return ResponseEntity.ok().body(successMessage);
    }

    @PostMapping("/adminArea/delete/person")
    public ResponseEntity<Object> adminDeletePostPerson(HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            String successMessage = personServices.deletePerson(data);
            return ResponseEntity.ok().body(successMessage);
        }
        return ResponseEntity.ok().body("Acces denied, you do not have admin permissions");

    }

    @PostMapping("/adminArea/deleteMovieGenre")
    public ResponseEntity<Object> adminDeleteMovieGenre(HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            String successMessage = movieGenresServices.deleteByMovieIdAndGenreId(data);
            return ResponseEntity.ok().body(successMessage);
        }
        return ResponseEntity.ok().body("Acces denied, you do not have admin permissions");

    }
    @PostMapping("/adminArea/deleteMovieKeywords")
    public ResponseEntity<Object> adminDeleteMovieKeywords(HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            String successMessage = movieKeywordsServices.deleteByMovieIdAndKeywordId(data);
            return ResponseEntity.ok().body(successMessage);
        }
        return ResponseEntity.ok().body("Acces denied, you do not have admin permissions");
    }
}