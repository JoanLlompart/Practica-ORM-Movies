package com.esliceu.movies.Services;

import com.esliceu.movies.DTO.MovieKeywordDTO;
import com.esliceu.movies.Entities.Movie;
import com.esliceu.movies.Entities.MovieKeywords;
import com.esliceu.movies.Repos.MovieKeywordsRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MovieKeywordsServices {
    @Autowired
    MovieKeywordsRepo movieKeywordsRepo;

    public void deleteByMovieId(Long movieId) {
        List<MovieKeywords> allMovieKeywords = movieKeywordsRepo.findAllByMovie_MovieId(movieId);
        movieKeywordsRepo.deleteAll(allMovieKeywords);
    }

    public List<?> findAllMovieKeywords(Map<String, String> data) {
        Long movieId = Long.valueOf(data.get("movieId"));
        List<MovieKeywords> movieKeywordsList = movieKeywordsRepo.findAllByMovie_MovieId(movieId);
        List<MovieKeywordDTO> movieKeywordDTOS = new ArrayList<>();
        //Pasam a el Record de MovieKeywordDTO
        for (MovieKeywords movieKeyword : movieKeywordsList) {
            Long idMovie = movieKeyword.getMovie().getMovieId();
            Long keywordId = movieKeyword.getKeyword().getKeywordId();
            String keywordName = movieKeyword.getKeyword().getKeywordName();
            //Cream el objetcet record de el dto el ficam a la llista.
            movieKeywordDTOS.add(new MovieKeywordDTO(idMovie,keywordId,keywordName));
        }
        return movieKeywordDTOS;
    }

    @Transactional
    public String deleteByMovieIdAndKeywordId(Map<String, String> data) {
        Long movieId = Long.valueOf(data.get("movieId"));
        Long keywordId = Long.valueOf(data.get("keywordId"));
        String keywordName = data.get("keywordName");
        if (movieKeywordsRepo.existsByKeyword_KeywordIdAndMovie_MovieId(keywordId,movieId)) {
            movieKeywordsRepo.deleteAllByMovie_MovieIdAndKeyword_KeywordId(movieId,keywordId);
            return "MovieKeywords delete with keyword " + keywordName +" successfully";
        }
        return "Error: MovieKeywords by not exist";
    }
}
