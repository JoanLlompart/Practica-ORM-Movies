package com.esliceu.movies.Services;

import com.esliceu.movies.Entities.Genre;
import com.esliceu.movies.Repos.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServices {
    @Autowired
    GenreRepo genreRepo;
    public List<Genre> filterByGenre(String keyword, Pageable pageable) {
        return genreRepo.findByGenreNameContainingIgnoreCase(keyword,pageable);
    }
}
