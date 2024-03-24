package com.example.CGI_projekt.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImdbResponseDTO {
    @JsonProperty("imdbRating")
    private String imdbRating;

    // Getters and Setters
    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }
}

