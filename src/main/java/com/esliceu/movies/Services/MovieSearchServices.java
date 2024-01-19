package com.esliceu.movies.Services;

import com.esliceu.movies.DTO.MovieDTO;
import com.esliceu.movies.Entities.Movie;
import com.esliceu.movies.Repos.MovieSearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MovieSearchServices {
    @Autowired
    MovieSearchRepo movieSearchRepo;


    public List<Movie> allMovies() {
        return movieSearchRepo.findAll();
    }

    public Page<MovieDTO> filterMovies(String filter, String keyword, Pageable pageable) {
        List<String> validFilters = Arrays.asList("title", "actor", "character", "genre", "director");

        if (validFilters.contains(filter)) {
            switch (filter) {
                case "actor":
                    List<Movie> movieList = movieSearchRepo.findMovieByActor(keyword);
                    List<MovieDTO> movieDTOList = new ArrayList<>();

                    for (Movie m : movieList) {
                        movieDTOList.add(new MovieDTO(m.getMovieId(), m.getTitle(), m.getReleaseDate(), m.getVoteAverage()));
                    }

                    return new PageImpl<>(movieDTOList, pageable, movieList.size());

                case "character":
                    // Implementa el caso "character" si es necesario
                    break;

                case "title":
                    Page<Movie> moviesPage = findMoviesByTitle(keyword, pageable.getPageNumber(), pageable.getPageSize());
                    List<MovieDTO> movieTitleDTOList = new ArrayList<>();

                    for (Movie m : moviesPage.getContent()) {
                        movieTitleDTOList.add(new MovieDTO(m.getMovieId(), m.getTitle(), m.getReleaseDate(), m.getVoteAverage()));
                    }

                    return new PageImpl<>(movieTitleDTOList, pageable, moviesPage.getTotalElements());

                case "genre":
                    // Implementa el caso "genre" si es necesario
                    break;

                case "director":
                    // Implementa el caso "director" si es necesario
                    break;

                default:
                    System.err.println("Tipo de filtro no encontrado");
                    break;
            }
        } else {
            System.out.println("Filtro no válido");
        }

        return Page.empty();
    }



    /*
    Funciona be
    public Page<Movie> getPage(Pageable pageable) {
        return movieSearchRepo.findAll(pageable);
    }

     */
/*
    public Page<MovieDTO> filterMovies(String filter, String keyword,Pageable pageable) {
        List<String> validFilters = Arrays.asList("title", "actor", "character", "genre", "director");

        if (validFilters.contains(filter)) {
            System.out.println("Es un filtre valid");
            switch (filter) {
                case "actor":
                    System.out.println("actor :" + keyword );

                   List<Movie> movieList= movieSearchRepo.findMovieByActor(keyword);
                   List<MovieDTO> movieDTOList = new ArrayList<>();
                   for (Movie m:movieList) {
                       MovieDTO dto = new MovieDTO();
                       dto.setMovieId(m.getMovieId());
                       dto.setTitle(m.getTitle());
                       dto.setReleaseDate(m.getReleaseDate());
                       dto.setVoteAverage(m.getVoteAverage());
                       //Una vegada el DTO te tots els atributs afegim a la llista
                       movieDTOList.add(dto);
                   }
                   return movieDTOList;
                case "character":

                    break;
                case "title":
                    Page<Movie> moviesList = findMoviesByTitle(keyword, page,size);
                    List<MovieDTO> movietitleDTO = new ArrayList<>();
                    for (Movie m:moviesList) {
                        MovieDTO dto = new MovieDTO();
                        dto.setMovieId(m.getMovieId());
                        dto.setTitle(m.getTitle());
                        dto.setReleaseDate(m.getReleaseDate());
                        dto.setVoteAverage(m.getVoteAverage());
                        movietitleDTO.add(dto);
                    }
                    return movietitleDTO;
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

 */
    public Page<Movie> findMoviesByTitle(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return movieSearchRepo.findMoviesByTitle("%" + keyword + "%", pageable);
    }
/*
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

 */
}




//anterior
/*

    public List<MovieDTO> filterMovies(String filter, String keyword,int page, int size) {
        List<String> validFilters = Arrays.asList("title", "actor", "character", "genre", "director");

        if (validFilters.contains(filter)) {
            System.out.println("Es un filtre valid");
            switch (filter) {
                case "actor":
                    System.out.println("actor :" + keyword );

                   List<Movie> movieList= movieSearchRepo.findMovieByActor(keyword);
                   List<MovieDTO> movieDTOList = new ArrayList<>();
                   for (Movie m:movieList) {
                       MovieDTO dto = new MovieDTO();
                       dto.setMovieId(m.getMovieId());
                       dto.setTitle(m.getTitle());
                       dto.setReleaseDate(m.getReleaseDate());
                       dto.setVoteAverage(m.getVoteAverage());
                       //Una vegada el DTO te tots els atributs afegim a la llista
                       movieDTOList.add(dto);
                   }
                   return movieDTOList;
                case "character":

                    break;
                case "title":
                    Page<Movie> moviesList = findMoviesByTitle(keyword, page,size);
                    for (Movie m:moviesList) {
                        MovieDTO dto = new MovieDTO();

                    }
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
 */