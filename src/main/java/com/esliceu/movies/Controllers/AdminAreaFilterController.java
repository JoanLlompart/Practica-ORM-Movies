package com.esliceu.movies.Controllers;

import com.esliceu.movies.Services.CrudFilterServices;
import com.esliceu.movies.Services.UserServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Controller
public class AdminAreaFilterController {
    @Autowired
    UserServices userServices;
    @Autowired
    CrudFilterServices crudFilterServices;
    @PostMapping("/adminArea/filter")
    public List<?> adminFilter(HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
      //  String successMessage =crudFilterServices.insertNewKeyword(data);
        return crudFilterServices.filterByEntity(data);
    }
}
