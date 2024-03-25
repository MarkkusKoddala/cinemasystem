package com.example.CGI_projekt.repository;

import com.example.CGI_projekt.entity.Movie;
import com.example.CGI_projekt.entity.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ShowTimeRepository extends JpaRepository<ShowTime, Long> {
    @Query("SELECT s from ShowTime s where s.startTime > CURRENT_TIMESTAMP")
    List<ShowTime> findFutureShows();

    @Query("SELECT s from ShowTime s where s.movie = :movie AND s.startTime > CURRENT_TIMESTAMP ")
    List<ShowTime> findMovieFutureShows(@Param("movie") Movie movie);

}
