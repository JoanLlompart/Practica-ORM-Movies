package com.esliceu.movies.Controllers;

import com.esliceu.movies.DTO.MovieDTO;
import com.esliceu.movies.DTO.MoviePageDTO;
import com.esliceu.movies.Entities.Movie;
import com.esliceu.movies.Services.MovieSearchServices;
import com.esliceu.movies.Services.UserServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class MovieSearchController {

    @Autowired
    MovieSearchServices movieSearchServices;
    @Autowired
    UserServices userServices;
    /*
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
     */
    @GetMapping("/movieSearch")
    public String showMovies(Model model, HttpSession session, @RequestParam(defaultValue = "0") int page) {
        //Numero de de elements per pagina
       // int pageElement = 10;
       // Page<Movie> pageRes = movieSearchServices.getPage(PageRequest.of(page, pageElement));

        List<Movie> movieList = movieSearchServices.allMovies();
        model.addAttribute("moviesFind", movieList);
        //model.addAttribute("moviesFind", pageRes.getContent());
        model.addAttribute("currentPage", page);
        //model.addAttribute("totalPages", pageRes.getTotalPages());

        return "movieSearch";
    }

    @PostMapping("/movieSearch")
    @ResponseBody
    public List<MovieDTO> filterMovies(@RequestBody Map<String, String> formData) {
        String filter = formData.get("filter");
        String keyword = formData.get("keyword");
        int page = Integer.parseInt(formData.get("page"));
        int size = Integer.parseInt(formData.get("size"));

        System.out.println("Filtram per: " + filter + ", amb la paraula clau: " + keyword);
        // En el service filtramos por el tipo de keyword y tratamos las datos.
        List<MovieDTO> movieList = movieSearchServices.filterMovies(filter, keyword,page,size);
        for(MovieDTO m: movieList) {
            System.out.println("Title " + m.getTitle()  + " , relaseDate : " + m.getReleaseDate() + " , voteAvarage : " + m.getVoteAverage());
        }
        return movieList;
    }

    /*
    @PostMapping("/adminArea/add/movie")
    public ResponseEntity<Object> adminAddPostMovie(HttpSession session , @RequestBody Map<String,String> data) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);
        String successMessage =movieSearchServices.insertNewMovie(data);
        return ResponseEntity.ok().body(successMessage);
    }

     */



    /*
    @PostMapping("/movieSearch")
    @ResponseBody
    public ResponseEntity<MoviePageDTO> filterMovies(@RequestBody Map<String, String> formData) {
        String filter = formData.get("filter");
        String keyword = formData.get("keyword");
        int page = Integer.parseInt(formData.get("page"));
        int size = Integer.parseInt(formData.get("size"));

        PageRequest pageable = PageRequest.of(page-1,size);
        System.out.println("Filtram per: " + filter + ", amb la paraula clau: " + keyword);
        // En el service filtramos por el tipo de keyword y tratamos las datos.
        Page<Movie> movieList = movieSearchServices.filterMovies(filter, keyword,pageable);
        MoviePageDTO moviePageDTO = new MoviePageDTO(movieList.getContent(),movieList.getTotalPages(),
                movieList.getTotalElements());
        return ResponseEntity.ok(moviePageDTO);
    }


     */



}