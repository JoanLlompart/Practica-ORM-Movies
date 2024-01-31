package com.esliceu.movies.Services;
import com.esliceu.movies.DTO.ActorDTO;
import com.esliceu.movies.DTO.MovieDTO;
import com.esliceu.movies.DTO.MovieInfoDTO;
import com.esliceu.movies.Entities.Movie;
import com.esliceu.movies.Repos.MovieSearchRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.*;

@Service
public class MovieSearchServices {
    @Autowired
    MovieSearchRepo movieSearchRepo;
    @Autowired
    MovieLanguageServices movieLanguageServices;
    @Autowired
    MovieKeywordsServices movieKeywordsServices;
    @Autowired
    MovieGenresServices movieGenresServices;
    @Autowired
    MovieCrewServices movieCrewServices;
    @Autowired
    MovieCompanyServices movieCompanyServices;
    @Autowired
    MovieCastServices movieCastServices;

    @Autowired
    ProductionCountryServices productionCountryServices;


    public List<Movie> allMovies() {
        return movieSearchRepo.findAll();
    }

    /*public Page<Movie> getPage(Pageable pageable) {
        return movieSearchRepo.findAll(pageable);
    }

     */

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
            Pageable pageable = PageRequest.of(page,size);
            List<MovieDTO> movieDTOList = new ArrayList<>();
            switch (filter) {
                case "actor":
                    System.out.println("actor :" + keyword );
                   // List<Movie> movieList= movieSearchRepo.findMovieByActor(keyword);
                    List<Movie> movieList= movieSearchRepo.findMovieByMoviecast_PersonPersonNameContaining(keyword,pageable);
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
                    movieList = movieSearchRepo.findMovieByMoviecastCharacterNameContaining(keyword,pageable);

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
                    movieList= movieSearchRepo.findMovieByAuthor(keyword,pageable);
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
                    List<Movie> moviesList = movieSearchRepo.findByTitleContainingIgnoreCase(keyword,pageable);
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
                    movieList = movieSearchRepo.findMovieByMovieGenres_GenreGenreNameContaining(keyword,pageable);
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
                    movieList = movieSearchRepo.findDistincMovieByMovieCrewsJobAndMovieCrews_PersonPersonNameContaining("Director",keyword,pageable);
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
        return keyword.matches("[a-zA-Z0-9_çÇñÑ -?!]+");
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
        Long movieId = null;
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


        if (data.get("value13") != null && !data.get("value13").isEmpty()) {
            movieId = Long.valueOf(data.get("value13"));
            if (movieSearchRepo.existsByMovieId(movieId)) {
                //Movie de la BD per compararla.
                Movie movieBD = movieSearchRepo.getReferenceById(movieId);
                // funcio que compari els valors i torni MovieBD
                String title = data.get("value1");
                if (!title.isEmpty()) {
                    if (!movieBD.getTitle().equals(title)) {
                        movieBD.setTitle(title);
                    }
                }
                //budget
                if (!data.get("value2").isEmpty()) {
                    budget = Integer.valueOf(data.get("value2"));
                    //Comproba que ha cambiat
                    if (!movieBD.getBudget().equals(budget)) {
                        //Nou valor asignat a Movie
                        movieBD.setBudget(budget);
                    }
                }
                //homepage
                if (!homepage.isEmpty()) {
                    //Comproba que ha cambiat
                    if (!movieBD.getHomepage().equals(homepage)) {
                        //Nou valor asignat a Movie
                        movieBD.setHomepage(homepage);
                    }
                }
                //Overview
                if (!overview.isEmpty()) {
                    //Comproba que ha cambiat
                    if (!movieBD.getOverview().equals(overview)) {
                        //Nou valor asignat a Movie
                        movieBD.setOverview(overview);
                    }
                }

                //Popularity
                if (!data.get("value5").isEmpty()) {
                    popularity = Double.valueOf(data.get("value5"));
                    //Comproba que ha cambiat
                    if (!movieBD.getPopularity().equals(popularity)) {
                        //Nou valor asignat a Movie
                        movieBD.setPopularity(popularity);
                    }
                }
                //RelaseDate
                if (!data.get("value6").isEmpty()) {
                    releaseDate = Date.valueOf(data.get("value6"));
                    //Comproba que ha cambiat
                    if (!movieBD.getReleaseDate().equals(releaseDate)) {
                        //Nou valor asignat a Movie
                        movieBD.setReleaseDate(releaseDate);
                    }
                }

                //Revenue
                if (!data.get("value7").isEmpty()) {
                    revenue = Long.valueOf(data.get("value7"));
                    //Comproba que ha cambiat
                    if (!movieBD.getRevenue().equals(revenue)) {
                        //Nou valor asignat a Movie
                        movieBD.setRevenue(revenue);
                    }
                }

                //Runtime
                if (!data.get("value8").isEmpty()) {
                    runtime = Integer.valueOf(data.get("value8"));
                    //Comproba que ha cambiat
                    if (!movieBD.getRuntime().equals(runtime)) {
                        //Nou valor asignat a Movie
                        movieBD.setRuntime(runtime);
                    }
                }

                //MovieStatus
                if (!movieStatus.isEmpty()) {
                    //Comproba que ha cambiat
                    if (!movieBD.getMovieStatus().equals(movieStatus)) {
                        //Nou valor asignat a Movie
                        movieBD.setMovieStatus(movieStatus);
                    }
                }

                //Tagline
                if (!tagline.isEmpty()) {
                    //Comproba que ha cambiat
                    if (!movieBD.getTagline().equals(tagline)) {
                        //Nou valor asignat a Movie
                        movieBD.setTagline(tagline);
                    }
                }


                //VoteAvarage
                if (!data.get("value11").isEmpty()) {
                    voteAverage = Double.valueOf(data.get("value11"));
                    //Comproba que ha cambiat
                    if (!movieBD.getVoteAverage().equals(voteAverage)) {
                        //Nou valor asignat a Movie
                        movieBD.setVoteAverage(voteAverage);
                    }
                }

                //VoteCount
                if (!data.get("value12").isEmpty()) {
                    voteCount = Integer.valueOf(data.get("value12"));
                    //Comproba que ha cambiat
                    if (!movieBD.getVoteCount().equals(voteCount)) {
                        //Nou valor asignat a Movie
                        movieBD.setVoteCount(voteCount);
                    }
                }

                movieSearchRepo.save(movieBD);
                return "Movie by id:" + movieId + " Update successfully";
            } else {
                return "The Movie you want to update does not match any id in the database";
            }
        } else {
            return "To Update a Movie need provided a Movie Id";
        }
    }


