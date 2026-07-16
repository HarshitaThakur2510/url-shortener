package com.harshita.urlShortener.dto;
//dto is data transfer obj which transfers data between client and the server
import jakarta.validation.constraints.NotBlank;// to reject request if user sends blank url
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlRequest {

    @NotBlank(message = "URL cannot be empty")
    private String originalUrl;

}