package com.example.CGI_projekt.utils;

import com.example.CGI_projekt.DTO.MovieShowTimeDTO;
import com.example.CGI_projekt.entity.Movie;
import com.example.CGI_projekt.entity.ShowTime;

public class MovieTransformerUtil {


    public static MovieShowTimeDTO createMovieShowTimeDTO(ShowTime futureShow) {
        MovieShowTimeDTO dto = new MovieShowTimeDTO();
        Movie movie = futureShow.getMovie();

        dto.setMovieId(movie.getId());
        dto.setMovieTitle(movie.getTitle());
        dto.setGenre(movie.getGenre());
        dto.setShowTime(futureShow.getStartTime());
        dto.setLanguage(movie.getLanguage());
        dto.setImdbRating(movie.getImdbRating());

        return dto;
    }

}
