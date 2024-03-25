package com.example.CGI_projekt.controller;

import com.example.CGI_projekt.DTO.CinemaHallDTO;
import com.example.CGI_projekt.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema")
public class CinemaController {


    private final CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService){
        this.cinemaService = cinemaService;
    }

    @GetMapping("/cinemaplan")
    public CinemaHallDTO getCinamonHall(@RequestParam int quantity){
        return cinemaService.getCinamonHallInfo(quantity);
    }
}
