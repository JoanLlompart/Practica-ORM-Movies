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
       // String isoCode = data.get("isoCode");
       // String nameCountry = data.get("name");
        String isoCode = data.get("value1");
        String nameCountry = data.get("value2");
        boolean valid =adminAddServices.insertCountry(isoCode,nameCountry);
        String successMessage;
        if (valid) {
            successMessage = "Country added successfully";
        } else {
            successMessage = "Country ADD FAILED";
        }
        // return ResponseEntity.ok().body(successMessage);
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
        // return ResponseEntity.ok().body(successMessage);
        return ResponseEntity.ok().body(successMessage);
    }

}
