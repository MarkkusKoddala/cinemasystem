package com.example.CGI_projekt.initialization;

import com.example.CGI_projekt.DTO.ImdbResponseDTO;
import com.example.CGI_projekt.entity.Movie;
import com.example.CGI_projekt.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class MovieIMDBScoreLoader implements CommandLineRunner {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MovieRepository movieRepository;

    @Value("${omdb.api.key}")
    private String omdbApiKey;

    @Override
    public void run(String... args) throws Exception {

         List<Movie> movieList = movieRepository.findAll();


        for (Movie movie : movieList) {
            String apiUrl = String.format("http://www.omdbapi.com/?t=%s&apikey=%s", movie.getTitle().replace(" ", "+"), omdbApiKey);
            ImdbResponseDTO imdbResponseDTO = restTemplate.getForObject(apiUrl, ImdbResponseDTO.class);
            if (imdbResponseDTO != null && imdbResponseDTO.getImdbRating() != null) {
                movie.setImdbRating(imdbResponseDTO.getImdbRating());
                movieRepository.save(movie);
            }
        }
    }
}
