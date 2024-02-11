package com.esliceu.movies.Services;

import com.esliceu.movies.DTO.MovieDTO;
import com.esliceu.movies.DTO.MovieDirectorDTO;
import com.esliceu.movies.Entities.*;
import com.esliceu.movies.Repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CrudFilterServices {

    @Autowired
    CountryServices countryServices;
    @Autowired
    LanguageServices languageServices;
    @Autowired
    LanguageRoleServices languageRoleServices;
    @Autowired
    MovieCastServices movieCastServices;

    @Autowired
    MovieCrewServices movieCrewServices;
    @Autowired
    MovieSearchRepo movieSearchRepo;
    @Autowired
    ProductionCompanyRepo productionCompanyRepo;
    @Autowired
    MovieGenresServices movieGenresServices;

    @Autowired
    GenreServices genreServices;
    @Autowired
    PersonRepo personRepo;
    @Autowired
    DepartmentRepo departmentRepo;
    @Autowired
    KeywordRepo keywordRepo;
    @Autowired
    MovieKeywordsServices movieKeywordsServices;

    public List<?> filterByEntity(Map<String, String> data) {
       String filter =data.get("filter");
        String keyword =data.get("keyword");
        int page = Integer.parseInt(data.get("page"));
        int size = Integer.parseInt(data.get("size"));
        System.out.println("Page :" + page + " , size : " + size);
        Pageable pageable = PageRequest.of(page,size);
        switch (filter) {
            case "country":
                return countryServices.filterByCountry(keyword,pageable);
            case "language":
                return languageServices.filterByLanguage(keyword,pageable);
            case "languageRole":
                return languageRoleServices.filterByLanguageRole(keyword,pageable);
            case "genre":
                return genreServices.filterByGenre(keyword,pageable);
            case "actor":
                return movieCastServices.filterByActor(keyword,pageable);
            case "movie":
                List<MovieDTO> movieDTOList = new ArrayList<>();
                List<Movie> movieList=  movieSearchRepo.findByTitleContainingIgnoreCase(keyword,pageable);
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
            case "productionCompany":
                return productionCompanyRepo.findByCompanyNameContainingIgnoreCase(keyword,pageable);
            case "person":
                return personRepo.findByPersonNameContaining(keyword,pageable);
            case "department":
                return departmentRepo.findByDepartmentNameContaining(keyword,pageable);
            case "keyword":
                return keywordRepo.findByKeywordNameContaining(keyword,pageable);
            default:
                break;
        }
        return null;
    }

    public List<?> findMovieDirector(Map<String, String> data) {
        Long movieId = Long.valueOf(data.get("movieId"));
        return movieCrewServices.findAllDirectors(movieId);
    }

    public List<?> findByGenre(Map<String, String> data) {
        Long movieId = Long.valueOf(data.get("movieId"));
        return movieGenresServices.findAllGenreByMovieId(movieId);
    }

    public List<?> findAllGenres(Map<String, String> data) {
        return movieGenresServices.findAllGenres(data);
    }


    public List<?> findByKeyword(Map<String, String> data) {
        return movieKeywordsServices.findAllMovieKeywords(data);
    }
}
