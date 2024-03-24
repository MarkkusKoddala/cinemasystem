package com.example.CGI_projekt.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CinemaHallDTO {

    private Integer rows;
    private Integer cols;
    private List<SeatDTO> seats;
}
