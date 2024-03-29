package com.esliceu.movies.Controllers;

import com.esliceu.movies.DTO.MovieDTO;
import com.esliceu.movies.DTO.MovieInfoDTO;
import com.esliceu.movies.Entities.Movie;
import com.esliceu.movies.Entities.Person;
import com.esliceu.movies.Services.MovieSearchServices;
import com.esliceu.movies.Services.PersonServices;
import com.esliceu.movies.Services.UserServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class MovieSearchController {

    @Autowired
    MovieSearchServices movieSearchServices;
    @Autowired
    UserServices userServices;
    @Autowired
    PersonServices personServices;

    @GetMapping("/movieSearch")
    public String showMovies(Model model, HttpSession session, @RequestParam(defaultValue = "0") int page) {
        String email = (String) session.getAttribute("email");
        printSession(session);
        List<Movie> movieList = movieSearchServices.allMovies();
        model.addAttribute("moviesFind", movieList);
        model.addAttribute("currentPage", page);
        model.addAttribute("email", email != null);


        return "movieSearch";
    }

    @PostMapping("/movieSearch")
    @ResponseBody
    public List<MovieDTO> filterMovies(@RequestBody Map<String, String> formData,HttpSession session) {
        String email = (String) session.getAttribute("email");
        printSession(session);
        String filter = formData.get("filter");
        String keyword = formData.get("keyword");
        int page = Integer.parseInt(formData.get("page"));
        int size = Integer.parseInt(formData.get("size"));

        System.out.println("Filtram per: " + filter + ", amb la paraula clau: " + keyword);
        // En el service filtramos por el tipo de keyword y tratamos las datos.
        List<MovieDTO> movieList = movieSearchServices.filterMovies(filter, keyword, page, size);
        return movieList;
    }


    @PostMapping("/movieSearch/person")
    @ResponseBody
    public List<Person> filterPerson(@RequestBody Map<String, String> formData, HttpSession session) {
        String email = (String) session.getAttribute("email");

        return personServices.findPersonsByKeyword(formData);
    }

    //Informacio de Movie completa amb un modal.
    @PostMapping("/movieSearch/infoMovie")
    @ResponseBody
    public Optional<MovieInfoDTO> viewAllMovieInfo(@RequestBody Map<String, String> formData) {
        return movieSearchServices.getAllMovieInfo(formData);
    }



    @PostMapping("/movieSearch/movieActor")
    @ResponseBody
    public List<?> viewAllActorsInMovie(@RequestBody Map<String, String> formData) {
        return movieSearchServices.getAllActors(formData);
    }




    @PostMapping("/adminArea/add/movie")
    public ResponseEntity<Object> adminAddPostMovie(HttpSession session, @RequestBody Map<String, String> data) {
        String email = (String) session.getAttribute("email");
        String successMessage = movieSearchServices.insertNewMovie(data);
        return ResponseEntity.ok().body(successMessage);
    }


    @PostMapping("/adminArea/update/movie")
    public ResponseEntity<Object> adminUpdatePostMovie(HttpSession session, @RequestBody Map<String, String> data) {
        String email = (String) session.getAttribute("email");
        String successMessage = movieSearchServices.updateMovie(data);
        return ResponseEntity.ok().body(successMessage);
    }

    @PostMapping("/adminArea/delete/movie")
    public ResponseEntity<Object> adminDeletePostMovie(HttpSession session, @RequestBody Map<String, String> data) {
        String email = (String) session.getAttribute("email");
        String successMessage = movieSearchServices.deleteMovie(data);
        return ResponseEntity.ok().body(successMessage);
    }

    private void printSession(HttpSession session) {
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            Object attributeValue = session.getAttribute(attributeName);
            System.out.println("(Ojo que en hi ha)Nombre del atributo: " + attributeName);
            System.out.println("(Ojo que en hi ha)Valor del atributo: " + attributeValue);
        }
    }

}