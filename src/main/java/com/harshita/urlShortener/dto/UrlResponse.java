package com.harshita.urlShortener.dto;
// we created response dto so that we only give client, url and short code
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UrlResponse { //return org url and short code

    private String originalUrl;

    private String shortCode;

}