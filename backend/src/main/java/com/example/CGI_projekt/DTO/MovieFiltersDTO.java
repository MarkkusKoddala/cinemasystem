package com.example.CGI_projekt.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieFiltersDTO {
    private String movieTitle;
    private String genre;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    private String language;
}
