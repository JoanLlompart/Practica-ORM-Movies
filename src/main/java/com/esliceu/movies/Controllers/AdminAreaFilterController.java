package com.esliceu.movies.Controllers;

import com.esliceu.movies.Services.UserServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
public class AdminAreaFilterController {
    @Autowired
    UserServices userServices;
    @PostMapping("/adminArea/filter")
    public ResponseEntity<Object> adminFilter(HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String successMessage =.insertNewKeyword(data);
        return ResponseEntity.ok().body(successMessage);
    }
}