    public String deleteMovie(Map<String, String> data) {
        Long movieId = Long.valueOf(data.get("value1"));
        if (movieSearchRepo.existsByMovieId(movieId)) {
            movieLanguageServices.deleteByMovieId(movieId);
            movieKeywordsServices.deleteByMovieId(movieId);
            movieGenresServices.deleteByMovieId(movieId);
            movieCrewServices.deleteByMovieId(movieId);
            movieCompanyServices.deleteByMovieId(movieId);
            movieCastServices.deleteByMovieId(movieId);
            productionCountryServices.deleteByMovieId(movieId);
            movieSearchRepo.deleteById(movieId);
            return "Movie by id ," + movieId + " ,Delete successfully";
        } else {
            return "Movie Delete Error";
        }
    }

    public Optional<MovieInfoDTO> getAllMovieInfo(Map<String, String> formData) {
        Long movieId = Long.valueOf(formData.get("movieId"));
       Optional<Movie> m=movieSearchRepo.findById(movieId);
       //Rellenam el DTO amb les dades de Movie
      /* MovieInfoDTO movieInfoDTO = new MovieInfoDTO(m.getMovieId(), m.get().getTitle(),
               m.get().getBudget(),m.get().getHomepage(),m.get().getOverview(),m.get().getPopularity(),
               m.get().getReleaseDate(),m.get().getRevenue(),m.get().getRuntime(),m.get().getMovieStatus(),
               m.get().getTagline(),m.get().getVoteAverage(), m.get().getVoteCount());

       */
        String director =movieCrewServices.findDirectorByMovieId(movieId);
        MovieInfoDTO movieInfoDTO = MovieInfoDTO.fromMovie(m.get(),director);
        System.out.println("El director de la pelicula es : " + director);
         return Optional.of(movieInfoDTO);
    }

    public Optional<List<ActorDTO>> getAllActors(Map<String, String> formData) {
        Long movieId = Long.valueOf(formData.get("movieId"));
        System.out.println("id de movie" +movieId);
        String actor = formData.get("filterType");
        List<ActorDTO> movieActors =movieCastServices.getActorsByMovieId(movieId);
        return Optional.of(movieActors);
    }
}



