package com.esliceu.movies.Controllers;

import com.esliceu.movies.Services.AdminAddServices;
import com.esliceu.movies.Services.UserServices;
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

    @PostMapping("/adminArea/update/country")
    public ResponseEntity<Object> adminUpdatePostCountry( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
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

    @PostMapping("/adminArea/add/genre")
    public ResponseEntity<Object> adminUpdatePostGenre( HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String successMessage =adminAddServices.updateGenre(data);
        return ResponseEntity.ok().body(successMessage);
    }
}
