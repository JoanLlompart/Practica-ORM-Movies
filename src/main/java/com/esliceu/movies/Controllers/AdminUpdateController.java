package com.esliceu.movies.Controllers;

import com.esliceu.movies.Services.AdminAddServices;
import com.esliceu.movies.Services.UserServices;
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
public class AdminUpdateController {
    @Autowired
    UserServices userServices;
    @Autowired
    AdminAddServices adminAddServices;

    @GetMapping("/adminArea/update")
    public String adminGet(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        return "adminArea";
    }




    @PostMapping("/adminArea/update/country")
    public ResponseEntity<Object> adminUpdatePostCountry(HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        System.err.println("Ha arribat a el servidor");
        String successMessage =adminAddServices.updateCountry(data);
        return ResponseEntity.ok().body(successMessage);
    }

    @PostMapping("/adminArea/update/language")
    public ResponseEntity<Object> adminUpdatePostLanguage( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String successMessage =adminAddServices.updateLanguage(data);
        return ResponseEntity.ok().body(successMessage);
    }
    @PostMapping("/adminArea/update/languageRole")
    public ResponseEntity<Object> adminUpdatePostLanguageRol( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String successMessage =adminAddServices.updateLanguageRol(data);
        return ResponseEntity.ok().body(successMessage);
    }

    @PostMapping("/adminArea/update/genre")
    public ResponseEntity<Object> adminUpdatePostGenre( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String successMessage =adminAddServices.updateGenre(data);
        return ResponseEntity.ok().body(successMessage);
    }

    @PostMapping("/adminArea/update/keyword")
    public ResponseEntity<Object> adminUpdatePostKeyword( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String successMessage =adminAddServices.updateKeyword(data);
        return ResponseEntity.ok().body(successMessage);
    }

    @PostMapping("/adminArea/update/productionCompany")
    public ResponseEntity<Object> adminUpdatePosProductionCompany( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String successMessage =adminAddServices.updateProductionCompany(data);
        return ResponseEntity.ok().body(successMessage);
    }

    @PostMapping("/adminArea/update/gender")
    public ResponseEntity<Object> adminUpdatePostGender( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String successMessage =adminAddServices.updateGender(data);
        return ResponseEntity.ok().body(successMessage);
    }

    @PostMapping("/adminArea/update/person")
    public ResponseEntity<Object> adminUpdatePostPerson( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String successMessage =adminAddServices.updatePerson(data);
        return ResponseEntity.ok().body(successMessage);
    }

    @PostMapping("/adminArea/update/department")
    public ResponseEntity<Object> adminUpdatePostDepartment( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String successMessage =adminAddServices.updateDepartment(data);
        return ResponseEntity.ok().body(successMessage);
    }

}
