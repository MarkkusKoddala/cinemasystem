package com.example.CGI_projekt.controller;

import com.example.CGI_projekt.DTO.LookupAttributesDTO;
import com.example.CGI_projekt.DTO.MovieFiltersDTO;
import com.example.CGI_projekt.DTO.MovieShowTimeDTO;
import com.example.CGI_projekt.service.MovieService;
import com.example.CGI_projekt.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/movies") // Base path for all endpoints in this controller
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/future")
    public List<MovieShowTimeDTO> getAllFutureMovies() {
        return movieService.getAllFutureMoviesShowTime();
    }

    @GetMapping("/lookups")
    public LookupAttributesDTO getAllGenresAndLanguages() {
        return movieService.getAllGenresAndLanguages();
    }

    @GetMapping("/filter")
    public List<MovieShowTimeDTO> getFilteredSchedules(@ModelAttribute MovieFiltersDTO moviefilters) {
        return movieService.findByFilters(moviefilters);
    }

}
