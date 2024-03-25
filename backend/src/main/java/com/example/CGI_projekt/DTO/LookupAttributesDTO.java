package com.example.CGI_projekt.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LookupAttributesDTO {
    private List<String> genres;
    private List<String> languages;
}
