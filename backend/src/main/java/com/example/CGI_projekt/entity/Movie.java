package com.example.CGI_projekt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Set;

@Entity
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String title;

    private String imdbRating;

    private String genre;

    private String language;

    @OneToMany(mappedBy = "movie")
    private Set<ShowTime> shows;
}



