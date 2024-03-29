package com.esliceu.movies.Services;
import com.esliceu.movies.Entities.MovieLanguages;
import com.esliceu.movies.Repos.MovieLanguagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieLanguageServices {
    @Autowired
    MovieLanguagesRepo movieLanguagesRepo;
    public void deleteByMovieId(Long movieId) {
        List<MovieLanguages> allMovieLanguages = movieLanguagesRepo.findAllByMovie_MovieId(movieId);
        movieLanguagesRepo.deleteAll(allMovieLanguages);
    }

    public void deleteByLanguageId(Long languageId) {
        List<MovieLanguages> allLanguageMovie = movieLanguagesRepo.findAllByLanguage_LanguageId(languageId);
        for (MovieLanguages mL:allLanguageMovie) {
            System.out.println(mL.toString());
        }
        movieLanguagesRepo.deleteAll(allLanguageMovie);
    }
}
