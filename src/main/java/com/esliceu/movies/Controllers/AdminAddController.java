package com.esliceu.movies.Controllers;

import com.esliceu.movies.Services.*;
import jakarta.servlet.http.HttpServletRequest;
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
public class AdminAddController {

    @Autowired
    UserServices userServices;
    @Autowired
    AdminAddServices adminAddServices;
    @Autowired
    MovieCrewServices movieCrewServices;
    @Autowired
    MovieGenresServices movieGenresServices;
    @Autowired
    MovieKeywordsServices movieKeywordsServices;
    @GetMapping("/adminArea/add")
    public String adminGet(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        return "adminArea";
    }

    @PostMapping("/adminArea/add")
    public ResponseEntity<Object> adminPost(HttpServletRequest req, HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String isoCode = data.get("value1");
        String nameCountry = data.get("value2");
        System.out.println("name" + nameCountry + " , CODE :"+ isoCode);
        boolean valid=adminAddServices.insertCountry(isoCode,nameCountry);
        String successMessage;
        if (valid) {
            successMessage = "Add admin AdminController";
        } else {
            successMessage = "Country ADD FAILED";
        }
        return ResponseEntity.ok().body(successMessage);
    }
    @PostMapping("/adminArea/add/country")
    public ResponseEntity<Object> adminAddPostCountry(HttpServletRequest req, HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String isoCode = data.get("value1");
        String nameCountry = data.get("value2");
        boolean valid =adminAddServices.insertCountry(isoCode,nameCountry);
        String successMessage;
        if (valid) {
            successMessage = "Country added successfully";
        } else {
            successMessage = "Country ADD FAILED";
        }
        return ResponseEntity.ok().body(successMessage);
    }

    @PostMapping("/adminArea/add/language")
    public ResponseEntity<Object> adminAddPostLanguage(HttpServletRequest req, HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String isoCode = data.get("value1");
        String nameCountry = data.get("value2");
        adminAddServices.insertLanguage(isoCode,nameCountry);
        String successMessage = "Country added successfully";
        return ResponseEntity.ok().body(successMessage);
    }

    @PostMapping("/adminArea/add/languageRole")
    public ResponseEntity<Object> adminAddPostLanguageRole( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String successMessage = adminAddServices.insertLanguageRole(data);
        return ResponseEntity.ok().body(successMessage);
    }

    @PostMapping("/adminArea/add/genre")
    public ResponseEntity<Object> adminAddPostGenre( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String successMessage =adminAddServices.insertNewGenre(data);
        return ResponseEntity.ok().body(successMessage);
    }

    @PostMapping("/adminArea/add/keyword")
    public ResponseEntity<Object> adminAddPostKeyword( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String successMessage =adminAddServices.insertNewKeyword(data);
        return ResponseEntity.ok().body(successMessage);
    }

    @PostMapping("/adminArea/add/productionCompany")
    public ResponseEntity<Object> adminAddPostProductionCompany( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);

        String successMessage =adminAddServices.insertNewProductionCompany(data);
        return ResponseEntity.ok().body(successMessage);
    }


    @PostMapping("/adminArea/add/gender")
    public ResponseEntity<Object> adminAddPostGender( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String successMessage =adminAddServices.insertNewGender(data);
        return ResponseEntity.ok().body(successMessage);
    }

    @PostMapping("/adminArea/add/department")
    public ResponseEntity<Object> adminAddPostDepartment( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String successMessage =adminAddServices.insertNewDepartment(data);
        return ResponseEntity.ok().body(successMessage);
    }

    @PostMapping("/adminArea/add/person")
    public ResponseEntity<Object> adminAddPostPerson( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String successMessage =adminAddServices.insertNewPerson(data);
        return ResponseEntity.ok().body(successMessage);
    }

    @PostMapping("/adminArea/addDirector")
    public ResponseEntity<Object> adminAddDirector( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String successMessage =movieCrewServices.addMovieDirector(data);
        return ResponseEntity.ok().body(successMessage);
    }

    @PostMapping("/adminArea/addGenre")
    public ResponseEntity<Object> adminAddGenre( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String successMessage =movieGenresServices.addMovieGenre(data);
        return ResponseEntity.ok().body(successMessage);
    }
    @PostMapping("/adminArea/addKeyword")
    public ResponseEntity<Object> adminAddMovieKeyword( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String successMessage =movieKeywordsServices.addMovieKeyword(data);
        return ResponseEntity.ok().body(successMessage);
    }

}

