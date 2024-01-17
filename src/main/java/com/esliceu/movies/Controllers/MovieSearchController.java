package com.esliceu.movies.Controllers;

import com.esliceu.movies.Entities.Movie;
import com.esliceu.movies.Services.MovieSearchServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieSearchController {

    @Autowired
    MovieSearchServices movieSearchServices;
    @GetMapping("/movieSearch")
    public String showMovies(Model model, HttpSession session, @RequestParam(defaultValue = "0") int page) {
        //Numero de de elements per pagina
        int pageElement = 10;

        Page<Movie> pageRes = movieSearchServices.getPage(PageRequest.of(page, pageElement));


        List<Movie> movieList = movieSearchServices.allMovies();
       // model.addAttribute("moviesFind", movieList);
        model.addAttribute("moviesFind", pageRes.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageRes.getTotalPages());

        return "movieSearch";
    }
    @PostMapping("/movieSearch")
    public String filterMovies(Model model, HttpSession session, HttpServletRequest req, @RequestParam String filter,
                               @RequestParam String keyword) {
        System.out.println("Filtram per : " + filter + " , amb la paraula clau : " + keyword );

        return "movieSearch";
    }


}
