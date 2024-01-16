package com.esliceu.movies.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovieSearchController {
    @GetMapping("/movieSearch")
    public String showMovies(Model model, HttpSession session) {
        return "movieSearch";
    }
}
