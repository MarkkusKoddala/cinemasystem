package com.example.CGI_projekt.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithPastShowtimesDTO {
    private Long id;
    private String name;
    private Boolean hasShowTimesBeforeNow;

}
