package com.example.CGI_projekt.DTO;


import com.example.CGI_projekt.enums.SeatStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatDTO {
    private String id;
    private Integer row;
    private Integer col;

    private SeatStatus status;

    public SeatDTO(SeatDTO seat) {
    }
}
