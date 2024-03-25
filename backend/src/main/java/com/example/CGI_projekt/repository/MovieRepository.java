package com.example.CGI_projekt.repository;

import com.example.CGI_projekt.DTO.MovieShowTimeDTO;
import com.example.CGI_projekt.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT DISTINCT m.genre from Movie m")
    List<String> findUniqueGenres();
    @Query("SELECT m FROM ShowTime st JOIN st.movie m where st.startTime > current_timestamp")
    List<Movie> getAllFutureMovies();
    @Query("SELECT DISTINCT m.genre FROM Movie m")
    List<String> findDistinctGenres();
    @Query("SELECT DISTINCT m.language FROM Movie m")
    List<String> findDistinctLanguages();

    @Query("SELECT DISTINCT new com.example.CGI_projekt.DTO.MovieShowTimeDTO(m.id, m.title, m.genre, sh.startTime, m.language, m.imdbRating) " +
            "FROM Movie m JOIN m.shows sh WHERE " +
            "(LOWER(m.title) LIKE LOWER(CONCAT(:title, '%')) OR :title = '') AND " +
            "(m.genre = :genre OR :genre = '') AND " +
            "(sh.startTime >= :startDate OR :startDate IS NULL) AND " +
            "(m.language = :language OR :language = '')")
    List<MovieShowTimeDTO> findByFilters(@Param("title") String title,
                              @Param("genre") String genre,
                              @Param("startDate") Date startDate,
                              @Param("language") String language);

}
