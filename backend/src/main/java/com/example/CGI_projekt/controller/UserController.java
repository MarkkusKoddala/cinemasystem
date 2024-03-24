package com.example.CGI_projekt.controller;

import com.example.CGI_projekt.DTO.MovieShowTimeDTO;
import com.example.CGI_projekt.DTO.UserWithPastShowtimesDTO;
import com.example.CGI_projekt.service.RecommendationService;
import com.example.CGI_projekt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final RecommendationService recommendationService;


    @Autowired
    public UserController(UserService userService, RecommendationService recommendationService) {
        this.userService = userService;
        this.recommendationService = recommendationService;
    }


    @GetMapping("/{id}/visitHistory")
    public ResponseEntity<Boolean> hasVisitHistory(@PathVariable Long id) {
        boolean hasHistory = userService.hasVisitHistory(id);
        return ResponseEntity.ok(hasHistory);
    }

    @GetMapping("/recommendations")
    public List<MovieShowTimeDTO> getRecommendedMoviesShowTime(@RequestParam Long id){
        return recommendationService.getRecommendedMoviesShowTime(id);
    }

    @GetMapping("/users")
    public List<UserWithPastShowtimesDTO> getAllUsers(){
        return userService.getAllUsers();
    }
}
