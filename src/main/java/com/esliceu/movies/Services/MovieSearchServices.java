package com.esliceu.movies.Services;

import com.esliceu.movies.DTO.MovieDTO;
import com.esliceu.movies.Entities.Movie;
import com.esliceu.movies.Repos.MovieSearchRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
                   // List<Movie> movieList= movieSearchRepo.findMovieByActor(keyword);
                    List<Movie> movieList= movieSearchRepo.findMovieByMoviecast_PersonPersonNameContaining(keyword);
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
                    //movieList = movieSearchRepo.findMovieByCharacter(keyword);
                    movieList = movieSearchRepo.findMovieByMoviecastCharacterNameContaining(keyword);

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
                    //movieList = movieSearchRepo.findByMovieCrewJobAndMovieCrewPersonPersonNameContainingIgnoreCase("Author",keyword);
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
                    //List<Movie> moviesList = movieSearchRepo.findMoviesByTitle(keyword);
                    List<Movie> moviesList = movieSearchRepo.findByTitleContainingIgnoreCase(keyword);
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
                   // movieList = movieSearchRepo.findMovieByGenre(keyword);
                    movieList = movieSearchRepo.findMovieByMovieGenres_GenreGenreNameContaining(keyword);
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
                   // movieList = movieSearchRepo.findMovieByDirector(keyword);
                    movieList = movieSearchRepo.findDistincMovieByMovieCrewsJobAndMovieCrews_PersonPersonNameContaining("Director",keyword);
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

    public String insertNewMovie(Map<String, String> data) {
        String title = data.get("value1");
        Integer budget = Integer.valueOf(data.get("value2"));
        String homepage = data.get("value3");
        String overview = data.get("value4");
        Double popularity = Double.valueOf(data.get("value5"));
        Date relaseDate = Date.valueOf(data.get("value6"));
        Long revenue = Long.valueOf(data.get("value7"));
        Integer runtime = Integer.valueOf(data.get("value8"));
        String movieStatus = data.get("value9");
        String tagline = data.get("value10");
        Double voteAvarage = Double.valueOf(data.get("value11"));
        Integer voteCount = Integer.valueOf(data.get("value12"));

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setBudget(budget);
        movie.setHomepage(homepage);
        movie.setOverview(overview);
        movie.setPopularity(popularity);
        movie.setReleaseDate(relaseDate);
        movie.setRevenue(revenue);
        movie.setRuntime(runtime);
        movie.setMovieStatus(movieStatus);
        movie.setTagline(tagline);
        movie.setVoteAverage(voteAvarage);
        movie.setVoteCount(voteCount);
        movieSearchRepo.save(movie);
        return "Movie Add successfully";
    }

    @Transactional
    public String updateMovie(Map<String, String> data) {
        /*
        String title = data.get("value1");
        Integer budget = Integer.valueOf(data.get("value2"));
        String homepage = data.get("value3");
        String overview = data.get("value4");
        Double popularity = Double.valueOf(data.get("value5"));
        Date relaseDate = Date.valueOf(data.get("value6"));
        Long revenue = Long.valueOf(data.get("value7"));
        Integer runtime = Integer.valueOf(data.get("value8"));
        String movieStatus = data.get("value9");
        String tagline = data.get("value10");
        Double voteAvarage = Double.valueOf(data.get("value11"));
        Integer voteCount = Integer.valueOf(data.get("value12"));
        Long movieId = Long.valueOf(data.get("value13"));
         */
        String title = data.get("value1");
        Integer budget = null;
        String homepage = data.get("value3");
        String overview = data.get("value4");
        Double popularity = null;
        Date releaseDate = null;
        Long revenue = null;
        Integer runtime = null;
        String movieStatus = data.get("value9");
        String tagline = data.get("value10");
        Double voteAverage = null;
        Integer voteCount = null;
        Long movieId = null;
        //Cream el objecte Movie per anar afegit els valors
        Movie movie = new Movie();

        if (data.get("value2") != null && !data.get("value2").isEmpty()) {
            budget = Integer.valueOf(data.get("value2"));
            movie.setBudget(budget);
        }

        if (data.get("value5") != null && !data.get("value5").isEmpty()) {
            popularity = Double.valueOf(data.get("value5"));
            movie.setPopularity(popularity);
        }

        if (data.get("value6") != null && !data.get("value6").isEmpty()) {
            releaseDate = Date.valueOf(data.get("value6"));
            movie.setReleaseDate(releaseDate);
        }

        if (data.get("value7") != null && !data.get("value7").isEmpty()) {
            revenue = Long.valueOf(data.get("value7"));
            movie.setRevenue(revenue);
        }

        if (data.get("value8") != null && !data.get("value8").isEmpty()) {
            runtime = Integer.valueOf(data.get("value8"));
            movie.setRuntime(runtime);
        }

        if (data.get("value11") != null && !data.get("value11").isEmpty()) {
            voteAverage = Double.valueOf(data.get("value11"));
            movie.setVoteAverage(voteAverage);
        }

        if (data.get("value12") != null && !data.get("value12").isEmpty()) {
            voteCount = Integer.valueOf(data.get("value12"));
            movie.setVoteCount(voteCount);
        }

        if (data.get("value13") != null && !data.get("value13").isEmpty()) {
            movieId = Long.valueOf(data.get("value13"));
            movie.setMovieId(movieId);
        }

        if (movieSearchRepo.existsByMovieId(movieId)) {
           movie.setTitle(title);
           movie.setTagline(tagline);
           movie.setHomepage(homepage);
           movie.setOverview(overview);
           movie.setMovieStatus(movieStatus);
           movieSearchRepo.save(movie);
            return "Movie by id:" + movieId + " Update successfully";
        }else {
            return "Movie by id:" + movieId + " Delete error";
        }
    }

    public String deleteMovie(Map<String, String> data) {
        Long movieId = Long.valueOf(data.get("value1"));
        if (movieSearchRepo.existsByMovieId(movieId)) {;
            movieSearchRepo.deleteById(movieId);
            return "Movie by id ," + movieId + " ,Delete successfully";
        } else {
            return "Movie Delete Error";
        }
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