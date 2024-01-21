package com.esliceu.movies.Services;

import com.esliceu.movies.DTO.MovieDTO;
import com.esliceu.movies.Entities.Movie;
import com.esliceu.movies.Repos.MovieSearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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


    /*
    public Page<Movie> filterMovies(String filter, String keyword, PageRequest pageRequest) {
        List<String> validFilters = Arrays.asList("title", "actor", "character", "genre", "director","author");
        if (validFilters.contains(filter)) {
            System.out.println("Es un filtre valid");
            switch (filter) {
                case "actor":
                    System.out.println("actor :" + keyword );
                    Page<Movie> movieList = movieSearchRepo.findMovieByActor(keyword,pageRequest);
                for (Movie mo:movieList) {
                    System.out.println(mo.getTitle());
                }
                return movieList;
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


    public Page<Movie> getPage(Pageable pageable) {
        return movieSearchRepo.findAll(pageable);
    }




    public List<MovieDTO> filterMovies(String filter, String keyword,int page, int size) {
        if(isValidInput(keyword)) {
            System.out.println("Input valid");
        } else {
            System.out.println("Input invalid");
            return null;
        }

        List<String> validFilters = Arrays.asList("title", "actor", "character", "genre", "director","author");
        if (validFilters.contains(filter)) {
            System.out.println("Es un filtre valid");
            List<MovieDTO> movieDTOList = new ArrayList<>();
            switch (filter) {
                case "actor":
                    System.out.println("actor :" + keyword );
                    List<Movie> movieList= movieSearchRepo.findMovieByActor(keyword);
                    for (Movie mo:movieList) {
                        System.out.println(mo.getTitle());
                    }
                    //List<MovieDTO> movieDTOList = new ArrayList<>();

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
                    System.out.println("Character of movie : " + keyword);
                    movieList = movieSearchRepo.findMovieByCharacter(keyword);
                    for (Movie mo:movieList) {
                        System.out.println(mo.getTitle());
                    }
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
                case "author":
                    System.out.println("author :" + keyword );
                    movieList= movieSearchRepo.findMovieByAuthor(keyword);
                    for (Movie mo:movieList) {
                        System.out.println(mo.getTitle());
                    }
                    //List<MovieDTO> movieDTOList = new ArrayList<>();
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
                case "title":
                    List<Movie> moviesList = movieSearchRepo.findMoviesByTitle(keyword);
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
                    System.out.println("Genre of movie : " + keyword);
                    movieList = movieSearchRepo.findMovieByGenre(keyword);
                    for (Movie mo:movieList) {
                        System.out.println(mo.getTitle());
                    }
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
                case "director":
                    System.out.println("Director of movie : " + keyword);
                    movieList = movieSearchRepo.findMovieByDirector(keyword);
                    for (Movie mo:movieList) {
                        System.out.println(mo.getTitle());
                    }
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
                default:
                    System.err.println("Tipus de filtre no trobat");
                    break;
            }
        } else {
            System.out.println("Filtre no valid");
        }
        return null;
    }


    //TODO: AMB PROBES DE SI POT CONTROLAR ELS FALSOS IMPUTS
    private boolean isValidInput(String keyword) {
        //Si el codi es una cadena buida o te nomes espais
        keyword =keyword.trim();
        //Permet numeros i lletras i espais enmitg ja que els altres se han eliminat
        return keyword.matches("[a-zA-Z0-9 ]+");
    }


    //TODO: PER PAGINAR
  /*  public Page<Movie> findMoviesByTitle(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return movieSearchRepo.findMoviesByTitle("%" + keyword + "%", pageable);
    }

   */

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