package com.esliceu.movies.Services;

import com.esliceu.movies.Entities.Genre;
import com.esliceu.movies.Entities.MovieGenres;
import com.esliceu.movies.Entities.MovieLanguages;
import com.esliceu.movies.Repos.MovieGenresRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MovieGenresServices {
@Autowired
    MovieGenresRepo movieGenresRepo;
    public void deleteByMovieId(Long movieId) {
        List<MovieGenres> allMovieGenres = movieGenresRepo.findAllByMovie_MovieId(movieId);
        movieGenresRepo.deleteAll(allMovieGenres);
    }

    public List<Genre> findAllGenreByMovieId(Long movieId) {
        List<MovieGenres> movieGenres =movieGenresRepo.findAllGenreByMovie_MovieId(movieId);
        List<Genre> genresInMovie = new ArrayList<>();
        for (MovieGenres mg : movieGenres) {
            Long genreId = mg.getGenre().getGenreId();
            String genreName = mg.getGenre().getGenreName();
            genresInMovie.add(new Genre(genreId,genreName));
        }
        return genresInMovie;
    }


    public String deleteByMovieIdAndGenreId(Map<String, String> data) {
        Long genreId = Long.valueOf(data.get("genreId"));
        return "FALTA IMPLEMENTAR NO HA ARRIBAT MovieId";
    }
}
