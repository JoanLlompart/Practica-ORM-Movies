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


    @PostMapping("/adminArea/add/country")
    public ResponseEntity<Object> adminAddPostCountry(HttpServletRequest req, HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            String successMessage= adminAddServices.insertCountry(data);
            return ResponseEntity.ok().body(successMessage);
        }
        return ResponseEntity.ok().body("Acces denied, you do not have admin permissions");
    }

    @PostMapping("/adminArea/add/language")
    public ResponseEntity<Object> adminAddPostLanguage(HttpServletRequest req, HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            String isoCode = data.get("value1");
            String nameCountry = data.get("value2");
            String successMessage = adminAddServices.insertLanguage(isoCode, nameCountry);
            return ResponseEntity.ok().body(successMessage);
        }
        return ResponseEntity.ok().body("Acces denied, you do not have admin permissions");
    }

    @PostMapping("/adminArea/add/languageRole")
    public ResponseEntity<Object> adminAddPostLanguageRole( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            String successMessage = adminAddServices.insertLanguageRole(data);
            return ResponseEntity.ok().body(successMessage);
        }
        return ResponseEntity.ok().body("Acces denied, you do not have admin permissions");
    }

    @PostMapping("/adminArea/add/genre")
    public ResponseEntity<Object> adminAddPostGenre( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            String successMessage = adminAddServices.insertNewGenre(data);
            return ResponseEntity.ok().body(successMessage);
        }
        return ResponseEntity.ok().body("Acces denied, you do not have admin permissions");
    }

    @PostMapping("/adminArea/add/keyword")
    public ResponseEntity<Object> adminAddPostKeyword( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            String successMessage = adminAddServices.insertNewKeyword(data);
            return ResponseEntity.ok().body(successMessage);
        }
        return ResponseEntity.ok().body("Acces denied, you do not have admin permissions");
    }

    @PostMapping("/adminArea/add/productionCompany")
    public ResponseEntity<Object> adminAddPostProductionCompany( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            String successMessage = adminAddServices.insertNewProductionCompany(data);
            return ResponseEntity.ok().body(successMessage);
        }
        return ResponseEntity.ok().body("Acces denied, you do not have admin permissions");
    }


    @PostMapping("/adminArea/add/gender")
    public ResponseEntity<Object> adminAddPostGender( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            String successMessage = adminAddServices.insertNewGender(data);
            return ResponseEntity.ok().body(successMessage);
        }
        return ResponseEntity.ok().body("Acces denied, you do not have admin permissions");
    }

    @PostMapping("/adminArea/add/department")
    public ResponseEntity<Object> adminAddPostDepartment( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            String successMessage = adminAddServices.insertNewDepartment(data);
            return ResponseEntity.ok().body(successMessage);
        }
        return ResponseEntity.ok().body("Acces denied, you do not have admin permissions");
    }

    @PostMapping("/adminArea/add/person")
    public ResponseEntity<Object> adminAddPostPerson( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            String successMessage = adminAddServices.insertNewPerson(data);
            return ResponseEntity.ok().body(successMessage);
        }
        return ResponseEntity.ok().body("Acces denied, you do not have admin permissions");
    }

    @PostMapping("/adminArea/addDirector")
    public ResponseEntity<Object> adminAddDirector( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            String successMessage = movieCrewServices.addMovieDirector(data);
            return ResponseEntity.ok().body(successMessage);
        }
        return ResponseEntity.ok().body("Acces denied, you do not have admin permissions");

    }

    @PostMapping("/adminArea/addGenre")
    public ResponseEntity<Object> adminAddGenre( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            String successMessage = movieGenresServices.addMovieGenre(data);
            return ResponseEntity.ok().body(successMessage);
        }
        return ResponseEntity.ok().body("Acces denied, you do not have admin permissions");
    }
    @PostMapping("/adminArea/addKeyword")
    public ResponseEntity<Object> adminAddMovieKeyword( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            String successMessage = movieKeywordsServices.addMovieKeyword(data);
            return ResponseEntity.ok().body(successMessage);
        }
        return ResponseEntity.ok().body("Acces denied, you do not have admin permissions");
    }

}
