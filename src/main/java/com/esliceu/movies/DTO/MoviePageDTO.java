package com.esliceu.movies.DTO;

import com.esliceu.movies.Entities.Movie;

import java.util.List;

public record MoviePageDTO(List<Movie> movies,int totalPages,long totalElements) {
    public MoviePageDTO {
    }
}
