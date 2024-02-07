package com.esliceu.movies.Services;

import com.esliceu.movies.Entities.*;
import com.esliceu.movies.Repos.GenderRepo;
import com.esliceu.movies.Repos.GenreRepo;
import com.esliceu.movies.Repos.MovieGenresRepo;
import com.esliceu.movies.Repos.MovieSearchRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MovieGenresServices {
    @Autowired
    MovieGenresRepo movieGenresRepo;
    @Autowired
    MovieSearchRepo movieSearchRepo;
    @Autowired
    GenreRepo genreRepo;
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


    @Transactional
    public String deleteByMovieIdAndGenreId(Map<String, String> data) {
        Long genreId = Long.valueOf(data.get("genreId"));
        Long movieId = Long.valueOf(data.get("movieId"));

        if (movieSearchRepo.existsByMovieId(movieId) && genreRepo.existsByGenreId(genreId)) {
            Movie movie = movieSearchRepo.getReferenceById(movieId);
            Genre genre = genreRepo.getReferenceById(genreId);
            movieGenresRepo.deleteByMovieAndGenre(movie,genre);
            return "MovieGenres delete successfully";
        }
        return "Delete MovieGenres failed";
    }
}
