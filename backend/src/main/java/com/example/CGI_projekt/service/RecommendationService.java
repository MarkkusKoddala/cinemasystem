package com.example.CGI_projekt.service;


import com.example.CGI_projekt.DTO.MovieShowTimeDTO;
import com.example.CGI_projekt.entity.Movie;
import com.example.CGI_projekt.entity.ShowTime;
import com.example.CGI_projekt.repository.MovieRepository;
import com.example.CGI_projekt.repository.ShowTimeRepository;
import com.example.CGI_projekt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import java.util.*;

import static com.example.CGI_projekt.utils.MovieTransformerUtil.createMovieShowTimeDTO;


//Siin service klassis implementeerin ApplicationListener interface. Põhjus oli selleks lihtne, eesmärk on iga kord, kui server käivitatakse, juba eeslpositsioneerida võimalikud unikaalse žanrid.
@Service
public class RecommendationService implements ApplicationListener<ContextRefreshedEvent> {
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    private final ShowTimeRepository showTimeRepository;

    //HashMapi kasutan, sest võimaldab teha put ja get operatsiooni O(1) keerukusega
    private final Map<String, Integer> uniqueGenresHashMap = new HashMap<>();

    @Autowired
    public RecommendationService(MovieRepository movieRepository, UserRepository userRepository, ShowTimeRepository showTimeRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.showTimeRepository = showTimeRepository;
    }


    //Funktsioon, mis kutsub peale serveri käivitamist koheselt välja initUniqueGenres.
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initUniqueGenres();
    }

    private void initUniqueGenres() {
        List<String> uniqueGenres = movieRepository.findUniqueGenres();
        int index = 0;
        for (String genre : uniqueGenres) {

            //index väärtu on siinkohal oluline, et tagada igas one-hot vektoris žanri õige asukoht
            uniqueGenresHashMap.put(genre, index++);
        }
    }

    //Funktsioon, mis tagastab soovitatud järjekorras filmide ajad.
    public List<MovieShowTimeDTO> getRecommendedMoviesShowTime(Long id) {
        Map<Movie, Double> recommendations;


        List<String> previousGenres = userRepository.findUserPreviousWatchedMoviesGenres(id);

        List<Movie> futureMovies = movieRepository.getAllFutureMovies();


        //Loome kasutaja varasemaid filmivaatamisi iseloomustava bitivektori / one-hot vektori, kus massivi iga liige tähistab mõnda žanri ja väärtus justkui osakaalu, kui palju on varasemalt seda žanri vaadatud
        double[] userVector = generateUserVector(previousGenres);


        //genereerime soovitused Mapi, kus igale filmile on lisatud iseloomustav skoor
        recommendations = generateRecommendations(userVector, futureMovies);



        //Sorteerib siis filmid kahel tingimusel. Seda funktsiooni luues kasutasin ChatGPT abi, kes pakkus lahenduseks sellise variandi.
        List<Movie> sortedMovies = recommendations.entrySet().stream()
                .sorted(Map.Entry.<Movie, Double>comparingByValue(Comparator.reverseOrder()) // Sorteerime skoori alusel kahanevalt
                        .thenComparing(e -> e.getKey().getImdbRating(), Comparator.reverseOrder())) // Seejärel sorteerime IMDB reitingu alusel kahanevalt
                .map(Map.Entry::getKey)
                .toList();


        List <MovieShowTimeDTO> reply = transformToDTOs(sortedMovies);

        return reply;
    }

    private List<MovieShowTimeDTO> transformToDTOs(List<Movie> sortedMovies) {
        List<MovieShowTimeDTO> returnList = new ArrayList<>();

        for (Movie sortedMovie : sortedMovies) {
            List <ShowTime> futureShows = showTimeRepository.findMovieFutureShows(sortedMovie);

            for (ShowTime futureShow : futureShows) {

                //abimeetod, mis teisendab kinoplaani ja filmi ühiseks DTO objektiks, mida kuvada Front Endis
                MovieShowTimeDTO dto = createMovieShowTimeDTO(futureShow);
                returnList.add(dto);
            }
        }

        return returnList;
    }


    //funktsioon, mis genereerib igale filmile skoori, kui sobilik või sarnane see on kasutaja varasemate vaatamistega
    private Map<Movie, Double> generateRecommendations(double[] userVector, List<Movie> futureMovies) {
        Map<Movie, Double> recommendations = new LinkedHashMap<>();

        for (Movie futureMovie : futureMovies) {

            // Genereerime filmivektori
            double [] movieVector = genreToVector(futureMovie.getGenre());

            // Abifunktsioon, mis arvutab kahe vektori sarnasuse
            double similarityScore = cosineSimilarity(userVector, movieVector);
            recommendations.put(futureMovie, similarityScore);
        }

        return recommendations;
    }


    //meetod arvutab inglise keeles "cosine similarity", mis annab tulemuseks väärtuse, mis iseloomustab kahe vektori sarnasust või mittesarnasust.
    // Sarnasust on just praegusel juhul vaja, et võrrelda žanreid ning inimese eelistusi.
    private double cosineSimilarity(double[] userVector, double[] movieVector) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < userVector.length; i++) {
            dotProduct += userVector[i] * movieVector[i];
            normA += Math.pow(userVector[i], 2);
            normB += Math.pow(movieVector[i], 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }


    //Metod genereerib kasutajale teda iseloomustava one-hot vektori.
    private double[] generateUserVector(List<String> previousGenres) {

        double [] userVector = new double[uniqueGenresHashMap.size()];

        int genresTotal = previousGenres.size();

        for (String previousGenre : previousGenres) {
            // Lisan 1/genresTotal, et kohe ka vektor ära normaliseerida, mis on hiljem oluline teiste vektoritega võrreldes.
            userVector[uniqueGenresHashMap.get(previousGenre)] += (double) 1 /genresTotal;
        }

        return userVector;
    }

    //Meetod, mis muudab iga žanri one-hot vektoriks.
    private double[] genreToVector(String genre) {
        double[] vector = new double[uniqueGenresHashMap.size()];
        vector[uniqueGenresHashMap.get(genre)] = 1.0;
        return vector;
    }
}