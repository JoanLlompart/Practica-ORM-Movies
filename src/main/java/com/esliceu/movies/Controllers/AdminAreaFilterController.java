package com.esliceu.movies.Controllers;

import com.esliceu.movies.Entities.Gender;
import com.esliceu.movies.Services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class AdminAreaFilterController {
    @Autowired
    UserServices userServices;
    @Autowired
    CrudFilterServices crudFilterServices;
    @Autowired
    MovieCastServices movieCastServices;
    @Autowired
    GenderServices genderServices;
    @Autowired
    MovieCrewServices movieCrewServices;


    @GetMapping("/adminArea/filter")
    public String adminAreaGet(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        return "adminArea";
    }
    @PostMapping("/adminArea/filter")
    @ResponseBody
    public List<?> adminFilter(HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        return crudFilterServices.filterByEntity(data);
    }

    @PostMapping("/adminArea/movieDirector")
    @ResponseBody
    public List<?> adminMovieRelationDirector(HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        return crudFilterServices.findMovieDirector(data);
    }

    @PostMapping("/adminArea/movieMovieGenre")
    @ResponseBody
    public List<?> adminMovieRelationGenre(HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        return crudFilterServices.findByGenre(data);
    }
    @PostMapping("/adminArea/movieMovieKeywords")
    @ResponseBody
    public List<?> adminMovieRelationKeywords(HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        return crudFilterServices.findByKeyword(data);
    }

    @PostMapping("/adminArea/allGenres")
    @ResponseBody
    public List<?> adminAllGenres(HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        //  userServices.setEmail(email);

        return crudFilterServices.findAllGenres(data);
    }

    @PostMapping("/adminArea/keywordName")
    @ResponseBody
    public List<?> adminAllKeywordsName(HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        // userServices.setEmail(email);
        return crudFilterServices.findAllKeywordsByName(data);
    }

    @PostMapping("/adminArea/decastActor")
    public ResponseEntity<Object> adminDecastActor(HttpSession session, @RequestBody Map<String, String> data) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            String successMessage = movieCastServices.decastActor(data);
            return ResponseEntity.ok().body(successMessage);
        }
        return ResponseEntity.ok().body("Acces denied, you do not have admin permissions");
    }

    @PostMapping("/adminArea/castPerson")
    public ResponseEntity<Object> adminCastPerson(HttpSession session, @RequestBody Map<String, String> data) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            String successMessage = movieCastServices.castActor(data);
            return ResponseEntity.ok().body(successMessage);
        }
        return ResponseEntity.ok().body("Acces denied, you do not have admin permissions");
    }
    @PostMapping("/adminArea/deleteDirector")
    public ResponseEntity<Object> adminDeleteDirectorInMovieCrew(HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            String successMessage = movieCrewServices.deleteDirector(data);
            return ResponseEntity.ok().body(successMessage);
        }
        return ResponseEntity.ok().body("Acces denied, you do not have admin permissions");
    }





    @PostMapping("/adminArea/allGender")
    @ResponseBody
    public List<Gender> viewAllGenders() {
        System.out.println("HA ENTRAT");
        return genderServices.getAllGender();
    }


}