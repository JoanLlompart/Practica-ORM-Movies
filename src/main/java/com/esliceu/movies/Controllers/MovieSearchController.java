package com.esliceu.movies.Controllers;

import com.esliceu.movies.Entities.Movie;
import com.esliceu.movies.Services.MovieSearchServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MovieSearchController {

    @Autowired
    MovieSearchServices movieSearchServices;
    @GetMapping("/movieSearch")
    public String showMovies(Model model, HttpSession session) {
        List<Movie> movieList = movieSearchServices.allMovies();
        model.addAttribute("moviesFind", movieList);
        return "movieSearch";
    }
}
