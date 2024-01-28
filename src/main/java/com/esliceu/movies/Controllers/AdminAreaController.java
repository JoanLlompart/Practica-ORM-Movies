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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AdminAreaController {
    @Autowired
    UserServices userServices;
    @Autowired
    AdminAddServices adminAddServices;

    @GetMapping("/adminArea")
    public String adminGet(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        return "adminArea";
    }

    @PostMapping("/adminArea")
    public String adminPost(HttpServletRequest req, HttpSession session) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
       // String isoCode = data.get("isoCode");
        //String nameCountry = data.get("name");
        //adminAddServices.insertCountry(isoCode, nameCountry);
        //String successMessage = "Country added successfully";
        return "adminArea";
    }




}
