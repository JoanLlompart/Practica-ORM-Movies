package com.esliceu.movies.Services;

import com.esliceu.movies.Entities.Movie;
import com.esliceu.movies.Repos.MovieSearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MovieSearchServices {
    @Autowired
    MovieSearchRepo movieSearchRepo;


    public List<Movie> allMovies() {
        return movieSearchRepo.findAll();
    }

    public Page<Movie> getPage(Pageable pageable) {
        return movieSearchRepo.findAll(pageable);
    }

    public List<Movie> filterMovies(String filter, String keyword) {
        List<String> validFilters = Arrays.asList("title", "actor", "character", "genre", "director");

        if (validFilters.contains(filter)) {
            System.out.println("Es un filtre valid");
            switch (filter) {
                case "actor":
                    System.out.println("actor :" + keyword );
                    return movieSearchRepo.findMovieByActor(keyword);
                case "character":
                    break;
                case "title":
                    System.out.println();
                    break;
                case "genre":
                    break;
                case "director":
                    break;
                default:
                    System.err.println("Tipus de filtre no trobat");
                    break;
            }
        } else {
            System.out.println("Filtre no valid");
        }
        return null;
    }


}
