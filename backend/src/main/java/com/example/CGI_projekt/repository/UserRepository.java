package com.example.CGI_projekt.repository;

import com.example.CGI_projekt.DTO.UserWithPastShowtimesDTO;
import com.example.CGI_projekt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT COUNT(st) > 0 FROM User u JOIN u.showtimes st WHERE u.id = :userId")
    boolean hasVisitHistory(Long userId);

    @Query("SELECT m.genre FROM User u JOIN u.showtimes st JOIN st.movie m WHERE u.id = :userId and st.startTime < local_datetime")
    List<String> findUserPreviousWatchedMoviesGenres(@Param("userId") Long userId);

    @Query("SELECT new com.example.CGI_projekt.DTO.UserWithPastShowtimesDTO(u.id, u.name, " +
            "(SELECT CASE WHEN COUNT(st) > 0 THEN TRUE ELSE FALSE END " +
            "FROM ShowTime st " +
            "JOIN st.userHistories u2 " +
            "WHERE st.startTime < local_datetime and u2.id = u.id)) " +
            "FROM User u")
    List<UserWithPastShowtimesDTO> getAllUsers();
}
