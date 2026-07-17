package com.harshita.urlShortener.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UrlResponse {

    private String originalUrl;

    private String shortCode;

}
