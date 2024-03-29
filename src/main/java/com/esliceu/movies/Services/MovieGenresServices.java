package com.esliceu.movies.Services;

import com.esliceu.movies.Entities.*;
import com.esliceu.movies.Repos.GenderRepo;
import com.esliceu.movies.Repos.GenreRepo;
import com.esliceu.movies.Repos.MovieGenresRepo;
import com.esliceu.movies.Repos.MovieSearchRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public List<?> findAllGenres(Map<String, String> data) {
        String keyword = data.get("keyword");
        int page = Integer.parseInt(data.get("genrePage"));
        int size = Integer.parseInt(data.get("genreSize"));
        Pageable pageable = PageRequest.of(page,size);
        return genreRepo.findByGenreNameContainingIgnoreCase(keyword,pageable);
    }

    public String addMovieGenre(Map<String, String> data) {
        Long genderId = Long.valueOf(data.get("genreId"));
        Long movieId = Long.valueOf(data.get("movieId"));

        if (genreRepo.existsByGenreId(genderId) && movieSearchRepo.existsByMovieId(movieId)) {
            Genre genre= genreRepo.getReferenceById(genderId);
            Movie movie = movieSearchRepo.getReferenceById(movieId);
            MovieGenres movieGenres = new MovieGenres(movie,genre);
            if (!movieGenresRepo.existsByGenreAndMovie(genre,movie)) {
                movieGenresRepo.save(movieGenres);
                return "Genre add to Movie successfully";
            } else {
                return "Error: this Genre already exist in this Movie";
            }
        } else {
            return "Failed: values of MovieId or GenderId not exist in Data Bases";
        }


    }
}
