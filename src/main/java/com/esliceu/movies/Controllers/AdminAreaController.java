package com.esliceu.movies.Controllers;

import com.esliceu.movies.Services.AdminAddServices;
import com.esliceu.movies.Services.MovieCastServices;
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
public class AdminAreaController {
    @Autowired
    UserServices userServices;
    @Autowired
    AdminAddServices adminAddServices;
    @Autowired
    MovieCastServices movieCastServices;

    @GetMapping("/adminArea")
    public String adminGet(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        return "adminArea";
    }

    @PostMapping("/adminArea")
    public String adminPost(HttpServletRequest req, HttpSession session) {
        String email = (String) session.getAttribute("email");
        return "adminArea";
    }

    @PostMapping("/adminArea/updateMoviCast")
    public ResponseEntity<Object> adminUpdateMovieCast(HttpSession session, @RequestBody Map<String, String> data) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            String successMessage = movieCastServices.updateMovieCast(data);
            return ResponseEntity.ok().body(successMessage);
        } else {
            return ResponseEntity.ok().body("Acces denied, you do not have admin permissions");
        }

    }




}
