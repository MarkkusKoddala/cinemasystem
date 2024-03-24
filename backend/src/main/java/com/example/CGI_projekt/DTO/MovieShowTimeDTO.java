package com.example.CGI_projekt.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieShowTimeDTO {
    private Long movieId;
    private String movieTitle;
    private String genre;
    private Date showTime;
    private String language;
    private String imdbRating;
}
