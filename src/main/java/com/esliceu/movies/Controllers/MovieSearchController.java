package com.esliceu.movies.Controllers;

import com.esliceu.movies.DTO.MovieDTO;
import com.esliceu.movies.Entities.Movie;
import com.esliceu.movies.Services.MovieSearchServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        model.addAttribute("moviesFind", movieList);
        model.addAttribute("moviesFind", pageRes.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageRes.getTotalPages());

        return "movieSearch";
    }

    /*
    @PostMapping("/movieSearch")
    @ResponseBody
    public String filterMovies(Model model, HttpSession session, HttpServletRequest req,
                               @RequestBody Map<String, String> formData ) {
        String filter = formData.get("filter");
        String keyword = formData.get("keyword");
        System.out.println("Filtram per : " + filter + " , amb la paraula clau : " + keyword );
        //En el service filtram per el tipus de keyword i tractam les dades.
        List<Movie> movieList= movieSearchServices.filterMovies(filter,keyword);
       // model.addAttribute("moviesFind",movieList);
        return "movieSearch";
    }

     */
    @PostMapping("/movieSearch")
    @ResponseBody
    public List<MovieDTO> filterMovies(@RequestBody Map<String, String> formData) {
        String filter = formData.get("filter");
        String keyword = formData.get("keyword");
        System.out.println("Filtram per: " + filter + ", amb la paraula clau: " + keyword);
        // En el service filtramos por el tipo de keyword y tratamos las datos.
        List<MovieDTO> movieList = movieSearchServices.filterMovies(filter, keyword);
        for(MovieDTO m: movieList) {
            System.out.println(m.getTitle());
        }
        return movieList;
    }

}
