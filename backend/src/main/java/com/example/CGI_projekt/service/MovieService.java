package com.example.CGI_projekt.service;

import com.example.CGI_projekt.DTO.LookupAttributesDTO;
import com.example.CGI_projekt.DTO.MovieFiltersDTO;
import com.example.CGI_projekt.DTO.MovieShowTimeDTO;
import com.example.CGI_projekt.entity.Movie;
import com.example.CGI_projekt.entity.ShowTime;
import com.example.CGI_projekt.repository.MovieRepository;
import com.example.CGI_projekt.repository.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.CGI_projekt.utils.MovieTransformerUtil.createMovieShowTimeDTO;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ShowTimeRepository showTimeRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, ShowTimeRepository showTimeRepository1) {
        this.movieRepository = movieRepository;
        this.showTimeRepository = showTimeRepository1;
    }

    public List<MovieShowTimeDTO> getAllFutureMoviesShowTime() {
        List <MovieShowTimeDTO> movieShowTimeDTOList = new ArrayList<>();

        List <ShowTime> futureShowTimesList = showTimeRepository.findFutureShows();

        for (ShowTime futureShow : futureShowTimesList) {
            MovieShowTimeDTO dto = createMovieShowTimeDTO(futureShow);
            movieShowTimeDTOList.add(dto);
        }

        return movieShowTimeDTOList;
    }


    public LookupAttributesDTO getAllGenresAndLanguages() {
        List<String> genres = movieRepository.findDistinctGenres();
        List<String> languages = movieRepository.findDistinctLanguages();
        return new LookupAttributesDTO(genres, languages);
    }

    public List<MovieShowTimeDTO> findByFilters(MovieFiltersDTO filters) {
        System.out.println(filters.getLanguage());

        List<MovieShowTimeDTO> response = movieRepository.findByFilters(
                filters.getMovieTitle(),
                filters.getGenre(),
                filters.getStartDate(),
                filters.getLanguage());
        System.out.println(response);

        return response;
    }
}
